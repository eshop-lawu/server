package com.lawu.eshop.member.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.event.EventPublisher;
import com.lawu.eshop.member.api.service.*;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.dto.ShoppingOrderMoneyDTO;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.constants.ThirdPartyBizFlagEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.dto.AlipayOauthDTO;
import com.lawu.eshop.property.dto.OauthTokenRtnDTO;
import com.lawu.eshop.property.param.AlipayOauthDataParam;
import com.lawu.eshop.property.param.AlipayOauthParam;
import com.lawu.eshop.property.param.ThirdPayDataParam;
import com.lawu.eshop.property.param.ThirdPayParam;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.AliUserInfoDataParam;
import com.lawu.eshop.user.param.AliUserInfoParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Description: 支付宝
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月7日 上午9:12:31
 */
@Api(tags = "alipay")
@RestController
@RequestMapping(value = "alipay/")
public class AlipayController extends BaseController {

    @Autowired
    private AlipayService alipayService;
    @Autowired
    private ShoppingOrderService shoppingOrderService;
    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private PayOrderService payOrderService;
    @Autowired
    private MerchantStoreService merchantStoreService;
    @Autowired
    private UserRedPacketService userRedPacketService;
    @Autowired
    private EventPublisher eventPublisher;
    @Autowired
    private RichAccountService richAccountService;

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "app调用支付宝获取请求参数，已签名加密", notes = "app调用支付宝时需要的请求参数，[4020|4021]，(杨清华)", httpMethod = "POST")
    @Authorization
    @RequestMapping(value = "getAppAlipayReqParams", method = RequestMethod.POST)
    public Result getAppAlipayReqParams(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                        @ModelAttribute @ApiParam ThirdPayParam param) {

        ThirdPayDataParam aparam = new ThirdPayDataParam();
        aparam.setBizIds(param.getBizIds());
        aparam.setThirdPayBodyEnum(param.getThirdPayBodyEnum());
        aparam.setBizFlagEnum(param.getBizFlagEnum());
        aparam.setSubject(param.getThirdPayBodyEnum().getVal());
        aparam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        aparam.setUserTypeEnum(UserTypeEnum.MEMBER);

        // 查询支付金额
        double money = 0;
        String rtnMoney = "";
        if (ThirdPartyBizFlagEnum.MEMBER_PAY_BILL.getVal().equals(param.getBizFlagEnum().getVal())) {
            ThirdPayCallBackQueryPayOrderDTO payOrderCallback = payOrderService.selectThirdPayCallBackQueryPayOrder(param.getBizIds());
            if (payOrderCallback == null) {
                return successCreated(ResultCode.PAY_ORDER_NULL);
            } else if (PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal().equals(payOrderCallback.getPayOrderStatusEnum().getVal())) {
                return successCreated(ResultCode.PAY_ORDER_IS_SUCCESS);
            }
            aparam.setSideUserNum(payOrderCallback.getBusinessUserNum());
            money = payOrderCallback.getActualMoney();
            rtnMoney = String.valueOf(money);

            VisitUserInfoDTO visitUserInfoDTO = merchantStoreService.findAccountAndRegionPathByNum(payOrderCallback.getBusinessUserNum());
            aparam.setRegionPath(visitUserInfoDTO.getRegionPath());
            aparam.setOutTradeNo(payOrderCallback.getOrderNum() == null ? "" : payOrderCallback.getOrderNum());
        } else if (ThirdPartyBizFlagEnum.MEMBER_PAY_ORDER.getVal().equals(param.getBizFlagEnum().getVal())) {
            // 考虑商品可能有减库存失败可能
            Result<ShoppingOrderMoneyDTO> result = shoppingOrderService.selectOrderMoney(param.getBizIds());
            if (!isSuccess(result)) {
                return successCreated(result.getRet());
            }
            money = result.getModel().getOrderTotalPrice().doubleValue();
            rtnMoney = result.getModel().getOrderTotalPrice().toString();
            aparam.setOutTradeNo(result.getModel().getOrderNum() == null ? "" : result.getModel().getOrderNum());
        } else if (ThirdPartyBizFlagEnum.MEMBER_PAY_BALANCE.getVal().equals(param.getBizFlagEnum().getVal())
                || ThirdPartyBizFlagEnum.MEMBER_PAY_POINT.getVal().equals(param.getBizFlagEnum().getVal())) {
            ThirdPayCallBackQueryPayOrderDTO recharge = rechargeService.getRechargeMoney(param.getBizIds());
            money = recharge.getActualMoney();
            rtnMoney = String.valueOf(money);
            aparam.setOutTradeNo(recharge.getOrderNum() == null ? "" : recharge.getOrderNum());
        } else if (ThirdPartyBizFlagEnum.MEMBER_RED_PACKET.getVal().equals(param.getBizFlagEnum().getVal())) {
            Result<ThirdPayCallBackQueryPayOrderDTO> moneyResult = userRedPacketService.selectUserRedPacketInfoForThrid(Long.valueOf(param.getBizIds()));
            money = moneyResult.getModel().getActualMoney();
            rtnMoney = String.valueOf(money);
            aparam.setOutTradeNo(moneyResult.getModel().getOrderNum() == null ? "" : moneyResult.getModel().getOrderNum());
        }
        if (StringUtil.doubleCompareTo(money, 0) == 0) {
            return successCreated(ResultCode.MONEY_IS_ZERO);
        }
        aparam.setTotalAmount(rtnMoney);

        return successCreated(alipayService.getAppAlipayReqParams(aparam));

    }

    @Audit(date = "2018-05-10", reviewer = "孙林青")
    @ApiOperation(value = "支付宝登陆授权获取code", notes = "支付宝登陆授权获取code(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "alipayUserInfoAuth", method = RequestMethod.GET)
    public Result<AlipayOauthDTO> alipayUserInfoAuth(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        AlipayOauthDataParam alipayOauthDataParam = new AlipayOauthDataParam();
        alipayOauthDataParam.setUserTypeEnum(UserTypeEnum.MEMBER);
        Result result = alipayService.alipayUserInfoAuth(alipayOauthDataParam);
        AlipayOauthDTO alipayOauthDTO = new AlipayOauthDTO();
        alipayOauthDTO.setOauthInfo(result.getModel().toString());
        return successCreated(alipayOauthDTO);
    }

    /**
     *
     * @param token
     * @param code
     * @return
     * @see com.lawu.eshop.user.param.AliUserInfoParam
     */
    @Audit(date = "2018-05-10", reviewer = "孙林青")
    @ApiOperation(value = "用code换取授权访问令牌，获取用户信息", notes = "用code换取授权访问令牌，获取用户信息(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "getOauthToken", method = RequestMethod.GET)
    public Result getOauthToken(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam("code") String code) {
        AlipayOauthParam alipayOauthParam = new AlipayOauthParam();
        alipayOauthParam.setCode(code);
        alipayOauthParam.setUserTypeEnum(UserTypeEnum.MEMBER);
        alipayOauthParam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        Result<OauthTokenRtnDTO> result = alipayService.getOauthToken(alipayOauthParam);
        if (result.getRet() != ResultCode.SUCCESS) {
            return result;
        }

        richAccountService.bindAlipayInfo(UserUtil.getCurrentUserNum(getRequest()), result.getModel().getAliUserId());

        // 瑞奇岛动力任务
        RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
        taskRecordParam.setMemberNum(UserUtil.getCurrentUserNum(getRequest()));
        taskRecordParam.setType(PowerTaskTypeEnum.ALIPAY_BIND);
        eventPublisher.publishRichPowerTaskEvent(taskRecordParam);

        //保存支付宝用户信息
        AliUserInfoParam aliUserInfoParam = new AliUserInfoParam();
        aliUserInfoParam.setAliUserId(result.getModel().getAliUserId());
        aliUserInfoParam.setAvatar(result.getModel().getAvatar());
        aliUserInfoParam.setProvince(result.getModel().getProvince());
        aliUserInfoParam.setCity(result.getModel().getCity());
        aliUserInfoParam.setNickName(result.getModel().getNickName());
        aliUserInfoParam.setIsStudentCertified(result.getModel().getIsStudentCertified());
        aliUserInfoParam.setUserType(result.getModel().getUserType());
        aliUserInfoParam.setUserStatus(result.getModel().getUserStatus());
        aliUserInfoParam.setIsCertified(result.getModel().getIsCertified());
        aliUserInfoParam.setGender(result.getModel().getGender());
        AliUserInfoDataParam aliUserInfoDataParam = new AliUserInfoDataParam();
        aliUserInfoDataParam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        aliUserInfoDataParam.setUserTypeEnum(com.lawu.eshop.framework.web.impl.constants.UserTypeEnum.MEMBER);
        aliUserInfoDataParam.setAliUserInfoParam(aliUserInfoParam);
        eventPublisher.publishGetAliUserInfoEvent(aliUserInfoDataParam);

        return successCreated();
    }
}

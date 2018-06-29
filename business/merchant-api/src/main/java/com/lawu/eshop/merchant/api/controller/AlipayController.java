package com.lawu.eshop.merchant.api.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.ad.dto.AdPayInfoDTO;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.event.EventPublisher;
import com.lawu.eshop.merchant.api.service.AdService;
import com.lawu.eshop.merchant.api.service.AlipayService;
import com.lawu.eshop.merchant.api.service.BusinessDepositService;
import com.lawu.eshop.merchant.api.service.PropertySrvPropertyService;
import com.lawu.eshop.merchant.api.service.RechargeService;
import com.lawu.eshop.merchant.api.service.RichAccountService;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.constants.ThirdPartyBizFlagEnum;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.dto.AlipayOauthDTO;
import com.lawu.eshop.property.dto.BusinessDepositDTO;
import com.lawu.eshop.property.dto.OauthTokenRtnDTO;
import com.lawu.eshop.property.param.AlipayOauthDataParam;
import com.lawu.eshop.property.param.AlipayOauthParam;
import com.lawu.eshop.property.param.PcAlipayDataParam;
import com.lawu.eshop.property.param.PcAlipayParam;
import com.lawu.eshop.property.param.ThirdPayDataParam;
import com.lawu.eshop.property.param.ThirdPayParam;
import com.lawu.eshop.user.param.AliUserInfoDataParam;
import com.lawu.eshop.user.param.AliUserInfoParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

    private static Logger logger = LoggerFactory.getLogger(AlipayController.class);

    @Autowired
    private AlipayService alipayService;
    @Autowired
    private PropertySrvPropertyService propertyService;
    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private AdService adService;
    @Autowired
    private MerchantApiConfig merchantApiConfig;
    @Autowired
    private BusinessDepositService businessDepositService;

    @Autowired
    private EventPublisher eventPublisher;
    @Autowired
    private RichAccountService richAccountService;


    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "app调用支付宝获取请求参数，已签名加密", notes = "app调用支付宝时需要的请求参数，[]，(杨清华)", httpMethod = "POST")
    @Authorization
    @RequestMapping(value = "getAppAlipayReqParams", method = RequestMethod.POST)
    public Result getAppAlipayReqParams(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                        @ModelAttribute @ApiParam ThirdPayParam param) {

        ThirdPayDataParam aparam = new ThirdPayDataParam();
        aparam.setSubject(param.getThirdPayBodyEnum().getVal());
        aparam.setBizIds(param.getBizIds());
        aparam.setThirdPayBodyEnum(param.getThirdPayBodyEnum());
        aparam.setBizFlagEnum(param.getBizFlagEnum());
        aparam.setUserNum(UserUtil.getCurrentUserNum(getRequest()));
        aparam.setUserTypeEnum(UserTypeEnum.MERCHANT);
        aparam.setMerchantId(UserUtil.getCurrentUserId(getRequest()));

        // 查询支付金额
        if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(param.getBizFlagEnum().getVal())) {
            Result bondRet = propertyService.getValue(PropertyType.MERCHANT_BONT);
            String bond = bondRet.getModel().toString();
            aparam.setTotalAmount(bond);
            Result<BusinessDepositDTO> businessDepositDTOResult = businessDepositService.getDepositById(Long.valueOf(param.getBizIds()));
            aparam.setOutTradeNo(businessDepositDTOResult.getModel() == null ? "" : businessDepositDTOResult.getModel().getDepositNumber());
        } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(param.getBizFlagEnum().getVal())
                || ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(param.getBizFlagEnum().getVal())) {
            ThirdPayCallBackQueryPayOrderDTO recharge = rechargeService.getRechargeMoney(param.getBizIds());
            double money = recharge.getActualMoney();
            if (StringUtil.doubleCompareTo(money, 0) == 0) {
                return successCreated(ResultCode.MONEY_IS_ZERO);
            }
            aparam.setTotalAmount(String.valueOf(money));
            aparam.setOutTradeNo(recharge.getOrderNum() == null ? "" : recharge.getOrderNum());
        } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(param.getBizFlagEnum().getVal())) {
            Result<AdPayInfoDTO> adRet = adService.selectAdPayInfoById(Long.parseLong(param.getBizIds()));
            AdPayInfoDTO ad = adRet.getModel();
            if (StringUtil.doubleCompareTo(ad.getTotalPoint().doubleValue(), 0) == 0) {
                return successCreated(ResultCode.MONEY_IS_ZERO);
            }
            aparam.setTotalAmount(ad.getTotalPoint().toString());
            aparam.setRegionPath(ad.getMerchantRegionPath());
            aparam.setOutTradeNo(ad.getAdOrderNum() == null ? "" : ad.getAdOrderNum());
        }

        return alipayService.getAppAlipayReqParams(aparam);

    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "PC端商家充值余额、积分、缴纳保证金接口", notes = "app调用支付宝时需要的请求参数，[]，(杨清华)", httpMethod = "POST")
    @RequestMapping(value = "initPcPay", method = RequestMethod.POST)
    public void initPcPay(@RequestParam @ApiParam(required = true, value = "token") String token,
                          @ModelAttribute @ApiParam PcAlipayParam param) throws IOException {
        String alipayEnv = merchantApiConfig.getAlipayEnv();

        String userNum = UserUtil.getCurrentUserNumByToken(token);
        PcAlipayDataParam aparam = new PcAlipayDataParam();
        aparam.setSubject(param.getThirdPayBodyEnum().getVal());
        aparam.setBizId(param.getBizId());
        aparam.setBizFlagEnum(param.getBizFlagEnum());
        aparam.setUserNum(userNum);
        aparam.setMerchantId(Long.valueOf(UserUtil.getCurrentUserIdByToken(token) == null ? "0" : UserUtil.getCurrentUserIdByToken(token)));

        // 查询支付金额
        if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BOND.getVal().equals(param.getBizFlagEnum().getVal())) {
            Result bondRet = propertyService.getValue(PropertyType.MERCHANT_BONT);
            String bond = bondRet.getModel().toString();
            aparam.setTotalAmount(bond);
        } else if (ThirdPartyBizFlagEnum.BUSINESS_PAY_BALANCE.getVal().equals(param.getBizFlagEnum().getVal())
                || ThirdPartyBizFlagEnum.BUSINESS_PAY_POINT.getVal().equals(param.getBizFlagEnum().getVal())) {
            ThirdPayCallBackQueryPayOrderDTO recharge = rechargeService.getRechargeMoney(param.getBizId());
            double money = recharge.getActualMoney();
            aparam.setTotalAmount(String.valueOf(money));
        } else if (ThirdPartyBizFlagEnum.BUSINESS_ADD_AD.getVal().equals(param.getBizFlagEnum().getVal())) {
            Result<AdPayInfoDTO> adRet = adService.selectAdPayInfoById(Long.parseLong(param.getBizId()));
            AdPayInfoDTO ad = adRet.getModel();
            if (StringUtil.doubleCompareTo(ad.getTotalPoint().doubleValue(), 0) == 0) {
                return;
            }
            aparam.setTotalAmount(ad.getTotalPoint().toString());
            aparam.setRegionPath(ad.getMerchantRegionPath());
        }

        Result result;
        if (StringUtils.equals("product", alipayEnv)) {
            result = alipayService.initPcPay(aparam);
        } else {
            result = alipayService.initPcPay2048(aparam);
        }
        if (ResultCode.SUCCESS == result.getRet()) {
            Object obj = result.getModel();
            HttpServletResponse response = getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(obj.toString());
        } else {
            logger.error(result.getRet() + "-->" + result.getMsg());
        }
    }

    @Audit(date = "2018-05-10", reviewer = "孙林青")
    @ApiOperation(value = "支付宝登陆授权获取code", notes = "支付宝登陆授权获取code(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "alipayUserInfoAuth", method = RequestMethod.GET)
    public Result<AlipayOauthDTO> alipayUserInfoAuth(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        AlipayOauthDataParam alipayOauthDataParam = new AlipayOauthDataParam();
        alipayOauthDataParam.setUserTypeEnum(UserTypeEnum.MERCHANT);
        Result result = alipayService.alipayUserInfoAuth(alipayOauthDataParam);
        AlipayOauthDTO alipayOauthDTO = new AlipayOauthDTO();
        alipayOauthDTO.setOauthInfo(result.getModel().toString());
        return successCreated(alipayOauthDTO);
    }

    @Audit(date = "2018-05-10", reviewer = "孙林青")
    @ApiOperation(value = "用code换取授权访问令牌，获取用户信息", notes = "用code换取授权访问令牌，获取用户信息(杨清华)", httpMethod = "GET")
    @Authorization
    @RequestMapping(value = "getOauthToken", method = RequestMethod.GET)
    public Result getOauthToken(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam("code") String code) {
        AlipayOauthParam alipayOauthParam = new AlipayOauthParam();
        alipayOauthParam.setCode(code);
        alipayOauthParam.setUserTypeEnum(UserTypeEnum.MERCHANT);
        Result<OauthTokenRtnDTO> result = alipayService.getOauthToken(alipayOauthParam);
        if (result.getRet() != ResultCode.SUCCESS) {
            return result;
        }

        richAccountService.bindAlipayInfo(UserUtil.getCurrentUserNum(getRequest()), result.getModel().getAliUserId());

        // 瑞奇岛动力任务
        RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
        taskRecordParam.setMerchantNum(UserUtil.getCurrentUserNum(getRequest()));
        taskRecordParam.setType(MerchantPowerTaskTypeEnum.ALIPAY_BIND);
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
        aliUserInfoDataParam.setUserTypeEnum(com.lawu.eshop.framework.web.impl.constants.UserTypeEnum.MERCHANT);
        aliUserInfoDataParam.setAliUserInfoParam(aliUserInfoParam);
        eventPublisher.publishGetAliUserInfoEvent(aliUserInfoDataParam);

        return successCreated();
    }

}

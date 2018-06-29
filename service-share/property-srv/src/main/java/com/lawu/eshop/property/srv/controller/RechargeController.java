package com.lawu.eshop.property.srv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.PayTypeEnum;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.dto.AreaRechargePointDTO;
import com.lawu.eshop.property.dto.BalanceAndPointListQueryDTO;
import com.lawu.eshop.property.dto.RechargeReportDTO;
import com.lawu.eshop.property.dto.RechargeSaveDTO;
import com.lawu.eshop.property.dto.ReportAreaRechargeDailyDTO;
import com.lawu.eshop.property.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.param.AgentReportRechargeQueryParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.param.RechargeQueryDataParam;
import com.lawu.eshop.property.param.RechargeReportParam;
import com.lawu.eshop.property.param.RechargeSaveDataParam;
import com.lawu.eshop.property.param.ReportAgentAreaPointParam;
import com.lawu.eshop.property.srv.bo.AgentReportRechargeQueryBO;
import com.lawu.eshop.property.srv.bo.AreaRechargePointBO;
import com.lawu.eshop.property.srv.bo.BalanceAndPointListQueryBO;
import com.lawu.eshop.property.srv.bo.PropertyInfoBO;
import com.lawu.eshop.property.srv.bo.RechargeReportBO;
import com.lawu.eshop.property.srv.service.PropertyInfoService;
import com.lawu.eshop.property.srv.service.PropertyService;
import com.lawu.eshop.property.srv.service.RechargeService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.BeanUtil;
import com.lawu.utils.DateUtil;
import com.lawu.utils.PwdUtil;

/**
 * <p>
 * Description:充值
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月12日 下午8:35:46
 */
@RestController
@RequestMapping(value = "recharge/")
public class RechargeController extends BaseController {

    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private PropertyService propertySrevice;
    @Autowired
    private PropertyInfoService propertyInfoService;

    /**
     * 用户商家第三方充值余额积分保存充值记录
     *
     * @param param
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody @Valid RechargeSaveDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        String value = "1";//充值余额
        if (PayTypeEnum.POINT.getVal().equals(param.getPayTypeEnum().getVal())) {
            // 获取第三方支付充值积分的比例
            String name = PropertyType.MEMBER_THIRD_PAY_POINT;
            if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                name = PropertyType.MERCHANT_THIRD_PAY_POINT;
            }
            value = propertySrevice.getValue(name);
        }
        param.setRechargeScale(value);

        // 余额支付时校验资产财产记录、余额、支付密码
        if (TransactionPayTypeEnum.BALANCE.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {
            PropertyInfoBO propertyInfoBO = propertyInfoService.getPropertyInfoByUserNum(param.getUserNum());
            if (propertyInfoBO == null) {
                return successCreated(ResultCode.PAY_PWD_NULL);
            } else {
                // 校验支付密码
                if (!PwdUtil.verify(param.getPayPwd(), propertyInfoBO.getPayPassword())) {
                    return successCreated(ResultCode.PAY_PWD_ERROR);
                }
            }
        }

        RechargeSaveDTO dto = rechargeService.save(param);
        if (dto == null) {
            return successCreated(ResultCode.FAIL, "充值失败！");
        }
        return successCreated(dto);
    }

    /**
     * 用户/商家微信/支付宝充值余额积分回调
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "doHandleRechargeNotify", method = RequestMethod.POST)
    public Result doHandleRechargeNotify(@RequestBody @Valid NotifyCallBackParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        return rechargeService.doHandleRechargeNotify(param);
    }

    /**
     * 调用第三方时获取支付金额
     *
     * @param rechargeId
     * @return
     */
    @RequestMapping(value = "getRechargeMoney", method = RequestMethod.GET)
    public ThirdPayCallBackQueryPayOrderDTO getRechargeMoney(@RequestParam String rechargeId) {
        if (rechargeId == null || "".equals(rechargeId)) {
            return null;
        }
        ThirdPayCallBackQueryPayOrderDTO recharge = rechargeService.getRechargeMoney(rechargeId);
        return recharge;
    }

    /**
     * 运营平台充值
     *
     * @param dparam
     * @return
     * @throws Exception
     * @author yangqh
     * @date 2017年5月16日 下午3:58:09
     */
    @RequestMapping(value = "selectPropertyinfoList", method = RequestMethod.POST)
    public Result<Page<BalanceAndPointListQueryDTO>> selectPropertyinfoList(@RequestBody RechargeQueryDataParam dparam) throws Exception {
        Page<BalanceAndPointListQueryBO> page = rechargeService.selectPropertyinfoList(dparam);
        List<BalanceAndPointListQueryBO> cbos = page.getRecords();
        List<BalanceAndPointListQueryDTO> dtos = new ArrayList<BalanceAndPointListQueryDTO>();
        for (BalanceAndPointListQueryBO bo : cbos) {
            BalanceAndPointListQueryDTO dto = new BalanceAndPointListQueryDTO();
            BeanUtil.copyProperties(bo, dto);
            dtos.add(dto);
        }
        Page<BalanceAndPointListQueryDTO> pageResult = new Page<BalanceAndPointListQueryDTO>();
        pageResult.setTotalCount(page.getTotalCount());
        pageResult.setCurrentPage(page.getCurrentPage());
        pageResult.setRecords(dtos);
        return successCreated(pageResult);
    }

    /**
     * 根据ID查询充值支付方式
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getRechargePayType/{id}", method = RequestMethod.GET)
    public Result<String> getRechargePayType(@PathVariable Long id) {
        String payType = rechargeService.getRechargePayType(id);
        return successGet(payType);
    }

    /**
     * 根据充值ID查询充值状态
     *
     * @param rechargeId
     * @return
     */
    @RequestMapping(value = "getRechargeById/{rechargeId}", method = RequestMethod.GET)
    public Result<ThirdPayStatusEnum> getRechargeById(@PathVariable Long rechargeId) {
        ThirdPayStatusEnum status = rechargeService.getRechargeById(rechargeId);
        return successGet(status);
    }

    // -------------------------------统计报表

    /**
     * 查询某天平台用户商家充值余额成功的记录
     *
     * @param param
     * @param result
     * @return
     * @throws Exception
     * @author yangqh
     * @date 2017年6月29日 下午5:26:31
     */
    @RequestMapping(value = "selectWithdrawCashListByDateAndStatus", method = RequestMethod.POST)
    public Result<RechargeReportDTO> selectWithdrawCashListByDateAndStatus(@RequestBody @Valid RechargeReportParam param, BindingResult result) throws Exception {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        RechargeReportBO bo = rechargeService.selectWithdrawCashListByDateAndStatus(param);
        RechargeReportDTO dto = new RechargeReportDTO();
        dto.setMemberRechargeMoney(bo.getMemberRechargeMoney());
        dto.setMerchantRechargeMoney(bo.getMerchantRechargeMoney());
        dto.setSumRechargeMoney(bo.getSumRechargeMoney());
        return successCreated(dto);
    }

    /**
     *代理商区域统计，获取某天第三方充值记录，分组查询
     * @param param
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "selectAgentAreaReportRechargeListByDate", method = RequestMethod.POST)
    public Result<List<ReportAreaRechargeDailyDTO>> selectAgentAreaReportRechargeListByDate(@RequestBody @Valid AgentReportRechargeQueryParam param, BindingResult result) throws Exception {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        List<ReportAreaRechargeDailyDTO> dtos = new ArrayList<>();
        List<AgentReportRechargeQueryBO> rntList;
        rntList = rechargeService.selectAgentAreaReportRechargeListByDate(param);
        for (AgentReportRechargeQueryBO bo : rntList) {
            ReportAreaRechargeDailyDTO dto = new ReportAreaRechargeDailyDTO();
            dto.setProvinceId(bo.getProvinceId());
            dto.setCityId(bo.getCityId());
            dto.setAreaId(bo.getAreaId());
            dto.setMemberRechargeBalance(bo.getMemberRechargeBalance());
            dto.setMemberRechargePoint(bo.getMemberRechargePoint());
            dto.setMerchantRechargeBalance(bo.getMerchantRechargeBalance());
            dto.setMerchantRechargePoint(bo.getMerchantRechargePoint());
            dto.setTotalRechargeBalance(bo.getMemberRechargeBalance().add(bo.getMerchantRechargeBalance()));
            dto.setTotalRechargePoint(bo.getMemberRechargePoint().add(bo.getMerchantRechargePoint()));
            dto.setGmtCreate(new Date());
            dto.setGmtReport(DateUtil.formatDate(param.getDate(), "yyyy-MM-dd"));
            dtos.add(dto);
        }
        return successGet(dtos);
    }
    /**
     * 查询区域充值积分记录
     * @param param
     * @return
     */
    @RequestMapping(value = "selectAreaRechargePoint", method = RequestMethod.POST)
    public Result<List<AreaRechargePointDTO>> selectAreaRechargePoint(@RequestBody ReportAgentAreaPointParam param) {
        List<AreaRechargePointDTO> dtos = new ArrayList<>();
        List<AreaRechargePointBO> rntList = rechargeService.selectAreaRechargePoint(param);
        if(rntList != null && !rntList.isEmpty()) {
        	for (AreaRechargePointBO bo : rntList) {
            	AreaRechargePointDTO dto = new AreaRechargePointDTO();
                dto.setAreaId(bo.getAreaId());
                dto.setCityId(bo.getCityId());
                dto.setProvinceId(bo.getProvinceId());
                dto.setTotalMoney(bo.getTotalMoney());
                dto.setType(bo.getType());
                dtos.add(dto);
            }
        }
        return successCreated(dtos);
    }
}

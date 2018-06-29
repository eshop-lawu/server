package com.lawu.eshop.agent.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.agent.api.service.ReportAreaPointConsumeDailyService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeDailyDTO;
import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeDailyForCircleDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;

/**
 * @author hongqm
 * @date 2017/8/15
 */
@Api(tags = "reportAreaPointConsumeDaily")
@RestController
@RequestMapping(value = "reportAreaPointConsumeDaily/")
public class ReportAreaPointConsumeDailyController extends BaseController {

    @Autowired
    private ReportAreaPointConsumeDailyService reportAreaPointConsumeDailyService;

    /**
     * 区域积分消费日查询
     *
     * @param token
     * @param param
     * @return
     */
    @RequestMapping(value = "getReportAreaPointConsumeDaily", method = RequestMethod.GET)
    public Result<ReportAreaPointConsumeDailyForCircleDTO> getReportAreaPointConsumeDaily(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                                          @ModelAttribute @Valid AgentReportParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Result<List<ReportAreaPointConsumeDailyDTO>> rtnResult = reportAreaPointConsumeDailyService.getReportAreaPointConsumeDaily(new Integer(param.getRegionPath().split("/")[1]), param.getBeginTime(), param.getEndTime());
        ReportAreaPointConsumeDailyForCircleDTO dto = new ReportAreaPointConsumeDailyForCircleDTO();
        BigDecimal memberPoint = new BigDecimal(0);
        BigDecimal memberRechargePoint = new BigDecimal(0);
        BigDecimal merchantPoint = new BigDecimal(0);
        BigDecimal merchantRechargePoint = new BigDecimal(0);
        BigDecimal totalPoint = new BigDecimal(0);
        BigDecimal totalRechargePoint = new BigDecimal(0);
        if (result.getModel() != null && !result.getModel().isEmpty()) {
            for (ReportAreaPointConsumeDailyDTO d : rtnResult.getModel()) {
                memberPoint = memberPoint.add(d.getMemberPoint());
                memberRechargePoint = memberRechargePoint.add(d.getMemberRechargePoint());
                merchantPoint = merchantPoint.add(d.getMerchantPoint());
                merchantRechargePoint = merchantRechargePoint.add(d.getMerchantRechargePoint());
                totalRechargePoint = totalRechargePoint.add(d.getTotalRechargePoint());
                totalPoint = totalPoint.add(d.getTotalPoint());
            }
            dto.setMemberPoint(memberPoint);
            dto.setMerchantPoint(merchantPoint);
            dto.setMemberRechargePoint(memberRechargePoint);
            dto.setMerchantRechargePoint(merchantRechargePoint);
        }
        return successGet(dto);
    }
}

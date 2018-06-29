package com.lawu.eshop.statistics.srv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.AgentAreaRechargeQReturnDTO;
import com.lawu.eshop.statistics.dto.ReportAreaRechargeDailyDTO;
import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.eshop.statistics.srv.bo.AgentAreaRechargeQReturnBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaRechargeDailyBO;
import com.lawu.eshop.statistics.srv.converter.ReportAreaRechargeConverter;
import com.lawu.eshop.statistics.srv.service.ReportAreaRechargeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Description: 区域充值统计 日 月
 * </p>
 *
 * @author Yangqh
 * @date 2017年6月28日 下午5:42:20
 */
@RestController
@RequestMapping(value = "reportAreaRecharge/")
public class ReportAreaRechargeController extends BaseController {

    @Autowired
    private ReportAreaRechargeService reportAreaRechargeService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveDaily", method = RequestMethod.POST)
    public Result saveDaily(@RequestBody List<AgentReportRechargeSaveParam> saveParams) {
        reportAreaRechargeService.saveDaily(saveParams);
        return successCreated(ResultCode.SUCCESS);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveMonth", method = RequestMethod.POST)
    public Result saveMonth(@RequestBody List<AgentReportRechargeSaveParam> saveParams) {
        reportAreaRechargeService.saveMonth(saveParams);
        return successCreated(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "getDailyList", method = RequestMethod.GET)
    public Result<List<ReportAreaRechargeDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate) {
        List<ReportAreaRechargeDailyBO> rntList = reportAreaRechargeService.getDailyList(reportDate);
        List<ReportAreaRechargeDailyDTO> dtoList = new ArrayList<>();
        for (ReportAreaRechargeDailyBO rbo : rntList) {
            ReportAreaRechargeDailyDTO dto = new ReportAreaRechargeDailyDTO();
            dto.setGmtCreate(new Date());
            dto.setGmtReport(DateUtil.formatDate(reportDate + "-01", "yyyy-MM-dd"));
            dto.setMemberRechargeBalance(rbo.getMemberRechargeBalance());
            dto.setMemberRechargePoint(rbo.getMemberRechargePoint());
            dto.setMerchantRechargeBalance(rbo.getMerchantRechargeBalance());
            dto.setMerchantRechargePoint(rbo.getMerchantRechargePoint());
            dto.setTotalRechargeBalance(rbo.getTotalRechargeBalance());
            dto.setTotalRechargePoint(rbo.getTotalRechargePoint());
            dto.setProvinceId(rbo.getProvinceId());
            dto.setCityId(rbo.getCityId());
            dto.setAreaId(rbo.getAreaId());
            dtoList.add(dto);
        }
        return successCreated(dtoList);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "deleteDailyByReportDate", method = RequestMethod.DELETE)
    public Result deleteDailyByReportDate(@RequestParam("reportDate") String reportDate) {
        reportAreaRechargeService.deleteDailyByReportDate(reportDate);
        return successCreated(ResultCode.SUCCESS);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "deleteMonthByReportDate", method = RequestMethod.DELETE)
    public Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate) {
        reportAreaRechargeService.deleteMonthByReportDate(reportDate);
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 代理商系统页面查询接口
     * @param param
     * @return
     * @author yangqh
     * @date 2017年8月15日
     */
    @RequestMapping(value = "getAreaRechargeList", method = RequestMethod.POST)
    public AgentAreaRechargeQReturnDTO getAreaRechargeList(@RequestBody AgentReportParam param) {
        AgentAreaRechargeQReturnBO bo = reportAreaRechargeService.getAreaRechargeList(param);
        return ReportAreaRechargeConverter.convertDTO(bo);
    }

}

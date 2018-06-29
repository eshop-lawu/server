package com.lawu.eshop.statistics.srv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.PointConsumeDailyDTO;
import com.lawu.eshop.statistics.dto.ReportCommonBackDTO;
import com.lawu.eshop.statistics.dto.ReportNewDateDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.eshop.statistics.srv.bo.PointConsumeDailyBO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportNewDateDOView;
import com.lawu.eshop.statistics.srv.service.PointConsumeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年6月30日 上午11:16:42
 *
 */
@RestController
@RequestMapping(value = "pointConsume/")
public class PointConsumeController extends BaseController {

	@Autowired
	private PointConsumeService pointConsumeService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveDaily", method = RequestMethod.POST)
	public Result saveDaily(@RequestBody @Valid ReportKCommonParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	pointConsumeService.saveDaily(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	
	@RequestMapping(value = "getDaily", method = RequestMethod.GET)
	public Date getDaily() {
		Date date = pointConsumeService.getDaily();
		return date;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveMonth", method = RequestMethod.POST)
	public Result saveMonth(@RequestBody @Valid ReportKCommonParam param, BindingResult result) {
		String message = validate(result);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	pointConsumeService.saveMonth(param);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@RequestMapping(value = "getMonth", method = RequestMethod.GET)
	public Date getMonth() {
		Date date = pointConsumeService.getMonth();
		return date;
	}
	
	@RequestMapping(value = "getDailyList", method = RequestMethod.GET)
	public Result<List<PointConsumeDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate) {
		List<PointConsumeDailyBO> rntList = pointConsumeService.getDailyList(reportDate);
		List<PointConsumeDailyDTO> dtoList = new ArrayList<>();
		for(PointConsumeDailyBO rdo : rntList){
			PointConsumeDailyDTO dto = new PointConsumeDailyDTO();
			dto.setGmtCreate(rdo.getGmtCreate());
			dto.setGmtReport(rdo.getGmtReport());
			dto.setId(rdo.getId());
			dto.setMemberPoint(rdo.getMemberPoint());
			dto.setMerchantPoint(rdo.getMerchantPoint());
			dto.setTotalPoint(rdo.getTotalPoint());
			dtoList.add(dto);
		}
		return successCreated(dtoList);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteDailyByReportDate", method = RequestMethod.DELETE)
	public Result deleteDailyByReportDate(@RequestParam("reportDate") String reportDate) {
		pointConsumeService.deleteDailyByReportDate(reportDate);
		return successCreated(ResultCode.SUCCESS);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteMonthByReportDate", method = RequestMethod.DELETE)
	public Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate) {
		pointConsumeService.deleteMonthByReportDate(reportDate);
		return successCreated(ResultCode.SUCCESS);
	}
	
	/**
	 * 
	 * @param bdate
	 * @param edate
	 * @return
	 * @author yangqh
	 * @date 2017年7月3日 下午3:40:52
	 */
	@RequestMapping(value = "selectReport", method = RequestMethod.GET)
	public ReportCommonBackDTO selectReport(@RequestParam("bdate") String bdate,@RequestParam("edate") String edate) {
		return pointConsumeService.selectReport(bdate,edate);
	}
	
	/**
     * 获取日统计最新一条记录日期
     *
     * @return
     */
    @RequestMapping(value = "getReportDatePointConsumeDaily", method = RequestMethod.GET)
    public Result<ReportNewDateDTO> getReportDateUserRegDaily() {
    	ReportNewDateDOView view = pointConsumeService.getReportDatePointConsumeDaily();
        return successGet(new ReportNewDateDTO(view.getGmtReport()));
    }
    

    /**
     * 获取日统计最新一条记录日期
     *
     * @return
     */
    @RequestMapping(value = "getReportDatePointConsumeMonth", method = RequestMethod.GET)
    public Result<ReportNewDateDTO> getReportDateUserRegMonth() {
    	ReportNewDateDOView view = pointConsumeService.getReportDatePointConsumeMonth();
        return successGet(new ReportNewDateDTO(view.getGmtReport()));
    }
}

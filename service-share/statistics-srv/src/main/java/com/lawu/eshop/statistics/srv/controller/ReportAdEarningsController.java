package com.lawu.eshop.statistics.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.statistics.dto.ReportAdEarningsDTO;
import com.lawu.eshop.statistics.dto.ReportAdEarningsEndDTO;
import com.lawu.eshop.statistics.param.ReportAdEarningsParam;
import com.lawu.eshop.statistics.param.ReportAdEarningsQueryParam;
import com.lawu.eshop.statistics.srv.bo.ReportAdEarningsBO;
import com.lawu.eshop.statistics.srv.converter.ReportAdEarningsConverter;
import com.lawu.eshop.statistics.srv.service.ReportAdEarningsService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * 广告投放收益统计
 * @author zhangrc
 * @date 2017/06/29
 * 
 */
@RestController
@RequestMapping(value = "reportAdEarnings/")
public class ReportAdEarningsController extends BaseController{
	
	@Autowired
	private ReportAdEarningsService reportAdEarningsService;
	
	/**
	 * 统计保存
	 * @param reportAdEarningsParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveReportAdEarnings", method = RequestMethod.POST)
	public Result saveReportAdEarnings(@RequestBody ReportAdEarningsParam reportAdEarningsParam){
		
		reportAdEarningsService.saveReportAdEarnings(reportAdEarningsParam);
		
		return successCreated(ResultCode.SUCCESS);
	}
	
	
	
	/**
	 * 统计查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "selectReportAdEarnings", method = RequestMethod.POST)
	public Result<Page<ReportAdEarningsDTO>> selectReportAdEarnings(@RequestBody ReportAdEarningsQueryParam query){
		 
		 Page<ReportAdEarningsBO>  pageBO=reportAdEarningsService.selectReportAdEarnings(query);
		 
		 List<ReportAdEarningsBO> list=pageBO.getRecords();
		 
		 List<ReportAdEarningsDTO> listDTO=new ArrayList<>();
		 for (ReportAdEarningsBO reportAdEarningsBO : list) {
			 listDTO.add(ReportAdEarningsConverter.convertDTO(reportAdEarningsBO));
		 }
		 
		 Page<ReportAdEarningsDTO> page=new Page<>();
		 page.setRecords(listDTO);
		 page.setTotalCount(pageBO.getTotalCount());
		 page.setCurrentPage(pageBO.getCurrentPage());
		 
		 return successGet(page);
	}
	
	/**
	 * 收益广告集合
	 * @return
	 */
	@RequestMapping(value = "getReportAdEarningsIds", method = RequestMethod.GET)
	public Result<List<ReportAdEarningsEndDTO>> getReportAdEarningsIds(){
		List<Long> ids=reportAdEarningsService.getReportAdEarningsIds();
		
		List<ReportAdEarningsEndDTO> list=new ArrayList<>();
		for (Long id : ids) {
			ReportAdEarningsEndDTO dto=new ReportAdEarningsEndDTO();
			dto.setId(id);
			list.add(dto);
		}
		return successCreated(list);
		
	}

}

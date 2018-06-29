package com.lawu.eshop.ad.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.ReportAdEarningsDetailDTO;
import com.lawu.eshop.ad.param.ReportAdEarningsDetailParam;
import com.lawu.eshop.ad.srv.bo.ReportAdEarningsDetailBO;
import com.lawu.eshop.ad.srv.service.ReportAdEarningsDetailService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "reportAdEarningsDetail/")
public class ReportAdEarningsDetailController extends BaseController{
	
	
	@Autowired
	private ReportAdEarningsDetailService reportAdEarningsDetailService;
	
	/**
	 * 统计详情
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "getReportAdEarningsDetail", method = RequestMethod.POST)
    public Result<Page<ReportAdEarningsDetailDTO>> getReportAdEarningsDetail(@RequestBody ReportAdEarningsDetailParam param) {
		
		Page<ReportAdEarningsDetailBO> pageBo=reportAdEarningsDetailService.getReportAdEarningsDetail(param);
		 
		 List<ReportAdEarningsDetailDTO> list=new ArrayList<>();
		 
		 for (ReportAdEarningsDetailBO reportAdEarningsDetailBO : pageBo.getRecords()) {
			 
			 ReportAdEarningsDetailDTO dto=new ReportAdEarningsDetailDTO();
			 dto.setTitle(reportAdEarningsDetailBO.getTitle());
			 dto.setPoint(reportAdEarningsDetailBO.getPoint());
			 dto.setGetPointTime(reportAdEarningsDetailBO.getGmtPointTime());
			 dto.setMemberId(reportAdEarningsDetailBO.getMemberId());
			 dto.setMerchantId(reportAdEarningsDetailBO.getMerchantId());
			 dto.setId(reportAdEarningsDetailBO.getId());
			 list.add(dto);
			 
		 }
		 Page<ReportAdEarningsDetailDTO> page =new Page<>();
		 page.setCurrentPage(pageBo.getCurrentPage());
		 page.setTotalCount(pageBo.getTotalCount());
		 page.setRecords(list);
		 
		 return  successCreated(page);
    }

}

package com.lawu.eshop.operator.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.dto.ReportAdEarningsDetailDTO;
import com.lawu.eshop.ad.dto.ReportAdEarningsPointDetailDTO;
import com.lawu.eshop.ad.param.ReportAdEarningsDetailParam;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.operator.api.service.MerchantStoreService;
import com.lawu.eshop.operator.api.service.ReportAdEarningsDetailService;
import com.lawu.eshop.operator.api.service.ReportAdEarningsService;
import com.lawu.eshop.statistics.dto.ReportAdEarningsDTO;
import com.lawu.eshop.statistics.param.ReportAdEarningsQueryParam;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@Api(tags = "reportAdEarnings")
@RestController
@RequestMapping(value = "reportAdEarnings/")
public class ReportAdEarningsController extends BaseController{
	
	@Autowired
	private ReportAdEarningsService reportAdEarningsService;
	
	@Autowired
	private ReportAdEarningsDetailService reportAdEarningsDetailService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MerchantStoreService merchantStoreService;
	
	
	
	
	@ApiOperation(value = "广告收益列表", notes = "广告收益列表,[]（张荣成）", httpMethod = "GET")
	@PageBody
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectReportAdEarnings", method = RequestMethod.GET)
    public Result<Page<ReportAdEarningsDTO>> selectReportAdEarnings(@ModelAttribute @ApiParam(value = "查询信息") ReportAdEarningsQueryParam param) {
        return reportAdEarningsService.selectReportAdEarnings(param);
    }
	
	
	@ApiOperation(value = "广告收益详情列表", notes = "广告收益详情列表,[]（张荣成）", httpMethod = "GET")
	@PageBody
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getReportAdEarningsDetail", method = RequestMethod.GET)
    public Result<Page<ReportAdEarningsPointDetailDTO>> getReportAdEarningsDetail(@ModelAttribute @ApiParam(value = "查询信息") ReportAdEarningsDetailParam param) {
		
		 Result<Page<ReportAdEarningsDetailDTO>>  result=reportAdEarningsDetailService.getReportAdEarningsDetail(param);
		 
		 Page<ReportAdEarningsPointDetailDTO>  page =new Page<>();
		 
		 if(isSuccess(result)){
			 List<ReportAdEarningsDetailDTO> list= result.getModel().getRecords();
			 
			 List<ReportAdEarningsPointDetailDTO> records=new ArrayList<>();
			 
			 for (ReportAdEarningsDetailDTO reportAdEarningsDetailDTO : list) {
				 ReportAdEarningsPointDetailDTO dto=new ReportAdEarningsPointDetailDTO();
				 dto.setGetPointTime(reportAdEarningsDetailDTO.getGetPointTime());
				 dto.setId(reportAdEarningsDetailDTO.getId());
				 dto.setPoint(reportAdEarningsDetailDTO.getPoint());
				 dto.setTitle(reportAdEarningsDetailDTO.getTitle());
				 dto.setAccount(memberService.getMemberAccountById(reportAdEarningsDetailDTO.getMemberId()));
				 Result<MerchantStoreDTO> merchantResult=merchantStoreService.selectMerchantStore(reportAdEarningsDetailDTO.getMerchantId());
				 if(merchantResult.getModel()!=null){
					 dto.setMerchantName(merchantResult.getModel().getName());	 
				 }else{
					 dto.setMerchantName("E店商家");
				 }
				 records.add(dto);
				 
			}
			 
			page.setRecords(records);
			page.setTotalCount(result.getModel().getTotalCount());
			page.setCurrentPage(result.getModel().getCurrentPage());
			 
		 }
		
        return successCreated(page);
    }

}

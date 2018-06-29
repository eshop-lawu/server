package com.lawu.eshop.statistics.srv.converter;

import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.eshop.statistics.constants.ReportAdEarningsStatusEnum;
import com.lawu.eshop.statistics.dto.ReportAdEarningsDTO;
import com.lawu.eshop.statistics.srv.bo.ReportAdEarningsBO;
import com.lawu.eshop.statistics.srv.domain.ReportAdEarningsDO;

public class ReportAdEarningsConverter {

	public static ReportAdEarningsBO convertBO(ReportAdEarningsDO reportAdEarningsDO){
		
		ReportAdEarningsBO reportAdEarningsBO=new ReportAdEarningsBO();
		if(reportAdEarningsDO==null){
			return reportAdEarningsBO;
		}
		reportAdEarningsBO.setId(reportAdEarningsDO.getId());
		reportAdEarningsBO.setAdId(reportAdEarningsDO.getAdId());
		reportAdEarningsBO.setAdCreateTime(reportAdEarningsDO.getAdCreateTime());
		reportAdEarningsBO.setAdStatusEnum(AdStatusEnum.getEnum(reportAdEarningsDO.getAdStatus()));
		reportAdEarningsBO.setAdTitle(reportAdEarningsDO.getAdTitle());
		reportAdEarningsBO.setAdTotalPoint(reportAdEarningsDO.getAdTotalPoint());
		reportAdEarningsBO.setAdTypeEnum(AdTypeEnum.getEnum(reportAdEarningsDO.getAdType()));
		reportAdEarningsBO.setGmtCreate(reportAdEarningsDO.getGmtCreate());
		reportAdEarningsBO.setIsProcessed(reportAdEarningsDO.getIsProcessed());
		reportAdEarningsBO.setLoveTotalPoint(reportAdEarningsDO.getLoveTotalPoint());
		reportAdEarningsBO.setMerchantName(reportAdEarningsDO.getMerchantName());
		reportAdEarningsBO.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.getEnum(reportAdEarningsDO.getStatus()));
		reportAdEarningsBO.setUserTotalPoint(reportAdEarningsDO.getUserTotalPoint());
		
		return reportAdEarningsBO;
		
	}
	
	
	public static ReportAdEarningsDTO convertDTO(ReportAdEarningsBO reportAdEarningsBO){
		
		ReportAdEarningsDTO reportAdEarningsDTO=new ReportAdEarningsDTO();
		if(reportAdEarningsBO==null){
			return reportAdEarningsDTO;
		}
		reportAdEarningsDTO.setId(reportAdEarningsBO.getId());
		reportAdEarningsDTO.setAdId(reportAdEarningsBO.getAdId());
		reportAdEarningsDTO.setAdCreateTime(reportAdEarningsBO.getAdCreateTime());
		reportAdEarningsDTO.setAdStatusEnum(reportAdEarningsBO.getAdStatusEnum());
		reportAdEarningsDTO.setAdTitle(reportAdEarningsBO.getAdTitle());
		reportAdEarningsDTO.setAdTotalPoint(reportAdEarningsBO.getAdTotalPoint());
		reportAdEarningsDTO.setAdTypeEnum(reportAdEarningsBO.getAdTypeEnum());
		reportAdEarningsDTO.setGmtCreate(reportAdEarningsBO.getGmtCreate());
		reportAdEarningsDTO.setIsProcessed(reportAdEarningsBO.getIsProcessed());
		reportAdEarningsDTO.setLoveTotalPoint(reportAdEarningsBO.getLoveTotalPoint());
		reportAdEarningsDTO.setMerchantName(reportAdEarningsBO.getMerchantName());
		reportAdEarningsDTO.setReportAdEarningsStatusEnum(reportAdEarningsBO.getReportAdEarningsStatusEnum());
		reportAdEarningsDTO.setUserTotalPoint(reportAdEarningsBO.getUserTotalPoint());
		
		return reportAdEarningsDTO;
		
	}
}

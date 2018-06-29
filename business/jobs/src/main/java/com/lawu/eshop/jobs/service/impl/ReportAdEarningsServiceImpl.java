package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.dto.ReportAdDTO;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.jobs.service.AdSrvService;
import com.lawu.eshop.jobs.service.MerchantStoreService;
import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.jobs.service.ReportAdEarningsCommissionService;
import com.lawu.eshop.jobs.service.ReportAdEarningsService;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.dto.ReportAdEarningsPointDTO;
import com.lawu.eshop.property.param.ReportAdEarningsPointParam;
import com.lawu.eshop.statistics.constants.AdStatusEnum;
import com.lawu.eshop.statistics.constants.ReportAdEarningsStatusEnum;
import com.lawu.eshop.statistics.dto.ReportAdEarningsEndDTO;
import com.lawu.eshop.statistics.param.ReportAdEarningsParam;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class ReportAdEarningsServiceImpl extends BaseController implements ReportAdEarningsCommissionService {
	
	@Autowired 
	private AdSrvService  adSrvService;
	
	@Autowired 
	private ReportAdEarningsService reportAdEarningsService;
	
	@Autowired 
	private MerchantStoreService merchantStoreService;
	
	@Autowired 
	private PropertySrvService propertySrvService;
	
	/**
	 * 统计广告投放收益清单
	 */
	public void executeSaveOrUpdateReportAdEarnings() {
		 Result<List<ReportAdDTO>>  adResult=adSrvService.selectReportAdEarnings();
		 if(isSuccess(adResult)){
			 List<ReportAdDTO> reportAdList= adResult.getModel();
			 if(!reportAdList.isEmpty()){
				 OK:
				 for (ReportAdDTO reportAdDTO : reportAdList) {
					 ReportAdEarningsParam reportAdEarningsParam=new ReportAdEarningsParam();
					 reportAdEarningsParam.setAdCreateTime(reportAdDTO.getGmtCreate());
					 reportAdEarningsParam.setAdId(reportAdDTO.getId());
					 reportAdEarningsParam.setAdTypeEnum(com.lawu.eshop.common.constants.AdTypeEnum.getEnum(reportAdDTO.getTypeEnum().getVal()));
					 reportAdEarningsParam.setAdTitle(reportAdDTO.getTitle());
					 reportAdEarningsParam.setAdTotalPoint(reportAdDTO.getTotalPoint());
					 reportAdEarningsParam.setMerchantNum(reportAdDTO.getMerchantNum());
					 if(reportAdDTO.getStatusEnum()==com.lawu.eshop.ad.constants.AdStatusEnum.AD_STATUS_PUTED || reportAdDTO.getStatusEnum()==com.lawu.eshop.ad.constants.AdStatusEnum.AD_STATUS_OUT){
						 reportAdEarningsParam.setAdStatusEnum(AdStatusEnum.AD_STATUS_PUTED);
					 }else{
						 reportAdEarningsParam.setAdStatusEnum(AdStatusEnum.getEnum(reportAdDTO.getStatusEnum().val));
					 }
					 
					 //获取商家信息
					 Result<MerchantStoreDTO> storeResult=  merchantStoreService.selectMerchantStoreByMId(reportAdDTO.getMerchantId());
					 if(storeResult.getModel()!=null){
						 reportAdEarningsParam.setMerchantName(storeResult.getModel().getName()); 
					 }
					 
					 //已经统计完结束的广告
					 Result<List<ReportAdEarningsEndDTO>> idsResult=reportAdEarningsService.getReportAdEarningsIds();
					 
					// 统计积分
					if (isSuccess(idsResult) && !idsResult.getModel().isEmpty()) {
						for (ReportAdEarningsEndDTO dto : idsResult.getModel()) {
							if (reportAdDTO.getId().intValue() == dto.getId().intValue()) {
								continue OK;
							}
						}
					}
					ReportAdEarningsPointParam reportAdEarningsPointParam = new ReportAdEarningsPointParam();
					reportAdEarningsPointParam.setBizId(reportAdDTO.getId());
					if (reportAdDTO.getTypeEnum() == AdTypeEnum.AD_TYPE_FLAT) {
						reportAdEarningsPointParam.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.AD_PLANE);
						reportAdEarningsPointParam.setLoveTypeEnum(LoveTypeEnum.AD_CLICK);
					} else if (reportAdDTO.getTypeEnum() == AdTypeEnum.AD_TYPE_VIDEO) {
						 reportAdEarningsPointParam.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.AD_VIDEO);
						 reportAdEarningsPointParam.setLoveTypeEnum(LoveTypeEnum.AD_CLICK);
					 }  else if (reportAdDTO.getTypeEnum() == AdTypeEnum.AD_TYPE_PRAISE) {
						reportAdEarningsPointParam.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.AD_QZ);
						reportAdEarningsPointParam.setLoveTypeEnum(LoveTypeEnum.AD_QZ);
					} else {
						reportAdEarningsPointParam.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.ADD_RED_SWEEP);
						reportAdEarningsPointParam.setLoveTypeEnum(LoveTypeEnum.RED_PACKAGE);
					}
					Result<ReportAdEarningsPointDTO> ponitResult = propertySrvService.getReportAdEarningsPoint(reportAdEarningsPointParam);
					reportAdEarningsParam.setUserTotalPoint(ponitResult.getModel().getUserTotalPoint());
					reportAdEarningsParam.setLoveTotalPoint(ponitResult.getModel().getLoveTotalPoint());

					/* 
					 * 1、平面和视频 用户总收益+爱心账户收益>投放金额*80%时为异常数据 
					 * 2、红包和E咻 用户总收益+爱心账户收益>投放金额时为异常数据 
					 */
					if(reportAdDTO.getTypeEnum()==AdTypeEnum.AD_TYPE_FLAT ||reportAdDTO.getTypeEnum()==AdTypeEnum.AD_TYPE_VIDEO ){
						if(ponitResult.getModel().getUserTotalPoint()
								.add(ponitResult.getModel().getLoveTotalPoint()).compareTo(
										reportAdDTO.getTotalPoint().multiply(BigDecimal.valueOf(0.8))) == 1 &&
										(reportAdDTO.getStatusEnum()==com.lawu.eshop.ad.constants.AdStatusEnum.AD_STATUS_PUTED || reportAdDTO.getStatusEnum()==com.lawu.eshop.ad.constants.AdStatusEnum.AD_STATUS_OUT)){
							reportAdEarningsParam.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.ANOMALY);
						}else{
							reportAdEarningsParam.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.NORMAL);
						}
					}else{
						if(ponitResult.getModel().getUserTotalPoint()
								.add(ponitResult.getModel().getLoveTotalPoint()).compareTo(
										reportAdDTO.getTotalPoint()) == 1 && (reportAdDTO.getStatusEnum()==com.lawu.eshop.ad.constants.AdStatusEnum.AD_STATUS_PUTED || reportAdDTO.getStatusEnum()==com.lawu.eshop.ad.constants.AdStatusEnum.AD_STATUS_OUT)){
							reportAdEarningsParam.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.ANOMALY);
						}else{
							reportAdEarningsParam.setReportAdEarningsStatusEnum(ReportAdEarningsStatusEnum.NORMAL);
						}
					}
					reportAdEarningsService.saveReportAdEarnings(reportAdEarningsParam);
				}
			 }
		 }
	}

}

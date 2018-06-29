package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.property.param.ReportAdEarningsPointParam;
import com.lawu.eshop.property.srv.bo.ReportAdEarningsPointBO;
import com.lawu.eshop.property.srv.bo.ReportEarningsBO;
import com.lawu.eshop.property.srv.domain.extend.ReportAdEarningsPointView;
import com.lawu.eshop.property.srv.domain.extend.ReportAdPointView;
import com.lawu.eshop.property.srv.mapper.extend.TransactionDetailExtendDOMapper;
import com.lawu.eshop.property.srv.service.ReportAdEarningsPointService;

@Service
public class ReportAdEarningsPointServiceImpl implements ReportAdEarningsPointService{
	
	@Autowired
	private TransactionDetailExtendDOMapper transactionDetailExtendDOMapper;

	@Override
	public ReportAdEarningsPointBO getReportAdEarningsPoint(ReportAdEarningsPointParam ReportAdEarningsPointParam) {
		ReportAdPointView view=new ReportAdPointView();
		view.setBizId(ReportAdEarningsPointParam.getBizId());
		view.setPointType(ReportAdEarningsPointParam.getMemberTransactionTypeEnum().getValue());
		view.setLoveType(ReportAdEarningsPointParam.getLoveTypeEnum().getValue());
		ReportAdEarningsPointView  viewDate=transactionDetailExtendDOMapper.getReportAdEarningsPoint(view);
		ReportAdEarningsPointView  viewLoveDate=transactionDetailExtendDOMapper.getReportAdEarningsLovePoint(view);
		ReportAdEarningsPointBO bo=new ReportAdEarningsPointBO();
		if(viewDate==null){
			bo.setUserTotalPoint(BigDecimal.valueOf(0));
		}else{
			bo.setUserTotalPoint(viewDate.getUserTotalPoint());
		}
		
		if(viewLoveDate==null){
			bo.setLoveTotalPoint(BigDecimal.valueOf(0));
		}else{
			bo.setLoveTotalPoint(viewLoveDate.getLoveTotalPoint());
		}
		
		return bo;
	}

	@Override
	public ReportEarningsBO getReportEarnings(List<Long> bizIds) {
		
		List<ReportAdEarningsPointView>  userPoints=transactionDetailExtendDOMapper.getUserPointByBzId(bizIds);
		 
		List<ReportAdEarningsPointView>  lovePoints=transactionDetailExtendDOMapper.getLovePointByBzId(bizIds);
		
		BigDecimal userPoint = new BigDecimal(0);
		BigDecimal lovePoint = new BigDecimal(0);
		
		for (ReportAdEarningsPointView reportAdEarningsPointView : lovePoints) {
			lovePoint=lovePoint.add(reportAdEarningsPointView.getLoveTotalPoint());
		}
		
		for (ReportAdEarningsPointView reportAdEarningsPointView : userPoints) {
			userPoint=lovePoint.add(reportAdEarningsPointView.getUserTotalPoint());
		}
		 
		ReportEarningsBO bo = new ReportEarningsBO();
		bo.setUserPoint(userPoint);

		bo.setLovaPoint(lovePoint);

		return bo;
	}

}

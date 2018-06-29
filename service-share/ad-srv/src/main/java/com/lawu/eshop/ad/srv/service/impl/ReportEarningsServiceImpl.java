package com.lawu.eshop.ad.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.param.AdReportParam;
import com.lawu.eshop.ad.srv.bo.ReportEarningsBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.AdDOExample;
import com.lawu.eshop.ad.srv.domain.extend.MemberAdRecordDOView;
import com.lawu.eshop.ad.srv.domain.extend.PointPoolDOView;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.extend.MemberAdRecordDOMapperExtend;
import com.lawu.eshop.ad.srv.mapper.extend.PointPoolDOMapperExtend;
import com.lawu.eshop.ad.srv.service.ReportEarningsService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.utils.DateUtil;

@Service
public class ReportEarningsServiceImpl implements ReportEarningsService {
	
	
	@Autowired
	private PointPoolDOMapperExtend pointPoolDOMapperExtend;
	
	@Autowired
	private MemberAdRecordDOMapperExtend memberAdRecordDOMapperExtend;
	
	@Autowired
	private AdDOMapper adDOMapper;
	
	

	@Override
	public List<ReportEarningsBO> getReportEarnings(AdReportParam param) {
		
		AdDOExample adDOExample=new AdDOExample();
		
	    String dateNowStr = param.getToday().substring(0, 10);
		
		Date begin = DateUtil.formatDate(dateNowStr+" 00:00:00","yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.formatDate(dateNowStr+" 23:59:59","yyyy-MM-dd HH:mm:ss");
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		adDOExample.createCriteria().andStatusBetween(AdStatusEnum.AD_STATUS_PUTED.val, AdStatusEnum.AD_STATUS_OUT.val)
					.andGmtModifiedBetween(begin, end);
		
		List<AdDO> list=adDOMapper.selectByExampleWithRowbounds(adDOExample, rowBounds);
		
		
		List<ReportEarningsBO> listBO=new ArrayList<>();
		
		for (AdDO adDO : list) {
			
			ReportEarningsBO bo=new ReportEarningsBO();
			bo.setId(adDO.getId());
			
			//平面和视频
			if(adDO.getType()==AdTypeEnum.AD_TYPE_FLAT.getVal() || adDO.getType()==AdTypeEnum.AD_TYPE_VIDEO.getVal()){
				
				MemberAdRecordDOView view=  memberAdRecordDOMapperExtend.getTotlePoint(adDO.getId());
				if(view==null){
					bo.setAdPoint(new BigDecimal("0"));
				}else{
					bo.setAdPoint(view.getTotlePoint());
				}
				
				 
			}else{ //红包和抢赞
				 
				PointPoolDOView view= pointPoolDOMapperExtend.getTotlePoint(adDO.getId());
				 if(view==null){
					bo.setAdPoint(new BigDecimal("0"));
				 }else{
					 bo.setAdPoint(view.getPoint());
				 }
			}
			
			listBO.add(bo);
			
		}
		
		return listBO;
	}



	@Override
	public List<ReportEarningsBO> getReportMonthEarnings() {
		AdDOExample adDOExample=new AdDOExample();
		
		Date begin = DateUtil.formatDate(DateUtil.getDateFormat(new Date())+" 00:00:00","yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.formatDate(DateUtil.getDateFormat(new Date())+" 23:59:59","yyyy-MM-dd HH:mm:ss");
		
		adDOExample.createCriteria().andStatusBetween(AdStatusEnum.AD_STATUS_PUTED.val, AdStatusEnum.AD_STATUS_OUT.val)
					.andGmtModifiedBetween(begin, end);
		
		List<AdDO> list=adDOMapper.selectByExample(adDOExample);
		
		
		List<ReportEarningsBO> listBO=new ArrayList<>();
		
		for (AdDO adDO : list) {
			
			ReportEarningsBO bo=new ReportEarningsBO();
			bo.setId(adDO.getId());
			
			//平面和视频
			if(adDO.getType()==AdTypeEnum.AD_TYPE_FLAT.getVal() || adDO.getType()==AdTypeEnum.AD_TYPE_VIDEO.getVal()){
				
				MemberAdRecordDOView view=  memberAdRecordDOMapperExtend.getTotlePoint(adDO.getId());
				bo.setAdPoint(view.getTotlePoint());
				 
			}else{ //红包和抢赞
				 
				PointPoolDOView view= pointPoolDOMapperExtend.getTotlePoint(adDO.getId());
				 bo.setAdPoint(view.getPoint());
			}
			
			listBO.add(bo);
			
		}
		
		return listBO;
	}

} 

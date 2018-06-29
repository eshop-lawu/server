package com.lawu.eshop.statistics.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;
import com.lawu.eshop.statistics.param.ReportAdEarningsParam;
import com.lawu.eshop.statistics.param.ReportAdEarningsQueryParam;
import com.lawu.eshop.statistics.srv.bo.ReportAdEarningsBO;
import com.lawu.eshop.statistics.srv.converter.ReportAdEarningsConverter;
import com.lawu.eshop.statistics.srv.domain.ReportAdEarningsDO;
import com.lawu.eshop.statistics.srv.domain.ReportAdEarningsDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportAdEarningsDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportAdEarningsDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.ReportAdEarningsService;

/**
 * 广告收益统计接口实现类
 * @author zhangrc
 * @date 2017/06/29
 *
 */
@Service
public class ReportAdEarningsServiceImpl implements ReportAdEarningsService {
	
	@Autowired 
	private ReportAdEarningsDOMapper ReportAdEarningsDOMapper;
	
	@Autowired 
	private ReportAdEarningsDOMapperExtend reportAdEarningsDOMapperExtend;

	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveReportAdEarnings(ReportAdEarningsParam reportAdEarningsParam) {
		ReportAdEarningsDO reportAdEarningsDO=new ReportAdEarningsDO();
		reportAdEarningsDO.setAdId(reportAdEarningsParam.getAdId());
		reportAdEarningsDO.setAdStatus(reportAdEarningsParam.getAdStatusEnum().val);
		reportAdEarningsDO.setAdTitle(reportAdEarningsParam.getAdTitle());
		reportAdEarningsDO.setAdCreateTime(reportAdEarningsParam.getAdCreateTime());
		reportAdEarningsDO.setAdTotalPoint(reportAdEarningsParam.getAdTotalPoint());
		reportAdEarningsDO.setAdType(reportAdEarningsParam.getAdTypeEnum().getVal());
		reportAdEarningsDO.setMerchantNum(reportAdEarningsParam.getMerchantNum());
		reportAdEarningsDO.setMerchantName(reportAdEarningsParam.getMerchantName());
		reportAdEarningsDO.setLoveTotalPoint(reportAdEarningsParam.getLoveTotalPoint());
		reportAdEarningsDO.setUserTotalPoint(reportAdEarningsParam.getUserTotalPoint());
		reportAdEarningsDO.setGmtCreate(new Date());
		reportAdEarningsDO.setStatus(reportAdEarningsParam.getReportAdEarningsStatusEnum().val);
		reportAdEarningsDO.setGmtModified(new Date());
		
		
		ReportAdEarningsDOExample example =new ReportAdEarningsDOExample();
		example.createCriteria().andAdIdEqualTo(reportAdEarningsParam.getAdId());
		Long count=ReportAdEarningsDOMapper.countByExample(example);
		
		if(count.intValue()>0){
			List<ReportAdEarningsDO> list=ReportAdEarningsDOMapper.selectByExample(example);
			reportAdEarningsDO.setId(list.get(0).getId());
			reportAdEarningsDO.setGmtModified(new Date());
			ReportAdEarningsDOMapper.updateByPrimaryKeySelective(reportAdEarningsDO);
		}else{
			reportAdEarningsDO.setGmtModified(new Date());
			ReportAdEarningsDOMapper.insertSelective(reportAdEarningsDO);
		}
	}

	@Override
	public Page<ReportAdEarningsBO> selectReportAdEarnings(ReportAdEarningsQueryParam query) {
		ReportAdEarningsDOExample example =new ReportAdEarningsDOExample();
		if(query.getAdTitle()!=null && query.getAdTitle()!=""){
			example.createCriteria().andAdTitleLike("%" + query.getAdTitle() + "%");
		}else if(query.getMerchantNum()!=null && query.getMerchantNum()!=""){
			example.createCriteria().andMerchantNumEqualTo(query.getMerchantNum());
		}else if(query.getAdStatusEnum()!=null){
			example.createCriteria().andAdStatusEqualTo(query.getAdStatusEnum().val);
		}else if(query.getBeginTime()!=null && query.getEndTime()!=null && query.getBeginTime()!="" && query.getEndTime()!=""){
			example.createCriteria().andGmtCreateBetween(DateUtil.formatDate(query.getBeginTime(), "yyyy-MM-dd"), DateUtil.formatDate(query.getEndTime(), "yyyy-MM-dd"));
		}
		Long count=ReportAdEarningsDOMapper.countByExample(example);
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		example.setOrderByClause("gmt_create desc");
		List<ReportAdEarningsDO>  list=ReportAdEarningsDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<ReportAdEarningsBO>  listBO=new ArrayList<>();
		for (ReportAdEarningsDO reportAdEarningsDO : list) {
			listBO.add(ReportAdEarningsConverter.convertBO(reportAdEarningsDO));
		}
		Page<ReportAdEarningsBO> page=new Page<>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count.intValue());
		page.setRecords(listBO);
		return page;
	}


	@Override
	public List<Long> getReportAdEarningsIds() {
		
		 List<ReportAdEarningsDO> dos=reportAdEarningsDOMapperExtend.getReportAdEarningsIds();
		 List<Long> ids=new ArrayList<>();
		 for (ReportAdEarningsDO reportAdEarningsDO : dos) {
			 ids.add(reportAdEarningsDO.getAdId());
		 }
		
		 return ids;
	}

}

package com.lawu.eshop.ad.srv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.ad.constants.PointPoolStatusEnum;
import com.lawu.eshop.ad.constants.PointPoolTypeEnum;
import com.lawu.eshop.ad.param.ReportAdEarningsDetailParam;
import com.lawu.eshop.ad.srv.bo.ReportAdEarningsDetailBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.MemberAdRecordDO;
import com.lawu.eshop.ad.srv.domain.MemberAdRecordDOExample;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.domain.PointPoolDOExample;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.MemberAdRecordDOMapper;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.eshop.ad.srv.service.ReportAdEarningsDetailService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

@Service
public class ReportAdEarningsDetailServiceImpl implements ReportAdEarningsDetailService{

	@Autowired
	private PointPoolDOMapper pointPoolDOMapper;
	
	@Autowired
	private MemberAdRecordDOMapper memberAdRecordDOMapper;
	
	@Autowired
	private AdDOMapper adDOMapper;
	
	@Override
	public Page<ReportAdEarningsDetailBO> getReportAdEarningsDetail(ReportAdEarningsDetailParam param) {
		
		List<ReportAdEarningsDetailBO> list =new ArrayList<>();
		
		AdDO ad=adDOMapper.selectByPrimaryKey(param.getAdId());
		
		Integer count=0;
		
		if(param.getAdTypeEnum().getVal()==AdTypeEnum.AD_TYPE_FLAT.getVal() || param.getAdTypeEnum().getVal()==AdTypeEnum.AD_TYPE_VIDEO.getVal()){
			MemberAdRecordDOExample memberAdRecordDOExample=new MemberAdRecordDOExample();
			memberAdRecordDOExample.createCriteria().andAdIdEqualTo(param.getAdId()).andGmtCreateLessThan(DateUtil.getNowDate());
			RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
			List<MemberAdRecordDO> memberAdRecordDOList= memberAdRecordDOMapper.selectByExampleWithRowbounds(memberAdRecordDOExample, rowBounds);
			
			Long c=memberAdRecordDOMapper.countByExample(memberAdRecordDOExample);
			count=c.intValue();
			
			for (MemberAdRecordDO memberAdRecordDO : memberAdRecordDOList) {
				ReportAdEarningsDetailBO reportAdEarningsDetailBO=new ReportAdEarningsDetailBO();
				reportAdEarningsDetailBO.setTitle(ad.getTitle());
				reportAdEarningsDetailBO.setPoint(memberAdRecordDO.getPoint());
				reportAdEarningsDetailBO.setGmtPointTime(memberAdRecordDO.getGmtCreate());
				reportAdEarningsDetailBO.setMemberId(memberAdRecordDO.getMemberId());
				reportAdEarningsDetailBO.setMerchantId(ad.getMerchantId());
				reportAdEarningsDetailBO.setId(memberAdRecordDO.getId());
				list.add(reportAdEarningsDetailBO);
			}
		}else {
			PointPoolDOExample pointPoolDOExample=new PointPoolDOExample();
			if(param.getAdTypeEnum().getVal()==AdTypeEnum.AD_TYPE_PRAISE.getVal()){
				pointPoolDOExample.createCriteria().andAdIdEqualTo(param.getAdId()).andTypeEqualTo(PointPoolTypeEnum.AD_TYPE_PRAISE.val)
								   .andStatusEqualTo(PointPoolStatusEnum.AD_POINT_GET.val).andGmtModifiedLessThan(DateUtil.getNowDate());
			}
			else{
				pointPoolDOExample.createCriteria().andAdIdEqualTo(param.getAdId()).andTypeEqualTo(PointPoolTypeEnum.AD_TYPE_PACKET.val)
				   .andStatusEqualTo(PointPoolStatusEnum.AD_POINT_GET.val).andGmtModifiedLessThan(DateUtil.getNowDate());;
				
			}
			RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
			
			List<PointPoolDO> pointPoolList =pointPoolDOMapper.selectByExampleWithRowbounds(pointPoolDOExample, rowBounds);
			
			Long c=pointPoolDOMapper.countByExample(pointPoolDOExample);
			count=c.intValue();
			
			for (PointPoolDO pointPoolDO : pointPoolList) {
				ReportAdEarningsDetailBO reportAdEarningsDetailBO=new ReportAdEarningsDetailBO();
				reportAdEarningsDetailBO.setTitle("红包");
				reportAdEarningsDetailBO.setPoint(pointPoolDO.getPoint());
				reportAdEarningsDetailBO.setGmtPointTime(pointPoolDO.getGmtModified()); 
				reportAdEarningsDetailBO.setMemberId(pointPoolDO.getMemberId());
				reportAdEarningsDetailBO.setMerchantId(ad.getMerchantId());
				reportAdEarningsDetailBO.setId(pointPoolDO.getId());
				list.add(reportAdEarningsDetailBO);
			}
			
		}
		
		Page<ReportAdEarningsDetailBO> page=new Page<>();
		page.setRecords(list);
		page.setTotalCount(count);
		page.setCurrentPage(param.getCurrentPage());
		return page;
	}

}

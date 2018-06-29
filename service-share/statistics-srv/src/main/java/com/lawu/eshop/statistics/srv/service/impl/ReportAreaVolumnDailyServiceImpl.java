package com.lawu.eshop.statistics.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.param.ReportAreaVolumnDailyParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumeDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnDailyInMonthBO;
import com.lawu.eshop.statistics.srv.converter.ReportAreaVolumeDailyConverter;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeDailyDOExample.Criteria;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaVolumnDailyInMonthDOView;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaVolumeDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportAreaVolumeDailyDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.ReportAreaVolumnDailyService;
import com.lawu.utils.DateUtil;

@Service
public class ReportAreaVolumnDailyServiceImpl implements ReportAreaVolumnDailyService{

	
	@Autowired
	private ReportAreaVolumeDailyDOMapper reportAreaVolumeDailyDOMapper;
	
	@Autowired
	private ReportAreaVolumeDailyDOMapperExtend reportAreaVolumeDailyDOMapperExtend;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertReportAreaVolumnDaily(ReportAreaVolumnDailyParam param) {
		ReportAreaVolumeDailyDOExample reportAreaVolumeDailyDOExample = new ReportAreaVolumeDailyDOExample();
		reportAreaVolumeDailyDOExample.createCriteria().andAreaIdEqualTo(param.getAreaId()).andGmtReportEqualTo(param.getGmtReport()).andTypeEqualTo(param.getType());
		reportAreaVolumeDailyDOMapper.deleteByExample(reportAreaVolumeDailyDOExample);
		
		ReportAreaVolumeDailyDO DO = new ReportAreaVolumeDailyDO();
		DO.setAreaId(param.getAreaId());
		DO.setCityId(param.getCityId());
		DO.setGmtCreate(new Date());
		DO.setGmtReport(param.getGmtReport());
		DO.setProvinceId(param.getProvinceId());
		DO.setReportTotalMoney(param.getReportTotalMoney());
		DO.setType(param.getType());
		return reportAreaVolumeDailyDOMapper.insertSelective(DO);
	}

	@Override
	public List<ReportAreaVolumeDailyBO> selectReportAreaVolumnDaily(Integer cityId, String bdate, String edate) {
		ReportAreaVolumeDailyDOExample reportAreaVolumeDailyDOExample = new ReportAreaVolumeDailyDOExample();
		Criteria criteria = reportAreaVolumeDailyDOExample.createCriteria();
		if(cityId != null) {
			criteria.andCityIdEqualTo(cityId);
		}
		if(bdate != null && !"".equals(bdate) && edate != null && !"".equals(edate)) {
			criteria.andGmtReportBetween(DateUtil.formatDate(bdate, "yyyy-MM-dd"), DateUtil.formatDate(edate, "yyyy-MM-dd"));
		}
		List<ReportAreaVolumeDailyDO> list = reportAreaVolumeDailyDOMapper.selectByExample(reportAreaVolumeDailyDOExample);
		List<ReportAreaVolumeDailyBO> rtnList = ReportAreaVolumeDailyConverter.convertDOToBO(list);
		return rtnList;
	}

	@Override
	public List<ReportAreaVolumnDailyInMonthBO> selectReportAreaVolumeDailyInMonth(String bdate, String edate) {
		List<ReportAreaVolumnDailyInMonthDOView> list = reportAreaVolumeDailyDOMapperExtend.selectReportAreaVolumeDailyInMonth(bdate, edate);
		List<ReportAreaVolumnDailyInMonthBO> rtnList = ReportAreaVolumeDailyConverter.convertDOViewToBO(list);
		return rtnList;
	}


}

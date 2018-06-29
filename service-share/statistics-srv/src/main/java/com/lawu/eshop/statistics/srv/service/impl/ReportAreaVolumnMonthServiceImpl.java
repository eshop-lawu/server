package com.lawu.eshop.statistics.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.statistics.param.ReportAreaVolumnMonthParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaVolumnMonthBO;
import com.lawu.eshop.statistics.srv.converter.ReportAreaVolumnMonthConverter;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeMonthDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportAreaVolumeMonthDOExample.Criteria;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaVolumeMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaVolumnMonthService;
@Service
public class ReportAreaVolumnMonthServiceImpl implements ReportAreaVolumnMonthService{

	@Autowired
	private ReportAreaVolumeMonthDOMapper reportAreaVolumeMonthDOMapper;
	
	@Override
	public int insertReportAreaVolumnMonth(ReportAreaVolumnMonthParam param) {
		ReportAreaVolumeMonthDOExample reportAreaVolumeMonthDOExample = new ReportAreaVolumeMonthDOExample();
		reportAreaVolumeMonthDOExample.createCriteria().andAreaIdEqualTo(param.getAreaId()).andGmtReportEqualTo(param.getGmtReport());
		reportAreaVolumeMonthDOMapper.deleteByExample(reportAreaVolumeMonthDOExample);
		ReportAreaVolumeMonthDO reportAreaVolumeMonthDO = new ReportAreaVolumeMonthDO();
		reportAreaVolumeMonthDO.setAreaId(param.getAreaId());
		reportAreaVolumeMonthDO.setCityId(param.getCityId());
		reportAreaVolumeMonthDO.setGmtCreate(new Date());
		reportAreaVolumeMonthDO.setGmtReport(param.getGmtReport());
		reportAreaVolumeMonthDO.setProvinceId(param.getProvinceId());
		reportAreaVolumeMonthDO.setReportTotalMoney(param.getReportTotalMoney());
		reportAreaVolumeMonthDO.setType(param.getType());
		return reportAreaVolumeMonthDOMapper.insertSelective(reportAreaVolumeMonthDO);
	}

	@Override
	public List<ReportAreaVolumnMonthBO> selectReportAreaVolumnMonth(Integer cityId, Date bdate, Date edate) {
		ReportAreaVolumeMonthDOExample reportAreaVolumeMonthDOExample = new ReportAreaVolumeMonthDOExample();
		Criteria criteria = reportAreaVolumeMonthDOExample.createCriteria();
		if(cityId != null) {
			criteria.andCityIdEqualTo(cityId);
		}
		if(bdate != null && edate != null) {
			criteria.andGmtReportBetween(bdate, edate);
		}
		List<ReportAreaVolumeMonthDO> boList = reportAreaVolumeMonthDOMapper.selectByExample(reportAreaVolumeMonthDOExample);
		return ReportAreaVolumnMonthConverter.converDOtoBOList(boList);
	}

}

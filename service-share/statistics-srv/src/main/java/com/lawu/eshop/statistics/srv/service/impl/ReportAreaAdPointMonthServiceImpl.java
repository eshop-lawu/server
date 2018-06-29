package com.lawu.eshop.statistics.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.statistics.param.AgentSelectAreaAdPointParam;
import com.lawu.eshop.statistics.param.ReportAreaAdPointMonthParams;
import com.lawu.eshop.statistics.srv.bo.ReportAreaAdPointMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaAdPointMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaAdPointMonthDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportAreaAdPointMonthDOExample.Criteria;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaAdPointMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaAdPointMonthService;
import com.lawu.utils.DateUtil;
@Service
public class ReportAreaAdPointMonthServiceImpl implements ReportAreaAdPointMonthService{

	@Autowired
	private ReportAreaAdPointMonthDOMapper reportAreaAdPointMonthDOMapper;
	
	@Override
	public int insertReportAreaAdPointMonth(ReportAreaAdPointMonthParams param) {
		ReportAreaAdPointMonthDOExample reportAreaAdPointMonthDOExample = new ReportAreaAdPointMonthDOExample();
		reportAreaAdPointMonthDOExample.createCriteria().andAreaIdEqualTo(param.getAreaId()).andGmtReportEqualTo(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date())));
		reportAreaAdPointMonthDOMapper.deleteByExample(reportAreaAdPointMonthDOExample);
		ReportAreaAdPointMonthDO reportAreaAdPointMonthDO = new ReportAreaAdPointMonthDO();
		reportAreaAdPointMonthDO.setAreaId(param.getAreaId());
		reportAreaAdPointMonthDO.setCityId(param.getCityId());
		reportAreaAdPointMonthDO.setGmtCreate(new Date());
		reportAreaAdPointMonthDO.setGmtReport(DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date())));
		reportAreaAdPointMonthDO.setProvinceId(param.getProvinceId());
		reportAreaAdPointMonthDO.setReportTotalPoint(param.getReportTotalPoint());
		return reportAreaAdPointMonthDOMapper.insertSelective(reportAreaAdPointMonthDO);
	}

	@Override
	public List<ReportAreaAdPointMonthBO> selectReportAreaAdPointMonth(AgentSelectAreaAdPointParam param) {
		ReportAreaAdPointMonthDOExample reportAreaAdPointMonthDOExample = new ReportAreaAdPointMonthDOExample();
		Criteria criteria = reportAreaAdPointMonthDOExample.createCriteria();
		if(param.getCityId() != null) {
			criteria.andCityIdEqualTo(param.getCityId());
		}
		if(param.getBdate() != null && !"".equals(param.getBdate()) || param.getEdate() != null && !"".equals(param.getEdate())) {
			criteria.andGmtReportBetween(DateUtil.formatDate(param.getBdate()+"-01", "yyyy-MM-dd"), DateUtil.formatDate(param.getEdate()+"-01", "yyyy-MM-dd"));
		}
		List<ReportAreaAdPointMonthDO> doList = reportAreaAdPointMonthDOMapper.selectByExample(reportAreaAdPointMonthDOExample);
		List<ReportAreaAdPointMonthBO> list = new ArrayList<ReportAreaAdPointMonthBO>();
		if(doList != null && !doList.isEmpty()) {
			for(ReportAreaAdPointMonthDO DO : doList) {
				ReportAreaAdPointMonthBO bo = new ReportAreaAdPointMonthBO();
				bo.setAreaId(DO.getAreaId());
				bo.setCityId(DO.getCityId());
				bo.setGmtCreate(DO.getGmtCreate());
				bo.setGmtReport(DO.getGmtReport());
				bo.setId(DO.getId());
				bo.setProvinceId(DO.getProvinceId());
				bo.setReportTotalPoint(DO.getReportTotalPoint());
				list.add(bo);
			}
		}
		return list;
	}

}

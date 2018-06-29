package com.lawu.eshop.statistics.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.statistics.param.ReportAreaPointConsumeMonthParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaPointConsumeMonthBO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaPointConsumeMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaPointConsumeMonthDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportAreaPointConsumeMonthDOExample.Criteria;
import com.lawu.eshop.statistics.srv.domain.extend.ReportAreaPointConsumeDailyInMonthDOView;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaPointConsumeMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportAreaPointConsumeMonthDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.ReportAreaPointConsumeMonthService;
import com.lawu.utils.DateUtil;
@Service
public class ReportAreaPointConsumeMonthServiceImpl implements ReportAreaPointConsumeMonthService{

	@Autowired
	private ReportAreaPointConsumeMonthDOMapper reportAreaPointConsumeMonthDOMapper;

	@Autowired
	private ReportAreaPointConsumeMonthDOMapperExtend reportAreaPointConsumeMonthDOMapperExtend;
	
	@Override
	public int saveReportAreaPointConsumeMonth(ReportAreaPointConsumeMonthParam param) {
		ReportAreaPointConsumeMonthDOExample example = new ReportAreaPointConsumeMonthDOExample();
		example.createCriteria().andAreaIdEqualTo(param.getAreaId()).andGmtReportEqualTo(param.getGmtReport());
		reportAreaPointConsumeMonthDOMapper.deleteByExample(example);
		ReportAreaPointConsumeMonthDO DO = new ReportAreaPointConsumeMonthDO();
		DO.setProvinceId(param.getProvinceId());
		DO.setAreaId(param.getAreaId());
		DO.setCityId(param.getCityId());
		DO.setGmtCreate(new Date());
		DO.setGmtReport(param.getGmtReport());
		DO.setMemberPoint(param.getMemberPoint() == null ? new BigDecimal(0) : param.getMemberPoint());
		DO.setMerchantPoint(param.getMerchantPoint() == null ? new BigDecimal(0) : param.getMerchantPoint());
		DO.setMemberRechargePoint(param.getMemberRechargePoint() == null ? new BigDecimal(0) : param.getMemberRechargePoint());
		DO.setMerchantRechargePoint(param.getMerchantRechargePoint() == null ? new BigDecimal(0) : param.getMerchantRechargePoint());
		DO.setTotalPoint(param.getMemberPoint() == null ? new BigDecimal(0) : param.getMemberPoint().add(param.getMerchantPoint() == null ? new BigDecimal(0) : param.getMerchantPoint()));
		DO.setTotalRechargePoint(param.getMemberRechargePoint() == null ? new BigDecimal(0) : param.getMemberRechargePoint().add(param.getMerchantRechargePoint() == null ? new BigDecimal(0) : param.getMerchantRechargePoint()));
		return reportAreaPointConsumeMonthDOMapper.insertSelective(DO);
	}

	@Override
	public List<ReportAreaPointConsumeMonthBO> selectReportAreaPointConsumeMonth(Integer cityId, String bdate,
			String edate) {
		ReportAreaPointConsumeMonthDOExample example = new ReportAreaPointConsumeMonthDOExample();
		Criteria c = example.createCriteria();
		if(cityId != null) {
			c.andCityIdEqualTo(cityId);
		}
		if(bdate != null && !"".equals(bdate) && edate != null && !"".equals(edate)) {
			c.andGmtReportBetween(DateUtil.formatDate(bdate, "yyyy-MM-dd"), DateUtil.formatDate(edate, "yyyy-MM-dd"));
		}
		List<ReportAreaPointConsumeMonthDO> doList = reportAreaPointConsumeMonthDOMapper.selectByExample(example);
		List<ReportAreaPointConsumeMonthBO> rtnList = new ArrayList<>();
		if(doList != null && !doList.isEmpty()) {
			for(ReportAreaPointConsumeMonthDO DO : doList) {
				ReportAreaPointConsumeMonthBO bo = new ReportAreaPointConsumeMonthBO();
				bo.setAreaId(DO.getAreaId());
				bo.setCityId(DO.getCityId());
				bo.setProvinceId(DO.getProvinceId());
				bo.setGmtCreate(DO.getGmtCreate());
				bo.setGmtReport(DO.getGmtReport());
				bo.setMemberPoint(DO.getMemberPoint());
				bo.setMerchantRechargePoint(DO.getMerchantRechargePoint());
				bo.setMemberRechargePoint(DO.getMemberRechargePoint());
				bo.setMerchantPoint(DO.getMerchantPoint());
				rtnList.add(bo);
			}
		}
		return rtnList;
	}

	@Override
	public void executeCollectReportAreaPointConsumeMonth(Integer pageSize) {
		Date firstDayOfLastMonth = DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(new Date()));
		Date lastDayOfLastMonth = DateUtil.getLastDayOfMonth(DateUtil.getMonthBefore(new Date()));
		int currentPage = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("bdate", DateUtil.getDateFormat(firstDayOfLastMonth));
		map.put("edate", DateUtil.getDateFormat(lastDayOfLastMonth));
		map.put("pageSize", pageSize);

		while (true) {
			currentPage++;
			map.put("offset", (currentPage - 1) * pageSize);
			List<ReportAreaPointConsumeDailyInMonthDOView> viewList = reportAreaPointConsumeMonthDOMapperExtend.executeCollectReportAreaPointConsumeMonth(map);
			if (viewList == null || viewList.isEmpty()) {
				return;
			}

			for (ReportAreaPointConsumeDailyInMonthDOView view : viewList) {
				ReportAreaPointConsumeMonthParam param = new ReportAreaPointConsumeMonthParam();
				param.setAreaId(view.getAreaId());
				param.setCityId(view.getCityId());
				param.setProvinceId(view.getProvinceId());
				param.setGmtReport(firstDayOfLastMonth);
				param.setMemberPoint(view.getMemberPoint());
				param.setMemberRechargePoint(view.getMemberRechargePoint());
				param.setMerchantPoint(view.getMerchantPoint());
				param.setMerchantRechargePoint(view.getMerchantRechargePoint());
				saveReportAreaPointConsumeMonth(param);
			}
		}
	}

}

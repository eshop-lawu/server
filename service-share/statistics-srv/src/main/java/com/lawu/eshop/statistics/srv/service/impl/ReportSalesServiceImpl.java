package com.lawu.eshop.statistics.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.param.PlatformTotalSalesQueryParam;
import com.lawu.eshop.statistics.param.PlatformTotalSalesSaveParam;
import com.lawu.eshop.statistics.srv.bo.ReportSalesBO;
import com.lawu.eshop.statistics.srv.converter.ReportSalesDailyConverter;
import com.lawu.eshop.statistics.srv.converter.ReportSalesMonthConverter;
import com.lawu.eshop.statistics.srv.domain.ReportSalesDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportSalesDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportSalesMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportSalesMonthDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportSalesDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportSalesMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportSalesService;
import com.lawu.utils.DateUtil;

/**
 * 平台总销量服务接口实现类
 * 
 * @author Sunny
 * @date 2017年6月30日
 */
@Service
public class ReportSalesServiceImpl implements ReportSalesService {

	@Autowired
	private ReportSalesDailyDOMapper reportSalesDailyDOMapper;

	@Autowired
	private ReportSalesMonthDOMapper reportSalesMonthDOMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(PlatformTotalSalesSaveParam param) {
		switch (param.getType()) {
			case DAILY:
			    // 如果已经有记录了，就直接返回
			    ReportSalesDailyDOExample reportSalesDailyDOExample = new ReportSalesDailyDOExample();
			    reportSalesDailyDOExample.createCriteria().andGmtReportEqualTo(param.getGmtReport());
			    Long reportSalesDailyCount = reportSalesDailyDOMapper.countByExample(reportSalesDailyDOExample);
			    if (reportSalesDailyCount > 0) {
			        return;
			    }
		        ReportSalesDailyDO reportSalesDailyDO = new ReportSalesDailyDO();
		        reportSalesDailyDO.setPayOrderAmount(param.getPayOrderAmount());
		        reportSalesDailyDO.setShoppingOrderAmount(param.getShoppingOrderAmount());
		        reportSalesDailyDO.setTotalAmount(param.getPayOrderAmount().add(param.getShoppingOrderAmount()));
		        reportSalesDailyDO.setGmtReport(param.getGmtReport());
		        reportSalesDailyDO.setGmtCreate(new Date());
				reportSalesDailyDOMapper.insertSelective(reportSalesDailyDO);
				break;
			case MONTH:
			    ReportSalesMonthDOExample reportSalesMonthDOExample = new ReportSalesMonthDOExample();
			    reportSalesMonthDOExample.createCriteria().andGmtReportEqualTo(param.getGmtReport());
			    Long reportSalesMonthCount = reportSalesMonthDOMapper.countByExample(reportSalesMonthDOExample);
			    if (reportSalesMonthCount > 0) {
			        return;
			    }
				Date start = DateUtil.getFirstDayOfMonth(param.getGmtReport());
				Date end = DateUtil.getLastDayOfMonth(param.getGmtReport());
				ReportSalesDailyDOExample example = new ReportSalesDailyDOExample();
				example.createCriteria().andGmtReportBetween(start, end);
				List<ReportSalesDailyDO> reportSalesDailyDOList = reportSalesDailyDOMapper.selectByExample(example);
		        ReportSalesMonthDO record = new ReportSalesMonthDO();
		        BigDecimal payOrderAmount = new BigDecimal(0);
		        BigDecimal shoppingOrderAmount = new BigDecimal(0);
		        for (ReportSalesDailyDO item : reportSalesDailyDOList) {
		            payOrderAmount = payOrderAmount.add(item.getPayOrderAmount());
		            shoppingOrderAmount = shoppingOrderAmount.add(item.getShoppingOrderAmount());
		        }
		        record.setPayOrderAmount(payOrderAmount);
		        record.setShoppingOrderAmount(shoppingOrderAmount);
		        record.setTotalAmount(payOrderAmount.add(shoppingOrderAmount));
		        record.setGmtReport(param.getGmtReport());
		        record.setGmtCreate(new Date());
				reportSalesMonthDOMapper.insertSelective(record);
				break;
			default:
				break;
		}

	}

    /**
     * 查询平台总销量记录
     * 
     * @param param
     * @return
     * @author Sunny
     * @date 2017年7月3日
     */
	@Override
	public List<ReportSalesBO> list(PlatformTotalSalesQueryParam param) {
		List<ReportSalesBO> rtn = null;
		switch (param.getType()) {
			case DAILY:
				ReportSalesDailyDOExample reportSalesDailyDOExample = new ReportSalesDailyDOExample();
				reportSalesDailyDOExample.createCriteria().andGmtReportBetween(param.getStart(), param.getEnd());
				reportSalesDailyDOExample.setOrderByClause("gmt_report asc");
				List<ReportSalesDailyDO> reportSalesDailyDOList = reportSalesDailyDOMapper.selectByExample(reportSalesDailyDOExample);
				rtn = ReportSalesDailyConverter.convertReportSalesBOList(reportSalesDailyDOList);
				break;
			case MONTH:
				ReportSalesMonthDOExample reportSalesMonthDOExample = new ReportSalesMonthDOExample();
				reportSalesMonthDOExample.createCriteria().andGmtReportBetween(param.getStart(), param.getEnd());
				reportSalesMonthDOExample.setOrderByClause("gmt_report asc");
				List<ReportSalesMonthDO> reportSalesMonthDOList = reportSalesMonthDOMapper.selectByExample(reportSalesMonthDOExample);
				rtn = ReportSalesMonthConverter.convertReportSalesBOList(reportSalesMonthDOList);
				break;
			default:
				break;
		}
		return rtn;
	}
}

package com.lawu.eshop.statistics.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveBO;
import com.lawu.eshop.statistics.srv.converter.UserActiveConverter;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveAreaMonthDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveMonthDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveAreaDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveAreaMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportUserActiveDOMapperExtend;
import com.lawu.eshop.statistics.srv.mapper.extend.UserActiveDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.ReportUserActiveDailyService;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
@Service
public class ReportUserActiveDailyServiceImpl implements ReportUserActiveDailyService {

    @Autowired
    private ReportUserActiveDailyDOMapper reportUserActiveDailyDOMapper;

    @Autowired
    private UserActiveDOMapperExtend userActiveDOMapperExtend;

    @Autowired
    private ReportUserActiveDOMapperExtend reportUserActiveDOMapperExtend;

    @Autowired
    private ReportUserActiveAreaDailyDOMapper reportUserActiveAreaDailyDOMapper;

    @Autowired
    private ReportUserActiveMonthDOMapper reportUserActiveMonthDOMapper;

    @Autowired
    private ReportUserActiveAreaMonthDOMapper reportUserActiveAreaMonthDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserActiveDaily(Integer memberCount, Integer merchantCount, String reportDate) {

        ReportUserActiveDailyDO userActiveDailyDO = new ReportUserActiveDailyDO();
        userActiveDailyDO.setMemberCount(memberCount);
        userActiveDailyDO.setMerchantCount(merchantCount);
        userActiveDailyDO.setGmtCreate(new Date());
        userActiveDailyDO.setGmtReport(DateUtil.getDateFormat(reportDate));
        reportUserActiveDailyDOMapper.insertSelective(userActiveDailyDO);
    }

    @Override
    public List<ReportUserActiveBO> getUserActiveListDaily(String beginTime, String endTime) {
        if(StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)){
            beginTime = DateUtil.getDateFormat(new Date(), "yyyy-MM")+"-01";
            endTime = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd");
        }
        Date begin = DateUtil.formatDate(beginTime, "yyyy-MM-dd");
        Date end = DateUtil.formatDate(endTime, "yyyy-MM-dd");
        ReportUserActiveDailyDOExample example = new ReportUserActiveDailyDOExample();
        example.createCriteria().andGmtReportGreaterThanOrEqualTo(begin).andGmtReportLessThanOrEqualTo(end);
        example.setOrderByClause(" gmt_report asc ");
        List<ReportUserActiveDailyDO> dailyDOS = reportUserActiveDailyDOMapper.selectByExample(example);
        List<ReportUserActiveBO> reportUserActiveBOS = UserActiveConverter.coverReportBOSWithDOS(dailyDOS);
        return reportUserActiveBOS;
    }

    @Override
    public List<ReportUserActiveBO> getUserActiveListMonth(String beginTime, String endTime) {
        if(StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)){
            beginTime = DateUtil.getDateFormat(new Date(), "yyyy"+"-01");
            endTime = DateUtil.getDateFormat(new Date(), "yyyy-MM");
        }
        ReportUserActiveMonthDOExample example = new ReportUserActiveMonthDOExample();
        Date begin = DateUtil.formatDate(beginTime+"-01", "yyyy-MM-dd");
        Date end = DateUtil.formatDate(endTime+"-01", "yyyy-MM-dd");
        example.createCriteria().andGmtReportBetween(begin, end);
        example.setOrderByClause(" gmt_report asc ");
        List<ReportUserActiveMonthDO> reportUserActiveMonthDOS = reportUserActiveMonthDOMapper.selectByExample(example);

       // List<ReportUserActiveDOView> userActiveDOViews = reportUserActiveDOMapperExtend.getUserActiveListMonth(param);
        List<ReportUserActiveBO> reportUserActiveBOS = UserActiveConverter.coverReportBOS(reportUserActiveMonthDOS);
        return reportUserActiveBOS;
    }

    @Override
    public List<ReportUserActiveAreaDailyBO> getReportUserActiveAreaDailyList(String reportDate) {
        ReportUserActiveAreaDailyDOExample example = new ReportUserActiveAreaDailyDOExample();
        example.createCriteria().andGmtReportEqualTo(DateUtil.formatDate(reportDate,"yyyy-MM-dd"));
       List<ReportUserActiveAreaDailyDO> areaDailyDOS = reportUserActiveAreaDailyDOMapper.selectByExample(example);
        List<ReportUserActiveAreaDailyBO> reportUserActiveBOS = UserActiveConverter.coverReportAreaBOS(areaDailyDOS);
        return reportUserActiveBOS;
    }

    @Override
    public List<ReportUserActiveAreaDailyBO> getAgentUserActiveListDaily(AgentReportParam param) {
        String beginTime;
        String endTime;
        if(StringUtils.isEmpty(param.getBeginTime()) || StringUtils.isEmpty(param.getEndTime())){
            endTime = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd");
            beginTime = DateUtil.getDateFormat(new Date(), "yyyy-MM")+"-01";
        }else{
            beginTime = param.getBeginTime();
            endTime = param.getEndTime();
        }
        Date begin = DateUtil.formatDate(beginTime, "yyyy-MM-dd");
        Date end = DateUtil.formatDate(endTime, "yyyy-MM-dd");
        String[] pathArr = param.getRegionPath().split("/");
        ReportUserActiveAreaDailyDOExample example = new ReportUserActiveAreaDailyDOExample();
        example.createCriteria().andGmtReportGreaterThanOrEqualTo(begin).andGmtReportLessThanOrEqualTo(end).andCityIdEqualTo(Integer.valueOf(pathArr[1]));
        example.setOrderByClause(" gmt_report asc ");
        List<ReportUserActiveAreaDailyDO> dailyDOS = reportUserActiveAreaDailyDOMapper.selectByExample(example);

        List<ReportUserActiveAreaDailyBO> reportUserActiveBOS = UserActiveConverter.coverReportAreaBOS(dailyDOS);
        return reportUserActiveBOS;
    }

    @Override
    public List<ReportUserActiveAreaMonthBO> getAgentUserActiveListMonth(AgentReportParam param) {
        String beginTime;
        String endTime;
        if (StringUtils.isEmpty(param.getBeginTime()) || StringUtils.isEmpty(param.getEndTime())) {
            endTime = DateUtil.getDateFormat(new Date(), "yyyy-MM");
            beginTime = DateUtil.getDateFormat(new Date(), "yyyy" + "-01");
        } else {
            beginTime = param.getBeginTime();
            endTime = param.getEndTime();
        }
        Date begin = DateUtil.formatDate(beginTime + "-01", "yyyy-MM-dd");
        Date end = DateUtil.formatDate(endTime + "-01", "yyyy-MM-dd");
        String[] pathArr = param.getRegionPath().split("/");
        ReportUserActiveAreaMonthDOExample example = new ReportUserActiveAreaMonthDOExample();
        example.createCriteria().andGmtReportGreaterThanOrEqualTo(begin).andGmtReportLessThanOrEqualTo(end).andCityIdEqualTo(Integer.valueOf(pathArr[1]));
        example.setOrderByClause(" gmt_report asc ");
        List<ReportUserActiveAreaMonthDO> list = reportUserActiveAreaMonthDOMapper.selectByExample(example);
        List<ReportUserActiveAreaMonthBO> monthBOS = UserActiveConverter.coverReportAreaMonthBOS(list);
        return monthBOS;
    }
}

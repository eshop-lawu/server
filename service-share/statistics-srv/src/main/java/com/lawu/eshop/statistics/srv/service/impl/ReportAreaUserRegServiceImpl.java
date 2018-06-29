package com.lawu.eshop.statistics.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegMonthBO;
import com.lawu.eshop.statistics.srv.converter.ReportAreaUserRegConverter;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegMonthDOExample;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaUserRegDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaUserRegMonthDOMapper;
import com.lawu.eshop.statistics.srv.service.ReportAreaUserRegService;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2017/8/11.
 */
@Service
public class ReportAreaUserRegServiceImpl implements ReportAreaUserRegService {

    @Autowired
    private ReportAreaUserRegDailyDOMapper reportAreaUserRegDailyDOMapper;

    @Autowired
    private ReportAreaUserRegMonthDOMapper reportAreaUserRegMonthDOMapper;

    @Override
    public List<ReportAreaUserRegDailyBO> getUserRegListDaily(AgentReportParam param) {
        String beginTime;
        String endTime;
        if (StringUtils.isEmpty(param.getBeginTime()) || StringUtils.isEmpty(param.getEndTime())) {
            beginTime = DateUtil.getDateFormat(new Date(), "yyyy-MM") + "-01";
            endTime = DateUtil.getDateFormat(new Date(), "yyyy-MM-dd");
        } else {
            beginTime = param.getBeginTime();
            endTime = param.getEndTime();
        }
        Date begin = DateUtil.formatDate(beginTime, "yyyy-MM-dd");
        Date end = DateUtil.formatDate(endTime, "yyyy-MM-dd");
        String[] pathArr = param.getRegionPath().split("/");
        ReportAreaUserRegDailyDOExample example = new ReportAreaUserRegDailyDOExample();
        example.createCriteria().andGmtReportGreaterThanOrEqualTo(begin).andGmtReportLessThanOrEqualTo(end).andCityIdEqualTo(Integer.valueOf(pathArr[1]));
        example.setOrderByClause(" gmt_report asc ");
        List<ReportAreaUserRegDailyDO> userRegDailyDOS = reportAreaUserRegDailyDOMapper.selectByExample(example);
        List<ReportAreaUserRegDailyBO> list = ReportAreaUserRegConverter.coverBOS(userRegDailyDOS);
        return list;
    }

    @Override
    public List<ReportAreaUserRegMonthBO> getUserRegListMonth(AgentReportParam param) {
        String beginTime;
        String endTime;
        if(StringUtils.isEmpty(param.getBeginTime()) || StringUtils.isEmpty(param.getEndTime())){
            beginTime = DateUtil.getDateFormat(new Date(), "yyyy"+"-01");
            endTime = DateUtil.getDateFormat(new Date(), "yyyy-MM");
        }else{
            beginTime = param.getBeginTime();
            endTime = param.getEndTime();
        }

        String[] pathArr = param.getRegionPath().split("/");
        ReportAreaUserRegMonthDOExample example = new ReportAreaUserRegMonthDOExample();
        Date begin = DateUtil.formatDate(beginTime+"-01", "yyyy-MM-dd");
        Date end = DateUtil.formatDate(endTime+"-01", "yyyy-MM-dd");
        example.createCriteria().andGmtReportGreaterThanOrEqualTo(begin).andGmtReportLessThanOrEqualTo(end).andCityIdEqualTo(Integer.valueOf(pathArr[1]));
        example.setOrderByClause(" gmt_report asc ");

        List<ReportAreaUserRegMonthDO> userRegMonthDOS = reportAreaUserRegMonthDOMapper.selectByExample(example);
        List<ReportAreaUserRegMonthBO> list = ReportAreaUserRegConverter.coverMonthBOS(userRegMonthDOS);
        return list;
    }
}

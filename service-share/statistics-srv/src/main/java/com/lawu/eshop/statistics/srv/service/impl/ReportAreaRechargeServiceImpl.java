package com.lawu.eshop.statistics.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.eshop.statistics.srv.bo.AgentAreaRechargeQReturnBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaRechargeDailyBO;
import com.lawu.eshop.statistics.srv.converter.ReportAreaRechargeConverter;
import com.lawu.eshop.statistics.srv.domain.ReportAreaRechargeDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaRechargeDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportAreaRechargeMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaRechargeMonthDOExample;
import com.lawu.eshop.statistics.srv.domain.extend.AgentAreaRechargeDailyMonthDOExtend;
import com.lawu.eshop.statistics.srv.domain.extend.AgentAreaRechargeExampleExtend;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaRechargeDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaRechargeMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportAreaRechargeDailyDOMapperExtend;
import com.lawu.eshop.statistics.srv.mapper.extend.ReportAreaRechargeMonthDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.ReportAreaRechargeService;
import com.lawu.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportAreaRechargeServiceImpl implements ReportAreaRechargeService {

    @Autowired
    private ReportAreaRechargeDailyDOMapper reportAreaRechargeDailyDOMapper;
    @Autowired
    private ReportAreaRechargeMonthDOMapper reportAreaRechargeMonthDOMapper;
    @Autowired
    private ReportAreaRechargeDailyDOMapperExtend reportAreaRechargeDailyDOMapperExtend;
    @Autowired
    private ReportAreaRechargeMonthDOMapperExtend reportAreaRechargeMonthDOMapperExtend;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaily(List<AgentReportRechargeSaveParam> saveParams) {
        ReportAreaRechargeDailyDO record = new ReportAreaRechargeDailyDO();
        for (AgentReportRechargeSaveParam param : saveParams) {
            record.setGmtCreate(param.getGmtCreate());
            record.setGmtReport(param.getGmtReport());
            record.setMemberRechargeBalance(param.getMemberRechargeBalance());
            record.setMemberRechargePoint(param.getMemberRechargePoint());
            record.setMerchantRechargeBalance(param.getMerchantRechargeBalance());
            record.setMerchantRechargePoint(param.getMerchantRechargePoint());
            record.setTotalRechargeBalance(param.getTotalRechargeBalance());
            record.setTotalRechargePoint(param.getTotalRechargePoint());
            record.setProvinceId(param.getProvinceId());
            record.setCityId(param.getCityId());
            record.setAreaId(param.getAreaId());
            reportAreaRechargeDailyDOMapper.insertSelective(record);
        }
    }

    @Override
    public void saveMonth(List<AgentReportRechargeSaveParam> saveParams) {
        ReportAreaRechargeMonthDO record = new ReportAreaRechargeMonthDO();
        for (AgentReportRechargeSaveParam param : saveParams) {
            record.setGmtCreate(param.getGmtCreate());
            record.setGmtReport(param.getGmtReport());
            record.setMemberRechargeBalance(param.getMemberRechargeBalance());
            record.setMemberRechargePoint(param.getMemberRechargePoint());
            record.setMerchantRechargeBalance(param.getMerchantRechargeBalance());
            record.setMerchantRechargePoint(param.getMerchantRechargePoint());
            record.setTotalRechargeBalance(param.getTotalRechargeBalance());
            record.setTotalRechargePoint(param.getTotalRechargePoint());
            record.setProvinceId(param.getProvinceId());
            record.setCityId(param.getCityId());
            record.setAreaId(param.getAreaId());
            reportAreaRechargeMonthDOMapper.insertSelective(record);
        }
    }

    @Override
    public List<ReportAreaRechargeDailyBO> getDailyList(String reportDate) {
        ReportAreaRechargeDailyDOExample example = new ReportAreaRechargeDailyDOExample();
        Date begin = DateUtil.formatDate(reportDate + "-01", "yyyy-MM-dd");
        Date end = DateUtil.getLastDayOfMonth(begin);
        example.createCriteria().andGmtReportBetween(begin, end);
        List<ReportAreaRechargeDailyDO> rntList = reportAreaRechargeDailyDOMapper.selectByExample(example);
        List<ReportAreaRechargeDailyBO> boList = new ArrayList<>();
        for (ReportAreaRechargeDailyDO rdo : rntList) {
            ReportAreaRechargeDailyBO bo = new ReportAreaRechargeDailyBO();
            bo.setGmtCreate(rdo.getGmtCreate());
            bo.setGmtReport(rdo.getGmtReport());
            bo.setId(rdo.getId());
            bo.setMemberRechargeBalance(rdo.getMemberRechargeBalance());
            bo.setMemberRechargePoint(rdo.getMemberRechargePoint());
            bo.setMerchantRechargeBalance(rdo.getMerchantRechargeBalance());
            bo.setMerchantRechargePoint(rdo.getMerchantRechargePoint());
            bo.setTotalRechargeBalance(rdo.getTotalRechargeBalance());
            bo.setTotalRechargePoint(rdo.getTotalRechargePoint());
            bo.setProvinceId(rdo.getProvinceId());
            bo.setCityId(rdo.getCityId());
            bo.setAreaId(rdo.getAreaId());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public void deleteDailyByReportDate(String reportDate) {
        ReportAreaRechargeDailyDOExample example = new ReportAreaRechargeDailyDOExample();
        example.createCriteria().andGmtReportEqualTo(DateUtil.formatDate(reportDate, "yyyy-MM-dd"));
        reportAreaRechargeDailyDOMapper.deleteByExample(example);
    }

    @Override
    public void deleteMonthByReportDate(String reportDate) {
        ReportAreaRechargeMonthDOExample example = new ReportAreaRechargeMonthDOExample();
        example.createCriteria().andGmtReportEqualTo(DateUtil.formatDate(reportDate, "yyyy-MM"));
        reportAreaRechargeMonthDOMapper.deleteByExample(example);
    }

    @Override
    public AgentAreaRechargeQReturnBO getAreaRechargeList(AgentReportParam param) {
        AgentAreaRechargeDailyMonthDOExtend doExtend;
        AgentAreaRechargeExampleExtend exampleExtend = new AgentAreaRechargeExampleExtend();
        String[] regionPaths = param.getRegionPath().split("/");
        if(DateUtil.checkDateFormat(param.getBeginTime(),"yyyy-MM-dd")){
            exampleExtend.setCityId(new Integer(regionPaths[1]));
            exampleExtend.setBeginTime(param.getBeginTime());
            exampleExtend.setEndTime(param.getEndTime());
            doExtend = reportAreaRechargeDailyDOMapperExtend.getAreaRechargeList(exampleExtend);
        } else {
            exampleExtend.setCityId(new Integer(regionPaths[1]));
            exampleExtend.setBeginTime(param.getBeginTime() + "-01");
            exampleExtend.setEndTime(param.getEndTime() + "-01");
            doExtend = reportAreaRechargeMonthDOMapperExtend.getAreaRechargeList(exampleExtend);
        }
        return ReportAreaRechargeConverter.convertBO(doExtend);
    }
}

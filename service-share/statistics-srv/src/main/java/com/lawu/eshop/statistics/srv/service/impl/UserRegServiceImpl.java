package com.lawu.eshop.statistics.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.statistics.param.UserRegAreaParam;
import com.lawu.eshop.statistics.param.UserRegParam;
import com.lawu.eshop.statistics.srv.bo.ReportUserRegAreaBO;
import com.lawu.eshop.statistics.srv.converter.ReportUserRegConverter;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportAreaUserRegMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserRegAreaDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserRegAreaDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportUserRegDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserRegDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportUserRegMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserRegMonthDOExample;
import com.lawu.eshop.statistics.srv.domain.extend.ReportNewDateDOView;
import com.lawu.eshop.statistics.srv.domain.extend.ReportUserRegDOView;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaUserRegDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportAreaUserRegMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportUserRegAreaDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportUserRegDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportUserRegMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.UserRegDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.UserRegService;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
@Service
public class UserRegServiceImpl implements UserRegService {

    @Autowired
    private ReportUserRegDailyDOMapper reportUserRegDailyDOMapper;

    @Autowired
    private ReportUserRegMonthDOMapper reportUserRegMonthDOMapper;

    @Autowired
    private ReportUserRegAreaDOMapper reportUserRegAreaDOMapper;

    @Autowired
    private UserRegDOMapperExtend userRegDOMapperExtend;

    @Autowired
    private ReportAreaUserRegDailyDOMapper reportAreaUserRegDailyDOMapper;

    @Autowired
    private ReportAreaUserRegMonthDOMapper reportAreaUserRegMonthDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRegDaily(Integer memberCount, Integer merchantCount) {
        ReportUserRegDailyDOExample example = new ReportUserRegDailyDOExample();
        example.createCriteria().andGmtReportEqualTo(DateUtil.getDayBefore(new Date()));
        reportUserRegDailyDOMapper.deleteByExample(example);

        ReportUserRegDailyDO reportUserRegDailyDO = new ReportUserRegDailyDO();
        reportUserRegDailyDO.setMemberCount(memberCount);
        reportUserRegDailyDO.setMerchantCount(merchantCount);
        reportUserRegDailyDO.setGmtReport(DateUtil.getDayBefore(new Date()));
        reportUserRegDailyDO.setGmtCreate(new Date());
        reportUserRegDailyDOMapper.insertSelective(reportUserRegDailyDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserRegMonth(Integer memberCount, Integer merchantCount) {
        ReportUserRegMonthDOExample example = new ReportUserRegMonthDOExample();
        example.createCriteria().andGmtReportEqualTo(DateUtil.getMonthBefore(new Date()));
        reportUserRegMonthDOMapper.deleteByExample(example);

        ReportUserRegMonthDO reportUserRegMonthDO = new ReportUserRegMonthDO();
        reportUserRegMonthDO.setMemberCount(memberCount);
        reportUserRegMonthDO.setMerchantCount(merchantCount);
        reportUserRegMonthDO.setGmtReport(DateUtil.getMonthBefore(new Date()));
        reportUserRegMonthDO.setGmtCreate(new Date());
        reportUserRegMonthDOMapper.insertSelective(reportUserRegMonthDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRegArea(UserRegAreaParam param) {
        ReportUserRegAreaDO reportUserRegAreaDO = new ReportUserRegAreaDO();
        reportUserRegAreaDO.setMemberCount(param.getMemberCount());
        reportUserRegAreaDO.setMerchantCount(param.getMerchantCount());
        reportUserRegAreaDO.setMerchantCommonCount(param.getMerchantCommonCount());
        reportUserRegAreaDO.setMerchantEntityCount(param.getMerchantEntityCount());
        reportUserRegAreaDO.setGmtUpdate(new Date());
        ReportUserRegAreaDOExample example = new ReportUserRegAreaDOExample();
        example.createCriteria().andCityIdEqualTo(param.getCityId());
        reportUserRegAreaDOMapper.updateByExampleSelective(reportUserRegAreaDO, example);
    }

    @Override
    public List<ReportUserRegDOView> getReportUserRegDaily(UserRegParam param) {
        return userRegDOMapperExtend.getReportUserRegDaily(param);
    }

    @Override
    public List<ReportUserRegDOView> getReportUserRegMonth(UserRegParam param) {
        return userRegDOMapperExtend.getReportUserRegMonth(param);
    }

    @Override
    public List<ReportUserRegAreaBO> getReportUserRegArea() {
        ReportUserRegAreaDOExample example = new ReportUserRegAreaDOExample();
        ReportUserRegAreaDOExample.Criteria criteria = example.createCriteria();

        ReportUserRegAreaDOExample.Criteria memberCriteria = example.or();
        memberCriteria.andMemberCountGreaterThan(0);
        memberCriteria.getAllCriteria().addAll(criteria.getAllCriteria());

        ReportUserRegAreaDOExample.Criteria merchantCriteria = example.or();
        merchantCriteria.andMerchantCountGreaterThan(0);
        merchantCriteria.getAllCriteria().addAll(criteria.getAllCriteria());

        List<ReportUserRegAreaDO> regAreaDOList = reportUserRegAreaDOMapper.selectByExample(example);
        return ReportUserRegConverter.convertAreaBO(regAreaDOList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserRegAreaDaily(UserRegAreaParam userRegAreaParam) {
        ReportAreaUserRegDailyDO userRegDailyDO = new ReportAreaUserRegDailyDO();
        userRegDailyDO.setCityId(userRegAreaParam.getCityId());
        userRegDailyDO.setCityName(userRegAreaParam.getName());
        userRegDailyDO.setMemberCount(userRegAreaParam.getMemberCount());
        userRegDailyDO.setMerchantEntityCount(userRegAreaParam.getMerchantEntityCount());
        userRegDailyDO.setMerchantNormalCount(userRegAreaParam.getMerchantCount());
        userRegDailyDO.setMerchantCount(userRegAreaParam.getMerchantCount());
        userRegDailyDO.setGmtCreate(new Date());
        userRegDailyDO.setGmtReport(DateUtil.getDayBefore(new Date()));
        reportAreaUserRegDailyDOMapper.insertSelective(userRegDailyDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserRegAreaMonth(UserRegAreaParam userRegAreaParam) {
        ReportAreaUserRegMonthDO userRegMonthDO = new ReportAreaUserRegMonthDO();
        userRegMonthDO.setCityId(userRegAreaParam.getCityId());
        userRegMonthDO.setCityName(userRegAreaParam.getName());
        userRegMonthDO.setMemberCount(userRegAreaParam.getMemberCount());
        userRegMonthDO.setMerchantEntityCount(userRegAreaParam.getMerchantEntityCount());
        userRegMonthDO.setMerchantNormalCount(userRegAreaParam.getMerchantCount());
        userRegMonthDO.setMerchantCount(userRegAreaParam.getMerchantCount());
        userRegMonthDO.setGmtCreate(new Date());
        userRegMonthDO.setGmtReport(DateUtil.getMonthBefore(new Date()));
        reportAreaUserRegMonthDOMapper.insertSelective(userRegMonthDO);
    }
    
    @Override
    public ReportNewDateDOView getReportDateUserRegDaily() {
        return userRegDOMapperExtend.getReportDateUserRegDaily();
    }
    
    @Override
    public ReportNewDateDOView getReportDateUserRegMonth() {
        return userRegDOMapperExtend.getReportDateUserRegMonth();
    }
}

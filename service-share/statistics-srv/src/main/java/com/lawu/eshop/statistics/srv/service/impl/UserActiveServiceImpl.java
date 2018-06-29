package com.lawu.eshop.statistics.srv.service.impl;

import com.lawu.eshop.statistics.srv.bo.UserActiveBO;
import com.lawu.eshop.statistics.srv.converter.UserActiveConverter;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveDailyDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveDailyDOExample;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveMonthDO;
import com.lawu.eshop.statistics.srv.domain.ReportUserActiveMonthDOExample;
import com.lawu.eshop.statistics.srv.domain.extend.UserActiveDOView;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveDailyDOMapper;
import com.lawu.eshop.statistics.srv.mapper.ReportUserActiveMonthDOMapper;
import com.lawu.eshop.statistics.srv.mapper.extend.UserActiveDOMapperExtend;
import com.lawu.eshop.statistics.srv.service.UserActiveService;
import com.lawu.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
@Service
public class UserActiveServiceImpl implements UserActiveService {

    @Autowired
    private UserActiveDOMapperExtend userActiveDOMapperExtend;
    
    @Autowired
    private ReportUserActiveDailyDOMapper reportUserActiveDailyDOMapper;
    
    @Autowired
    private ReportUserActiveMonthDOMapper reportUserActiveMonthDOMapper;
    
    @Override
    public Integer collectionMemberActiveDaily(Date date) {
        Integer count = userActiveDOMapperExtend.collectionMemberActiveDaily(date);
        return count;
    }

    @Override
    public Integer collectionMerchantActiveDaily(Date date) {
        Integer count = userActiveDOMapperExtend.collectionMerchantActiveDaily(date);
        return count;
    }

    @Override
    public Integer collectionMemberActiveMonth(Date date) {
        Integer count = userActiveDOMapperExtend.collectionMemberActiveMonth(date);
        return count;
    }

    @Override
    public Integer collectionMerchantActiveMonth(Date date) {
        Integer count = userActiveDOMapperExtend.collectionMerchantActiveMonth(date);
        return count;
    }

    @Override
    public List<UserActiveBO> collectionMemberActiveAreaDaily(String reportDate) {
        Date time = DateUtil.getDateFormat(reportDate);
        List<UserActiveDOView> userActiveDOViews = userActiveDOMapperExtend.collectionMemberActiveAreaDaily(time);
        List<UserActiveBO> userActiveBOS = UserActiveConverter.coverBOS(userActiveDOViews);
        return userActiveBOS;
    }

    @Override
    public List<UserActiveBO> collectionMerchantActiveAreaDaily(String reportDate) {
        Date time = DateUtil.getDateFormat(reportDate);
        List<UserActiveDOView>  userActiveDOViews = userActiveDOMapperExtend.collectionMerchantActiveAreaDaily(time);
        List<UserActiveBO> userActiveBOS = UserActiveConverter.coverBOS(userActiveDOViews);
        return userActiveBOS;
    }

    @Override
    public List<UserActiveBO> collectionMemberActiveAreaMonth(String reportDate) {
    	Date time = DateUtil.getDateFormat(reportDate);
        List<UserActiveDOView>  userActiveDOViews = userActiveDOMapperExtend.collectionMemberActiveAreaMonth(time);
        List<UserActiveBO> userActiveBOS = UserActiveConverter.coverBOS(userActiveDOViews);
        return userActiveBOS;
    }

    @Override
    public List<UserActiveBO> collectionMerchantActiveAreaMonth(String reportDate) {
    	Date time = DateUtil.getDateFormat(reportDate);
        List<UserActiveDOView>  userActiveDOViews = userActiveDOMapperExtend.collectionMerchantActiveAreaMonth(time);
        List<UserActiveBO> userActiveBOS = UserActiveConverter.coverBOS(userActiveDOViews);
        return userActiveBOS;
    }

	@Override
	public Date getMemberActiveDaily() {
		ReportUserActiveDailyDOExample example = new ReportUserActiveDailyDOExample();
		example.setOrderByClause("gmt_report desc");
		List<ReportUserActiveDailyDO> list = reportUserActiveDailyDOMapper.selectByExample(example);
		if(list != null && !list.isEmpty())
			return list.get(0).getGmtReport();
		return null;
	}

	@Override
	public Date getMemberActiveMonth() {
		ReportUserActiveMonthDOExample example = new ReportUserActiveMonthDOExample();
		example.setOrderByClause("gmt_report desc");
		List<ReportUserActiveMonthDO> list = reportUserActiveMonthDOMapper.selectByExample(example);
		if(list != null && !list.isEmpty())
			return list.get(0).getGmtReport();
		return null;
	}
}

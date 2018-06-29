package com.lawu.eshop.jobs.service.impl;

import com.lawu.eshop.jobs.service.CollectionUserActiveService;
import com.lawu.eshop.jobs.service.UserActiveService;
import com.lawu.eshop.jobs.service.UserActiveStatisticsService;
import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
@Service
public class UserActiveStatisticsServiceImpl implements UserActiveStatisticsService{

    @Autowired
    private CollectionUserActiveService collectionUserActiveService;
    @Autowired
    private UserActiveService userActiveService;
    @Override
    public void executeCollectionUserActiveDaily() {
    	Date lastDay = userActiveService.getMemberActiveDaily();
    	if(lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getSomeDay(new Date(), -2))) {
			int betweenDay = DateUtil.daysOfTwo(lastDay);
			for(int i = 1; i < betweenDay; i++) {
				String reportDate = DateUtil.getDateFormat(DateUtil.getSomeDay(lastDay, i),"yyyy-MM-dd");
				Result<Integer> memberResult = collectionUserActiveService.collectionMemberActiveDaily(reportDate);
			    Result<Integer> merchantResult = collectionUserActiveService.collectionMerchantActiveDaily(reportDate);
			    userActiveService.saveUserActiveDaily(memberResult.getModel(), merchantResult.getModel(), reportDate);
			}
    	} else {
    		String reportDate = DateUtil.getDateFormat(DateUtil.getSomeDay(new Date(),-1),"yyyy-MM-dd");
			Result<Integer> memberResult = collectionUserActiveService.collectionMemberActiveDaily(reportDate);
		    Result<Integer> merchantResult = collectionUserActiveService.collectionMerchantActiveDaily(reportDate);
		    userActiveService.saveUserActiveDaily(memberResult.getModel(), merchantResult.getModel(), reportDate);
    	}
    }

    @Override
    public void executeCollectionUserActiveMonth() {
    	Date lastDay = userActiveService.getMemberActiveMonth();
    	if(lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(DateUtil.getMonthBefore(new Date()))))) {
			int i = 0;
			while(true) {
				//统计lastday的后面几个月直到上个月 
				Date date = DateUtil.nextMonthFirstDate(lastDay, 2 + i);	
				if(DateUtils.isSameDay(date, DateUtil.getFirstDayOfMonth(DateUtil.nextMonthFirstDate(new Date(), 1)))) {
					break;
				}
				i++;
				Result<Integer> memberResult = collectionUserActiveService.collectionMemberActiveMonth(DateUtil.getDateFormat(date));
		        Result<Integer> merchantResult = collectionUserActiveService.collectionMerchantActiveMonth(DateUtil.getDateFormat(date));
		        userActiveService.saveUserActiveMonth(memberResult.getModel(), merchantResult.getModel(), DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(DateUtil.nextMonthFirstDate(date, -1))));
			}
    	} else {
    		Result<Integer> memberResult = collectionUserActiveService.collectionMemberActiveMonth(DateUtil.getDate());
            Result<Integer> merchantResult = collectionUserActiveService.collectionMerchantActiveMonth(DateUtil.getDate());
            userActiveService.saveUserActiveMonth(memberResult.getModel(), merchantResult.getModel(), DateUtil.getDateFormat(DateUtil.getSomeDay(new Date(), -1)));
    	}
    }

    @Override
    public void executeCollectionUserActiveAreaDaily() {
    	Date lastDay = userActiveService.getAreaDaily();
    	if(lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getSomeDay(new Date(), -2))) {
    		int betweenDay = DateUtil.daysOfTwo(lastDay);
			for(int i = 1; i < betweenDay; i++) {
				String reportDate = DateUtil.getDateFormat(DateUtil.getSomeDay(lastDay, i),"yyyy-MM-dd");
				//查询用户活跃总数并统计
		        Result<List<UserActiveDTO>> memberResult = collectionUserActiveService.collectionMemberActiveAreaDaily(reportDate);
		        if(memberResult.getModel() != null && !memberResult.getModel().isEmpty()){
		            userActiveService.saveUserActiveAreaDaily(memberResult.getModel());
		        }
		        //查询商家活跃总数
		        Result<List<UserActiveDTO>> merchantResult = collectionUserActiveService.collectionMerchantActiveAreaDaily(reportDate);
		        //更新统计商家总数
		        if(merchantResult.getModel() != null && !merchantResult.getModel().isEmpty()){
		            userActiveService.saveMerchantActiveAreaDaily(merchantResult.getModel());
		        }
			}
    	} else {
    		//查询用户活跃总数并统计
            Result<List<UserActiveDTO>> memberResult = collectionUserActiveService.collectionMemberActiveAreaDaily(DateUtil.getDateFormat(DateUtil.getSomeDay(new Date(), -1)));
            if(memberResult.getModel() != null && !memberResult.getModel().isEmpty()){
                userActiveService.saveUserActiveAreaDaily(memberResult.getModel());
            }
            //查询商家活跃总数
            Result<List<UserActiveDTO>> merchantResult = collectionUserActiveService.collectionMerchantActiveAreaDaily(DateUtil.getDateFormat(DateUtil.getSomeDay(new Date(), -1)));
            //更新统计商家总数
            if(merchantResult.getModel() != null && !merchantResult.getModel().isEmpty()){
                userActiveService.saveMerchantActiveAreaDaily(merchantResult.getModel());
            }
    	}
    }

    @Override
    public void executeCollectionUserActiveAreaMonth() {
    	Date lastDay = userActiveService.getAreaMonth();
    	if(lastDay != null && !DateUtils.isSameDay(lastDay, DateUtil.getFirstDayOfMonth(DateUtil.getMonthBefore(DateUtil.getMonthBefore(new Date()))))) {
			int i = 0;
			while(true) {
				//统计lastday的后面几个月直到上个月 
				Date date = DateUtil.nextMonthFirstDate(lastDay, 2 + i);	
				if(DateUtils.isSameDay(date, DateUtil.getFirstDayOfMonth(DateUtil.getSomeDay(new Date(), 1)))) {
					break;
				}
				i++;
				//查询用户活跃总数并统计
		        Result<List<UserActiveDTO>> memberResult = collectionUserActiveService.collectionMemberActiveAreaMonth(DateUtil.getDateFormat(date));
		        if(memberResult.getModel() != null && !memberResult.getModel().isEmpty()){
		            userActiveService.saveUserActiveAreaMonth(memberResult.getModel());
		        }
		        //查询商家活跃总数
		        Result<List<UserActiveDTO>> merchantResult = collectionUserActiveService.collectionMerchantActiveAreaMonth(DateUtil.getDateFormat(date));
		        //更新统计商家总数
		        if(merchantResult.getModel() != null && !merchantResult.getModel().isEmpty()){
		            userActiveService.saveMerchantActiveAreaMonth(merchantResult.getModel());
		        }
			}
    	} else {
    		//查询用户活跃总数并统计
            Result<List<UserActiveDTO>> memberResult = collectionUserActiveService.collectionMemberActiveAreaMonth(DateUtil.getDate());
            if(memberResult.getModel() != null && !memberResult.getModel().isEmpty()){
                userActiveService.saveUserActiveAreaMonth(memberResult.getModel());
            }
            //查询商家活跃总数
            Result<List<UserActiveDTO>> merchantResult = collectionUserActiveService.collectionMerchantActiveAreaMonth(DateUtil.getDate());
            //更新统计商家总数
            if(merchantResult.getModel() != null && !merchantResult.getModel().isEmpty()){
                userActiveService.saveMerchantActiveAreaMonth(merchantResult.getModel());
            }
    	}
    }
}

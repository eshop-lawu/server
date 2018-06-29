package com.lawu.eshop.cache.srv.service;

import java.util.Map;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author zhangyong
 * @date 2017/7/3.
 */
public interface UserVisitService {

    void addUserVisitCount(String userNum, String nowTimeStr, Long userId, UserTypeEnum type);

    Map<String, String> getVisitRecords(Integer currentPage, String time, Byte type);

    void delVisitRecords(String time);

    /**
     * 用户上次访问接口时间
     *
     * @param userId
     * @param type
     * @author meishuquan
     */
    @Deprecated
    void addUserVisitTime(Long userId, UserTypeEnum type);

    /**
     * 用户时间周期内访问接口频率
     *
     * @param userId
     * @param type
     * @param expireTime
     * @author meishuquan
     */
    void addUserVisitFrequency(Long userId, UserTypeEnum type, Integer expireTime);

    /**
     * 查询用户上次访问接口时间
     *
     * @param userId
     * @param type
     * @return
     * @author meishuquan
     */
    @Deprecated
    Long getUserVisitTime(Long userId, UserTypeEnum type);

    /**
     * 查询用户时间周期内访问接口频率
     *
     * @param userId
     * @param type
     * @return
     * @author meishuquan
     */
    Integer getUserVisitFrequency(Long userId, UserTypeEnum type);

    /**
     * 删除用户时间周期内访问接口频率
     *
     * @param userId
     * @param type
     * @return
     * @author meishuquan
     */
    void delUserVisitFrequency(Long userId, UserTypeEnum type);

    /**
     * 保存用户访问接口次数和时间
     *
     * @param userNum
     * @param nowTimeStr
     * @param userId
     * @param type
     * @param currTime
     * @return
     * @author meishuquan
     */
    Long addUserVisitCountAndTime(String userNum, String nowTimeStr, Long userId, UserTypeEnum type, String currTime);

    /**
     * 获取当天访问接口次数
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    Integer getDailyVisitTimes(String userNum);

}

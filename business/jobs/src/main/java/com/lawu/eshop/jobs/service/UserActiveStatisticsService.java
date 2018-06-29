package com.lawu.eshop.jobs.service;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
public interface UserActiveStatisticsService {

    void executeCollectionUserActiveDaily();

    void executeCollectionUserActiveMonth();

    void executeCollectionUserActiveAreaDaily();

    void executeCollectionUserActiveAreaMonth();
}

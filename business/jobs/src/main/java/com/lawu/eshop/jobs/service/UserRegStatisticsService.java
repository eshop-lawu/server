package com.lawu.eshop.jobs.service;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
public interface UserRegStatisticsService {

    void executeCollectionUserRegDaily();

    void executeCollectionUserRegMonth();

    void executeCollectionUserRegArea();

    void executeCollectionUserRegAreaDaily();

    void executeCollectionUserRegAreaMonth();
}

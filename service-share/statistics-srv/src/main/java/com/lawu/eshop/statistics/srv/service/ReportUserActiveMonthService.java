package com.lawu.eshop.statistics.srv.service;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public interface ReportUserActiveMonthService {
    void saveUserActiveMonth(Integer memberCount, Integer merchantCount, String reportDate);
}

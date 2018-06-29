package com.lawu.eshop.jobs.service;

/**
 * @author zhangyong
 * @date 2018/3/1.
 */
public interface AbnormalActivityMemberService {

    void executeUpdateActivitySuspectedStatus();

    void executeSignAbnormalMemberJob();
}

package com.lawu.eshop.synchronization.lock.impl;

import com.lawu.eshop.synchronization.lock.impl.LockConstant.LockModule;

/**
 * 分布式事务锁接口
 * 
 * @author jiangxinjun
 * @createDate 2017年12月20日
 * @updateDate 2017年12月20日
 */
public interface LockService {

    /**
     * 判断锁是否存在 如果存在返回false 如果不存在，生成一个锁，并且返回true
     * 
     * @param lockModule
     *            锁的模块
     * @param lockKey
     *            锁的名称
     * @param relatedId
     *            关联id
     * @return
     * @author Sunny
     * @date 2017年5月31日
     */
    boolean tryLock(LockModule lockModule, String lockKey, Long relatedId);

    /**
     * 判断锁是否存在 如果存在返回false 如果不存在，生成一个锁，并且返回true
     * 
     * @param lockModule
     *            锁的模块
     * @param lockKey
     *            锁的名称
     * @return
     * @author Sunny
     * @date 2017年5月31日
     */
    boolean tryLock(LockModule lockModule, String lockKey);

    /**
     * 判断锁是否存在 如果存在返回false 如果不存在，生成一个锁，并且返回true
     * 
     * @param lockModule
     *            锁的模块
     * @param lockKey
     *            锁的名称
     * @param waitTime
     *            等待时间
     * @param leaseTime
     *            释放时间
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月5日
     * @updateDate 2018年1月5日
     */
    boolean tryLock(long waitTime, long leaseTime, LockModule lockModule, String lockKey);

    /**
     * 释放锁
     * 
     * @param lockModule
     *            锁的模块
     * @param lockKey
     *            锁的名称
     * @param relatedId
     *            关联id
     * @author Sunny
     * @date 2017年5月31日
     */
    void unLock(LockModule lockModule, String lockKey, Long relatedId);

    /**
     * 释放锁
     * 
     * @param lockModule
     *            锁的模块
     * @param lockKey
     *            锁的名称
     * @author Sunny
     * @date 2017年5月31日
     */
    void unLock(LockModule lockModule, String lockKey);
}

package com.lawu.eshop.synchronization.lock.impl;

import org.springframework.stereotype.Service;

import com.lawu.eshop.synchronization.lock.impl.LockConstant.LockModule;
import com.lawu.synchronization.lock.service.AbstractLockService;

/**
 * 分布式事务锁接口实现类
 * 
 * @author jiangxinjun
 * @createDate 2017年12月20日
 * @updateDate 2017年12月20日
 */
@Service
public class LockServiceImpl extends AbstractLockService implements LockService {
	
	/**
	 * 判断锁是否存在
	 * 如果存在返回false
	 * 如果不存在，生成一个锁，并且返回true
	 * 
	 * @param lockModule 锁的模块
	 * @param lockKey 锁的名称
	 * @param relatedId 关联id
	 * @return
	 * @author Sunny
	 * @date 2017年5月31日
	 */
	public boolean tryLock(LockModule lockModule, String lockKey, Long relatedId) {
		// 拼接锁名
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(lockModule.getName()).append("_").append(lockKey).append("_").append(relatedId);
		return tryLock(stringBuilder.toString());
	}
	
	/**
	 * 判断锁是否存在
	 * 如果存在返回false
	 * 如果不存在，生成一个锁，并且返回true
	 * 
	 * @param lockModule 锁的模块
	 * @param lockKey 锁的名称
	 * @return
	 * @author Sunny
	 * @date 2017年5月31日
	 */
	public boolean tryLock(LockModule lockModule, String lockKey) {
		// 拼接锁名
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(lockModule.getName()).append("_").append(lockKey);
		return tryLock(stringBuilder.toString());
	}
	
    @Override
    public boolean tryLock(long waitTime, long leaseTime, LockModule lockModule, String lockKey) {
        // 拼接锁名
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lockModule.getName()).append("_").append(lockKey);
        return tryLock(waitTime, leaseTime, stringBuilder.toString());
    }
	
	/**
	 * 释放锁
	 * 
	 * @param lockModule 锁的模块
	 * @param lockKey 锁的名称
	 * @param relatedId 关联id
	 * @author Sunny
	 * @date 2017年5月31日
	 */
	public void unLock(LockModule lockModule, String lockKey, Long relatedId) {
		// 拼接锁名
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(lockModule.getName()).append("_").append(lockKey).append("_").append(relatedId);
		unLock(stringBuilder.toString());
	}
	
	/**
	 * 释放锁
	 * 
	 * @param lockModule 锁的模块
	 * @param lockKey 锁的名称
	 * @author Sunny
	 * @date 2017年5月31日
	 */
	public void unLock(LockModule lockModule, String lockKey) {
		// 拼接锁名
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(lockModule.getName()).append("_").append(lockKey);
		unLock(stringBuilder.toString());
	}
	
}

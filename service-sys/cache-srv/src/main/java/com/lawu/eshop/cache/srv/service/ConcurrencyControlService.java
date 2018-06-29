package com.lawu.eshop.cache.srv.service;

import java.util.concurrent.TimeUnit;

/**
 * 并发控制接口
 * 
 * @author jiangxinjun
 * @createDate 2018年3月24日
 * @updateDate 2018年3月24日
 */
public interface ConcurrencyControlService {
    
    /**
     * 递增并返回之后的值
     * @param key
     * @param timeout
     * @param unit
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月24日
     * @updateDate 2018年3月24日
     */
    Long incrementAndGet(String key, long timeout, TimeUnit unit);
    
    /**
     * 递减并返回之后的值
     * @param key
     * @param timeout
     * @param unit
     * @author jiangxinjun
     * @createDate 2018年3月24日
     * @updateDate 2018年3月24日
     */
    Long decrementAndGet(String key, long timeout, TimeUnit unit);
    
    /**
     * 设置一个新的值
     * @param key
     * @param newValue
     * @param timeout
     * @param unit
     * @author jiangxinjun
     * @createDate 2018年3月30日
     * @updateDate 2018年3月30日
     */
    void set(String key, long newValue, long timeout, TimeUnit unit);
    
    /**
     * 删除这个key
     * @param key
     * @author jiangxinjun
     * @createDate 2018年3月30日
     * @updateDate 2018年3月30日
     */
    void delete(String key);
    
    /**
     * 获取这个key对应的值
     * @param key
     * @return
     * @author jiangxinjun
     * @createDate 2018年4月2日
     * @updateDate 2018年4月2日
     */
    Long get(String key);
}

package com.lawu.eshop.ad.constants;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;


public class AdPraiseConfig {
	
	
	 /**
     * 核心线程数，指保留的线程池大小
     */
	@Value(value="${core_pool_size}")
	public static Integer CORE_POOL_SIZE;
    
    /**
     *  指的是线程池的最大数
     */
	@Value(value="${maximum_pool_size}")
	public  static int MAXIMUM_POLL_SIZE;
    
    /**
     * 指的是空闲线程结束的超时时间
     */ 
	@Value(value="${keep_alive_time}")
	public static Integer KEEP_ALIVE_TIME;
	

}

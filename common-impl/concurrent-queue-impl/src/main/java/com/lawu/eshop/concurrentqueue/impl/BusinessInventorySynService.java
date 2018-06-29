package com.lawu.eshop.concurrentqueue.impl;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * 秒杀库存同步缓存服务接口
 * 
 * @author jiangxinjun
 * @createDate 2017年11月30日
 * @updateDate 2017年11月30日
 */
@FeignClient(value = "cache-srv", path = "businessInventorySyn/")
public interface BusinessInventorySynService {
	
    /**
     * 从缓存中获取剩余库存
     * @param businessKey
     * @param id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月30日
     * @updateDate 2017年11月30日
     */
    @RequestMapping(value = "inventory", method = RequestMethod.GET)
    Result<Integer> getInventory(@RequestParam("businessKey") String businessKey, @RequestParam("id") Object id);

    /**
     * 更新缓存中的库存
     * @param businessKey
     * @param id
     * @param inventory
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月30日
     * @updateDate 2017年11月30日
     */
    @RequestMapping(value = "setInventory", method = RequestMethod.PUT)
    Result<?> setInventory(@RequestParam("businessKey") String businessKey, @RequestParam("id") Object id, @RequestParam("inventory") Integer inventory);

    /**
     * 缓存中的库存量减一
     * @param businessKey
     * @param id
     * @param inventory
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月30日
     * @updateDate 2017年11月30日
     */
    @RequestMapping(value = "decreaseInventory", method = RequestMethod.PUT)
    Result<Integer> decreaseInventory(@RequestParam("businessKey") String businessKey, @RequestParam("id") Object id);

    /**
     * 缓存中的库存量加一
     * 
     * @param businessKey
     * @param id
     * @author jiangxinjun
     * @createDate 2017年11月30日
     * @updateDate 2017年11月30日
     */
    @RequestMapping(value = "increaseInventory", method = RequestMethod.PUT)
    Result<?> increaseInventory(@RequestParam("businessKey") String businessKey, @RequestParam("id") Object id);
    
    /**
     * 删除缓存中的库存key
     * 
     * @param businessKey
     * @param id
     * @author jiangxinjun
     * @createDate 2017年12月21日
     * @updateDate 2017年12月21日
     */
    @RequestMapping(value = "clearInventory", method = RequestMethod.PUT)
    Result<?> clearInventory(@RequestParam("businessKey") String businessKey, @RequestParam("id") Object id);
}

package com.lawu.eshop.concurrentqueue.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.concurrentqueue.bizctrl.AbstractBusinessInventorySynService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.web.Result;

/**
 * 
 * @author jiangxinjun
 * @createDate 2017年11月30日
 * @updateDate 2017年11月30日
 */
@Service
public class BusinessInventorySynServiceImpl extends AbstractBusinessInventorySynService {

    @Autowired
    private BusinessInventorySynService businessInventorySynService;
    
    @Override
    public Integer getInventoryFromCache(String businessKey, Object id) {
        Result<Integer> result = businessInventorySynService.getInventory(businessKey, id);
        if (result.getRet() != ResultCode.SUCCESS) {
            return null;
        }
        return result.getModel();
    }

    @Override
    public void setInventoryToCache(String businessKey, Object id, Integer inventory) {
        businessInventorySynService.setInventory(businessKey, id, inventory);
    }

    @Override
    public Integer decreaseInventoryToCache(String businessKey, Object id) {
        Result<Integer> result = businessInventorySynService.decreaseInventory(businessKey, id);
        if (result.getRet() != ResultCode.SUCCESS) {
            return null;
        }
        return result.getModel();
    }

    @Override
    public void increaseInventoryToCache(String businessKey, Object id) {
        businessInventorySynService.increaseInventory(businessKey, id);
    }
}

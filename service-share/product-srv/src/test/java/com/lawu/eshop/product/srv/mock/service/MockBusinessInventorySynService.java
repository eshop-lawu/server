package com.lawu.eshop.product.srv.mock.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lawu.eshop.concurrentqueue.impl.BusinessInventorySynService;
import com.lawu.framework.web.Result;

@Primary
@Service
public class MockBusinessInventorySynService implements BusinessInventorySynService {

    @Override
    public Result<Integer> getInventory(String businessKey, Object id) {
        return null;
    }

    @Override
    public Result<?> setInventory(String businessKey, Object id, Integer inventory) {
        return null;
    }

    @Override
    public Result<Integer> decreaseInventory(String businessKey, Object id) {
        return null;
    }

    @Override
    public Result<?> increaseInventory(String businessKey, Object id) {
        return null;
    }

    @Override
    public Result<?> clearInventory(String businessKey, Object id) {
        return null;
    }
}

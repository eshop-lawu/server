package com.lawu.eshop.idworker.client.impl;

import java.util.ArrayList;
import java.util.List;

import com.lawu.idworker.client.service.IdWorkerService;
import com.lawu.utils.RandomUtil;

/**
 * 
 * @author jiangxinjun
 * @date 2017年10月26日
 */
public class MockIdWorkerServiceImpl implements IdWorkerService {
    
    /**
     * 自动生成18位编号
     * @return
     * @author jiangxinjun
     * @date 2017年10月24日
     */
    @Override
    public String generate() {
        return RandomUtil.getRandomString(1, 18);
    }
    
    @Override
    public List<String> batchGenerate() {
        List<String> rtn = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            rtn.add(generate());
        }
        return rtn;
    }
}

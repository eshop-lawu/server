package com.lawu.eshop.user.srv.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.lawu.idworker.client.service.IdWorkerService;
import com.lawu.utils.RandomUtil;

/**
 * 
 * @author jiangxinjun
 * @date 2017年10月26日
 */
@Primary
@Service
public class MockIdWorkerService implements IdWorkerService {
    
    /**
     * 自动生成18位编号
     * @return
     * @author jiangxinjun
     * @date 2017年10月24日
     */
    public String generate() {
        return RandomUtil.getRandomString(1, 18);
    }
    
    public List<String> batchGenerate() {
        List<String> rtn = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            rtn.add(generate());
        }
        return rtn;
    }
}

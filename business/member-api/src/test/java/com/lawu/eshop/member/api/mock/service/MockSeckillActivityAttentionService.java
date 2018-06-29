package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawu.eshop.member.api.service.SeckillActivityAttentionService;
import com.lawu.eshop.product.param.SeckillActivityProductAttentionParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockSeckillActivityAttentionService extends BaseController implements SeckillActivityAttentionService {

    @Override
    public Result attention(@PathVariable("activityProductId") Long activityProductId, @RequestBody SeckillActivityProductAttentionParam param) {
        return null;
    }
}

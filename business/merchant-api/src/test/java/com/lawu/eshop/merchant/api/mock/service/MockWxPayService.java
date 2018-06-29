package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.WxPayService;
import com.lawu.eshop.property.param.ThirdPayDataParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockWxPayService extends BaseController implements WxPayService {
    @Override
    public Result getPrepayInfo(@RequestBody ThirdPayDataParam param) {
        SortedMap<Object, Object> map = new TreeMap<>();
        map.put("codeUrl", "test");
        return successCreated(map);
    }
}

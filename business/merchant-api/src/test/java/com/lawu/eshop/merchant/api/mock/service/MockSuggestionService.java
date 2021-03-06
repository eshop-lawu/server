package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.mall.param.SuggestionParam;
import com.lawu.eshop.merchant.api.service.SuggestionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockSuggestionService extends BaseController implements SuggestionService {
    @Override
    public Result save(@PathVariable("userNum") String userNum, @RequestBody SuggestionParam parm) {
        return successCreated();
    }
}

package com.lawu.eshop.activity.srv.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.lawu.eshop.activity.srv.servcie.PointLotteryNumService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/23.
 */
@Service
public class MockPointLotteryNumService extends BaseController implements PointLotteryNumService {

    @Override
    public Result<Long> getNewPointLotteryNum(@PathVariable("pointLotteryActivityId") Long pointLotteryActivityId) {
        return successGet(1L);
    }

    @Override
    public Result delRecommendFoodConsume(@PathVariable("pointLotteryActivityId") Long pointLotteryActivityId) {
        return successDelete();
    }
}

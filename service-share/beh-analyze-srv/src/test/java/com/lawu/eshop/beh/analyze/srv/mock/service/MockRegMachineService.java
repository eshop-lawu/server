package com.lawu.eshop.beh.analyze.srv.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.beh.analyze.srv.service.RegMachineService;
import com.lawu.eshop.cache.dto.AbnormalRedisCountDTO;
import com.lawu.eshop.cache.param.AbnormalInfoParam;
import com.lawu.eshop.cache.param.EarlyAbnormalParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/1/29.
 */
@Service
public class MockRegMachineService extends BaseController implements RegMachineService {
    @Override
    public Result addHfRegRedisRecord(@ModelAttribute AbnormalInfoParam infoParam) {
        return successCreated();
    }

    @Override
    public Result<AbnormalRedisCountDTO> getAbnormalCount(@RequestParam("userNum") String userNum) {
        return null;
    }

    @Override
    public Result addEarlyHfRedisRecord(@RequestBody EarlyAbnormalParam param) {
        return successCreated();
    }
}

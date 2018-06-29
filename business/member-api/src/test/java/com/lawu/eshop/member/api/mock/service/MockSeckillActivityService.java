package com.lawu.eshop.member.api.mock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawu.eshop.member.api.service.SeckillActivityService;
import com.lawu.eshop.product.dto.RecentlySeckillActivityDTO;
import com.lawu.eshop.product.dto.SeckillActivityThatDayDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockSeckillActivityService extends BaseController implements SeckillActivityService {

    @Override
    public Result<List<SeckillActivityThatDayDTO>> thatDayList() {
        return null;
    }

    @Override
    public Result<List<SeckillActivityThatDayDTO>> recentlyList() {
        return null;
    }

    @Override
    public Result<RecentlySeckillActivityDTO> recentlyActivity() {
        return null;
    }
}

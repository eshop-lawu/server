package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.LotteryInfoDTO;
import com.lawu.eshop.mall.dto.LotteryRecordDTO;
import com.lawu.eshop.mall.param.LotteryRecordParam;
import com.lawu.eshop.mall.query.LotteryRecordQuery;
import com.lawu.eshop.member.api.service.LotteryRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockLotteryRecordService extends BaseController implements LotteryRecordService {

    @Override
    public Result saveLotteryRecord(@ModelAttribute LotteryRecordParam param) {
        return null;
    }

    @Override
    public Result<List<LotteryInfoDTO>> listLotteryInfo() {
        List<LotteryInfoDTO> list = new ArrayList<>();
        return successGet(list);
    }

    @Override
    public Result<Page<LotteryRecordDTO>> listLotteryRecord(@ModelAttribute LotteryRecordQuery query) {
        Page<LotteryRecordDTO> page = new Page<>();
        return successGet(page);
    }

    @Override
    public Result<Boolean> lotteryRecord(@PathVariable("lotteryActivityId") Long lotteryActivityId, @RequestParam("userNum") String userNum) {
        return successGet(false);
    }
}

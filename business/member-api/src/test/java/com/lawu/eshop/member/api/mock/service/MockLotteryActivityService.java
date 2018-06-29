package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.mall.dto.LotteryActivityDTO;
import com.lawu.eshop.mall.query.LotteryActivityRealQuery;
import com.lawu.eshop.member.api.service.LotteryActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockLotteryActivityService extends BaseController implements LotteryActivityService {

    @Override
    public Result<Page<LotteryActivityDTO>> listLotteryActivity(@ModelAttribute LotteryActivityRealQuery query) {
        Page<LotteryActivityDTO> page = new Page<>();
        return successGet(page);
    }

    @Override
    public Result<LotteryActivityDTO> getLotteryActivityById(@PathVariable("id") Long id) {
        LotteryActivityDTO dto = new LotteryActivityDTO();
        dto.setGradeEnum(MemberGradeEnum.GOLD);
        return successGet(dto);
    }
}

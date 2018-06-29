package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.UserGradeService;
import com.lawu.eshop.user.dto.LotteryGradeDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockUserGradeService extends BaseController implements UserGradeService {

    @Override
    public Result<Integer> selectLotteryActivityPointByGradeValue(@RequestParam("gradeValue") Byte gradeValue) {
        Integer point = 10;
        return successGet(point);
    }

    @Override
    public Result<LotteryGradeDTO> getLotteryGradeInfo(@RequestParam Long userId, @RequestParam Byte gradeValue) {
        LotteryGradeDTO dto = new LotteryGradeDTO();
        dto.setLotteryActivityPoint(10);
        dto.setFreeLotteryCount(1);
        return successGet(dto);
    }
}

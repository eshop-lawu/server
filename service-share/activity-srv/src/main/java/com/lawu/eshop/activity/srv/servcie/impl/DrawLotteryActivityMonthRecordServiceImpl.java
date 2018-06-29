package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityMonthRecordDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityMonthRecordDOExample;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityMonthRecordDOMapper;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityMonthRecordService;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/1/26.
 */
@Service
public class DrawLotteryActivityMonthRecordServiceImpl implements DrawLotteryActivityMonthRecordService {

    @Autowired
    private DrawLotteryActivityMonthRecordDOMapper drawLotteryActivityMonthRecordDOMapper;

    @Override
    public Integer countMonthFreeLottery(String userNum) {
        String nowDate = DateUtil.getYearMonthDate();
        DrawLotteryActivityMonthRecordDOExample recordDOExample = new DrawLotteryActivityMonthRecordDOExample();
        recordDOExample.createCriteria().andUserNumEqualTo(userNum).andRecordDateEqualTo(nowDate);
        List<DrawLotteryActivityMonthRecordDO> monthRecordDOS = drawLotteryActivityMonthRecordDOMapper.selectByExample(recordDOExample);
        if (monthRecordDOS.isEmpty()) {
            return 0;
        }
        return monthRecordDOS.get(0).getFreeTimes();
    }
}

package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.LotteryGradeDTO;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
@FeignClient(value = "user-srv")
public interface UserGradeService {

    /**
     * 查询等级对应积分
     *
     * @param gradeValue
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "grade/selectLotteryActivityPointByGradeValue")
    Result<Integer> selectLotteryActivityPointByGradeValue(@RequestParam("gradeValue") Byte gradeValue);

    /**
     * 查询用户等级对应的免费抽奖次数和活动等级对应的积分
     *
     * @param userId
     * @param gradeValue
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "grade/getLotteryGradeInfo")
    Result<LotteryGradeDTO> getLotteryGradeInfo(@RequestParam("userId") Long userId, @RequestParam("gradeValue") Byte gradeValue);

}

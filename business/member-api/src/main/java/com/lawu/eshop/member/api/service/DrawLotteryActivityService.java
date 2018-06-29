package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.DrawLotteryActivityDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDetailDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityNoticeDTO;
import com.lawu.eshop.activity.query.DrawLotteryActivityQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@FeignClient(value = "activity-srv", path = "drawLotteryActivity/")
public interface DrawLotteryActivityService {

    /**
     * 抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listDrawLotteryActivity", method = RequestMethod.POST)
    Result<Page<DrawLotteryActivityDTO>> listDrawLotteryActivity(@RequestBody DrawLotteryActivityQuery query);

    /**
     * 抽奖活动详情
     *
     * @param id
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDrawLotteryActivityDetail/{id}", method = RequestMethod.GET)
    Result<DrawLotteryActivityDetailDTO> getDrawLotteryActivityDetail(@PathVariable("id") Long id, @RequestParam("userNum") String userNum);

    /**
     * 中奖公告列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listDrawLotteryActivityNotice", method = RequestMethod.POST)
    Result<Page<DrawLotteryActivityNoticeDTO>> listDrawLotteryActivityNotice(@RequestBody DrawLotteryActivityQuery query);

    /**
     * 根据抽奖活动id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDrawLotteryActivity/{id}", method = RequestMethod.GET)
    Result<DrawLotteryActivityDTO> getDrawLotteryActivity(@PathVariable("id") Long id);

}

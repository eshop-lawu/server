package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDTO;
import com.lawu.eshop.activity.param.DrawLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@FeignClient(value = "activity-srv", path = "drawLotteryActivity/")
public interface DrawLotteryActivityService {

    /**
     * 新增抽奖活动
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveDrawLotteryActivity", method = RequestMethod.POST)
    Result saveDrawLotteryActivity(@RequestBody DrawLotteryActivityParam param);

    /**
     * 运营平台抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorDrawLotteryActivity", method = RequestMethod.POST)
    Result<Page<DrawLotteryActivityDTO>> listOperatorDrawLotteryActivity(@RequestBody OperatorDrawLotteryActivityQuery query);

    /**
     * 更新抽奖活动状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateDrawLotteryActivityStatus/{id}", method = RequestMethod.PUT)
    Result updateDrawLotteryActivityStatus(@PathVariable("id") Long id, @RequestParam("statusEnum") DrawLotteryActivityStatusEnum statusEnum);

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

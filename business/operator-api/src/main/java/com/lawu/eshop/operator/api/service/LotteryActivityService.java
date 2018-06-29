package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.dto.LotteryActivityOperatorDTO;
import com.lawu.eshop.mall.param.LotteryActivityParam;
import com.lawu.eshop.mall.query.ListLotteryActivityQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
@FeignClient(value = "mall-srv")
public interface LotteryActivityService {

    /**
     * 运营平台查询抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "lotteryActivity/listOperatorLotteryActivity")
    Result<Page<LotteryActivityOperatorDTO>> listOperatorLotteryActivity(@ModelAttribute ListLotteryActivityQuery query);

    /**
     * 根据id更新活动状态
     *
     * @param id
     * @param statusEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.PUT, value = "lotteryActivity/updateLotteryActivityStatus/{id}")
    Result updateLotteryActivityStatusById(@PathVariable("id") Long id, @RequestParam("statusEnum") LotteryActivityStatusEnum statusEnum);

    /**
     * 根据id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.GET, value = "lotteryActivity/getOperatorLotteryActivity/{id}")
    Result<LotteryActivityOperatorDTO> getOperatorLotteryActivity(@PathVariable("id") Long id);

    /**
     * 新增抽奖活动
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "lotteryActivity/saveLotteryActivity")
    Result saveLotteryActivity(@RequestBody LotteryActivityParam param);

}

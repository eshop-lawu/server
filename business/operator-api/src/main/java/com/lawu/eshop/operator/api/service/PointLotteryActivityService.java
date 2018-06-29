package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.PointLotteryActivityOperatorDTO;
import com.lawu.eshop.activity.dto.PointLotteryRelateDTO;
import com.lawu.eshop.activity.param.GenerateBasicNumberParam;
import com.lawu.eshop.activity.param.PointLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorPointLotteryActivityQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 积分夺宝活动服务接口
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
@FeignClient(value = "activity-srv", path = "pointLotteryActivity/")
public interface PointLotteryActivityService {

    /**
     * 生成基础中奖号码
     * @param id 积分夺宝活动id
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @RequestMapping(value = "generateBasicNumber/{id}", method = RequestMethod.PUT)
    Result<Integer> generateBasicNumber(@PathVariable("id") Long id, @RequestBody GenerateBasicNumberParam param);
    
    /**
     * 保存中奖号码
     * @param id 积分夺宝活动id
     * @param prizeNumbers 中奖号码用','分隔
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveWinningNumber/{id}", method = RequestMethod.PUT)
    Result saveWinningNumber(@PathVariable("id") Long id, @RequestParam("prizeNumbers") String prizeNumbers);

    /**
     * 新增抽奖活动
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "savePointLotteryActivity", method = RequestMethod.POST)
    Result savePointLotteryActivity(@RequestBody PointLotteryActivityParam param);

    /**
     * 运营平台抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorPointLotteryActivity", method = RequestMethod.POST)
    Result<Page<PointLotteryActivityOperatorDTO>> listOperatorPointLotteryActivity(@RequestBody OperatorPointLotteryActivityQuery query);

    /**
     * 更新抽奖活动状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updatePointLotteryActivityStatus/{id}", method = RequestMethod.PUT)
    Result updatePointLotteryActivityStatus(@PathVariable("id") Long id, @RequestParam("statusEnum") PointLotteryActivityStatusEnum statusEnum);

    /**
     * 根据抽奖活动id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPointLotteryActivity/{id}", method = RequestMethod.GET)
    Result<PointLotteryActivityOperatorDTO> getPointLotteryActivity(@PathVariable("id") Long id);

    /**
     * 查询进行中和即将开始的积分抽奖活动
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listRelatePointLotteryActivity", method = RequestMethod.GET)
    Result<List<PointLotteryRelateDTO>> listRelatePointLotteryActivity();

}

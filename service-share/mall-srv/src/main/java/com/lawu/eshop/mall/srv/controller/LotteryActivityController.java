package com.lawu.eshop.mall.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.dto.LotteryActivityDTO;
import com.lawu.eshop.mall.dto.LotteryActivityOperatorDTO;
import com.lawu.eshop.mall.param.LotteryActivityParam;
import com.lawu.eshop.mall.query.ListLotteryActivityQuery;
import com.lawu.eshop.mall.query.LotteryActivityRealQuery;
import com.lawu.eshop.mall.srv.bo.LotteryActivityBO;
import com.lawu.eshop.mall.srv.converter.LotteryActivityConverter;
import com.lawu.eshop.mall.srv.service.LotteryActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
@RestController
@RequestMapping(value = "lotteryActivity/")
public class LotteryActivityController extends BaseController {

    @Autowired
    private LotteryActivityService lotteryActivityService;

    /**
     * 抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listLotteryActivity", method = RequestMethod.POST)
    public Result<Page<LotteryActivityDTO>> listLotteryActivity(@RequestBody LotteryActivityRealQuery query) {
        Page<LotteryActivityBO> activityBOPage = lotteryActivityService.listLotteryActivity(query);
        Page<LotteryActivityDTO> page = new Page<>();
        page.setCurrentPage(activityBOPage.getCurrentPage());
        page.setTotalCount(activityBOPage.getTotalCount());
        page.setRecords(LotteryActivityConverter.converDTOS(activityBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 根据id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getLotteryActivity/{id}", method = RequestMethod.GET)
    public Result<LotteryActivityDTO> getLotteryActivity(@PathVariable Long id) {
        LotteryActivityBO activityBO = lotteryActivityService.getLotteryActivityById(id);
        return successGet(LotteryActivityConverter.converDTO(activityBO));
    }

    /**
     * 运营平台查询抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorLotteryActivity", method = RequestMethod.POST)
    public Result<Page<LotteryActivityOperatorDTO>> listOperatorLotteryActivity(@RequestBody ListLotteryActivityQuery query) {
        Page<LotteryActivityBO> activityBOPage = lotteryActivityService.listOperatorLotteryActivity(query);
        Page<LotteryActivityOperatorDTO> page = new Page<>();
        page.setCurrentPage(activityBOPage.getCurrentPage());
        page.setTotalCount(activityBOPage.getTotalCount());
        page.setRecords(LotteryActivityConverter.converOperatorDTOS(activityBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 根据id更新活动状态
     *
     * @param id
     * @param statusEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "updateLotteryActivityStatus/{id}", method = RequestMethod.PUT)
    public Result updateLotteryActivityStatus(@PathVariable Long id, @RequestParam LotteryActivityStatusEnum statusEnum) {
        LotteryActivityBO activityBO = lotteryActivityService.getLotteryActivityById(id);
        if (activityBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        lotteryActivityService.updateLotteryActivityStatus(id, statusEnum);
        return successCreated();
    }

    /**
     * 根据id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getOperatorLotteryActivity/{id}", method = RequestMethod.GET)
    public Result<LotteryActivityOperatorDTO> getOperatorLotteryActivity(@PathVariable Long id) {
        LotteryActivityBO activityBO = lotteryActivityService.getLotteryActivityById(id);
        return successGet(LotteryActivityConverter.converOperatorDTO(activityBO));
    }

    /**
     * 新增抽奖活动
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveLotteryActivity", method = RequestMethod.POST)
    public Result saveLotteryActivity(@RequestBody LotteryActivityParam param) {
        lotteryActivityService.saveLotteryActivity(param);
        return successCreated();
    }

    /**
     * 定时更新活动进行中、已结束状态
     *
     * @author meishuquan
     */
    @RequestMapping(value = "executeUpdateLotteryActivityStatus", method = RequestMethod.PUT)
    public Result executeUpdateLotteryActivityStatus() {
        lotteryActivityService.executeUpdateLotteryActivityStatus();
        return successCreated();
    }

}

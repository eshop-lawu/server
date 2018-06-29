package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityDetailDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityNoticeDTO;
import com.lawu.eshop.activity.param.DrawLotteryActivityParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityQuery;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityBO;
import com.lawu.eshop.activity.srv.converter.DrawLotteryActivityConverter;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@RestController
@RequestMapping(value = "drawLotteryActivity/")
public class DrawLotteryActivityController extends BaseController {

    @Autowired
    private DrawLotteryActivityService drawLotteryActivityService;

    /**
     * 抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listDrawLotteryActivity", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityDTO>> listDrawLotteryActivity(@RequestBody DrawLotteryActivityQuery query) {
        Page<DrawLotteryActivityBO> activityBOPage = drawLotteryActivityService.listDrawLotteryActivity(query);
        Page<DrawLotteryActivityDTO> page = new Page<>();
        page.setCurrentPage(activityBOPage.getCurrentPage());
        page.setTotalCount(activityBOPage.getTotalCount());
        page.setRecords(DrawLotteryActivityConverter.convertDTOS(activityBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 抽奖活动详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDrawLotteryActivityDetail/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityDetailDTO> getDrawLotteryActivityDetail(@PathVariable Long id, @RequestParam String userNum) {
        DrawLotteryActivityBO activityBO = drawLotteryActivityService.getDrawLotteryActivityDetail(id, userNum);
        return successGet(DrawLotteryActivityConverter.convertDetailDTO(activityBO));
    }

    /**
     * 中奖公告列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listDrawLotteryActivityNotice", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityNoticeDTO>> listDrawLotteryActivityNotice(@RequestBody DrawLotteryActivityQuery query) {
        Page<DrawLotteryActivityBO> activityBOPage = drawLotteryActivityService.listDrawLotteryActivityNotice(query);
        Page<DrawLotteryActivityNoticeDTO> page = new Page<>();
        page.setCurrentPage(activityBOPage.getCurrentPage());
        page.setTotalCount(activityBOPage.getTotalCount());
        page.setRecords(DrawLotteryActivityConverter.convertNoticeDTOS(activityBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 根据抽奖活动id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDrawLotteryActivity/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityDTO> getDrawLotteryActivity(@PathVariable Long id) {
        DrawLotteryActivityBO activityBO = drawLotteryActivityService.getDrawLotteryActivityById(id);
        return successGet(DrawLotteryActivityConverter.convertDTO(activityBO));
    }

    /**
     * 新增抽奖活动
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveDrawLotteryActivity", method = RequestMethod.POST)
    public Result saveDrawLotteryActivity(@RequestBody DrawLotteryActivityParam param) {
        drawLotteryActivityService.saveDrawLotteryActivity(param);
        return successCreated();
    }

    /**
     * 运营平台抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorDrawLotteryActivity", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityDTO>> listOperatorDrawLotteryActivity(@RequestBody OperatorDrawLotteryActivityQuery query) {
        Page<DrawLotteryActivityBO> activityBOPage = drawLotteryActivityService.listOperatorDrawLotteryActivity(query);
        Page<DrawLotteryActivityDTO> page = new Page<>();
        page.setCurrentPage(activityBOPage.getCurrentPage());
        page.setTotalCount(activityBOPage.getTotalCount());
        page.setRecords(DrawLotteryActivityConverter.convertDTOS(activityBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 更新抽奖活动状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateDrawLotteryActivityStatus/{id}", method = RequestMethod.PUT)
    public Result updateDrawLotteryActivityStatus(@PathVariable Long id, @RequestParam DrawLotteryActivityStatusEnum statusEnum) {
        drawLotteryActivityService.updateDrawLotteryActivityStatus(id, statusEnum);
        return successCreated();
    }

    /**
     * 定时更新抽奖活动进行中和已结束状态
     *
     * @author meishuquan
     */
    @RequestMapping(value = "executeUpdateDrawLotteryActivityStatus", method = RequestMethod.PUT)
    public Result executeUpdateDrawLotteryActivityStatus() {
        drawLotteryActivityService.executeUpdateDrawLotteryActivityStatus();
        return successCreated();
    }

}

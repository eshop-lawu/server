package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordOperatorDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.dto.TakePartLotteryInfoDTO;
import com.lawu.eshop.activity.param.TakeLotteryParam;
import com.lawu.eshop.activity.param.TakePartLotteryParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityRecordQuery;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityRecordQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.converter.DrawLotteryActivityRecordConverter;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityRecordService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
@RestController
@RequestMapping(value = "drawLotteryActivityRecord/")
public class DrawLotteryActivityRecordController extends BaseController {

    @Autowired
    private DrawLotteryActivityRecordService drawLotteryActivityRecordService;

    /**
     * 查询已参与的抽奖
     *
     * @param drawLotteryActivityId
     * @param userNum
     * @param statusEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getTakePartLottery/{drawLotteryActivityId}", method = RequestMethod.GET)
    public Result<TakePartLotteryInfoDTO> getTakePartLottery(@PathVariable Long drawLotteryActivityId, @RequestParam String userNum, @RequestParam DrawLotteryActivityRecordStatusEnum statusEnum) {
        Long id = drawLotteryActivityRecordService.getTakePartLottery(drawLotteryActivityId, userNum, statusEnum);
        TakePartLotteryInfoDTO lotteryInfoDTO = new TakePartLotteryInfoDTO();
        lotteryInfoDTO.setId(id);
        return successGet(lotteryInfoDTO);
    }

    /**
     * 参与抽奖
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "takePartLottery", method = RequestMethod.POST)
    public Result<TakePartLotteryInfoDTO> takePartLottery(@RequestBody TakePartLotteryParam param) {
        Long id = drawLotteryActivityRecordService.takePartLottery(param);
        TakePartLotteryInfoDTO lotteryInfoDTO = new TakePartLotteryInfoDTO();
        lotteryInfoDTO.setId(id);
        return successCreated(lotteryInfoDTO);
    }

    /**
     * 未中奖
     *
     * @param id
     * @author meishuquan
     */
    @RequestMapping(value = "notLottery/{id}", method = RequestMethod.PUT)
    public Result notLottery(@PathVariable Long id) {
        drawLotteryActivityRecordService.updateLotteryStatus(id, DrawLotteryActivityRecordStatusEnum.NOT_LOTTERY);
        return successCreated();
    }

    /**
     * 放弃领奖
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "giveUpTakeLottery/{id}", method = RequestMethod.PUT)
    public Result giveUpTakeLottery(@PathVariable Long id) {
        drawLotteryActivityRecordService.updateLotteryStatus(id, DrawLotteryActivityRecordStatusEnum.GIVE_UP_TAKE_LOTTERY);
        return successCreated();
    }

    /**
     * 中奖
     *
     * @param id
     * @param drawLotteryActivityPrizeId
     * @author meishuquan
     */
    @RequestMapping(value = "winLottery/{id}", method = RequestMethod.PUT)
    public Result winLottery(@PathVariable Long id, @RequestParam Long drawLotteryActivityPrizeId) {
        int retCode = ResultCode.FAIL;
        try {
            retCode = drawLotteryActivityRecordService.winLottery(id, drawLotteryActivityPrizeId);
        } catch (Exception e) {
            return successCreated(ResultCode.LOTTERY_PRIZE_INVENTORY_SHORTAGE);
        }
        return successCreated(retCode);
    }

    /**
     * 领奖
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "takeLottery", method = RequestMethod.PUT)
    public Result takeLottery(@RequestBody TakeLotteryParam param) {
        drawLotteryActivityRecordService.takeLottery(param);
        return successCreated();
    }

    /**
     * 根据抽奖记录id查询活动抽奖活动id
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDrawLotteryActivityId/{id}", method = RequestMethod.GET)
    public Result<Long> getDrawLotteryActivityId(@PathVariable Long id) {
        DrawLotteryActivityRecordBO recordBO = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(id);
        if (recordBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(recordBO.getDrawLotteryActivityId());
    }

    /**
     * 运营平台抽奖记录列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorDrawLotteryActivityRecord", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityRecordOperatorDTO>> listOperatorDrawLotteryActivityRecord(@RequestBody OperatorDrawLotteryActivityRecordQuery query) {
        Page<DrawLotteryActivityRecordBO> recordBOPage = drawLotteryActivityRecordService.listOperatorDrawLotteryActivityRecord(query);
        Page<DrawLotteryActivityRecordOperatorDTO> page = new Page<>();
        page.setCurrentPage(recordBOPage.getCurrentPage());
        page.setTotalCount(recordBOPage.getTotalCount());
        page.setRecords(DrawLotteryActivityRecordConverter.convertOperatorDTOS(recordBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 根据id查询抽奖记录
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDrawLotteryActivityRecord/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityRecordDTO> getDrawLotteryActivityRecord(@PathVariable Long id) {
        DrawLotteryActivityRecordBO recordBO = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(id);
        return successGet(DrawLotteryActivityRecordConverter.convertDTO(recordBO));
    }

    /**
     * 发放奖品
     *
     * @param id
     * @author meishuquan
     */
    @RequestMapping(value = "sendPrize/{id}", method = RequestMethod.PUT)
    public Result sendPrize(@PathVariable Long id , @RequestParam String expressNum) {
        drawLotteryActivityRecordService.sendPrize(id, expressNum);
        return successCreated();
    }
    
    
    /**
     * 
     * @param memberNum
     * @param query
     * @return
     */
    @RequestMapping(value = "listDrawLotteryActivityRecord", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityRecordPageDTO>> listDrawLotteryActivityRecord(@RequestParam String memberNum,@RequestBody DrawLotteryActivityRecordQuery query) {
        Page<DrawLotteryActivityRecordBO> recordBOPage = drawLotteryActivityRecordService.listDrawLotteryActivityRecord(memberNum,query);
        Page<DrawLotteryActivityRecordPageDTO> page = new Page<>();
        page.setCurrentPage(recordBOPage.getCurrentPage());
        page.setTotalCount(recordBOPage.getTotalCount());
        page.setRecords(DrawLotteryActivityRecordConverter.convertDTOS(recordBOPage.getRecords()));
        return successCreated(page);
    }

}

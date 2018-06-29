package com.lawu.eshop.mall.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.mall.dto.LotteryInfoDTO;
import com.lawu.eshop.mall.dto.LotteryRecordDTO;
import com.lawu.eshop.mall.dto.LotteryRecordOperatorDTO;
import com.lawu.eshop.mall.param.LotteryRecordParam;
import com.lawu.eshop.mall.query.LotteryRecordQuery;
import com.lawu.eshop.mall.query.OperatorLotteryRecordQuery;
import com.lawu.eshop.mall.srv.bo.LotteryInfoBO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordBO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordOperatorBO;
import com.lawu.eshop.mall.srv.converter.LotteryRecordConverter;
import com.lawu.eshop.mall.srv.service.LotteryRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.StringUtil;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
@RestController
@RequestMapping(value = "lotteryRecord/")
public class LotteryRecordController extends BaseController {

    @Autowired
    private LotteryRecordService lotteryRecordService;

    /**
     * 保存抽奖记录
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveLotteryRecord", method = RequestMethod.POST)
    public Result listLotteryActivity(@RequestBody LotteryRecordParam param) {
        lotteryRecordService.saveLotteryRecord(param);
        return successCreated();
    }

    /**
     * 查询中奖滚动列表
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listLotteryInfo", method = RequestMethod.GET)
    public Result<List<LotteryInfoDTO>> listLotteryInfo() {
        List<LotteryInfoBO> infoBOS = lotteryRecordService.listLotteryInfo();
        List<LotteryInfoDTO> infoDTOS = new ArrayList<>();
        for (LotteryInfoBO infoBO : infoBOS) {
            LotteryInfoDTO infoDTO = new LotteryInfoDTO();
            infoDTO.setAccount(StringUtil.hideUserAccount(infoBO.getAccount()));
            infoDTO.setPrizeName(infoBO.getPrizeName());
            infoDTOS.add(infoDTO);
        }
        return successGet(infoDTOS);
    }

    /**
     * 查询中奖公告列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listLotteryRecord", method = RequestMethod.POST)
    public Result<Page<LotteryRecordDTO>> listLotteryRecord(@RequestBody LotteryRecordQuery query) {
        Page<LotteryRecordBO> recordBOPage = lotteryRecordService.listLotteryRecord(query);
        Page<LotteryRecordDTO> page = new Page<>();
        page.setCurrentPage(recordBOPage.getCurrentPage());
        page.setTotalCount(recordBOPage.getTotalCount());
        page.setRecords(LotteryRecordConverter.converDTOS(recordBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 运营平台查询参与抽奖列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorLotteryRecord", method = RequestMethod.POST)
    public Result<Page<LotteryRecordOperatorDTO>> listOperatorLotteryRecord(@RequestBody OperatorLotteryRecordQuery query) {
        Page<LotteryRecordOperatorBO> recordBOPage = lotteryRecordService.listOperatorLotteryRecord(query);
        Page<LotteryRecordOperatorDTO> page = new Page<>();
        page.setCurrentPage(recordBOPage.getCurrentPage());
        page.setTotalCount(recordBOPage.getTotalCount());
        page.setRecords(LotteryRecordConverter.converOperatorDTOS(recordBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 查询用户是否参与抽奖
     *
     * @param lotteryActivityId
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "lotteryRecord/{lotteryActivityId}", method = RequestMethod.GET)
    public Result<Boolean> lotteryRecord(@PathVariable Long lotteryActivityId, @RequestParam String userNum) {
        Boolean result = lotteryRecordService.lotteryRecord(lotteryActivityId, userNum);
        return successGet(result);
    }

    /**
     * 更新抽奖结果
     *
     * @param lotteryActivityId
     * @param account
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "updateLotteryResult/{lotteryActivityId}", method = RequestMethod.PUT)
    public Result updateLotteryResult(@PathVariable Long lotteryActivityId, @RequestParam String account) {
        int result = lotteryRecordService.updateLotteryResult(lotteryActivityId, account);
        return successCreated(result);
    }

}

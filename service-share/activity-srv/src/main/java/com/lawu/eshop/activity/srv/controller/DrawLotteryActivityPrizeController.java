package com.lawu.eshop.activity.srv.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDetailDTO;
import com.lawu.eshop.activity.param.DrawLotteryActivityPrizeParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityPrizeQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.eshop.activity.srv.converter.DrawLotteryActivityPrizeConverter;
import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityPrizeService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@RestController
@RequestMapping(value = "drawLotteryActivityPrize/")
public class DrawLotteryActivityPrizeController extends BaseController {

    @Autowired
    private DrawLotteryActivityPrizeService drawLotteryActivityPrizeService;

    /**
     * 查询奖品详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPrizeDetail/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityPrizeDetailDTO> getPrizeDetail(@PathVariable Long id) {
        DrawLotteryActivityPrizeBO prizeBO = drawLotteryActivityPrizeService.getPrizeDetail(id);
        return successGet(DrawLotteryActivityPrizeConverter.convertDetailDTO(prizeBO));
    }

    /**
     * 查询活动所有奖品
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getActivityAllPrize/{drawLotteryActivityId}", method = RequestMethod.GET)
    public Result<List<DrawLotteryActivityPrizeDTO>> getActivityAllPrize(@PathVariable Long drawLotteryActivityId) {
        List<DrawLotteryActivityPrizeBO> prizeBOS = drawLotteryActivityPrizeService.getActivityAllPrize(drawLotteryActivityId);
        return successGet(DrawLotteryActivityPrizeConverter.convertDTOS(prizeBOS));
    }

    /**
     * 新增抽奖奖品
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveDrawLotteryActivityPrize", method = RequestMethod.POST)
    public Result saveDrawLotteryActivityPrize(@RequestBody DrawLotteryActivityPrizeParam param) {
        drawLotteryActivityPrizeService.saveDrawLotteryActivityPrize(param);
        return successCreated();
    }

    /**
     * 运营平台抽奖奖品列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorDrawLotteryActivityPrize", method = RequestMethod.POST)
    public Result<Page<DrawLotteryActivityPrizeDTO>> listOperatorDrawLotteryActivityPrize(@RequestBody OperatorDrawLotteryActivityPrizeQuery query) {
        Page<DrawLotteryActivityPrizeBO> prizeBOPage = drawLotteryActivityPrizeService.listOperatorDrawLotteryActivityPrize(query);
        Page<DrawLotteryActivityPrizeDTO> page = new Page<>();
        page.setCurrentPage(prizeBOPage.getCurrentPage());
        page.setTotalCount(prizeBOPage.getTotalCount());
        page.setRecords(DrawLotteryActivityPrizeConverter.convertDTOS(prizeBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 更新奖品状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateDrawLotteryActivityPrizeStatus/{id}", method = RequestMethod.PUT)
    public Result updateDrawLotteryActivityPrizeStatus(@PathVariable Long id, @RequestParam DrawLotteryActivityPrizeStatusEnum statusEnum) {
        drawLotteryActivityPrizeService.updateDrawLotteryActivityPrizeStatus(id, statusEnum);
        return successCreated();
    }

    /**
     * 查询奖品剩余中奖率
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getLeftPrizeRate/{drawLotteryActivityId}", method = RequestMethod.GET)
    public Result<BigDecimal> getLeftPrizeRate(@PathVariable Long drawLotteryActivityId) {
        BigDecimal leftPrizeRate = drawLotteryActivityPrizeService.getLeftPrizeRate(drawLotteryActivityId);
        return successGet(leftPrizeRate);
    }

    /**
     * 随机查询奖品广告图片
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getRandPrizeAdImg/{drawLotteryActivityId}", method = RequestMethod.GET)
    public Result<String> getRandPrizeAdImg(@PathVariable Long drawLotteryActivityId) {
        String adImgPath = drawLotteryActivityPrizeService.getRandPrizeAdImg(drawLotteryActivityId);
        return successGet(adImgPath);
    }

    /**
     * 根据抽奖记录id查询奖品信息
     *
     * @param recordId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPrizeByRecordId/{recordId}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityPrizeDetailDTO> getPrizeByRecordId(@PathVariable Long recordId) {
        DrawLotteryActivityPrizeBO prizeBO = drawLotteryActivityPrizeService.getPrizeByRecordId(recordId);
        return successGet(DrawLotteryActivityPrizeConverter.convertDetailDTO(prizeBO));
    }

}

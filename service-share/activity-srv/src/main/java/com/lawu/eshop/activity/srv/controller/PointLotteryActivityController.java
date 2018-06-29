package com.lawu.eshop.activity.srv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.PointLotteryActivityPrizeImageTypeEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.dto.PointLotteryActivityOperatorDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityPrizeImageDTO;
import com.lawu.eshop.activity.dto.PointLotteryDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryInfoDTO;
import com.lawu.eshop.activity.dto.PointLotteryRelateDTO;
import com.lawu.eshop.activity.param.GenerateBasicNumberParam;
import com.lawu.eshop.activity.param.PointLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorPointLotteryActivityQuery;
import com.lawu.eshop.activity.query.PointLotteryActivityQuery;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityPrizeImageBO;
import com.lawu.eshop.activity.srv.converter.PointLotteryActivityConverter;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityPrizeImageService;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityRecordService;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityService;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
@RestController
@RequestMapping(value = "pointLotteryActivity/")
public class PointLotteryActivityController extends BaseController {

    @Autowired
    private PointLotteryActivityService pointLotteryActivityService;

    @Autowired
    private PointLotteryActivityRecordService pointLotteryActivityRecordService;

    @Autowired
    private PointLotteryActivityPrizeImageService pointLotteryActivityPrizeImageService;

    /**
     * 生成基础中奖号码
     *
     * @param id    积分夺宝活动id
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @RequestMapping(value = "generateBasicNumber/{id}", method = RequestMethod.PUT)
    public Result<Integer> generateBasicNumber(@PathVariable("id") Long id, @RequestBody @Validated GenerateBasicNumberParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            Integer basicNumber = pointLotteryActivityService.generateBasicNumber(id, param);
            return successCreated(basicNumber);
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        }
    }

    /**
     * 保存中奖号码
     *
     * @param id           积分夺宝活动id
     * @param prizeNumbers 中奖号码,用','分隔
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveWinningNumber/{id}", method = RequestMethod.PUT)
    public Result saveWinningNumber(@PathVariable("id") Long id, @RequestParam("prizeNumbers") String prizeNumbers) {
        Pattern pattern = Pattern.compile("^\\d+(,\\d+)*$");
        if (!pattern.matcher(prizeNumbers).matches()) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, "中奖号码格式错误,中奖号码为数字,且用逗号(',')分隔");
        }
        try {
            pointLotteryActivityService.saveWinningNumber(id, prizeNumbers);
            return successCreated();
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        }
    }

    /**
     * 积分抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listPointLotteryActivity", method = RequestMethod.POST)
    public Result<Page<PointLotteryInfoDTO>> listPointLotteryActivity(@RequestBody PointLotteryActivityQuery query) {
        Page<PointLotteryActivityBO> activityBOPage = pointLotteryActivityService.listPointLotteryActivity(query);
        Page<PointLotteryInfoDTO> page = new Page<>();
        page.setCurrentPage(activityBOPage.getCurrentPage());
        page.setTotalCount(activityBOPage.getTotalCount());
        page.setRecords(PointLotteryActivityConverter.convertInfoDTOS(activityBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 积分抽奖活动详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPointLotteryDetail/{id}", method = RequestMethod.GET)
    public Result<PointLotteryDetailDTO> getPointLotteryDetail(@PathVariable Long id, @RequestParam String userNum) {
        PointLotteryActivityBO activityBO = pointLotteryActivityService.getPointLotteryActivity(id);
        Integer attentCount = pointLotteryActivityRecordService.countPointLotteryActivityRecord(id, userNum);
        List<String> lotteryDetailImages = pointLotteryActivityPrizeImageService.listImagePath(id, PointLotteryActivityPrizeImageTypeEnum.ACTIVITY_INTRODUCTIONS);
        List<String> lotteryResultImages = pointLotteryActivityPrizeImageService.listImagePath(id, PointLotteryActivityPrizeImageTypeEnum.LOTTERY_INFO);
        PointLotteryDetailDTO detailDTO = PointLotteryActivityConverter.convertDetailDTO(activityBO);
        if (detailDTO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        detailDTO.setAttentCount(attentCount);
        detailDTO.setLotteryDetailImages(lotteryDetailImages);
        detailDTO.setLotteryResultImages(lotteryResultImages);
        return successGet(detailDTO);
    }

    /**
     * 根据id查询积分抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPointLotteryInfo/{id}", method = RequestMethod.GET)
    public Result<PointLotteryInfoDTO> getPointLotteryInfo(@PathVariable Long id) {
        PointLotteryActivityBO activityBO = pointLotteryActivityService.getPointLotteryActivity(id);
        return successGet(PointLotteryActivityConverter.convertInfoDTO(activityBO));
    }

    /**
     * 新增抽奖活动
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "savePointLotteryActivity", method = RequestMethod.POST)
    public Result savePointLotteryActivity(@RequestBody PointLotteryActivityParam param) {
        pointLotteryActivityService.savePointLotteryActivity(param);
        return successCreated();
    }

    /**
     * 运营平台抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorPointLotteryActivity", method = RequestMethod.POST)
    public Result<Page<PointLotteryActivityOperatorDTO>> listOperatorPointLotteryActivity(@RequestBody OperatorPointLotteryActivityQuery query) {
        Page<PointLotteryActivityBO> activityBOPage = pointLotteryActivityService.listOperatorPointLotteryActivity(query);
        Page<PointLotteryActivityOperatorDTO> page = new Page<>();
        page.setCurrentPage(activityBOPage.getCurrentPage());
        page.setTotalCount(activityBOPage.getTotalCount());
        page.setRecords(PointLotteryActivityConverter.convertOperatorDTOS(activityBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 更新抽奖活动状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updatePointLotteryActivityStatus/{id}", method = RequestMethod.PUT)
    public Result updatePointLotteryActivityStatus(@PathVariable Long id, @RequestParam PointLotteryActivityStatusEnum statusEnum) {
        pointLotteryActivityService.updatePointLotteryActivityStatus(id, statusEnum);
        return successCreated();
    }

    /**
     * 定时更新抽奖活动进行中、已结束、已开奖状态
     *
     * @author meishuquan
     */
    @RequestMapping(value = "executeUpdatePointLotteryActivityStatus", method = RequestMethod.PUT)
    public Result executeUpdatePointLotteryActivityStatus() {
        pointLotteryActivityService.executeUpdatePointLotteryActivityStatus();
        return successCreated();
    }

    /**
     * 根据id查询抽奖活动详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPointLotteryActivity/{id}", method = RequestMethod.GET)
    public Result<PointLotteryActivityOperatorDTO> getPointLotteryActivity(@PathVariable Long id) {
        PointLotteryActivityBO activityBO = pointLotteryActivityService.getPointLotteryActivity(id);
        List<PointLotteryActivityPrizeImageBO> imageBOS = pointLotteryActivityPrizeImageService.listPointLotteryActivityPrizeImage(id, PointLotteryActivityPrizeImageTypeEnum.ACTIVITY_INTRODUCTIONS);
        List<PointLotteryActivityPrizeImageBO> imageBOList = pointLotteryActivityPrizeImageService.listPointLotteryActivityPrizeImage(id, PointLotteryActivityPrizeImageTypeEnum.LOTTERY_INFO);
        PointLotteryActivityOperatorDTO operatorDTO = PointLotteryActivityConverter.convertOperatorDTO(activityBO);
        List<PointLotteryActivityPrizeImageDTO> lotteryDetailDTOS = new ArrayList<>();
        List<PointLotteryActivityPrizeImageDTO> lotteryResultDTOS = new ArrayList<>();
        if (!imageBOS.isEmpty()) {
            for (PointLotteryActivityPrizeImageBO imageBO : imageBOS) {
                PointLotteryActivityPrizeImageDTO imageDTO = new PointLotteryActivityPrizeImageDTO();
                imageDTO.setImagePath(imageBO.getImagePath());
                imageDTO.setOrderNum(imageBO.getOrderNum());
                lotteryDetailDTOS.add(imageDTO);
            }
        }
        if (!imageBOList.isEmpty()) {
            for (PointLotteryActivityPrizeImageBO imageBO : imageBOList) {
                PointLotteryActivityPrizeImageDTO imageDTO = new PointLotteryActivityPrizeImageDTO();
                imageDTO.setImagePath(imageBO.getImagePath());
                imageDTO.setOrderNum(imageBO.getOrderNum());
                lotteryResultDTOS.add(imageDTO);
            }
        }
        operatorDTO.setLotteryDetailDTOS(lotteryDetailDTOS);
        operatorDTO.setLotteryResultDTOS(lotteryResultDTOS);
        return successGet(operatorDTO);
    }

    /**
     * 查询进行中和即将开始的积分抽奖活动
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listRelatePointLotteryActivity", method = RequestMethod.GET)
    public Result<List<PointLotteryRelateDTO>> listRelatePointLotteryActivity() {
        List<PointLotteryActivityBO> activityBOS = pointLotteryActivityService.listRelatePointLotteryActivity();
        List<PointLotteryRelateDTO> relateDTOS = PointLotteryActivityConverter.convertRelateDTOS(activityBOS);
        return successGet(relateDTOS);
    }

}

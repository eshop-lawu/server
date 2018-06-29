package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;
import com.lawu.eshop.activity.dto.PointLotteryAttentDTO;
import com.lawu.eshop.activity.param.PointLotteryAttentParam;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityOrderBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryAttentBO;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityOrderService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
@RestController
@RequestMapping(value = "pointLotteryActivityOrder/")
public class PointLotteryActivityOrderController extends BaseController {

    @Autowired
    private PointLotteryActivityOrderService pointLotteryActivityOrderService;

    /**
     * 保存积分抽奖活动订单
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "savePointLotteryActivityOrder", method = RequestMethod.POST)
    public Result<PointLotteryAttentDTO> savePointLotteryActivityOrder(@RequestBody PointLotteryAttentParam param) {
        Long id = pointLotteryActivityOrderService.savePointLotteryActivityOrder(param);
        PointLotteryAttentDTO attentDTO = new PointLotteryAttentDTO();
        attentDTO.setId(id);
        return successCreated(attentDTO);
    }

    /**
     * 参与抽奖详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getPointLotteryAttentInfo/{id}", method = RequestMethod.GET)
    public Result<PointLotteryAttentDTO> getPointLotteryAttentInfo(@PathVariable Long id) {
        PointLotteryActivityOrderBO orderBO = pointLotteryActivityOrderService.getPointLotteryActivityOrder(id);
        if (orderBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        PointLotteryAttentDTO attentDTO = new PointLotteryAttentDTO();
        attentDTO.setId(id);
        attentDTO.setStatusEnum(PointLotteryActivityOrderStatusEnum.getEnum(orderBO.getStatus()));
        if (!attentDTO.getStatusEnum().equals(PointLotteryActivityOrderStatusEnum.SUCCESS)) {
            return successGet(attentDTO);
        }

        PointLotteryAttentBO attentBO = pointLotteryActivityOrderService.getPointLotteryAttentInfo(orderBO.getId());
        attentDTO.setLotteryNum(attentBO.getLotteryNum());
        attentDTO.setLotteryCnt(attentBO.getLotteryCnt());
        attentDTO.setLotteryTime(attentBO.getLotteryTime());
        return successGet(attentDTO);
    }

}

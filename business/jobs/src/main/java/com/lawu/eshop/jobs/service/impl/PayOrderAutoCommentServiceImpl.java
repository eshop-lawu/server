package com.lawu.eshop.jobs.service.impl;

import com.lawu.eshop.jobs.service.CommentMerchantService;
import com.lawu.eshop.jobs.service.PayOrderAutoCommentService;
import com.lawu.eshop.jobs.service.PayOrderService;
import com.lawu.eshop.mall.param.PayOrderAutoCommentParam;
import com.lawu.eshop.order.dto.PayOrderAutoCommentDTO;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/7/28.
 */
@Service
public class PayOrderAutoCommentServiceImpl implements PayOrderAutoCommentService {

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    CommentMerchantService commentMerchantService;
    @Override
    public void executeAutoComment() {
        //查询大于等于7天时间的未评价买单记录
        Result<List<PayOrderAutoCommentDTO>> result = payOrderService.getAutoCommentPayOrderList();

        if(result.getModel().isEmpty()){
            return;
        }
        PayOrderAutoCommentParam param;
        for (PayOrderAutoCommentDTO payOrderDTO : result.getModel()) {
            //默认好评
            param = new PayOrderAutoCommentParam();
            param.setMemberId(payOrderDTO.getMemberId());
            param.setMerchantId(payOrderDTO.getMerchantId());
            param.setAvgSpend(payOrderDTO.getActualAmount());
            param.setPayOrderId(payOrderDTO.getId());
            commentMerchantService.payOrderAutoComment(param);
        }

    }
}

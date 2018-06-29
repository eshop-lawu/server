package com.lawu.eshop.jobs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.CommissionCommonService;
import com.lawu.eshop.jobs.service.OrderSrvService;
import com.lawu.eshop.jobs.service.PayCommissionRefactorService;
import com.lawu.eshop.jobs.service.PropertySrvService;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.eshop.property.param.CommissionParam;
import com.lawu.framework.web.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayCommissionRefactorServiceImpl implements PayCommissionRefactorService {

    private static Logger logger = LoggerFactory.getLogger(PayCommissionRefactorServiceImpl.class);

    @Autowired
    private CommissionCommonService userCommonService;
    @Autowired
    private PropertySrvService propertySrvService;
    @Autowired
    private OrderSrvService orderSrvService;

    @Override
    public void commissionRefactor(ShoppingOrderCommissionDTO shoppingOrderCommissionDTO) {
        if ((shoppingOrderCommissionDTO.getMemberNum() == null || "".equals(shoppingOrderCommissionDTO.getMemberNum())) && (shoppingOrderCommissionDTO.getMerchantNum() == null || "".equals(shoppingOrderCommissionDTO.getMerchantNum()))) {
            logger.error("买单提成数据用户编号和商家编号均为空！订单ID={}", shoppingOrderCommissionDTO.getId());
            throw new RuntimeException();
        }

        //分别查询产生交易用户和商家的上3级推荐关系
        List<CommissionInvitersUserDTO> memberInviters = userCommonService.selectHigherLevelInviters(shoppingOrderCommissionDTO.getMemberNum(), 3, true);
        List<CommissionInvitersUserDTO> merchantInviters = userCommonService.selectHigherLevelInviters(shoppingOrderCommissionDTO.getMerchantNum(), 3, true);

        List<CommissionInvitersUserDTO> inviters = new ArrayList<>();
        inviters.addAll(memberInviters);
        inviters.addAll(merchantInviters);

        if (inviters != null && !inviters.isEmpty()) {
            CommissionParam param = new CommissionParam();
            param.setId(shoppingOrderCommissionDTO.getId());
            param.setActualAmount(shoppingOrderCommissionDTO.getActualAmount());
            param.setMemberNum(shoppingOrderCommissionDTO.getMemberNum());
            param.setMerchantNum(shoppingOrderCommissionDTO.getMerchantNum());
            param.setInviters(inviters);
            param.setCommissionEnum(CommissionEnum.PAY);
            Result result = propertySrvService.calculationRefactor(param);
            if (result.getRet() != ResultCode.SUCCESS) {// 所有上线提成计算成功才算成功
                logger.error("买单提成记录（订单ID={}）计算上级提成时存在失败情况，异常信息：{}", shoppingOrderCommissionDTO.getId(), result.getMsg());
                throw new RuntimeException();
            }
        }

        //修改订单是否计算提成状态
        List<Long> successOrderIds = new ArrayList<Long>();
        successOrderIds.add(shoppingOrderCommissionDTO.getId());
        Result result = orderSrvService.updatePayOrderCommissionStatus(successOrderIds);
        if (result.getRet() != ResultCode.SUCCESS) {
            logger.error("买单更新订单状态返回错误,retCode={},订单ID={}", result.getRet(),shoppingOrderCommissionDTO.getId());
            throw new RuntimeException();
        }
    }

}

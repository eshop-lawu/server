package com.lawu.eshop.property.srv.service;

import java.math.BigDecimal;

import com.lawu.eshop.property.dto.AdCommissionResultDTO;
import com.lawu.eshop.property.param.CommissionResultParam;
import org.springframework.stereotype.Service;

@Service
public class CommissionUtilImpl {

    public AdCommissionResultDTO getCommissionResult(CommissionResultParam param) {

        BigDecimal minThreshold = new BigDecimal("0.000001");
        BigDecimal actureMoneyIn;
        BigDecimal actureLoveIn = null;
        if (param.getDept().intValue() == 3) {
            actureMoneyIn = param.getBeforeMoney().multiply(param.getCommission0()).multiply(param.getCurrentCommission()).setScale(6, BigDecimal.ROUND_HALF_UP);// 第三级进积分，无爱心账户
        } else {
            actureMoneyIn = param.getBeforeMoney().multiply(param.getCommission0()).multiply(param.getCurrentCommission()).multiply(param.getActualCommissionScope()).setScale(6, BigDecimal.ROUND_HALF_UP);// 实际所得余额
            actureLoveIn = param.getBeforeMoney().multiply(param.getCommission0()).multiply(param.getCurrentCommission()).multiply(param.getLoveAccountScale()).setScale(6, BigDecimal.ROUND_HALF_UP);// 爱心账户

            if (actureLoveIn.compareTo(minThreshold) < 0) {
                actureLoveIn = new BigDecimal("0");
            }
        }
        if (actureMoneyIn.compareTo(minThreshold) < 0) {
            actureMoneyIn = new BigDecimal("0");
        }
        AdCommissionResultDTO rtnDTO = new AdCommissionResultDTO();
        rtnDTO.setActureMoneyIn(actureMoneyIn);
        rtnDTO.setActureLoveIn(actureLoveIn);
        return rtnDTO;
    }


}

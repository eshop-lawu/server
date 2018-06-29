package com.lawu.eshop.property.srv.service;

import java.util.List;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.common.param.CommissionJobParam;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.eshop.property.param.CommissionParam;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月24日 下午4:34:16
 */
public interface CommissionService {

    /**
     * 定时任务计算上线提成(点广告、买单、商品订单)
     * A->B->C->D（用户）A、B、C也可能是商家
     *
     * @param param
     * @return
     */
    int calculation(CommissionJobParam param);

    void calculationAd(CommissionParam param, List<CommissionInvitersUserDTO> inviters, CommissionEnum commissionEnum);

    void calculationSalesAndVolume(CommissionParam param, List<CommissionInvitersUserDTO> inviters, CommissionEnum commissionEnum);
}

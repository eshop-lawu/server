package com.lawu.eshop.property.srv.service;

import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import com.lawu.eshop.property.dto.BusinessDepositInitDTO;
import com.lawu.eshop.property.param.BusinessDepositOperDataParam;
import com.lawu.eshop.property.param.BusinessDepositQueryDataParam;
import com.lawu.eshop.property.param.BusinessDepositSaveDataParam;
import com.lawu.eshop.property.param.BusinessRefundDepositDataParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.srv.bo.BusinessDepositBO;
import com.lawu.eshop.property.srv.bo.BusinessDepositDetailBO;
import com.lawu.eshop.property.srv.bo.BusinessDepositQueryBO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

public interface BusinessDepositService {

    /**
     * 初始化商家保证金记录
     *
     * @param param
     * @return
     */
    BusinessDepositInitDTO save(BusinessDepositSaveDataParam param);

    /**
     * 处理第三方支付回调
     * 校验金额，新增商家交易明细，更新保证金表
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    Result doHandleDepositNotify(NotifyCallBackParam param);

    /**
     * 运营平台列表查询
     *
     * @param param
     * @return
     */
    Page<BusinessDepositQueryBO> selectDepositList(BusinessDepositQueryDataParam param);

    /**
     * 保证金操作
     *
     * @param param
     * @return
     */
    int updateBusinessDeposit(BusinessDepositOperDataParam param);

    /**
     * 商家申请退保证金:无未完结订单、保证金核实后90天
     *
     * @param dparam
     * @return
     */
    int refundDeposit(BusinessRefundDepositDataParam dparam);

    /**
     * 商家我的保证金
     *
     * @param businessId
     * @return
     */
    BusinessDepositDetailBO selectDeposit(String businessId);

    /**
     * @param depositId
     * @return
     */
    BusinessDepositStatusEnum getDepositStatusById(Long depositId);

    BusinessDepositBO getDepositById(Long depositId);
}

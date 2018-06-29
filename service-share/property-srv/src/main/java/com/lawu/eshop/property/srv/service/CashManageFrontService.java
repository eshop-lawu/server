package com.lawu.eshop.property.srv.service;

import java.util.List;

import com.lawu.eshop.property.param.CashBillDataParam;
import com.lawu.eshop.property.param.CashChargeDataParam;
import com.lawu.eshop.property.param.CashDataParam;
import com.lawu.eshop.property.srv.bo.CashFeeBO;
import com.lawu.eshop.property.srv.bo.CashFeeParamBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashDetailBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashQueryBO;
import com.lawu.framework.core.page.Page;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月5日 下午2:48:51
 */
public interface CashManageFrontService {

    /**
     * 提现操作，校验规则：
     * <p>
     * 1、校验提现金额小数最大2位
     * 2、校验提现比例系统参数未配置
     * 3、校验自然月提现次数大于1次时，提现金额必须大于5元
     * 4、校验用户对应财产记录是否为空、记录数是否正确（仅一条）
     * 5、校验银行卡（是否存在、是否是该用户的）
     * 6、校验用户余额、支付密码
     *
     * @param cash
     */
    int save(CashDataParam cash);

    /**
     * 用户、商家提现明细
     *
     * @param cash
     * @return
     */
    Page<WithdrawCashQueryBO> findCashList(CashBillDataParam cash);

    /**
     * 提现明细
     *
     * @param id
     * @return
     */
    WithdrawCashDetailBO cashDetail(Long id);

    /**
     * 查询提现详情
     *
     * @param ids 提现id列表
     * @return
     */
    List<WithdrawCashBO> list(List<Long> ids);

    /**
     * 判断用户是否存在提现申请
     *
     * @param userNum
     * @param bankAccountId
     * @return
     */
    Boolean isExistCash(String userNum, Long bankAccountId);

    CashFeeBO calculationFee(CashChargeDataParam cash);

    CashFeeParamBO getCalculationFeeParam(String userNum);
}

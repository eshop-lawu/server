package com.lawu.eshop.property.srv.pay;

import javax.validation.Valid;

import com.lawu.eshop.property.param.BalancePayValidateDataParam;
import com.lawu.eshop.property.srv.exception.BalanceNegativeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.TransactionTitleEnum;
import com.lawu.eshop.property.param.BalancePayDataParam;
import com.lawu.eshop.property.srv.service.BalancePayService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * <p>
 * Description: 余额支付
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月11日 下午5:23:33
 */
@RestController
@RequestMapping(value = "balancePay/")
public class BalancePayController extends BaseController {

    @Autowired
    private BalancePayService balancePayService;

    /**
     * 余额支付订单
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "orderPay", method = RequestMethod.POST)
    public Result orderPay(@RequestBody @Valid BalancePayDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.PAY_ORDERS);
        int retCode = ResultCode.FAIL;
        try {
            retCode = balancePayService.balancePayProductOrder(param);
        }catch (BalanceNegativeException e){
            return successCreated(ResultCode.ERROR_BALANCE_NEGATIVE);
        }
        return successCreated(retCode);
    }

    /**
     * 余额支付买单
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "billPay", method = RequestMethod.POST)
    public Result billPay(@RequestBody @Valid BalancePayDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.PAY);
        param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.PAY);

        int retCode = ResultCode.FAIL;
        try {
            retCode = balancePayService.balancePay(param);
        }catch (BalanceNegativeException e){
            return successCreated(ResultCode.ERROR_BALANCE_NEGATIVE);
        }
        return successCreated(retCode);
    }

    /**
     * 余额支付充值积分
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "balancePayPoint", method = RequestMethod.POST)
    public Result balancePayPoint(@RequestBody @Valid BalancePayDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.RECHARGE_POINT);
        param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.INTEGRAL_RECHARGE);
        param.setTitle(TransactionTitleEnum.INTEGRAL_RECHARGE.getVal());
        int retCode = ResultCode.FAIL;
        try {
            retCode = balancePayService.balancePayPoint(param);
        }catch (BalanceNegativeException e){
            return successCreated(ResultCode.ERROR_BALANCE_NEGATIVE);
        }
        return successCreated(retCode);
    }

    /**
     * 用户发红包余额支付
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "memberRedPacketPay", method = RequestMethod.POST)
    public Result memberRedPacketPay(@RequestBody @Valid BalancePayDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.ADD_RED_SWEEP);
        param.setTitle(MemberTransactionTypeEnum.ADD_RED_SWEEP.getName());
        int retCode = ResultCode.FAIL;
        try {
            retCode = balancePayService.memberRedPacketPay(param);
        }catch (BalanceNegativeException e){
            return successCreated(ResultCode.ERROR_BALANCE_NEGATIVE);
        }
        return successCreated(retCode);
    }


    //##################################################################以下需要校验支付密码


    /**
     * 余额支付订单
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "orderPayValidatePwd", method = RequestMethod.POST)
    public Result orderPayValidatePwd(@RequestBody @Valid BalancePayValidateDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.PAY_ORDERS);
        int retCode = ResultCode.FAIL;
        try {
            retCode = balancePayService.balancePayProductOrderValidatePwd(param);
        }catch (BalanceNegativeException e){
            return successCreated(ResultCode.ERROR_BALANCE_NEGATIVE);
        }
        return successCreated(retCode);
    }

    /**
     * 余额支付买单
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "billPayValidatePwd", method = RequestMethod.POST)
    public Result billPayValidatePwd(@RequestBody @Valid BalancePayValidateDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.PAY);
        param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.PAY);
        int retCode = ResultCode.FAIL;
        try {
            retCode = balancePayService.balancePayValidatePwd(param);
        }catch (BalanceNegativeException e){
            return successCreated(ResultCode.ERROR_BALANCE_NEGATIVE);
        }
        return successCreated(retCode);
    }

    /**
     * 余额支付充值积分
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "balancePayPointValidatePwd", method = RequestMethod.POST)
    public Result balancePayPointValidatePwd(@RequestBody @Valid BalancePayValidateDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.RECHARGE_POINT);
        param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.INTEGRAL_RECHARGE);
        param.setTitle(TransactionTitleEnum.INTEGRAL_RECHARGE.getVal());
        int retCode = ResultCode.FAIL;
        try {
            retCode = balancePayService.balancePayPointValidatePwd(param);
        }catch (BalanceNegativeException e){
            return successCreated(ResultCode.ERROR_BALANCE_NEGATIVE);
        }
        return successCreated(retCode);
    }

    /**
     * 用户发红包余额支付
     *
     * @param param
     * @param result
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "memberRedPacketPayValidatePwd", method = RequestMethod.POST)
    public Result memberRedPacketPayValidatePwd(@RequestBody @Valid BalancePayValidateDataParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.ADD_RED_SWEEP);
        param.setTitle(MemberTransactionTypeEnum.ADD_RED_SWEEP.getName());
        int retCode = ResultCode.FAIL;
        try {
            retCode = balancePayService.memberRedPacketPayValidatePwd(param);
        }catch (BalanceNegativeException e){
            return successCreated(ResultCode.ERROR_BALANCE_NEGATIVE);
        }
        return successCreated(retCode);
    }
}

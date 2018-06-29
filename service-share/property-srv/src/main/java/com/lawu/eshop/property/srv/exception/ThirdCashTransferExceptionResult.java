package com.lawu.eshop.property.srv.exception;/**
 * Created by ${Yangqh} on 2018/1/29.
 */

import com.lawu.eshop.property.constants.BankAccountTypeEnum;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2018/1/29 18:16
 */
public class ThirdCashTransferExceptionResult {
    //第三方交易号
    private String outBizNo;
    //平台类型
    private BankAccountTypeEnum bankAccountTypeEnum;
    //返回状态码
    private String code;
    //业务状态码
    private String subCode;
    //微信（错误码），支付宝（结果状态码）
    private String errCode;

    public ThirdCashTransferExceptionResult(String outBizNo,BankAccountTypeEnum bankAccountTypeEnum,String code, String subCode, String errCode) {
        this.bankAccountTypeEnum = bankAccountTypeEnum;
        this.outBizNo = outBizNo;
        this.code = code;
        this.subCode = subCode;
        this.errCode = errCode;
    }

    public BankAccountTypeEnum getBankAccountTypeEnum() {
        return bankAccountTypeEnum;
    }

    public void setBankAccountTypeEnum(BankAccountTypeEnum bankAccountTypeEnum) {
        this.bankAccountTypeEnum = bankAccountTypeEnum;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}

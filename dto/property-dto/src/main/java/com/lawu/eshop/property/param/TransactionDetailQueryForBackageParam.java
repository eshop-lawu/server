package com.lawu.eshop.property.param;

import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/5/22.
 */
public class TransactionDetailQueryForBackageParam extends AbstractPageParam{

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    @ApiModelProperty(value = "会员交易类型")
    private MemberTransactionTypeEnum memberTransactionType;

    @ApiModelProperty(value = "商家交易类型")
    private MerchantTransactionTypeEnum merchantTransactionType;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public MemberTransactionTypeEnum getMemberTransactionType() {
        return memberTransactionType;
    }

    public void setMemberTransactionType(MemberTransactionTypeEnum memberTransactionType) {
        this.memberTransactionType = memberTransactionType;
    }

    public MerchantTransactionTypeEnum getMerchantTransactionType() {
        return merchantTransactionType;
    }

    public void setMerchantTransactionType(MerchantTransactionTypeEnum merchantTransactionType) {
        this.merchantTransactionType = merchantTransactionType;
    }
}

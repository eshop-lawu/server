package com.lawu.eshop.property.dto;/**
 * Created by ${Yangqh} on 2018/1/15.
 */

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2018/1/15 16:21
 */
public class BankAccountThirdDTO {

    @ApiModelProperty(value = "银行卡列表")
    private List<BankAccountDTO> bankList = new ArrayList<>();

    @ApiModelProperty(value = "第三方账号列表")
    private List<BankThirdAccountDTO> thirdAccountList = new ArrayList<>();

    public List<BankAccountDTO> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankAccountDTO> bankList) {
        this.bankList = bankList;
    }

    public List<BankThirdAccountDTO> getThirdAccountList() {
        return thirdAccountList;
    }

    public void setThirdAccountList(List<BankThirdAccountDTO> thirdAccountList) {
        this.thirdAccountList = thirdAccountList;
    }
}

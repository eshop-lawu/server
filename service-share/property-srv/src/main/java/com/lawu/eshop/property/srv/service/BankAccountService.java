package com.lawu.eshop.property.srv.service;

import java.util.List;

import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import com.lawu.eshop.property.param.BandingWxDataParam;
import com.lawu.eshop.property.param.BandingWxEditDataParam;
import com.lawu.eshop.property.param.BankAccountOperatorParam;
import com.lawu.eshop.property.param.BankAccountParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditParam;
import com.lawu.eshop.property.srv.bo.BankAccountBO;
import com.lawu.eshop.property.srv.bo.BankAccountOperatorBO;

/**
 * 银行卡管理接口
 *
 * @author zhangrc
 * @date 2017/03/29
 */
public interface BankAccountService {

    /**
     * 添加银行卡
     *
     * @param bankAccountParam
     * @return
     */
    Integer saveBankAccount(String userNum, BankAccountParam bankAccountParam);

    /**
     * 查询我绑定的银行卡
     *
     * @return
     */
    List<BankAccountBO> selectMyBank(String userNum);

    /**
     * 删除绑定的银行卡
     *
     * @param id
     * @return
     */
    Integer remove(Long id);

    /**
     * 根据卡号查询
     *
     * @param account
     * @return
     */
    Boolean selectByAccount(String account, Byte userType, String num, Byte bankAccountType);

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    BankAccountBO selectAccount(Long id);

    /**
     * 修改银行卡
     *
     * @param id
     * @param bankAccountParam
     * @return
     */
    Integer updateBankAccount(Long id, BankAccountParam bankAccountParam);

    /**
     * 查询当前用户银行卡用户名
     *
     * @param num
     * @return
     */
    String selectBankName(String num);

    /**
     * 运营平台修改银行卡
     *
     * @param id
     * @param param
     */
    void updateBankOperator(Long id, BankAccountOperatorParam param);


    List<BankAccountOperatorBO> selectBankOperator(String userNum);


    Boolean selectByAccountTypeAndUserNum(String userNum, BankAccountTypeEnum bankAccountTypeEnum);

    int saveThirdAccount(BankAccountThirdAccountDataParam bankAccountThirdAccountDataParam);

    List<BankAccountBO> selectThirdAccountList(String userNum);

    BankAccountBO selectThirdAccount(Long id);

    int updateThirdAccount(Long id, BankAccountThirdAccountEditDataParam bankAccountThirdAccountEditDataParam);

    boolean selectThirdAccountDouble(String userNum, Byte val);

    int saveWxAccount(BandingWxDataParam bandingWxDataParam);

    int updateWxAccount(Long id, BandingWxEditDataParam bandingWxEditDataParam);
}

package com.lawu.eshop.merchant.api.service;

import java.util.List;

import com.lawu.eshop.property.dto.BankAccountThirdDTO;
import com.lawu.eshop.property.dto.BankThirdAccountDTO;
import com.lawu.eshop.property.param.BandingWxDataParam;
import com.lawu.eshop.property.param.BandingWxEditDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditDataParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.property.dto.BankAccountDTO;
import com.lawu.eshop.property.dto.BankAccountNameDTO;
import com.lawu.eshop.property.param.BankAccountParam;

/**
 * 商家api 银行卡管理接口
 *
 * @author zhangrc
 * @date 2017/03/30
 */
@FeignClient(value = "property-srv")
public interface BankAccountService {


    /**
     * 添加银行卡
     *
     * @param bankAccountDO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "bankAccount/saveBankAccount")
    Result saveBankAccount(@RequestParam("userNum") String userNum, @RequestBody BankAccountParam bankAccountParam);

    /**
     * 查询我绑定的银行卡
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "bankAccount/selectMyBankAccount")
    Result<List<BankAccountDTO>> selectMyBank(@RequestParam("userNum") String userNum);

    /**
     * 删除银行卡
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "bankAccount/remove/{id}")
    Result delete(@PathVariable("id") Long id);

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "bankAccount/selectAccount/{id}", method = RequestMethod.GET)
    public Result<BankAccountDTO> selectAccount(@PathVariable("id") Long id);


    /**
     * 修改
     *
     * @param id
     * @param bankAccountParam
     * @return
     */
    @RequestMapping(value = "bankAccount/updateBankAccount/{id}", method = RequestMethod.PUT)
    public Result updateBankAccount(@PathVariable("id") Long id, @RequestParam("userNum") String userNum, @RequestBody BankAccountParam bankAccountParam);

    /**
     * 获取银行卡用户名称
     *
     * @param userNum
     * @return
     */
    @RequestMapping(value = "bankAccount/selectBankName", method = RequestMethod.GET)
    Result<BankAccountNameDTO> selectBankName(@RequestParam("userNum") String userNum);

    /**
     * 新增第三方账户
     * @since server.1.4.0
     * @since app.1.1.2 or 1.2.0
     *
     * @param bankAccountThirdAccountDataParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "bankAccount/saveThirdAccount")
    Result saveThirdAccount(@RequestBody BankAccountThirdAccountDataParam bankAccountThirdAccountDataParam);

    /**
     * 我的银行卡
     * @since server.1.4.0
     * @since app.1.1.2 or 1.2.0
     * @param userNum
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "bankAccount/selectBankAccountByUserNum")
    Result<BankAccountThirdDTO> selectBankAccountByUserNum(@RequestParam("userNum") String userNum);

    /**
     * 根据ID获取第三方账号
     * @since server.1.4.0
     * @since app.1.1.2 or 1.2.0
     * @param id
     * @return
     */
    @RequestMapping(value = "bankAccount/selectThirdAccount/{id}", method = RequestMethod.GET)
    Result<BankThirdAccountDTO> selectThirdAccount(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "bankAccount/updateThirdAccount")
    Result updateThirdAccount(@RequestBody BankAccountThirdAccountEditDataParam bankAccountThirdAccountEditDataParam);

    /**
     * 绑定微信
     * @param bandingWxDataParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "bankAccount/saveWxAccount")
    Result saveWxAccount(@RequestBody BandingWxDataParam bandingWxDataParam);

    /**
     * 修改微信
     * @param bandingWxEditDataParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "bankAccount/updateWxAccount")
    Result updateWxAccount(@RequestBody BandingWxEditDataParam bandingWxEditDataParam);
}

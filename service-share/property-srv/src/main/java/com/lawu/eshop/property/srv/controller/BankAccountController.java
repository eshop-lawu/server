package com.lawu.eshop.property.srv.controller;

import java.util.List;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import com.lawu.eshop.property.dto.BankAccountDTO;
import com.lawu.eshop.property.dto.BankAccountNameDTO;
import com.lawu.eshop.property.dto.BankAccountOperatorDTO;
import com.lawu.eshop.property.dto.BankAccountThirdDTO;
import com.lawu.eshop.property.dto.BankThirdAccountDTO;
import com.lawu.eshop.property.param.BandingWxDataParam;
import com.lawu.eshop.property.param.BandingWxEditDataParam;
import com.lawu.eshop.property.param.BankAccountOperatorParam;
import com.lawu.eshop.property.param.BankAccountParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditDataParam;
import com.lawu.eshop.property.srv.bo.BankAccountBO;
import com.lawu.eshop.property.srv.bo.BankAccountOperatorBO;
import com.lawu.eshop.property.srv.converter.BankAccountConverter;
import com.lawu.eshop.property.srv.service.BankAccountService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangrc
 * @date 2017/3/29
 */
@RestController
@RequestMapping(value = "bankAccount/")
public class BankAccountController extends BaseController {

    @Autowired
    private BankAccountService bankAccountService;

    /**
     * 银行卡绑定
     *
     * @param userNum
     * @param bankAccountParam
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "saveBankAccount", method = RequestMethod.POST)
    public Result saveBankAccount(@RequestParam String userNum, @RequestBody BankAccountParam bankAccountParam) {
        Boolean flag = bankAccountService.selectByAccount(bankAccountParam.getAccountNumber(), bankAccountParam.getUserType().getValue(), userNum, BankAccountTypeEnum.BANK.getVal());
        if (flag) {
            return successCreated(ResultCode.BANK_ACCOUNT_IS_EXIST);
        }
        bankAccountService.saveBankAccount(userNum, bankAccountParam);
        return successCreated();
    }

    /**
     * 查询已经绑定的银行卡
     *
     * @param userNum
     * @return
     */
    @RequestMapping(value = "selectMyBankAccount", method = RequestMethod.GET)
    public Result<List<BankAccountDTO>> selectMyBankAccount(@RequestParam String userNum) {
        List<BankAccountBO> BOS = bankAccountService.selectMyBank(userNum);
        return successAccepted(BankAccountConverter.convertDTOS(BOS));
    }

    /**
     * 删除绑定的银行卡
     *
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable Long id) {
        bankAccountService.remove(id);
        return successDelete();
    }

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectAccount/{id}", method = RequestMethod.GET)
    public Result<BankAccountDTO> selectAccount(@PathVariable Long id) {
        BankAccountBO BO = bankAccountService.selectAccount(id);
        return successAccepted(BankAccountConverter.convertDTO(BO));
    }

    /**
     * 修改
     *
     * @param id
     * @param bankAccountParam
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "updateBankAccount/{id}", method = RequestMethod.PUT)
    public Result updateBankAccount(@PathVariable Long id, @RequestParam String userNum, @RequestBody BankAccountParam bankAccountParam) {
        BankAccountBO bo = bankAccountService.selectAccount(id);
        if (!bo.getAccountNumber().equals(bankAccountParam.getAccountNumber())) {
            Boolean flag = bankAccountService.selectByAccount(bankAccountParam.getAccountNumber(), bankAccountParam.getUserType().getValue(), userNum, BankAccountTypeEnum.BANK.getVal());
            if (flag) {
                return successCreated(ResultCode.BANK_ACCOUNT_IS_EXIST);
            }
        }
        bankAccountService.updateBankAccount(id, bankAccountParam);
        return successCreated();
    }

    /**
     * 获取银行卡用户名称
     *
     * @param userNum
     * @return
     */
    @RequestMapping(value = "selectBankName", method = RequestMethod.GET)
    public Result<BankAccountNameDTO> selectBankName(@RequestParam String userNum) {
        String name = bankAccountService.selectBankName(userNum);
        BankAccountNameDTO dto = new BankAccountNameDTO();
        dto.setAccountName(name);
        return successCreated(dto);
    }

    /**
     * 运营平台修改银行卡
     *
     * @param id
     * @param param
     * @return
     */
    @RequestMapping(value = "updateBankOperator/{id}", method = RequestMethod.PUT)
    public Result updateBankOperator(@PathVariable Long id, @RequestBody BankAccountOperatorParam param) {
        BankAccountBO bo = bankAccountService.selectAccount(id);
        if (!bo.getAccountNumber().equals(param.getAccountNumber())) {
            Boolean flag = bankAccountService.selectByAccount(param.getAccountNumber(), param.getUserType().getValue(), param.getUserNum(), BankAccountTypeEnum.BANK.getVal());
            if (flag) {
                return successCreated(ResultCode.BANK_ACCOUNT_IS_EXIST);
            }
        }
        bankAccountService.updateBankOperator(id, param);

        return successCreated();
    }


    /**
     * 运营平台查询用户银行卡列表
     *
     * @param userNum
     * @return
     */
    @RequestMapping(value = "selectBankOperator", method = RequestMethod.GET)
    public Result<List<BankAccountOperatorDTO>> selectBankOperator(@RequestParam String userNum) {
        List<BankAccountOperatorBO> BOS = bankAccountService.selectBankOperator(userNum);
        return successAccepted(BankAccountConverter.convertOperatorDTOS(BOS));
    }

    /**
     * 绑定第三方账户
     *
     * @param bankAccountThirdAccountDataParam
     * @return
     */
    @RequestMapping(value = "saveThirdAccount", method = RequestMethod.POST)
    public Result saveThirdAccount(@RequestBody BankAccountThirdAccountDataParam bankAccountThirdAccountDataParam) {
        boolean formatFlag = StringUtil.verifyThirdAccount(bankAccountThirdAccountDataParam.getAccountNumber());
        if (!formatFlag) {
            return successCreated(ResultCode.BANK_ACCOUNT_THIRD_ACCOUNT_FORMAT);
        }
        boolean isDouble = bankAccountService.selectThirdAccountDouble(bankAccountThirdAccountDataParam.getUserNum(), bankAccountThirdAccountDataParam.getBankAccountTypeEnum().getVal());
        if (isDouble) {
            return successCreated(ResultCode.BANK_ACCOUNT_THIRD_TYPE_IS_EXIST);
        }
        Boolean flag = bankAccountService.selectByAccount(bankAccountThirdAccountDataParam.getAccountNumber(), bankAccountThirdAccountDataParam.getUserTypeEnum().getValue(), bankAccountThirdAccountDataParam.getUserNum(), bankAccountThirdAccountDataParam.getBankAccountTypeEnum().getVal());
        if (flag) {
            return successCreated(ResultCode.BANK_ACCOUNT_THIRD_IS_EXIST);
        }
        bankAccountService.saveThirdAccount(bankAccountThirdAccountDataParam);
        return successCreated();
    }

    /**
     * 修改第三方账户
     *
     * @param bankAccountThirdAccountEditDataParam
     * @return
     */
    @RequestMapping(value = "updateThirdAccount", method = RequestMethod.POST)
    public Result updateThirdAccount(@RequestBody BankAccountThirdAccountEditDataParam bankAccountThirdAccountEditDataParam) {
        boolean formatFlag = StringUtil.verifyThirdAccount(bankAccountThirdAccountEditDataParam.getAccountNumber());
        if (!formatFlag) {
            return successCreated(ResultCode.BANK_ACCOUNT_THIRD_ACCOUNT_FORMAT);
        }
        BankAccountBO bo = bankAccountService.selectThirdAccount(bankAccountThirdAccountEditDataParam.getId());
        if (!bankAccountThirdAccountEditDataParam.getAccountNumber().equals(bo.getAccountNumber())) {
            Boolean flag = bankAccountService.selectByAccount(bankAccountThirdAccountEditDataParam.getAccountNumber(), bankAccountThirdAccountEditDataParam.getUserTypeEnum().getValue(), bankAccountThirdAccountEditDataParam.getUserNum(), bankAccountThirdAccountEditDataParam.getBankAccountTypeEnum().getVal());
            if (flag) {
                return successCreated(ResultCode.BANK_ACCOUNT_THIRD_IS_EXIST);
            }
        }
        bankAccountService.updateThirdAccount(bankAccountThirdAccountEditDataParam.getId(), bankAccountThirdAccountEditDataParam);
        return successCreated();
    }

    /**
     * 我的银行卡列表
     *
     * @param userNum
     * @return
     * @since app.1.1.2 or 1.2.0
     */
    @RequestMapping(value = "selectBankAccountByUserNum", method = RequestMethod.GET)
    public Result<BankAccountThirdDTO> selectBankAccountByUserNum(@RequestParam String userNum) {
        BankAccountThirdDTO rtnDTO = new BankAccountThirdDTO();
        List<BankAccountBO> bankBos = bankAccountService.selectMyBank(userNum);
        List<BankAccountDTO> bankList = BankAccountConverter.convertDTOS(bankBos);
        rtnDTO.setBankList(bankList);
        List<BankAccountBO> thirdAccountBos = bankAccountService.selectThirdAccountList(userNum);
        List<BankThirdAccountDTO> thirdAccountList = BankAccountConverter.convertThirdDTO(thirdAccountBos);
        rtnDTO.setThirdAccountList(thirdAccountList);
        return successAccepted(rtnDTO);
    }

    /**
     * 根据ID获取第三方账号
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectThirdAccount/{id}", method = RequestMethod.GET)
    public Result<BankThirdAccountDTO> selectThirdAccount(@PathVariable Long id) {
        BankAccountBO bo = bankAccountService.selectThirdAccount(id);
        return successCreated(BankAccountConverter.convertThirdAccountDTO(bo));
    }

    /**
     * 绑定微信账号
     *
     * @param bandingWxDataParam
     * @return
     */
    @RequestMapping(value = "saveWxAccount", method = RequestMethod.POST)
    public Result saveWxAccount(@RequestBody BandingWxDataParam bandingWxDataParam) {
        boolean isDouble = bankAccountService.selectThirdAccountDouble(bandingWxDataParam.getUserNum(), BankAccountTypeEnum.WEIXIN.getVal());
        if (isDouble) {
            return successCreated(ResultCode.BANK_ACCOUNT_THIRD_TYPE_WX_IS_EXIST);
        }
        Boolean flag = bankAccountService.selectByAccount(bandingWxDataParam.getAccountNumber(), bandingWxDataParam.getUserTypeEnum().getValue(), bandingWxDataParam.getUserNum(), BankAccountTypeEnum.WEIXIN.getVal());
        if (flag) {
            return successCreated(ResultCode.BANK_ACCOUNT_THIRD_IS_EXIST);
        }
        bankAccountService.saveWxAccount(bandingWxDataParam);
        return successCreated();
    }

    /**
     * 绑定微信账号
     *
     * @param bandingWxEditDataParam
     * @return
     */
    @RequestMapping(value = "updateWxAccount", method = RequestMethod.POST)
    public Result updateWxAccount(@RequestBody BandingWxEditDataParam bandingWxEditDataParam) {
        if (StringUtils.isNotBlank(bandingWxEditDataParam.getAccountNumber())) {
            Boolean flag = bankAccountService.selectByAccount(bandingWxEditDataParam.getAccountNumber(), bandingWxEditDataParam.getUserTypeEnum().getValue(), bandingWxEditDataParam.getUserNum(), BankAccountTypeEnum.WEIXIN.getVal());
            if (flag) {
                return successCreated(ResultCode.BANK_ACCOUNT_THIRD_IS_EXIST);
            }
        }
        bankAccountService.updateWxAccount(bandingWxEditDataParam.getId(), bandingWxEditDataParam);
        return successCreated();
    }
}

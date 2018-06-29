package com.lawu.eshop.merchant.api.controller;

import javax.validation.Valid;
import java.util.List;

import com.lawu.eshop.merchant.api.service.AuthService;
import com.lawu.eshop.property.dto.BankAccountThirdDTO;
import com.lawu.eshop.property.dto.BankThirdAccountDTO;
import com.lawu.eshop.property.param.BandingWxDataParam;
import com.lawu.eshop.property.param.BandingWxEditDataParam;
import com.lawu.eshop.property.param.BandingWxEditParam;
import com.lawu.eshop.property.param.BandingWxParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.weixinapi.dto.WeixinUserDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.merchant.api.service.BankAccountService;
import com.lawu.eshop.merchant.api.service.CashManageFrontService;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.eshop.merchant.api.service.PropertyInfoService;
import com.lawu.eshop.property.dto.BankAccountDTO;
import com.lawu.eshop.property.dto.BankAccountNameDTO;
import com.lawu.eshop.property.param.BankAccountParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 商家银行卡管理
 *
 * @author zhangrc
 * @date 2017/03/30
 */
@Api(tags = "bankAccount")
@RestController
@RequestMapping(value = "bankAccount/")
public class BankAccountController extends BaseController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private PropertyInfoService propertyInfoService;

    @Autowired
    private CashManageFrontService cashManageFrontService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private AuthService authService;

    /**
     * @param token
     * @return
     */
    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "我绑定的银行卡", notes = "查询当前用户绑定的银行卡[]（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<List<BankAccountDTO>> selectMyBank(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return bankAccountService.selectMyBank(userNum);
    }

    /**
     * 绑定银行卡
     *
     * @param token
     * @param bankAccountParam
     * @return
     */
    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "添加银行卡", notes = "添加银行卡[6000|1022|1002|6021]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveBankAccount", method = RequestMethod.POST)
    public Result saveBankAccount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                  @RequestParam @ApiParam(required = true, value = "支付密码") String payPwd,
                                  @ModelAttribute @ApiParam(required = true, value = "银行卡信息") BankAccountParam bankAccountParam) {
        if (!bankAccountParam.getAccountNumber().matches("^[0-9]*$")) {
            return successCreated(ResultCode.BANK_ACCOUNT_ERROR);
        }
        if (bankAccountParam.getAccountNumber().length() > 26 || bankAccountParam.getAccountNumber().length() < 12) {
            return successCreated(ResultCode.BANK_ACCOUNT_LENTH_OUT_OF_RANGE);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result flag = propertyInfoService.varifyPayPwd(userNum, payPwd);
        if (flag.getModel() != null && (Boolean) flag.getModel()) {
            bankAccountParam.setUserType(com.lawu.eshop.framework.web.impl.constants.UserTypeEnum.MERCHANT);
            return bankAccountService.saveBankAccount(userNum, bankAccountParam);
        } else {
            return successCreated(ResultCode.PAY_PWD_ERROR);
        }

    }

    /**
     * 删除已经绑定的银行卡
     *
     * @param token
     * @param id
     * @return
     */
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "删除银行卡", notes = "删除银行卡[1002]（张荣成）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                         @PathVariable @ApiParam(required = true, value = "id") Long id) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<Boolean> bankRs = cashManageFrontService.isExistCash(userNum, id);
        if (isSuccess(bankRs)) {
            if (bankRs.getModel()) {
                return successCreated(ResultCode.BANK_CASH_EXIST);
            } else {
                bankAccountService.delete(id);
            }
        }
        return successDelete();
    }

    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @Authorization
    @ApiOperation(value = "单个查询", notes = "单个查询（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "selectAccount/{id}", method = RequestMethod.GET)
    public Result<BankAccountDTO> selectAccount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                @PathVariable @ApiParam(required = true, value = "id") Long id) {
        return successCreated(bankAccountService.selectAccount(id));
    }

    @SuppressWarnings("unchecked")
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "修改银行卡", notes = "修改银行卡[6000|6021]（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "updateBankAccount/{id}", method = RequestMethod.PUT)
    public Result<BankAccountDTO> updateBankAccount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                    @PathVariable @ApiParam(required = true, value = "id") Long id,
                                                    @RequestParam @ApiParam(required = true, value = "支付密码") String payPwd,
                                                    @ModelAttribute @ApiParam(required = true, value = "银行卡信息") BankAccountParam bankAccountParam) {

        if (!bankAccountParam.getAccountNumber().matches("^[0-9]*$")) {
            return successCreated(ResultCode.BANK_ACCOUNT_ERROR);
        }
        if (bankAccountParam.getAccountNumber().length() > 26 || bankAccountParam.getAccountNumber().length() < 12) {
            return successCreated(ResultCode.BANK_ACCOUNT_LENTH_OUT_OF_RANGE);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<Boolean> bankRs = cashManageFrontService.isExistCash(userNum, id);
        if (!isSuccess(bankRs)) {
            return successCreated(bankRs.getRet());
        }
        if (bankRs.getModel()) {
            return successCreated(ResultCode.BANK_CASH_EXIST);
        } else {
            Result flag = propertyInfoService.varifyPayPwd(userNum, payPwd);
            if (flag.getModel() != null && (Boolean) flag.getModel()) {
                bankAccountParam.setUserType(com.lawu.eshop.framework.web.impl.constants.UserTypeEnum.MERCHANT);
                return bankAccountService.updateBankAccount(id, userNum, bankAccountParam);
            } else {
                return successCreated(ResultCode.PAY_PWD_ERROR);
            }
        }
    }

    @Audit(date = "2017-10-04", reviewer = "杨清华")
    @SuppressWarnings("unchecked")
    @Authorization
    @ApiOperation(value = "获取银行卡用户名称", notes = "获取银行卡用户名称（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "selectBankName", method = RequestMethod.GET)
    public Result<BankAccountNameDTO> selectBankName(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<BankAccountNameDTO> result = bankAccountService.selectBankName(userNum);
        //初次绑定获取负责人姓名
        if (result.getModel().getAccountName() == null) {
            Result<String> nameRs = merchantStoreService.getPrincipalName(UserUtil.getCurrentUserId(getRequest()));
            result.getModel().setAccountName(nameRs.getModel());
        }
        return successGet(result);
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "添加第三方账号", notes = "添加第三方账号[1000|1004|1023|6033]（杨清华）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveThirdAccount", method = RequestMethod.POST)
    public Result saveThirdAccount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                   @RequestParam @ApiParam(required = true, value = "支付密码") String payPwd,
                                   @ModelAttribute @Valid BankAccountThirdAccountParam bankAccountThirdAccountParam, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result flag = propertyInfoService.varifyPayPwd(userNum, payPwd);
        if (flag.getModel() != null && (Boolean) flag.getModel()) {
            BankAccountThirdAccountDataParam bankAccountThirdAccountDataParam = new BankAccountThirdAccountDataParam();
            bankAccountThirdAccountDataParam.setUserNum(userNum);
            bankAccountThirdAccountDataParam.setAccountName(bankAccountThirdAccountParam.getAccountName());
            bankAccountThirdAccountDataParam.setAccountNumber(bankAccountThirdAccountParam.getAccountNumber());
            bankAccountThirdAccountDataParam.setBankAccountTypeEnum(bankAccountThirdAccountParam.getBankAccountTypeEnum());
            bankAccountThirdAccountDataParam.setUserTypeEnum(UserTypeEnum.MERCHANT);
            return bankAccountService.saveThirdAccount(bankAccountThirdAccountDataParam);
        } else {
            return successCreated(ResultCode.PAY_PWD_ERROR);
        }
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "修改第三方账号", notes = "修改第三方账号[1000|1004|1023|6033]（杨清华）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "updateThirdAccount", method = RequestMethod.POST)
    public Result updateThirdAccount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                     @RequestParam @ApiParam(required = true, value = "支付密码") String payPwd,
                                     @ModelAttribute @Valid BankAccountThirdAccountEditParam bankAccountThirdAccountEditParam, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result flag = propertyInfoService.varifyPayPwd(userNum, payPwd);
        if (flag.getModel() != null && (Boolean) flag.getModel()) {
            BankAccountThirdAccountEditDataParam bankAccountThirdAccountEditDataParam = new BankAccountThirdAccountEditDataParam();
            bankAccountThirdAccountEditDataParam.setId(bankAccountThirdAccountEditParam.getId());
            bankAccountThirdAccountEditDataParam.setUserNum(userNum);
            bankAccountThirdAccountEditDataParam.setAccountName(bankAccountThirdAccountEditParam.getAccountName());
            bankAccountThirdAccountEditDataParam.setAccountNumber(bankAccountThirdAccountEditParam.getAccountNumber());
            bankAccountThirdAccountEditDataParam.setBankAccountTypeEnum(bankAccountThirdAccountEditParam.getBankAccountTypeEnum());
            bankAccountThirdAccountEditDataParam.setUserTypeEnum(UserTypeEnum.MERCHANT);
            return bankAccountService.updateThirdAccount(bankAccountThirdAccountEditDataParam);
        } else {
            return successCreated(ResultCode.PAY_PWD_ERROR);
        }
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "我绑定的银行卡(第三方账户)", notes = "我绑定的银行卡(第三方账户)[]（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectBankAccountByUserNum", method = RequestMethod.GET)
    public Result<BankAccountThirdDTO> selectBankAccountByUserNum(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return successGet(bankAccountService.selectBankAccountByUserNum(userNum));
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "根据ID获取第三方账号", notes = "根据ID获取第三方账号（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "selectThirdAccount/{id}", method = RequestMethod.GET)
    public Result<BankThirdAccountDTO> selectThirdAccount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                          @PathVariable @ApiParam(required = true, value = "id") Long id) {
        return successGet(bankAccountService.selectThirdAccount(id));
    }

    @Audit(date = "2018-03-01", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "绑定微信账号", notes = "绑定微信账号（杨清华）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveWxAccount", method = RequestMethod.POST)
    public Result saveWxAccount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                @RequestParam @ApiParam(required = true, value = "支付密码") String payPwd,
                                @ModelAttribute @Valid BandingWxParam bandingWxParam, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result flag = propertyInfoService.varifyPayPwd(userNum, payPwd);
        if (flag.getModel() != null && (Boolean) flag.getModel()) {
            Result<WeixinUserDTO> wxResult = authService.getMerchantAuthUserInfo(bandingWxParam.getCode());
            if(!isSuccess(wxResult)){
                return successCreated(ResultCode.WX_AUTH_FAIL);
            }
            BandingWxDataParam bandingWxDataParam = new BandingWxDataParam();
            bandingWxDataParam.setUserNum(userNum);
            bandingWxDataParam.setAccountName(bandingWxParam.getAccountName());
            bandingWxDataParam.setAccountNumber(wxResult.getModel().getOpenid());
            bandingWxDataParam.setWxNickname(wxResult.getModel().getNickname());
            bandingWxDataParam.setUserTypeEnum(UserTypeEnum.MERCHANT);
            return bankAccountService.saveWxAccount(bandingWxDataParam);
        } else {
            return successCreated(ResultCode.PAY_PWD_ERROR);
        }
    }

    @Audit(date = "2018-03-01", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "修改绑定微信账号", notes = "修改绑定微信账号（杨清华）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "updateWxAccount", method = RequestMethod.POST)
    public Result updateWxAccount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                  @RequestParam @ApiParam(required = true, value = "支付密码") String payPwd,
                                  @ModelAttribute @Valid BandingWxEditParam bandingWxEditParam, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result flag = propertyInfoService.varifyPayPwd(userNum, payPwd);
        if (flag.getModel() != null && (Boolean) flag.getModel()) {
            BandingWxEditDataParam bandingWxEditDataParam = new BandingWxEditDataParam();
            if(StringUtils.isNotBlank(bandingWxEditParam.getCode())){
                Result<WeixinUserDTO> wxResult = authService.getMerchantAuthUserInfo(bandingWxEditParam.getCode());
                if(!isSuccess(wxResult)){
                    return successCreated(ResultCode.WX_AUTH_FAIL);
                }
                bandingWxEditDataParam.setAccountNumber(wxResult.getModel().getOpenid());
                bandingWxEditDataParam.setWxNickname(wxResult.getModel().getNickname());
            }
            bandingWxEditDataParam.setId(bandingWxEditParam.getId());
            bandingWxEditDataParam.setUserNum(userNum);
            bandingWxEditDataParam.setAccountName(bandingWxEditParam.getAccountName());
            bandingWxEditDataParam.setUserTypeEnum(UserTypeEnum.MERCHANT);
            return bankAccountService.updateWxAccount(bandingWxEditDataParam);
        } else {
            return successCreated(ResultCode.PAY_PWD_ERROR);
        }
    }
}

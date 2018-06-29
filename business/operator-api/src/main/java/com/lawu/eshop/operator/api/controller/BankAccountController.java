package com.lawu.eshop.operator.api.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.BankAccountService;
import com.lawu.eshop.operator.api.service.UserService;
import com.lawu.eshop.operator.dto.UserListDTO;
import com.lawu.eshop.property.dto.BankAccountDTO;
import com.lawu.eshop.property.dto.BankAccountOperatorDTO;
import com.lawu.eshop.property.dto.BankDTO;
import com.lawu.eshop.property.param.BankAccountOperatorParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
/**
 * 会员银行卡管理
 * @author zhangrc
 * @date 2017/10/24
 *
 */
@Api(tags = "bankAccount")
@RestController
@RequestMapping(value = "bankAccount/")
public class BankAccountController extends BaseController{
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private UserService userService;

    @ApiOperation(value = "用户银行卡列表", notes = "用户银行卡列表[]（张荣成）", httpMethod = "GET")
    @PageBody
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("bank:list")
    @RequestMapping(value = "selectBankOperator", method = RequestMethod.GET)
    public Result<List<BankAccountOperatorDTO>> selectBankOperator(@RequestParam @ApiParam(required = true, value = "用户编号") String userNum) {
    	Result<List<BankAccountOperatorDTO>> result = bankAccountService.selectBankOperator(userNum);
    	List<BankAccountOperatorDTO> list = result.getModel();
    	if(list != null && !list.isEmpty()){
             for(BankAccountOperatorDTO dto : list){
                 if (dto.getAuditorId() != null && dto.getAuditorId() > 0) {
                     Result<UserListDTO> userListDTOResult = userService.findUserById(dto.getAuditorId());
                     if (isSuccess(userListDTOResult)) {
                    	 dto.setAuditorName(userListDTOResult.getModel().getName());
                     }
                 }
             }
         }
    	return result;
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "单个查询", notes = "单个查询（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequiresPermissions("bank:get")
    @RequestMapping(value = "selectAccount/{id}", method = RequestMethod.GET)
    public Result<BankAccountDTO> selectAccount(
                         @PathVariable @ApiParam(required = true, value = "id") Long id) {
        return successCreated(bankAccountService.selectAccount(id));
    }

	@SuppressWarnings("unchecked")
    @ApiOperation(value = "修改银行卡", notes = "修改银行卡[2011|6000|6021]（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
	@RequiresPermissions("bank:edit")
    @RequestMapping(value = "updateBankOperator/{id}", method = RequestMethod.PUT)
    public Result<BankAccountDTO> updateBankOperator(
                         @PathVariable @ApiParam(required = true, value = "id") Long id,
                         @ModelAttribute @ApiParam(required = true, value = "银行卡信息") BankAccountOperatorParam bankAccountParam) {
    	if(!bankAccountParam.getAccountNumber().matches("^[0-9]*$")){
    		return successCreated(ResultCode.BANK_ACCOUNT_ERROR);
    	}
    	if(bankAccountParam.getAccountNumber().length() > 26 || bankAccountParam.getAccountNumber().length() < 12) {
    		return successCreated(ResultCode.BANK_ACCOUNT_LENTH_OUT_OF_RANGE);
    	}
    	
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            bankAccountParam.setAuditorId(userResult.getModel().getId());
        }
    		
    	return bankAccountService.updateBankOperator(id, bankAccountParam);
			
    }
	
	
	/**
	 * 查询所有银行
	 * @return
	 */
	@ApiOperation(value = "银行数据信息查询", notes = "银行数据信息查询[]（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "all", method = RequestMethod.GET)
    public Result<List<BankDTO>> findBank() {
		Result<List<BankDTO>> DTOS = bankAccountService.findBank();
		return  DTOS;
    }
	
	
}

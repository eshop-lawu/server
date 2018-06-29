package com.lawu.eshop.merchant.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.CashManageFrontService;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.eshop.merchant.api.service.TransactionDetailService;
import com.lawu.eshop.property.constants.MerchantTransactionCategoryEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.dto.MonthlyBillDTO;
import com.lawu.eshop.property.dto.TransactionDetailInfoMerchantDTO;
import com.lawu.eshop.property.dto.TransactionDetailToMerchantDTO;
import com.lawu.eshop.property.dto.TransactionTypeDTO;
import com.lawu.eshop.property.dto.WithdrawCashStatusDTO;
import com.lawu.eshop.property.dto.foreign.TransactionDetailOfMerchantDTO;
import com.lawu.eshop.property.param.TransactionDetailQueryForMerchantParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMerchantForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMerchantForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny 
 * @date 2017/3/29
 */
@Api(tags = "transactionDetail")
@RestController
@RequestMapping(value = "transactionDetail/")
public class TransactionDetailController extends BaseController {

    @Autowired
    private TransactionDetailService transactionDetailService;
    
    @Autowired
    private CashManageFrontService cashManageFrontService;
    
    @Autowired
    private MerchantStoreService merchantStoreService;
    
    /**
     * 根据用户编号分页获取交易明细列表。
     * 
     * @param token
     * @param param 查询参数
     * @return
     */
    @Deprecated
    @SuppressWarnings("unchecked")
	@Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "获取交易明细列表", notes = "根据用户编号分页获取交易明细列表。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "findPageByUserNum", method = RequestMethod.GET)
    public Result<Page<TransactionDetailToMerchantDTO>> page(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询参数") TransactionDetailQueryForMerchantParam param) {
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	
    	Result<Page<TransactionDetailToMerchantDTO>> result = transactionDetailService.findPageByUserNumForMerchant(userNum, param);
    	if (!isSuccess(result)) {
    		return successGet(result.getRet());
    	}
    	
    	// 如果交易类型为提现需要获取提现状态
    	List<TransactionDetailToMerchantDTO> transactionDetailDTOList = result.getModel().getRecords();
    	
    	// 把所有需要查询的id放入set
    	List<Long> ids = new ArrayList<>();
    	for (TransactionDetailToMerchantDTO item : transactionDetailDTOList) {
    		if (MerchantTransactionTypeEnum.WITHDRAW.equals(item.getTransactionType()) && !StringUtils.isEmpty(item.getBizId())) {
    			ids.add(Long.valueOf(item.getBizId()));
    		}
    	}
    	
    	//如果list中不存在提现的记录，不需要查询，直接返回数据
    	if (ids.isEmpty()) {
    		return successGet(result);
    	}
    	
    	Result<List<WithdrawCashStatusDTO>> resultWithdrawCashStatusDTOList = cashManageFrontService.findCashDetailStatus(ids);
    	
    	if (!isSuccess(resultWithdrawCashStatusDTOList)) {
    		return successGet(resultWithdrawCashStatusDTOList.getRet());
    	}
    	
    	// 把数据缓存到Map
    	Map<String, WithdrawCashStatusDTO> withdrawCashStatusDTOMap = new HashMap<>();
    	for (WithdrawCashStatusDTO item : resultWithdrawCashStatusDTOList.getModel()) {
    		withdrawCashStatusDTOMap.put(item.getId().toString(), item);
    	}
    	
    	// 组合数据
    	WithdrawCashStatusDTO withdrawCashStatusDTO = null;
    	for (TransactionDetailToMerchantDTO item : transactionDetailDTOList) {
    		if (!StringUtils.isEmpty(item.getBizId())) {
    			withdrawCashStatusDTO = withdrawCashStatusDTOMap.get(item.getBizId());
    			if (withdrawCashStatusDTO != null) {
    				item.setStatus(withdrawCashStatusDTO.getStatus());
    			}
    		}
    	}
    	
    	return successGet(result);
    }

    @Audit(date = "2017-10-20", reviewer = "杨清华")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户的所有交易类型。", notes = "获取商家的所有交易类型。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "transactionType", method = RequestMethod.GET)
    public Result<List<TransactionTypeDTO>> transactionType(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        Result<ManageTypeEnum> result = merchantStoreService.getManageType(merchantId);
        if (!isSuccess(result)) {
            return successGet(result);
        }
        ManageTypeEnum manageType = result.getModel();
        List<TransactionTypeDTO> rtn = new ArrayList<>();
        for (MerchantTransactionCategoryEnum item : MerchantTransactionCategoryEnum.values()) {
            if (item.getType() != null) {
                for (ManageTypeEnum manageTypeItem : item.getType()) {
                    if (manageType == null || manageTypeItem.getVal().equals(manageType.getVal())) {
                        TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
                        transactionTypeDTO.setName(item.getName());
                        transactionTypeDTO.setValue(item.name());
                        rtn.add(transactionTypeDTO);
                        break;
                    }
                }
            }
        }
        return successGet(rtn);
    }

    @Audit(date = "2017-10-20", reviewer = "杨清华")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取交易明细列表", notes = "分页获取交易明细列表。[1004]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    public Result<Page<TransactionDetailOfMerchantDTO>> page(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询参数") @Valid TransactionDetailQueryForMerchantForeignParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<Page<TransactionDetailOfMerchantDTO>> result = transactionDetailService.page(userNum, param);
        if (!isSuccess(result)) {
            return successGet(result);
        }
        return successGet(result);
    }

    @Audit(date = "2017-10-20", reviewer = "杨清华")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取月结账单", notes = "获取月结账单。[1004]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "monthlyBill", method = RequestMethod.GET)
    public Result<MonthlyBillDTO> monthlyBill(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询参数") @Valid TransactionDetailMonthlyBillOfMerchantForeignParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<MonthlyBillDTO> result = transactionDetailService.monthlyBill(userNum, param);
        if (!isSuccess(result)) {
            return successGet(result);
        }
        return successGet(result);
    }

    @Audit(date = "2017-11-24", reviewer = "孙林青")
    @ApiOperation(value = "获取交易明细详情", notes = "分页获取交易明细详情。[]（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Result<TransactionDetailInfoMerchantDTO> getById(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable @ApiParam(required = true, value = "列表ID") Long id) {
        Result<TransactionDetailInfoMerchantDTO> result = transactionDetailService.getById(id);
        if (!isSuccess(result)) {
            return successGet(result);
        }
        return successGet(result);
    }
}

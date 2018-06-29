package com.lawu.eshop.member.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.lawu.eshop.member.api.service.ShoppingOrderService;
import com.lawu.eshop.property.dto.TransactionDetailInfoMemberDTO;
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
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.CashManageFrontService;
import com.lawu.eshop.member.api.service.TransactionDetailService;
import com.lawu.eshop.property.constants.MemberTransactionCategoryEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.dto.MonthlyBillDTO;
import com.lawu.eshop.property.dto.TransactionDetailDTO;
import com.lawu.eshop.property.dto.TransactionDetailToMemberDTO;
import com.lawu.eshop.property.dto.TransactionTypeDTO;
import com.lawu.eshop.property.dto.WithdrawCashStatusDTO;
import com.lawu.eshop.property.dto.foreign.TransactionDetailOfMemberDTO;
import com.lawu.eshop.property.param.TransactionDetailQueryForMemberParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMemberForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMemberForeignParam;
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
    private ShoppingOrderService shoppingOrderService;
    
    @Deprecated
    @SuppressWarnings("unchecked")
	@Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "获取交易明细列表", notes = "根据用户编号分页获取交易明细列表。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public Result<Page<TransactionDetailToMemberDTO>> page(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询资料") @Valid TransactionDetailQueryForMemberParam param, BindingResult bindingResult) {
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	
    	Result<Page<TransactionDetailToMemberDTO>> result = transactionDetailService.findPageByUserNumForMember(userNum, param);
    	if (!isSuccess(result)) {
    		return successGet(result.getRet());
    	}
    	
    	// 如果交易类型为提现需要获取提现状态
    	List<TransactionDetailToMemberDTO> transactionDetailDTOList = result.getModel().getRecords();
    	
    	// 把所有需要查询的id放入set
    	List<Long> ids = new ArrayList<>();
    	for (TransactionDetailToMemberDTO item : transactionDetailDTOList) {
    		if (MemberTransactionTypeEnum.WITHDRAW.equals(item.getTransactionType()) && !StringUtils.isEmpty(item.getBizId())) {
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
    	for (TransactionDetailDTO item : transactionDetailDTOList) {
    		if (!StringUtils.isEmpty(item.getBizId())) {
    			withdrawCashStatusDTO = withdrawCashStatusDTOMap.get(item.getBizId());
    			if (withdrawCashStatusDTO != null) {
    				item.setStatus(withdrawCashStatusDTO.getStatus());
    			}
    		}
    	}
    	
    	return successGet(result);
    }
    
    @Deprecated
	@Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "获取用户的所有交易类型。", notes = "获取用户的所有交易类型。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getAllTransactionType", method = RequestMethod.GET)
    public Result<List<TransactionTypeDTO>> page(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
    	List<TransactionTypeDTO> list = new ArrayList<>();
    	for (MemberTransactionTypeEnum item : MemberTransactionTypeEnum.values()) {
    		TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
    		transactionTypeDTO.setName(item.getName());
    		transactionTypeDTO.setValue(item.name());
    		list.add(transactionTypeDTO);
    	}
    	
    	return successGet(list);
    }

    @Audit(date = "2017-10-20", reviewer = "杨清华")
    @ApiOperation(value = "获取用户的所有交易类型。", notes = "获取用户的所有交易类型。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "transactionType", method = RequestMethod.GET)
    public Result<List<TransactionTypeDTO>> transactionType(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        List<TransactionTypeDTO> list = new ArrayList<>();
        for (MemberTransactionCategoryEnum item : MemberTransactionCategoryEnum.values()) {
            TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO();
            transactionTypeDTO.setName(item.getName());
            transactionTypeDTO.setValue(item.name());
            list.add(transactionTypeDTO);
        }
        return successGet(list);
    }

    @Audit(date = "2017-10-20", reviewer = "杨清华")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取交易明细列表", notes = "分页获取交易明细列表。[1004]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    public Result<Page<TransactionDetailOfMemberDTO>> page(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询参数") @Valid TransactionDetailQueryForMemberForeignParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<Page<TransactionDetailOfMemberDTO>> result = transactionDetailService.page(userNum, param);
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
    public Result<MonthlyBillDTO> monthlyBill(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "查询参数") @Valid TransactionDetailMonthlyBillOfMemberForeignParam param, BindingResult bindingResult) {
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
    public Result<TransactionDetailInfoMemberDTO> getById(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable @ApiParam(required = true, value = "列表ID") Long id) {
        Result<TransactionDetailInfoMemberDTO> result = transactionDetailService.getById(id);
        if (!isSuccess(result)) {
            return successGet(result);
        }
        if (MemberTransactionTypeEnum.PAY_ORDERS.getValue().equals(result.getModel().getMemberTransactionTypeEnum().getValue())){
            String bizIds = result.getModel().getBizId();
            Result<BigDecimal> orderResult = shoppingOrderService.selectDeductionPointsAmount(bizIds);
            result.getModel().setDeductionPointsAmount(orderResult.getModel());
            result.getModel().setTotalAmount(result.getModel().getAmount().add(orderResult.getModel()));
        }
        return successGet(result);
    }
}

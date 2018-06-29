package com.lawu.eshop.member.api.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.concurrentqueue.requestctrl.ConcurrentTaskExecutor;
import com.lawu.eshop.concurrentqueue.impl.AbstractBaseConcurrentTask;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.ShoppingCartExtendService;
import com.lawu.eshop.member.api.service.ShoppingCartService;
import com.lawu.eshop.order.dto.foreign.ShoppingCartQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingCartSettlementDTO;
import com.lawu.eshop.order.param.ShoppingCartParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.eshop.order.param.foreign.BatchCreateOrderParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderBuyNowCreateOrderForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderSettlementForeignParam;
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
 * @date 2017/3/27
 */
@Api(tags = "shoppingCart")
@RestController
@RequestMapping(value = "shoppingCart/")
public class ShoppingCartController extends BaseController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    private ShoppingCartExtendService shoppingcartExtendService;
    
    @Autowired
    private ConcurrentTaskExecutor concurrentTaskExecutor;
    
    /**
     * 加入购物车。
     * 
     * @param token
     * @param param
     * @return
     */
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "加入购物车", notes = "加入购物车。[1004|1005]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "加入购物车参数") @Validated ShoppingCartParam param, BindingResult bindingResult) {
    	
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
    	Result result = shoppingcartExtendService.save(memberId, param);
    	if (!isSuccess(result)) {
    		return successCreated(result);
    	}
    	
    	return successCreated();
    }

    /**
     * 根据memberId查询用户的购物车列表。
     * 
     * @param token
     * @return
     */
	@SuppressWarnings("unchecked")
	@Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "查询购物车列表", notes = "查询购物车列表。[1004]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "findListByMemberId", method = RequestMethod.GET)
    public Result<ShoppingCartQueryDTO> findListByMemberId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
    	
     	Result<ShoppingCartQueryDTO> result = shoppingcartExtendService.findListByMemberId(memberId);
    	if (!isSuccess(result)) {
    		return successGet(result);
    	}
     	
    	return successGet(result);
    }

	/**
	 * 根据id更新购物车的商品（使用实时更新不采用批量更新的方式）。
	 *
	 * @param token
	 * @param id
	 * @param param
	 * @param bindingResult
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "更新购物车商品", notes = "根据id更新购物车的商品（使用实时更新不采用批量更新的方式）。[1004|1100|1024]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public Result update(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable("id") @ApiParam(name = "id", required = true, value = "购物车ID") Long id, @ModelAttribute @ApiParam(name = "param", value = "加入购物车参数") @Validated ShoppingCartUpdateParam param, BindingResult bindingResult) {
    	String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
    	Result result = shoppingCartService.update(id, memberId, param);
    	if (!isSuccess(result)) {
    		return successCreated(result);
    	}
    			
    	return successCreated();
    }
    
    @SuppressWarnings({ "rawtypes"})
	@Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "删除购物车的商品", notes = "根据id列表删除购物车的商品。[1004|1100|1024]（蒋鑫俊）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @Authorization
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Result delete(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestBody @ApiParam(name = "ids", required = true, value = "购物车ID列表") @Validated @NotNull List<Long> ids, BindingResult bindingResult) {
    	String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
    	
    	Result result = shoppingCartService.delete(memberId, ids);
    	if (!isSuccess(result)) {
    		return successCreated(result);
    	}
    	
    	return successDelete();
    }

	/**
	 * 根据购物车id列表结算购物车的商品生成结算数据
	 *
	 * @param token
	 * @param ids
	 * @param bindingResult
	 * @return
	 */
	@Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "购物车的商品结算", notes = "根据购物车id列表结算购物车的商品生成结算数据。[1003]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
	@RequestMapping(value = "settlement", method = RequestMethod.POST)
	public Result<ShoppingCartSettlementDTO> settlement(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestBody @ApiParam(name = "ids", required = true, value = "购物车ID列表") @Validated @NotNull List<Long> ids, BindingResult bindingResult) {
    	String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	
    	String memberNum = UserUtil.getCurrentUserNum(getRequest());
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
    	Result<ShoppingCartSettlementDTO> result = shoppingcartExtendService.settlement(ids, memberNum, memberId);
    	if (!isSuccess(result)) {
    		return successGet(result.getRet());
    	}
    	
    	return successGet(result);
    }
    
    /**
     * 根据订单参数列表批量创建订单
     * 
     * @param params 订单参数
     * @return
     */
	@Deprecated
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings({"unchecked" })
	@ApiOperation(value = "创建订单", notes = "根据订单参数创建订单。[1002|1003|1004|1005|4018]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
	@RequestMapping(value = "createOrder", method = RequestMethod.POST)
	public Result<List<Long>> createOrder(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestBody @ApiParam(name = "param", required = true, value = "订单参数") @Validated List<ShoppingOrderSettlementForeignParam> params, BindingResult bindingResult) {
    	String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
    	String memberNum = UserUtil.getCurrentUserNum(getRequest());
    	BatchCreateOrderParam param = new BatchCreateOrderParam();
    	param.setAddressId(params.get(0).getAddressId());
    	param.setSettlementParams(params);
    	Result<List<Long>> result = shoppingcartExtendService.createOrder(memberId, param, memberNum);
    	if (!isSuccess(result)) {
    		return successCreated(result.getRet());
    	}
    	return successCreated(result);
    }

	@Audit(date = "2018-03-17", reviewer = "杨清华")
    @SuppressWarnings({"unchecked" })
    @ApiOperation(value = "创建订单", notes = "根据订单参数创建订单。[1002|1003|1004|1005|1037|4018]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "batchCreateOrder", method = RequestMethod.POST)
    public Result<List<Long>> batchCreateOrder(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestBody @Validated BatchCreateOrderParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        Result<List<Long>> result = shoppingcartExtendService.createOrder(memberId, param, memberNum);
        if (!isSuccess(result)) {
            return successCreated(result.getRet());
        }
        return successCreated(result);
    }
    
    @Audit(date = "2017-05-03", reviewer = "孙林青")
    @SuppressWarnings({ "unchecked" })
    @ApiOperation(value = "立即购买", notes = "立即购买。[1003|1004|1005]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "buyNow", method = RequestMethod.POST)
    public Result<ShoppingCartSettlementDTO> buyNow(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
            @ModelAttribute @ApiParam(name = "param", value = "加入购物车参数") @Validated ShoppingCartParam param,
            BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        Result<ShoppingCartSettlementDTO> result = shoppingcartExtendService.buyNow(memberId, memberNum, param);
        return successCreated(result);
    }

	@SuppressWarnings("unchecked")
    @Audit(date = "2017-05-12", reviewer = "孙林青")
	@ApiOperation(value = "立即购买，创建订单", notes = "立即购买，创建订单。[1003|1004|1005|4018]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
	@RequestMapping(value = "buyNowCreateOrder", method = RequestMethod.POST)
	public Result<Long> buyNowCreateOrder(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(name = "param", value = "创建订单参数") @Validated ShoppingOrderBuyNowCreateOrderForeignParam param, BindingResult bindingResult) {
    	String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
    	String memberNum = UserUtil.getCurrentUserNum(getRequest());
    	Result<Long> result = null;
    	if (param.getActivityProductModelId() == null) {
        	result = shoppingcartExtendService.buyNowCreateOrder(memberId, param, memberNum);
    	} else {
    	    // 通过线程池拦截部分请求
    	    result = (Result<Long>) concurrentTaskExecutor.execute(new AbstractBaseConcurrentTask<Result<Long>, Result<Long>>() {
                @Override
                public Result<Long> execute() {
                    return shoppingcartExtendService.buyNowCreateOrder(memberId, param, param.getActivityProductModelId(), memberNum);
                }
                
                @Override
                public Result<Long> executeWhenSuccess(Result<Long> successInfo) {
                    return successInfo;
                }
            });
    	}
    	return successCreated(result);
    }
}

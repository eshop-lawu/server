package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.ShoppingOrderExtendService;
import com.lawu.eshop.operator.api.service.ShoppingOrderService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForOperatorDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToOperatorDTO;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToOperatorParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderUpdateInfomationForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny
 * @date 2017/04/13
 */
@Api(tags = "shoppingOrder")
@RestController
@RequestMapping(value = "shoppingOrder/")
public class ShoppingOrderController extends BaseController {

    @Autowired
    private ShoppingOrderService shoppingOrderService;

    @Autowired
    private ShoppingOrderExtendService shoppingOrderExtendService;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "分页查询订单", notes = "根据查询参数分页查询。[1004]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    @RequiresPermissions("order:list")
    @RequestMapping(value = "selectPage", method = RequestMethod.GET)
    public Result<Page<ShoppingOrderQueryToOperatorDTO>> selectPageByMerchantId(@ModelAttribute @ApiParam(name = "param", value = "查询参数") ShoppingOrderQueryForeignToOperatorParam param) {
        // 校验参数
        if (param == null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }

        Result<Page<ShoppingOrderQueryToOperatorDTO>> result = shoppingOrderService.selectPage(param);
        if (!isSuccess(result)) {
            return successGet(result.getRet());
        }

        return successGet(result);
    }
    
    /**
     * @deprecated
     * @see #detail(Long)
     * @param id
     * @return
     * @author jiangxinjun
     * @date 2017年9月6日
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询购物订单详情", notes = "根据购物订单id查询购物订单详情。[1100]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderExtendDetailDTO> get(@PathVariable("id") @ApiParam(value = "购物订单id") Long id) {
        Result<ShoppingOrderExtendDetailDTO> result = shoppingOrderService.get(id);
        return successGet(result);
    }
    
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询购物订单详情", notes = "根据购物订单id查询购物订单详情。[1100]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public Result<ShoppingOrderDetailDTO> detail(@PathVariable("id") @ApiParam(value = "购物订单id") Long id) {
        Result<ShoppingOrderDetailDTO> result = shoppingOrderService.detail(id);
        return successGet(result);
    }

    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ORDER_INFO_UPDATE,idParamName ="id")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "更新订单信息", notes = "更新订单信息。[1002|1003]（蒋鑫俊）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("order:edit")
    @RequestMapping(value = "updateInformation/{id}", method = RequestMethod.POST)
    public Result updateInformation(@PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id, @ModelAttribute @ApiParam(name = "param", value = "更新参数") @Validated ShoppingOrderUpdateInfomationForeignParam param, BindingResult bindingResult) {

        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        Result result = shoppingOrderExtendService.updateInformation(id, param);

        if (!isSuccess(result)) {
            return successCreated(result.getRet());
        }

        return successCreated(result);
    }

    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ORDER_CANCEL_TRANSACTION,idParamName ="id")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "取消购物订单", notes = "取消购物订单。[1002|1003|4002]（蒋鑫俊）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("order:cancel")
    @RequestMapping(value = "cancelOrder/{id}", method = RequestMethod.PUT)
    public Result cancelOrder(@PathVariable("id") @ApiParam(name = "id", value = "购物订单id") Long id) {

        Result resultShoppingOrderExpressDTO = shoppingOrderService.cancelOrder(id);

        if (!isSuccess(resultShoppingOrderExpressDTO)) {
            return successCreated(resultShoppingOrderExpressDTO.getRet());
        }

        return successCreated();
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "分页查询退款记录", notes = "分页查询退款记录。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    @RequiresPermissions("refund:list")
    @RequestMapping(value = "selectRefundPage", method = RequestMethod.GET)
    public Result<Page<ShoppingOrderItemRefundForOperatorDTO>> selectRefundPage(@ModelAttribute @ApiParam(name = "param", value = "查询参数") ShoppingRefundQueryForeignParam param) {

        Result<Page<ShoppingOrderItemRefundForOperatorDTO>> result = shoppingOrderService.selectRefundPage(param);

        if (!isSuccess(result)) {
            return successGet(result.getRet());
        }
        return successGet(result);
    }
}

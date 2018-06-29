package com.lawu.eshop.merchant.api.controller;

import java.util.List;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.merchant.api.service.ShoppingOrderService;
import com.lawu.eshop.property.dto.FreezeDTO;
import com.lawu.eshop.property.param.FreezeParam;
import com.lawu.eshop.property.param.FreezeQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.PropertyInfoService;
import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny
 * @date 2017/3/29
 */
@Api(tags = "propertyInfo")
@RestController
@RequestMapping(value = "propertyInfo/")
public class PropertyInfoController extends BaseController {

    @Autowired
    private PropertyInfoService propertyInfoService;
    @Autowired
    private ShoppingOrderService shoppingOrderService;

    /**
     * 根据用户编号获取资产余额。
     *
     * @param token
     * @return
     */
    @SuppressWarnings("unchecked")
	@Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "获取资产余额", notes = "根据用户编号获取资产余额。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "balance", method = RequestMethod.GET)
    public Result<PropertyBalanceDTO> getPropertyBalance(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return successGet(propertyInfoService.getPropertyBalance(userNum));
    }

    /**
     * 根据用户编号获取积分。
     *
     * @param token
     * @return
     */
    @SuppressWarnings("unchecked")
	@Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "获取积分", notes = "根据用户编号获取积分。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "point", method = RequestMethod.GET)
    public Result<PropertyPointDTO> getPropertyPoint(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return successGet(propertyInfoService.getPropertyPoint(userNum));
    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "验证支付密码", notes = "验证支付密码(true--正确，false--错误)。[1002|1022]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "varifyPayPwd", method = RequestMethod.GET)
    public Result varifyPayPwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                               @RequestParam @ApiParam(required = true, value = "支付密码") String payPwd) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return propertyInfoService.varifyPayPwd(userNum, payPwd);
    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "获取资产余额积分", notes = "根据用户编号获取资产余额积分。[]（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getPropertyInfoMoney", method = RequestMethod.GET)
    public Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	return successGet(propertyInfoService.getPropertyInfoMoney(userNum));
    }

    @Audit(date = "2017-06-30", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取用户是否冻结", notes = "获取用户是否冻结(NO-否|YES-是)。[6026]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getPropertyinfoFreeze", method = RequestMethod.GET)
    public Result<PropertyInfoFreezeDTO> getPropertyinfoFreeze(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<PropertyInfoFreezeDTO> result = propertyInfoService.getPropertyinfoFreeze(userNum);
        return successGet(result);
    }

    @Audit(date = "2017-08-08", reviewer = "孙林青")
    @ApiOperation(value = "获取冻结资金列表", notes = "获取冻结资金列表。[]（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getFreezeList", method = RequestMethod.GET)
    public Result<Page<FreezeDTO>> getFreezeList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                 @ModelAttribute @ApiParam( value = "查询信息") FreezeParam freezeParam) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        FreezeQueryParam param = new FreezeQueryParam();
        param.setUserNum(userNum);
        param.setCurrentPage(freezeParam.getCurrentPage());
        param.setPageSize(freezeParam.getPageSize());
        Result<Page<FreezeDTO>> pageRet = propertyInfoService.getFreezeList(param);
        Page<FreezeDTO> page = pageRet.getModel();
        List<FreezeDTO> list =  page.getRecords();
        for(FreezeDTO fdo : list){
            Result<String> orderItemProductNameRet = shoppingOrderService.getOrderItemProductName(fdo.getBizId() == null ? "" : fdo.getBizId().toString());
            fdo.setTitle("购物-"+orderItemProductNameRet.getModel());
        }
        return successGet(pageRet);
    }
}

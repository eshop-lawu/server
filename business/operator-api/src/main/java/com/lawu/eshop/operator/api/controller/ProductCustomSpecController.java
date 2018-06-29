package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.ProductCustomSpecService;
import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.eshop.product.dto.ProductCustomSpecDTO;
import com.lawu.eshop.product.query.ProductCustomSpecPageQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月16日
 */
@Api(tags = "productCustomSpec")
@RestController
@RequestMapping(value = "productCustomSpec/")
public class ProductCustomSpecController extends BaseController{
	
	@Autowired
	private ProductCustomSpecService productCustomSpecService;
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "自定义类别，品牌，规格列表", notes = "自定义类别，品牌，规格列表（张荣成）", httpMethod = "POST")
	@PageBody
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequiresPermissions("customSpec:list")
	@RequestMapping(value = { "specPage" }, method = RequestMethod.POST)
	public Result<Page<ProductCustomSpecDTO>> specPage(@RequestBody @ApiParam ProductCustomSpecPageQuery query) {
		Result<Page<ProductCustomSpecDTO>> result = productCustomSpecService.specPage(query);
		return successCreated(result);
	}
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "自定义类别，品牌，规格处理", notes = "自定义类别，品牌，规格处理（张荣成）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = { "dealCustomSpec/{id}" }, method = RequestMethod.PUT)
	public Result dealCustomSpec(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id ,@RequestParam @ApiParam(name = "statusEnum", required = true, value = "状态") CustomSpecStatusEnum statusEnum) {
		Result result = productCustomSpecService.dealCustomSpec(id, statusEnum);
		return successCreated(result);
	}

}

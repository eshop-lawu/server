package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.member.api.service.ProductCategoryService;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductCategoryHotDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Yangqh
 * @date 2017/3/11
 */
@Api(tags = "productCategory")
@RestController
@RequestMapping(value = "productCategory/")
public class ProductCategoryController extends BaseController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@Audit(date = "2017-04-01", reviewer = "孙林青")
	@ApiOperation(value = "查询所有商品分类（一级）", notes = "查询所有商品分类（一级），(杨清华)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "all", method = RequestMethod.GET)
	public Result<List<ProductCategoryDTO>> findAll() {

		return productCategoryService.listRecommendProductCategory();
	}

	@Audit(date = "2017-04-01", reviewer = "孙林青")
	@ApiOperation(value = "根据ID查询商品分类", notes = "根据ID查询商品分类，(杨清华)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Result<ProductCategoryDTO> getById(@PathVariable @ApiParam(name = "id", required = true, value = "商品分类ID") Integer id) {

		ProductCategoryDTO dto = productCategoryService.getById(id);
		return successCreated(dto);
	}

	@AutoTesting
	@Audit(date = "2017-04-26", reviewer = "孙林青")
	@ApiOperation(value = "根据父ID查询商品分类", notes = "根据父ID查询商品分类，(杨清华)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "find/{parentId}", method = RequestMethod.GET)
	public Result<List<ProductCategoryDTO>> find(
			@PathVariable @ApiParam(name = "parentId", required = true, value = "父ID") Integer parentId) {

		return productCategoryService.find(parentId);
	}

	@Audit(date = "2018-04-18", reviewer = "孙林青")
	@ApiOperation(value = "查询热门商品分类", notes = "查询热门商品分类(章勇)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getHotProductCategory", method = RequestMethod.GET)
	public Result<ProductCategoryHotDTO> getHotProductCategory() {
		return productCategoryService.getHotProductCategory();
	}
}

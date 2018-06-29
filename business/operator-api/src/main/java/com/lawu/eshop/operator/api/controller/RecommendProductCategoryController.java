package com.lawu.eshop.operator.api.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.RecommendProductCategoryService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.product.dto.OperatorProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.param.EditRecommendProductCategoryParam;
import com.lawu.eshop.product.param.ListRecommendProductCategoryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
@Api(tags = "recommendProductCategory")
@RestController
@RequestMapping(value = "recommendProductCategory/")
public class RecommendProductCategoryController extends BaseController {

    @Autowired
    private RecommendProductCategoryService recommendProductCategoryService;

    @LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.PRODUCT_CATEGORY_ADD)
    @ApiOperation(value = "新增推荐商品类别", notes = "新增推荐商品类别。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("product-recommend:add")
    @RequestMapping(value = "saveRecommendProductCategory", method = RequestMethod.POST)
    public Result saveRecommendProductCategory(@ModelAttribute EditRecommendProductCategoryParam param) {
        return recommendProductCategoryService.saveRecommendProductCategory(param);
    }

    /*@ApiOperation(value = "删除推荐商品类别", notes = "根据ID推荐商品类别。[1002]（梅述全）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "deleteRecommendProductCategory/{id}", method = RequestMethod.DELETE)
    public Result deleteRecommendProductCategory(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id) {
        return recommendProductCategoryService.deleteRecommendProductCategoryById(id);
    }*/

    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.PRODUCT_CATEGORY_UPDATE,idParamName ="id")
    @ApiOperation(value = "修改推荐商品类别", notes = "修改推荐商品类别。[1002]（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("product-recommend:edit")
    @RequestMapping(value = "updateRecommendProductCategory/{id}", method = RequestMethod.POST)
    public Result updateRecommendProductCategory(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id,
                                                 @ModelAttribute EditRecommendProductCategoryParam param) {
        return recommendProductCategoryService.updateRecommendProductCategoryById(id, param);
    }

    @ApiOperation(value = "根据ID查询推荐商品类别", notes = "根据ID查询推荐商品类别。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "getRecommendProductCategory/{id}", method = RequestMethod.GET)
    public Result<OperatorProductCategoryDTO> getRecommendProductCategory(@PathVariable @ApiParam(name = "id", required = true, value = "ID") Long id) {
        return recommendProductCategoryService.getRecommendProductCategoryById(id);
    }

    @ApiOperation(value = "推荐商品类别列表", notes = "推荐商品类别列表。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("product-recommend:list")
    @PageBody
    @RequestMapping(value = "listRecommendProductCategory", method = RequestMethod.GET)
    public Result<Page<OperatorProductCategoryDTO>> listRecommendProductCategory(@ModelAttribute @ApiParam ListRecommendProductCategoryParam listRecommendProductCategoryParam) {
        return recommendProductCategoryService.listRecommendProductCategory(listRecommendProductCategoryParam);
    }

    @ApiOperation(value = "查询商品一级类别", notes = "商品一级类别列表。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("product-category-level1:list")
    @RequestMapping(value = "listLevelOneProductCategory", method = RequestMethod.GET)
    public Result<List<ProductCategoryDTO>> listLevelOneProductCategory() {
        return recommendProductCategoryService.listRecommendProductCategory();
    }

    @ApiOperation(value = "编辑是否热门", notes = "编辑是否热门。（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("product-recommend:hot")
    @RequestMapping(value = "editHot/{id}", method = RequestMethod.PUT)
    public Result editHot(@PathVariable("id") Long id, @RequestParam("isHot") Boolean isHot) {
        return recommendProductCategoryService.editHot(id, isHot);
    }
}

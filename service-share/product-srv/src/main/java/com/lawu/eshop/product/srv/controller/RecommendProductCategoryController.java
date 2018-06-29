package com.lawu.eshop.product.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.dto.OperatorProductCategoryDTO;
import com.lawu.eshop.product.dto.RecommendProductCategoryDTO;
import com.lawu.eshop.product.param.EditRecommendProductCategoryParam;
import com.lawu.eshop.product.param.ListRecommendProductCategoryParam;
import com.lawu.eshop.product.srv.bo.RecommendProductCategoryBO;
import com.lawu.eshop.product.srv.converter.RecommendProductCategoryConverter;
import com.lawu.eshop.product.srv.service.RecommendProductCategoryService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
@RestController
@RequestMapping(value = "recommendProductCategory/")
public class RecommendProductCategoryController extends BaseController {

    @Autowired
    private RecommendProductCategoryService recommendProductCategoryService;

    /**
     * 保存商品类别
     *
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "saveRecommendProductCategory", method = RequestMethod.POST)
    public Result saveRecommendProductCategory(@RequestBody EditRecommendProductCategoryParam param) {
        recommendProductCategoryService.saveRecommendProductCategory(param);
        return successCreated();
    }

    /**
     * 根据ID删除商品类别
     *
     * @param id
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "deleteRecommendProductCategory/{id}", method = RequestMethod.DELETE)
    public Result deleteRecommendProductCategory(@PathVariable Long id) {
        RecommendProductCategoryBO recommendProductCategoryBO = recommendProductCategoryService.getRecommendProductCategoryById(id);
        if (recommendProductCategoryBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        recommendProductCategoryService.deleteRecommendProductCategoryById(id);
        return successDelete();
    }

    /**
     * 根据ID修改商品类别
     *
     * @param id
     * @param param
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateRecommendProductCategory/{id}", method = RequestMethod.POST)
    public Result updateRecommendProductCategory(@PathVariable Long id, @RequestBody EditRecommendProductCategoryParam param) {
        RecommendProductCategoryBO recommendProductCategoryBO = recommendProductCategoryService.getRecommendProductCategoryById(id);
        if (recommendProductCategoryBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        recommendProductCategoryService.updateRecommendProductCategoryById(id, param);
        return successCreated();
    }

    /**
     * 根据ID查询商品类别
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getRecommendProductCategory/{id}", method = RequestMethod.GET)
    public Result<OperatorProductCategoryDTO> getRecommendProductCategory(@PathVariable Long id) {
        RecommendProductCategoryBO recommendProductCategoryBO = recommendProductCategoryService.getRecommendProductCategoryById(id);
        return successGet(RecommendProductCategoryConverter.convertOperatorDTO(recommendProductCategoryBO));
    }

    /**
     * 商品类别列表
     *
     * @param listRecommendProductCategoryParam
     * @return
     */
    @RequestMapping(value = "listRecommendProductCategory", method = RequestMethod.POST)
    public Result<Page<OperatorProductCategoryDTO>> listRecommendProductCategory(@RequestBody ListRecommendProductCategoryParam listRecommendProductCategoryParam) {
        Page<RecommendProductCategoryBO> recommendProductCategoryBOPage = recommendProductCategoryService.listRecommendProductCategory(listRecommendProductCategoryParam);
        Page<OperatorProductCategoryDTO> page = new Page<>();
        page.setRecords(RecommendProductCategoryConverter.convertOperatorDTO(recommendProductCategoryBOPage.getRecords()));
        page.setCurrentPage(recommendProductCategoryBOPage.getCurrentPage());
        page.setTotalCount(recommendProductCategoryBOPage.getTotalCount());
        return successCreated(page);
    }

    /**
     * 商品类别列表
     *
     * @return
     */
    @RequestMapping(value = "listAllRecommendProductCategory", method = RequestMethod.GET)
    public Result<List<RecommendProductCategoryDTO>> listAllRecommendProductCategory() {
        List<RecommendProductCategoryBO> recommendProductCategoryBOS = recommendProductCategoryService.listAllRecommendProductCategory();
        return successGet(RecommendProductCategoryConverter.convertDTO(recommendProductCategoryBOS));
    }


    /**
     * 查询热门分类列表
     * @return
     */
    @RequestMapping(value = "getHotRecommendProductCategory", method = RequestMethod.GET)
    public Result<List<RecommendProductCategoryDTO>> getHotRecommendProductCategory() {
        return successGet(RecommendProductCategoryConverter
                .convertDTO(recommendProductCategoryService.getHotRecommendProductCategory()));
    }

    /**
     * 编辑是否热门
     * @param id
     * @param isHot
     * @return
     */
    @RequestMapping(value = "editHot/{id}", method = RequestMethod.PUT)
    public Result editHot(@PathVariable("id") Long id, @RequestParam("isHot") Boolean isHot) {
        recommendProductCategoryService.editHot(id,isHot);
        return successCreated();
    }

}

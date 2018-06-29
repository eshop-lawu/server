package com.lawu.eshop.product.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.dto.FavoriteProductDTO;
import com.lawu.eshop.product.query.FavoriteProductQuery;
import com.lawu.eshop.product.srv.bo.FavoriteProductBO;
import com.lawu.eshop.product.srv.converter.FavoriteProductConverter;
import com.lawu.eshop.product.srv.service.FavoriteProductService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 商品收藏
 * @author zhangrc
 * @date 2017/03/31
 *
 */
@RestController
@RequestMapping(value = "favoriteProduct/")
public class FavoriteProductController extends BaseController{
	
	@Autowired
	private FavoriteProductService favoriteProductService;
	
	/**
	 * 收藏
	 * @param memberId
	 * @param productId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestParam Long memberId,@RequestParam  Long  productId ) {
    	Integer i=favoriteProductService.save(memberId,productId);
    	if(i>0){
    		return successCreated(ResultCode.SUCCESS);
    	}else if(i==0){
    		return successCreated(ResultCode.GOODS_PRODUCT_FACORITE_EXIST);
    	}else{
    		return successCreated(ResultCode.SAVE_FAIL);
    	}
    	
    }
	
	/**
	 * 取消收藏
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "remove/{productId}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable Long productId,@RequestParam Long memberId) {
    	favoriteProductService.remove(productId,memberId);
    	return successDelete();
    	
    }
	
	/**
	 * 我收藏的商品列表
	 * @param memberId
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "selectMyFavoriteProduct", method = RequestMethod.POST)
    public Result<Page<FavoriteProductDTO>> selectMyFavoriteProduct(@RequestParam Long memberId,@RequestBody FavoriteProductQuery query ) {
		Page<FavoriteProductBO>  pageBO=favoriteProductService.selectMyFavoriteProduct(memberId, query);
		Page<FavoriteProductDTO> pageDTO=new Page<FavoriteProductDTO>();
		pageDTO.setRecords(FavoriteProductConverter.convertDTOS(pageBO.getRecords()));
		pageDTO.setCurrentPage(pageBO.getCurrentPage());
		pageDTO.setTotalCount(pageBO.getTotalCount());
    	return successGet(pageDTO);
    	
    }

	/**
	 * 查询用户是否收藏了该商品
	 * @param productId
	 * @param memberId
	 * @return
	 * @date 2017年5月2日 上午11:16:21
	 * @author yangqh
	 */
	@RequestMapping(value = "getUserFavorite/{productId}", method = RequestMethod.GET)
    public boolean getUserFavorite(@PathVariable Long productId,@RequestParam Long memberId) {
    	Integer i = favoriteProductService.getUserFavorite(productId,memberId);
    	if(i > 0){
    		return true;
    	}
    	return false;
    	
    }

	@RequestMapping(value = "getFavoriteProductCount", method = RequestMethod.GET)
	public Result<Integer> getFavoriteProductCount(@RequestParam("memberId") Long memberId) {
		Integer count = favoriteProductService.getFavoriteProductCount(memberId);
		return successGet(count);
	}
}

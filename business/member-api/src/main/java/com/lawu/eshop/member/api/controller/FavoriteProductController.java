package com.lawu.eshop.member.api.controller;

import com.lawu.eshop.framework.web.impl.constants.UserConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.member.api.service.FavoriteProductService;
import com.lawu.eshop.product.dto.FavoriteProductDTO;
import com.lawu.eshop.product.query.FavoriteProductQuery;
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
 * 描述：商品收藏
 * @author zhangrc
 * @date 2017/03/31
 */
@Api(tags = "favoriteProduct")
@RestController
@RequestMapping(value = "favoriteProduct/")
public class FavoriteProductController extends BaseController{
	
	@Autowired
	private FavoriteProductService favoriteProductService;
	
	/**
	 * 商品的收藏
	 * @param token
	 * @param productId
	 * @return
	 */
	@Audit(date = "2017-04-01", reviewer = "孙林青")
	@Authorization
    @ApiOperation(value = "收藏商品", notes = "收藏商品[]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "addFavoriteProduct", method = RequestMethod.POST)
    public Result addFavoriteProduct(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
    						@RequestParam @ApiParam(required = true, value = "商品id") Long productId) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
        return favoriteProductService.save(memberId, productId);
    }
	
	/**
	 * 会员收藏的商品列表
	 * @param token
	 * @param pageQuery
	 * @return
	 */
	@Audit(date = "2017-04-01", reviewer = "孙林青")
	@ApiOperation(value = "我收藏的商品", notes = "我收藏的商品，[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectMyFavoriteProduct", method = RequestMethod.GET)
    public Result<Page<FavoriteProductDTO>> selectInviteeMerchant(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                 @ModelAttribute @ApiParam( value = "查询信息") FavoriteProductQuery pageQuery) {
    	Long memberId=UserUtil.getCurrentUserId(getRequest());
    	return favoriteProductService.selectMyFavoriteProduct(memberId, pageQuery);
    }

	/**
	 * 取消收藏
	 * @param token
	 * @param productId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@Authorization
    @ApiOperation(value = "取消收藏的商品", notes = "取消收藏的商品[1002]（张荣成）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "remove/{productId}", method = RequestMethod.DELETE)
    public Result remove(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                         @PathVariable @ApiParam(required = true, value = "商品id") Long productId) {
		Long memberId=UserUtil.getCurrentUserId(getRequest());
        favoriteProductService.remove(productId,memberId);
        return successDelete();
    }
}

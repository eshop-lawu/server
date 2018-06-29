package com.lawu.eshop.member.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.FavoriteMerchantService;
import com.lawu.eshop.member.api.service.ProductService;
import com.lawu.eshop.user.dto.FavoriteMerchantDTO;
import com.lawu.eshop.user.param.FavoriteMerchantParam;
import com.lawu.eshop.user.param.FavoriteStoreParam;
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
 * 商家收藏
 * @author zhangrc
 * @date 2017/03/27
 *
 */
@Api(tags = "favoriteMerchant")
@RestController
@RequestMapping(value = "favoriteMerchant/")
@Deprecated
public class FavoriteMerchantController extends BaseController{
	
	@Resource
	private FavoriteMerchantService favoriteMerchantService;
	
	@Resource
	private ProductService productService;

	@Deprecated
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-03-29", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "商家收藏", notes = "商家收藏[2014]（张荣成）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "save", method = RequestMethod.PUT)
	public Result save(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			 @ModelAttribute @ApiParam(value = "查询信息") FavoriteStoreParam param) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		return favoriteMerchantService.save(memberId, param);
	}
	
	@Deprecated
	@Audit(date = "2017-03-29", reviewer = "孙林青")
	@ApiOperation(value = "我收藏的商家", notes = "我收藏商家列表查询,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getMyFavoriteMerchant", method = RequestMethod.GET)
    public Result<Page<FavoriteMerchantDTO>> findMemberListByUser(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
																  @ModelAttribute @ApiParam(value = "查询信息") FavoriteMerchantParam pageQuery) {
		Long memberId=UserUtil.getCurrentUserId(getRequest());
    	Result<Page<FavoriteMerchantDTO>> pageRs = favoriteMerchantService.getMyFavoriteMerchant(memberId,pageQuery);
    	 List<FavoriteMerchantDTO> list =pageRs.getModel().getRecords();
    	 Page<FavoriteMerchantDTO> newPage=new Page<>();
    	 newPage.setCurrentPage(pageQuery.getCurrentPage());
    	 newPage.setTotalCount(pageRs.getModel().getTotalCount());
    	 List<FavoriteMerchantDTO> newList=new ArrayList<>();
    	 if(!list.isEmpty()){
    		 for (FavoriteMerchantDTO favoriteMerchantDTO : list) {
    			 Result<Integer> rsCount= productService.selectProductCount(favoriteMerchantDTO.getMerchantId());
    			 if(rsCount.getModel()==null){
    				 favoriteMerchantDTO.setSaleCount(0);
    			 }else{
    				 favoriteMerchantDTO.setSaleCount(rsCount.getModel());
    			 }
    			 newList.add(favoriteMerchantDTO);
			}
    	 }
    	 newPage.setRecords(newList);
    	return successGet(newPage);
    }
	
	@Deprecated
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-03-29", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "取消商家收藏", notes = "取消商家收藏[]（张荣成）", httpMethod = "DELETE")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "remove", method = RequestMethod.DELETE)
	public Result remove(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam(value = "查询信息") FavoriteStoreParam param) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		favoriteMerchantService.remove(param,memberId);
		return successDelete();
	}

}

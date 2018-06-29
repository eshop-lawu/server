package com.lawu.eshop.user.srv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.FavoriteMerchantDTO;
import com.lawu.eshop.user.param.FavoriteMerchantParam;
import com.lawu.eshop.user.param.FavoriteStoreParam;
import com.lawu.eshop.user.srv.bo.FavoriteMerchantBO;
import com.lawu.eshop.user.srv.converter.FavoriteMerchantConverter;
import com.lawu.eshop.user.srv.service.FavoriteMerchantService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 商家收藏
 * @author zhangrc
 * @date 2017/03/27
 *
 */
@RestController
@RequestMapping(value = "favoriteMerchant/")
public class FavoriteMerchantController extends BaseController{
	
	@Resource
	private FavoriteMerchantService favoriteMerchantService;
	
	/**
	 * 添加商家收藏
	 * @return
	 */
   @RequestMapping(value = "save", method = RequestMethod.POST)
   public Result save(@RequestParam  Long memberId ,@RequestBody FavoriteStoreParam param) {
	   if(favoriteMerchantService.get(memberId, param)){
		   return successCreated(ResultCode.MERCHANT_STORE_IS_FAVORITE);
	   }
   	   Integer i=favoriteMerchantService.save(memberId,param);
	   if(i>0){
	   		return successCreated(ResultCode.SUCCESS);
	   }else{
	   		return successCreated(ResultCode.FAIL);
	   }
   	
   }
   
   @RequestMapping(value = "getMyFavoriteMerchant", method = RequestMethod.POST)
   public Result<Page<FavoriteMerchantDTO>> getMyFavoriteMerchant(@RequestParam  Long memberId ,@RequestBody FavoriteMerchantParam pageQuery) {
       Page<FavoriteMerchantBO> pageBO =favoriteMerchantService.getMyFavoriteMerchant(memberId,pageQuery);
       List<FavoriteMerchantBO> list=pageBO.getRecords();
       List<FavoriteMerchantDTO> listDTO=new ArrayList<>();
       for (FavoriteMerchantBO favoriteMerchantBO : list) {
    	   FavoriteMerchantDTO dto= FavoriteMerchantConverter.convertDTO(favoriteMerchantBO);
    	   listDTO.add(dto);
	   }
       Page<FavoriteMerchantDTO> page=new Page<FavoriteMerchantDTO>();
       page.setCurrentPage(pageBO.getCurrentPage());
       page.setTotalCount(pageBO.getTotalCount());
       page.setRecords(listDTO);
       return successGet(page);
   }
   
   
   /**
	 * 取消收藏
	 * @param param
	*  @param memberId
	 * @return
	 */
	@RequestMapping(value = "remove", method = RequestMethod.DELETE)
   public Result remove(@RequestBody FavoriteStoreParam param, @RequestParam Long memberId) {
   	  favoriteMerchantService.remove(param,memberId);
      return successCreated();
   }

}

package com.lawu.eshop.member.api.controller;

import java.util.List;

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
import com.lawu.eshop.ad.dto.FavoriteAdDOViewDTO;
import com.lawu.eshop.ad.param.FavoriteAdParam;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.FavoriteAdService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
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
 * 描述：广告收藏
 * @author zhangrc
 * @date 2017/03/31
 */
@Api(tags = "favoriteAd")
@RestController
@RequestMapping(value = "favoriteAd/")
public class FavoriteAdController extends BaseController{
	
	@Autowired
	private FavoriteAdService favoriteAdService;
	
	@Autowired
	private MerchantStoreService merchantStoreService;
	
	/**
	 * 广告的收藏
	 * @param token
	 * @param adId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@Authorization
    @ApiOperation(value = "收藏广告", notes = "收藏广告[5002]（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "save", method = RequestMethod.PUT)
    public Result save(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
    						@RequestParam @ApiParam(required = true, value = "商品id") Long adId) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result rs = favoriteAdService.save(memberId, adId,userNum);
        if(!isSuccess(rs)){
        	return successCreated(rs.getRet());
        }
        return rs;
    }
	
	/**
	 * 会员收藏的广告列表
	 * @param token
	 * @param param
	 * @return
	 */
	@Audit(date = "2017-04-15", reviewer = "孙林青")
	@ApiOperation(value = "我收藏的广告", notes = "我收藏的广告，[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectMyFavoriteAd", method = RequestMethod.GET)
    public Result<Page<FavoriteAdDOViewDTO>> selectInviteeMerchant(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                 @ModelAttribute @ApiParam( value = "查询信息") FavoriteAdParam param) {
    	Long memberId=UserUtil.getCurrentUserId(getRequest());
    	Result<Page<FavoriteAdDOViewDTO>>  pageDTOS=favoriteAdService.selectMyFavoriteAd(memberId, param);
    	if(!isSuccess(pageDTOS)){
    		return successCreated(pageDTOS.getRet());
    	}
    	List<FavoriteAdDOViewDTO> list=pageDTOS.getModel().getRecords();
		for (FavoriteAdDOViewDTO favoriteAdDOViewDTO : list) {
			Result<MerchantStoreDTO> rs=merchantStoreService.selectMerchantStoreByMId(favoriteAdDOViewDTO.getMerchantId());
			if(isSuccess(rs)){
				favoriteAdDOViewDTO.setLogoUrl(rs.getModel().getLogoUrl());
				favoriteAdDOViewDTO.setName(rs.getModel().getName());
			}
		}
    	return pageDTOS;
    }

	/**
	 * 取消收藏
	 * @param token
	 * @param adId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@Authorization
    @ApiOperation(value = "取消收藏的广告", notes = "取消收藏的广告[1002]（张荣成）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "remove/{adId}", method = RequestMethod.DELETE)
    public Result remove(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                         @PathVariable @ApiParam(required = true, value = "广告id") Long adId) {
		Long memberId=UserUtil.getCurrentUserId(getRequest());
        favoriteAdService.remove(adId,memberId);
        return successDelete();
    }
}

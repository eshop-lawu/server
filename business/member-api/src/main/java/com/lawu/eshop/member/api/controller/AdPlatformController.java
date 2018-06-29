package com.lawu.eshop.member.api.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.util.UserUtil;
import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.dto.AdPlatformDTO;
import com.lawu.eshop.ad.dto.AdPlatformVideoFlatDTO;
import com.lawu.eshop.ad.param.AdPlatformFindEginParam;
import com.lawu.eshop.ad.param.AdPlatformInternalParam;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.AdPlatformService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.user.dto.AdQueryMemberInfoDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 描述：广告管理
 * @author zhangrc
 * @date 2017/04/5
 */
@Api(tags = "adPlatform")
@RestController
@RequestMapping(value = "adPlatform/")
public class AdPlatformController extends BaseController {

    @Autowired
    private AdPlatformService adPlatformService;
    
    
    @Autowired
    private MemberService memberService;

    /**
     * 根据位置查询广告
     * @param positionEnum  
     * @return
     */
	@AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "广告信息查询 (banner)", notes = "广告信息查询[]（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectByPosition", method = RequestMethod.GET)
    public Result<List<AdPlatformDTO>> selectByPosition(@RequestParam @ApiParam(required = true, value = "POSITON_RECOMMEND 人气推荐 POSITON_SHOP_TOP 要购物顶部广告 POSITON_SHOP_CHOOSE"
			+ "要购物今日推荐  POSITON_SHOP_GOODS 要购物精品 POSITON_AD_TOP 看广告顶部广告 GAME_TOP 游戏顶部") PositionEnum positionEnum) {
        return adPlatformService.selectByPosition(positionEnum);
    }

	@AutoTesting
    @Audit(date = "2017-08-09", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "广告首页广告位 (广告首页4个广告位) ", notes = "广告首页广告位 (广告首页4个广告位)[]（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selAdPlatformPositionAd", method = RequestMethod.GET)
    public Result<AdPlatformVideoFlatDTO> selAdPlatformPositionAd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(value = "查询信息") AdPlatformFindEginParam query) {
    	Long memberId = UserUtil.getCurrentUserId(getRequest());
    	AdPlatformInternalParam param = new AdPlatformInternalParam();
    	param.setLatitude(query.getLatitude());
    	param.setLongitude(query.getLongitude());
    	if(memberId==0){
    		if (StringUtils.isNotBlank(query.getTransRegionPath())) {
    			param.setAreas(Arrays.asList(StringUtils.split(query.getTransRegionPath(), "/")));
    		}
    	}else{
    		// 获取查询广告所需要的信息
    		Result<AdQueryMemberInfoDTO> adQueryMemberInfoResult = memberService.adQueryMemberInfo(memberId);
    		if (!isSuccess(adQueryMemberInfoResult)) {
    			return successGet(adQueryMemberInfoResult);
    		}
    		AdQueryMemberInfoDTO adQueryMemberInfoDTO = adQueryMemberInfoResult.getModel();
    		param.setMerchantIds(adQueryMemberInfoDTO.getFansList());
    		if (StringUtils.isNotBlank(query.getTransRegionPath())) {
    			param.setAreas(Arrays.asList(StringUtils.split(query.getTransRegionPath(), "/")));
    		}
    	}
    	
        return adPlatformService.selAdPlatformPositionAd(param);
    }
    

}

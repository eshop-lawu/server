package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.ad.dto.FavoriteAdCountDTO;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.FavoriteCountDTO;
import com.lawu.eshop.member.api.service.FansMerchantService;
import com.lawu.eshop.member.api.service.FavoriteAdService;
import com.lawu.eshop.member.api.service.FavoriteProductService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/6/7.
 */
@Api(tags = "favorite")
@RestController
@RequestMapping(value = "favorite/")
public class FavoriteController extends BaseController{

    @Autowired
    private FansMerchantService fansMerchantService;

    @Autowired
    private FavoriteAdService favoriteAdService;

    @Autowired
    private FavoriteProductService favoriteProductService;


    @Audit(date = "2018-06-08", reviewer = "孙林青")
    @ApiOperation(value = "查询收藏的数量", notes = "查询收藏的数量，[]（章勇）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getFavoriteCount", method = RequestMethod.GET)
    public Result<FavoriteCountDTO> getFavoriteCount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
        FavoriteCountDTO countDTO = new FavoriteCountDTO();
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        Result<FavoriteAdCountDTO> adCountDTOResult = favoriteAdService.getFavoriteAdCount(memberId);
        countDTO.setAdCount(adCountDTOResult.getModel().getAdCount());
        countDTO.setEpraiseCount(adCountDTOResult.getModel().getEpraiseCount());
        countDTO.setProductCount(favoriteProductService.getFavoriteProductCount(memberId).getModel());
        countDTO.setStoreCount(fansMerchantService.getAttentionMerchantCount(memberId).getModel());
        return successGet(countDTO);
    }
}

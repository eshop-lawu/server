package com.lawu.eshop.member.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.dto.AdPlatformDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.member.api.service.AdPlatformService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.RecommendStoreCacheService;
import com.lawu.eshop.member.api.service.RegionService;
import com.lawu.eshop.member.api.service.StoreSolrService;
import com.lawu.eshop.user.dto.NewMerchantStoreDTO;
import com.lawu.eshop.user.dto.RecommendFoodDTO;
import com.lawu.eshop.user.dto.RecommendFoodStoreDTO;
import com.lawu.eshop.user.dto.StoreSearchWordDTO;
import com.lawu.eshop.user.dto.StoreSolrDTO;
import com.lawu.eshop.user.dto.StoreSolrInfoDTO;
import com.lawu.eshop.user.param.DiscountStoreParam;
import com.lawu.eshop.user.param.RecommendFoodParam;
import com.lawu.eshop.user.param.StoreSolrParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.DateUtil;
import com.lawu.utils.DistanceUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/3/30.
 */
@Api(tags = "storeSolr")
@RestController
@RequestMapping(value = "storeSolr/")
public class StoreSolrController extends BaseController {

    @Autowired
    private StoreSolrService storeSolrService;

    @Autowired
    private AdPlatformService adPlatformService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private RecommendStoreCacheService recommendStoreCacheService;

    @AutoTesting
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "搜索门店/猜你喜欢/更多商家", notes = "搜索门店(名称搜索)/猜你喜欢(全部商家)/更多商家(同行业商家)。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listStore", method = RequestMethod.GET)
    public Result<Page<StoreSolrDTO>> listStore(@ModelAttribute @ApiParam StoreSolrParam storeSolrParam) {
        Result<Page<StoreSolrDTO>> result = storeSolrService.listStore(storeSolrParam);
        if (!isSuccess(result)) {
            return result;
        }
        for (StoreSolrDTO storeSolrDTO : result.getModel().getRecords()) {
            if (StringUtils.isNotEmpty(storeSolrDTO.getRegionPath())) {
                String areaName = regionService.getAreaName(storeSolrDTO.getRegionPath()).getModel();
                storeSolrDTO.setAreaName(areaName);
            }
        }
        return result;
    }

    @AutoTesting
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "搜索词关联词频查询", notes = "根据搜索词推荐关联词和频率查询。 (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listStoreSearchWord", method = RequestMethod.GET)
    public Result<List<StoreSearchWordDTO>> listStoreSearchWord(@RequestParam @ApiParam(name = "name", required = true, value = "门店名称") String name,
                                                                @RequestParam @ApiParam(name = "regionPath", required = true, value = "区域路径") String regionPath) {
        return storeSolrService.listStoreSearchWord(name, regionPath);
    }

    @AutoTesting
    @Audit(date = "2017-04-26", reviewer = "孙林青")
    @ApiOperation(value = "要买单人气推荐", notes = "要买单人气推荐。[1100]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listRecommendStore", method = RequestMethod.GET)
    public Result<List<StoreSolrDTO>> listRecommendStore(@RequestParam @ApiParam(name = "regionPath", required = true, value = "区域路径") String regionPath) {
        Result<List<AdPlatformDTO>> recommendResult = adPlatformService.getAdPlatformByTypePositionRegionPath(TypeEnum.TYPE_STORE, PositionEnum.POSITON_RECOMMEND, regionPath);
        if (!isSuccess(recommendResult)) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        // 把要查询的id放入list,统一一次性查询
        Set<Long> merchantStoreIds = new HashSet<Long>();
        List<AdPlatformDTO> adPlatformDTOS = recommendResult.getModel();
        for (AdPlatformDTO adPlatformDTO : adPlatformDTOS) {
            merchantStoreIds.add(adPlatformDTO.getMerchantStoreId());
        }

        Result<List<StoreSolrInfoDTO>> listResult = merchantStoreService.getMerchantStoreByIds(new ArrayList<Long>(merchantStoreIds));
        Map<Long,StoreSolrInfoDTO> mapResult = new HashMap<Long,StoreSolrInfoDTO>();
        if(!listResult.getModel().isEmpty()){
            for (StoreSolrInfoDTO storeInfoDTO : listResult.getModel()) {
                if (!mapResult.containsKey(storeInfoDTO.getMerchantStoreId())) {
                    mapResult.put(storeInfoDTO.getMerchantStoreId(), storeInfoDTO);
                }
            }
        }
        List<StoreSolrDTO> storeSolrDTOS = new ArrayList<>();
        StoreSolrInfoDTO storeInfo;
        for (AdPlatformDTO adPlatformDTO : recommendResult.getModel()) {
            StoreSolrDTO storeSolrDTO = new StoreSolrDTO();
            storeSolrDTO.setMerchantStoreId(adPlatformDTO.getMerchantStoreId());
            storeSolrDTO.setName(adPlatformDTO.getTitle());
            storeSolrDTO.setStorePic(adPlatformDTO.getMediaUrl());
            storeInfo = mapResult.get(adPlatformDTO.getMerchantStoreId());
            if(storeInfo == null){
                storeSolrDTO.setMerchantId(0L);
                storeSolrDTO.setIndustryPath("");
                storeSolrDTO.setIndustryName("");
            }else{
                storeSolrDTO.setMerchantId(storeInfo.getMerchantId());
                storeSolrDTO.setIndustryPath(storeInfo.getIndustryPath());
                storeSolrDTO.setIndustryName(storeInfo.getIndustryName());
            }
            storeSolrDTOS.add(storeSolrDTO);
        }
        return successGet(storeSolrDTOS);
    }

    @AutoTesting
    @Audit(date = "2017-08-01", reviewer = "孙林青")
    @ApiOperation(value = "新店推荐", notes = "新店推荐(前50)。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listNewMerchant", method = RequestMethod.GET)
    public Result<List<NewMerchantStoreDTO>> listNewMerchant(@RequestParam @ApiParam(name = "regionPath", required = true, value = "区域路径") String regionPath) {
        Result<String> result = recommendStoreCacheService.getNewMerchant(regionPath);
        if (StringUtils.isEmpty(result.getModel())) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        List<NewMerchantStoreDTO> storeDTOS = JSON.parseArray(result.getModel(), NewMerchantStoreDTO.class);
        for (NewMerchantStoreDTO storeDTO : storeDTOS) {
            if (StringUtils.isNotEmpty(storeDTO.getRegionPath())) {
                String areaName = regionService.getAreaName(storeDTO.getRegionPath()).getModel();
                storeDTO.setAreaName(areaName);
            }
        }
        return successGet(storeDTOS);
    }

    @AutoTesting
    @Audit(date = "2017-08-01", reviewer = "孙林青")
    @ApiOperation(value = "专属特惠", notes = "专属特惠(优惠系数升序)。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "discountStore", method = RequestMethod.GET)
    public Result<Page<StoreSolrDTO>> discountStore(@ModelAttribute @ApiParam DiscountStoreParam discountStoreParam) {
        Result<Page<StoreSolrDTO>> result = storeSolrService.discountStore(discountStoreParam);
        if (!isSuccess(result)) {
            return result;
        }
        for (StoreSolrDTO storeSolrDTO : result.getModel().getRecords()) {
            if (StringUtils.isNotEmpty(storeSolrDTO.getRegionPath())) {
                String areaName = regionService.getAreaName(storeSolrDTO.getRegionPath()).getModel();
                storeSolrDTO.setAreaName(areaName);
            }
        }
        return result;
    }

    @AutoTesting
    @Audit(date = "2017-08-01", reviewer = "孙林青")
    @ApiOperation(value = "优选美食", notes = "优选美食(人气最高和评分最高各5个)。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "listRecommendFood", method = RequestMethod.GET)
    public Result<RecommendFoodStoreDTO> listRecommendFood(@ModelAttribute @ApiParam RecommendFoodParam recommendFoodParam) {
        RecommendFoodStoreDTO storeDTO = new RecommendFoodStoreDTO();
        List<RecommendFoodDTO> consumeDTOS = new ArrayList<>();
        List<RecommendFoodDTO> commentDTOS = new ArrayList<>();
        Result<String> consumeResult = recommendStoreCacheService.getRecommendFoodConsume(recommendFoodParam.getRegionPath());
        Result<String> commentResult = recommendStoreCacheService.getRecommendFoodComment(recommendFoodParam.getRegionPath());
        if (!StringUtils.isEmpty(consumeResult.getModel())) {
            consumeDTOS = JSON.parseArray(consumeResult.getModel(), RecommendFoodDTO.class);
            for (RecommendFoodDTO foodDTO : consumeDTOS) {
                double distance = DistanceUtil.getDistance(recommendFoodParam.getLongitude().doubleValue(), recommendFoodParam.getLatitude().doubleValue(), foodDTO.getLongitude().doubleValue(), foodDTO.getLatitude().doubleValue());
                foodDTO.setDistance(distance / 1000);
                foodDTO.setAverageConsumeAmount(BigDecimal.valueOf(foodDTO.getAverageConsumeAmount().intValue()));
                if (foodDTO.getAverageScore().compareTo(BigDecimal.valueOf(0)) == 0) {
                    foodDTO.setAverageScore(BigDecimal.valueOf(4));
                }
                if (StringUtils.isNotEmpty(foodDTO.getFavoreEndTime()) && DateUtil.isOverdue(DateUtil.formatDate(foodDTO.getFavoreEndTime(), "yyyy-MM-dd"))) {
                    foodDTO.setFavoreInfo("");
                }
            }
        }
        if (!StringUtils.isEmpty(commentResult.getModel())) {
            commentDTOS = JSON.parseArray(commentResult.getModel(), RecommendFoodDTO.class);
            for (RecommendFoodDTO foodDTO : commentDTOS) {
                double distance = DistanceUtil.getDistance(recommendFoodParam.getLongitude().doubleValue(), recommendFoodParam.getLatitude().doubleValue(), foodDTO.getLongitude().doubleValue(), foodDTO.getLatitude().doubleValue());
                foodDTO.setDistance(distance / 1000);
                foodDTO.setAverageConsumeAmount(BigDecimal.valueOf(foodDTO.getAverageConsumeAmount().intValue()));
                if (foodDTO.getAverageScore().compareTo(BigDecimal.valueOf(0)) == 0) {
                    foodDTO.setAverageScore(BigDecimal.valueOf(4));
                }
                if (StringUtils.isNotEmpty(foodDTO.getFavoreEndTime()) && DateUtil.isOverdue(DateUtil.formatDate(foodDTO.getFavoreEndTime(), "yyyy-MM-dd"))) {
                    foodDTO.setFavoreInfo("");
                }
            }
        }
        storeDTO.setRecommendConsume(consumeDTOS);
        storeDTO.setRecommendComment(commentDTOS);
        return successGet(storeDTO);
    }

}

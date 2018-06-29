package com.lawu.eshop.ad.srv.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.dto.AdPlatformDTO;
import com.lawu.eshop.ad.dto.AdPlatformFlatDTO;
import com.lawu.eshop.ad.dto.AdPlatformOperatorDTO;
import com.lawu.eshop.ad.dto.AdPlatformProductDTO;
import com.lawu.eshop.ad.dto.AdPlatformVideoDTO;
import com.lawu.eshop.ad.dto.AdPlatformVideoFlatDTO;
import com.lawu.eshop.ad.param.AdPlatformFindParam;
import com.lawu.eshop.ad.param.AdPlatformInternalParam;
import com.lawu.eshop.ad.param.AdPlatformParam;
import com.lawu.eshop.ad.param.AdPlatformUpdateParam;
import com.lawu.eshop.ad.srv.AdSrvConfig;
import com.lawu.eshop.ad.srv.bo.AdPlatformBO;
import com.lawu.eshop.ad.srv.bo.AdPlatformFlatBO;
import com.lawu.eshop.ad.srv.bo.AdPlatformVideoBO;
import com.lawu.eshop.ad.srv.bo.AdPlatformVideoFlatBO;
import com.lawu.eshop.ad.srv.converter.AdPlatformConverter;
import com.lawu.eshop.ad.srv.service.AdPlatformService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 运营平台接口提供
 *
 * @author zhangrc
 * @date 2017/4/5
 */
@RestController
@RequestMapping(value = "adPlatform/")
public class AdPlatformController extends BaseController {

    @Resource
    private AdPlatformService adPlatformService;

    @Resource
    private AdSrvConfig adSrvConfig;
    /**
     * 添加广告
     *
     * @param adPlatformParam
     * @return
     */
    @RequestMapping(value = "saveAdPlatform", method = RequestMethod.POST)
    public Result saveAdPlatform(@RequestBody AdPlatformParam adPlatformParam) {
        Integer id = adPlatformService.saveAdPlatform(adPlatformParam);
        if (id > 0) {
            return successCreated(ResultCode.SUCCESS);
        } else {
            return successCreated(ResultCode.FAIL);
        }
    }

    /**
     * 根据位置查询广告
     *
     * @param positionEnum
     * @return
     */
    @RequestMapping(value = "selectByPosition", method = RequestMethod.POST)
    public Result<List<AdPlatformDTO>> selectByPosition(@RequestBody PositionEnum positionEnum) {
        List<AdPlatformBO> BOS = adPlatformService.selectByPosition(positionEnum);
        List<AdPlatformDTO> list;
        list = AdPlatformConverter.convertDTOS(BOS);
        if (list == null) {
            list = new ArrayList<>();
        }
        return successAccepted(list);
    }

    /**
     * 根据位置查询广告
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "selectList", method = RequestMethod.POST)
    public Result<Page<AdPlatformOperatorDTO>> selectList(@RequestBody AdPlatformFindParam param) {
        Page<AdPlatformBO> page = adPlatformService.selectList(param);
        List<AdPlatformOperatorDTO> list;
        list = AdPlatformConverter.convertOperatorDTOS(page.getRecords());
        if (list == null) {
            list = new ArrayList<>();
        }
        Page<AdPlatformOperatorDTO> newPage = new Page<>();
        newPage.setCurrentPage(page.getCurrentPage());
        newPage.setTotalCount(page.getTotalCount());
        newPage.setRecords(list);
        return successAccepted(newPage);
    }

    /**
     * 删除广告
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "removeAdPlatform/{id}", method = RequestMethod.PUT)
    public Result removeAdPlatform(@PathVariable Long id,@RequestParam PositionEnum positionEnum) {
    	if(positionEnum == PositionEnum.POSITON_SHOP_CHOOSE){
    		int count = adPlatformService.count(positionEnum);
			if (count <= adSrvConfig.getAdPlatformChooseTodyCount()) {
				return successCreated(ResultCode.AD_PLATFORM_LESS_COUNT, "趣乐购今日推荐不能低于"+adSrvConfig.getAdPlatformChooseTodyCount());
			}
    	}else if(positionEnum == PositionEnum.SHOPPING_HOT){
    		int count = adPlatformService.count(positionEnum);
			if (count <= adSrvConfig.getAdPlatformHotCount()) {
				return successCreated(ResultCode.AD_PLATFORM_LESS_COUNT, "趣乐购热门商品不能低于"+adSrvConfig.getAdPlatformHotCount());
			}
    	}
        Integer i = adPlatformService.removeAdPlatform(id);
        if (i > 0) {
            return successCreated();
        } else {
            return successCreated(ResultCode.FAIL);
        }
    }

   
    /**
     * 广告下架
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "unShelve/{id}", method = RequestMethod.PUT)
    public Result unShelve(@PathVariable Long id,@RequestParam PositionEnum positionEnum) {
    	if(positionEnum == PositionEnum.POSITON_SHOP_CHOOSE){
    		int count = adPlatformService.count(positionEnum);
    		if(count <=adSrvConfig.getAdPlatformChooseTodyCount()){
    			return successCreated(ResultCode.AD_PLATFORM_LESS_COUNT,"趣乐购今日推荐不能低于"+adSrvConfig.getAdPlatformChooseTodyCount());
    		}
    	}else if(positionEnum == PositionEnum.SHOPPING_HOT){
    		int count = adPlatformService.count(positionEnum);
    		if(count <=adSrvConfig.getAdPlatformHotCount()){
    			return successCreated(ResultCode.AD_PLATFORM_LESS_COUNT,"趣乐购热门商品不能低于"+adSrvConfig.getAdPlatformHotCount());
    		}
    	}
        adPlatformService.unShelve(id);
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 广告上架
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "onShelve/{id}", method = RequestMethod.PUT)
    public Result onShelve(@PathVariable Long id) {
        adPlatformService.onShelve(id);
        return successCreated(ResultCode.SUCCESS);
    }


    /**
     * 修改
     *
     * @param id
     * @param adPlatformParam
     * @param url
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public Result update(@PathVariable Long id, @RequestBody AdPlatformParam adPlatformParam) {
        Integer i = adPlatformService.update(id, adPlatformParam);
        if (i > 0) {
            return successCreated(ResultCode.SUCCESS);
        } else {
            return successCreated(ResultCode.FAIL);
        }
    }

    /**
     * 单个查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "select/{id}", method = RequestMethod.GET)
    public Result<AdPlatformOperatorDTO> select(@PathVariable Long id) {
        AdPlatformBO adPlatformBO = adPlatformService.select(id);
        return successGet(AdPlatformConverter.convertOperatorDTO(adPlatformBO));
    }

    /**
     * 根据类型位置查询广告
     *
     * @param typeEnum
     * @param positionEnum
     * @return
     */
    @RequestMapping(value = "getAdPlatformByTypePosition", method = RequestMethod.GET)
    public Result<List<AdPlatformProductDTO>> getAdPlatformByTypePosition(@RequestParam TypeEnum typeEnum, @RequestParam PositionEnum positionEnum) {
        List<AdPlatformBO> adPlatformBOS = adPlatformService.getAdPlatformByTypePosition(typeEnum, positionEnum);
        return successGet(AdPlatformConverter.convertDTO(adPlatformBOS));
    }

    
    @RequestMapping(value = "getAdPlatformProductTop", method = RequestMethod.GET)
    public Result<List<AdPlatformProductDTO>> getAdPlatformProductTop() {
        List<AdPlatformBO> adPlatformBOS = adPlatformService.getAdPlatformByTypePosition();
        return successGet(AdPlatformConverter.convertDTO(adPlatformBOS));
    }
    
    
    /**
     * 根据类型位置查询广告
     *
     * @param typeEnum
     * @param positionEnum
     * @param regionPath
     * @return
     */
    @RequestMapping(value = "getAdPlatformByTypePositionRegionPath", method = RequestMethod.GET)
    public Result<List<AdPlatformDTO>> getAdPlatformByTypePositionRegionPath(@RequestParam TypeEnum typeEnum, @RequestParam PositionEnum positionEnum, @RequestParam String regionPath) {
        List<AdPlatformBO> adPlatformBOS = adPlatformService.getAdPlatformByTypePositionRegionPath(typeEnum, positionEnum, regionPath);
        if (adPlatformBOS == null || adPlatformBOS.isEmpty()) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(AdPlatformConverter.convertDTOS(adPlatformBOS));
    }

    /**
     * 根据商品ID查询该商品是否已上架广告位.y
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "selectByProductIdAndStatus", method = RequestMethod.GET)
    public Result<Boolean> selectByProductIdAndStatus(@RequestParam Long productId) {
        boolean flag = adPlatformService.selectByProductIdAndStatus(productId);
        return successGet(flag);
    }
    
    /**
     * 广告首页广告位
     * @return
     */
    @RequestMapping(value = "selAdPlatformPositionAd", method = RequestMethod.POST)
    public Result<AdPlatformVideoFlatDTO> selAdPlatformPositionAd(@RequestBody AdPlatformInternalParam param) {
        AdPlatformVideoFlatBO bo = adPlatformService.selAdPlatformPositionAd(param);
        
        List<AdPlatformFlatDTO> listDTOOneFlat = new ArrayList<>();
		List<AdPlatformFlatDTO> listDTOTwoFlat = new ArrayList<>();
		List<AdPlatformFlatDTO> listDTOFiveFlat = new ArrayList<>();
		List<AdPlatformVideoDTO> listDTOVideo= new ArrayList<>();
		
        List<AdPlatformFlatBO> listFiveFlat = bo.getListFiveFlat();
        for (AdPlatformFlatBO adPlatformFlatBO : listFiveFlat) {
        	AdPlatformFlatDTO dto = new  AdPlatformFlatDTO();
        	dto.setAdId(adPlatformFlatBO.getAdId());
        	dto.setMediaUrl(adPlatformFlatBO.getMediaUrl());
        	listDTOFiveFlat.add(dto);
		}
        List<AdPlatformFlatBO> listOneFlat = bo.getListOneFlat();
        for (AdPlatformFlatBO adPlatformFlatBO : listOneFlat) {
        	AdPlatformFlatDTO dto = new  AdPlatformFlatDTO();
        	dto.setAdId(adPlatformFlatBO.getAdId());
        	dto.setMediaUrl(adPlatformFlatBO.getMediaUrl());
        	listDTOOneFlat.add(dto);
		}
        List<AdPlatformFlatBO> listTwoFlat = bo.getListTwoFlat();
        for (AdPlatformFlatBO adPlatformFlatBO : listTwoFlat) {
        	AdPlatformFlatDTO dto = new  AdPlatformFlatDTO();
        	dto.setAdId(adPlatformFlatBO.getAdId());
        	dto.setMediaUrl(adPlatformFlatBO.getMediaUrl());
        	listDTOTwoFlat.add(dto);
		}
        List<AdPlatformVideoBO> videoList = bo.getListVideo();
        for (AdPlatformVideoBO adPlatformVideoBO : videoList) {
        	AdPlatformVideoDTO dto = new  AdPlatformVideoDTO();
        	dto.setAdId(adPlatformVideoBO.getAdId());
        	dto.setContent(adPlatformVideoBO.getContent());
        	dto.setId(adPlatformVideoBO.getId());
        	dto.setName(adPlatformVideoBO.getName());
        	dto.setTitle(adPlatformVideoBO.getTitle());
        	dto.setVideoImgUrl(adPlatformVideoBO.getVideoImgUrl());
        	dto.setLogoUrl(adPlatformVideoBO.getLogoUrl());
        	listDTOVideo.add(dto);
		}
        AdPlatformVideoFlatDTO dto = new AdPlatformVideoFlatDTO();
        
        dto.setListFiveFlat(listDTOFiveFlat);
        dto.setListOneFlat(listDTOOneFlat);
        dto.setListTwoFlat(listDTOTwoFlat);
        dto.setListVideo(listDTOVideo);
        
        return successGet(dto);
    }
    
    
    @RequestMapping(value = "updatePriority/{id}", method = RequestMethod.PUT)
    public Result updatePriority(@PathVariable Long id,@RequestBody AdPlatformUpdateParam param){
    	adPlatformService.updatePriority(id, param);
    	return successCreated();
    }
    
   
}

package com.lawu.eshop.ad.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.constants.FavoriteAdTypeEnum;
import com.lawu.eshop.ad.dto.FavoriteAdCountDTO;
import com.lawu.eshop.ad.dto.FavoriteAdDOViewDTO;
import com.lawu.eshop.ad.dto.FavoriteAdPraiseWarnDTO;
import com.lawu.eshop.ad.param.FavoriteAdParam;
import com.lawu.eshop.ad.param.PraiseWarnParam;
import com.lawu.eshop.ad.srv.bo.FavoriteAdDOViewBO;
import com.lawu.eshop.ad.srv.bo.FavoriteAdPraiseWarnBO;
import com.lawu.eshop.ad.srv.converter.FavoriteAdConverter;
import com.lawu.eshop.ad.srv.service.FavoriteAdService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 广告收藏
 * @author zhangrc
 * @date 2017/4/8
 *
 */
@RestController
@RequestMapping(value = "favoriteAd/")
public class FavoriteAdController extends BaseController{

	@Autowired
	private FavoriteAdService favoriteAdService;
	
	/**
	 * 收藏
	 * @param memberId
	 * @param adId
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.PUT)
    public Result save(@RequestParam Long memberId,@RequestParam  Long  adId,@RequestParam String userNum ) {
    	Integer i=favoriteAdService.save(memberId,adId,userNum);
    	if(i>0){
    		return successCreated(ResultCode.SUCCESS);
    	}else if(i==0){
    		return successCreated(ResultCode.AD_FACORITE_EXIST);
    	}else{
    		return successCreated(ResultCode.SAVE_FAIL);
    	}
    	
    }
	
	/**
	 * 取消收藏
	 * @param adId
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "remove/{adId}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable Long adId,@RequestParam Long memberId) {
    	favoriteAdService.remove(adId, memberId);
    	return successCreated();
    	
    }
	
	/**
	 * 我收藏的商品列表
	 * @param memberId
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "selectMyFavoriteAd", method = RequestMethod.POST)
    public Result<Page<FavoriteAdDOViewDTO>> selectMyFavoriteAd(@RequestParam Long memberId,@RequestBody FavoriteAdParam param ) {
		Page<FavoriteAdDOViewBO>   pageBO=favoriteAdService.selectMyFavoriteAd(param, memberId);
		Page<FavoriteAdDOViewDTO> pageDTO=new Page<FavoriteAdDOViewDTO>();
		pageDTO.setRecords(FavoriteAdConverter.convertDTOS(pageBO.getRecords()));
		pageDTO.setCurrentPage(pageBO.getCurrentPage());
		pageDTO.setTotalCount(pageBO.getTotalCount());
    	return successGet(pageDTO);
    	
    }
	
	/**
	 * 是否收藏
	 * @param adId
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "isFavoriteAd", method = RequestMethod.GET)
    public Result<Boolean> isFavoriteAd(@RequestParam Long adId,@RequestParam Long memberId) {
    	return successGet(favoriteAdService.isFavoriteAd(adId, memberId));
    	
    }
	
	/**
	 * 收藏的抢赞十分提示
	 * @return
	 */
	@RequestMapping(value = "selectFavoriteAdPraise", method = RequestMethod.POST)
    public Result<List<FavoriteAdPraiseWarnDTO>> selectFavoriteAdPraise(@RequestBody PraiseWarnParam param) {
		List<FavoriteAdPraiseWarnBO> list=favoriteAdService.selectFavoriteAdPraise(param);
		
		List<FavoriteAdPraiseWarnDTO> dtoList=new ArrayList<>();
		
		for (FavoriteAdPraiseWarnBO bo : list) {
			
			FavoriteAdPraiseWarnDTO dto = new FavoriteAdPraiseWarnDTO();
			dto.setAdId(bo.getAdId());
			dto.setMemberId(bo.getMemberId());
			dto.setTitle(bo.getTitle());
			dto.setMemberNum(bo.getMemberNum());
			dto.setId(bo.getId());
			dtoList.add(dto);
		}
		
    	return successGet(dtoList);
    	
    }
	
	/**
	 * 将抢赞设置为已发送消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateIsSend/{id}", method = RequestMethod.PUT)
    public Result updateIsSend(@PathVariable Long id) {
    	favoriteAdService.updateIsSend(id);
    	return successCreated();
    	
    }

	/**
	 * 查询收藏的咻一咻，广告总数
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "getFavoriteAdCount", method = RequestMethod.GET)
	public Result<FavoriteAdCountDTO> getFavoriteAdCount(@RequestParam("memberId") Long memberId) {
		FavoriteAdCountDTO countDTO = new FavoriteAdCountDTO();
		countDTO.setAdCount(favoriteAdService.getFavoriteAdCount(memberId, FavoriteAdTypeEnum.AD_TYPE_EGAIN.val));
		countDTO.setEpraiseCount(favoriteAdService.getFavoriteAdCount(memberId, FavoriteAdTypeEnum.AD_TYPE_EPRAISE.val));
		return successGet(countDTO);
	}

}

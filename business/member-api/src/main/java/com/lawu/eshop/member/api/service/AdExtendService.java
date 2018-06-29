package com.lawu.eshop.member.api.service;

import java.util.List;

import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.AdEgainQueryDTO;
import com.lawu.eshop.ad.dto.AdFlatVideoDTO;
import com.lawu.eshop.ad.dto.AdPointDTO;
import com.lawu.eshop.ad.dto.AdPraiseDTO;
import com.lawu.eshop.ad.dto.ChoicenessAdDTO;
import com.lawu.eshop.ad.dto.ClickAdPointDTO;
import com.lawu.eshop.ad.dto.PraisePointDTO;
import com.lawu.eshop.ad.param.AdChoicenessParam;
import com.lawu.eshop.ad.param.AdEgainParam;
import com.lawu.eshop.ad.param.AdPointForeignParam;
import com.lawu.eshop.ad.param.AdPointParam;
import com.lawu.eshop.ad.param.AdPraiseParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

public interface AdExtendService {
	

	/**
	 * 查询广告
	 * @param adEgainParam
	 * @return
	 */
    Result<Page<AdDTO>> selectListByMember(AdEgainParam adEgainParam);
    
    /**
     * 今日精选
     * @param adChoicenessParam
     * @return
     */
    @Deprecated
    Result<Page<AdDTO>> selectChoiceness(AdChoicenessParam adChoicenessParam);
    
    /**
     * 积分榜，人气榜查询
     * @param adPointParam
     * @return
     */
    @Deprecated
    Result<List<AdDTO>> selectAdTopList(AdPointParam adPointParam);
	
	/**
	 * E赞查询
	 * @param adPraiseParam
	 * @return
	 */
    Result<Page<AdPraiseDTO>> selectAdPraiseList(AdPraiseParam adPraiseParam);
    
    /**
     * E赞详情
     * @param id
     * @return
     */
    Result<AdPraiseDTO> selectAbPraiseById(Long id);
	
	/**
	 * 抢赞
	 * @param id
	 * @return
	 */
    Result<PraisePointDTO> clickPraise(Long id,PraiseTypeEnum praiseEnum,Long memberId,String num);
    
    /**
     * 精选广告
     * @param adEgainParam
     * @return
     */
    @Deprecated
    Result<Page<AdFlatVideoDTO>> selectEgainAd(AdEgainParam adEgainParam);
    
    /**
	 * 点击广告
	 * @param id
	 * @param memberId
	 * @return
	 */
    Result<ClickAdPointDTO> clickAd( Long id, Long memberId,String num);
    
    /**
     * 查询E赚广告
     * 
     * @param adEgainParam
     * @return
     * @author jiangxinjun
     * @date 2017年7月18日
     */
    Result<Page<AdEgainQueryDTO>> selectEgain(AdEgainParam adEgainParam);
    
    /**
     * 积分榜，人气榜查询
     * 
     * @param adPointParam
     * @return
     * @author jiangxinjun
     * @date 2017年7月19日
     */
    Result<List<AdPointDTO>> selectAdTop(AdPointForeignParam adPointParam);
    
    /**
     * 查询精品推荐广告列表
     * 
     * @param adChoicenessParam
     * @return
     * @author jiangxinjun
     * @date 2017年7月19日
     */
    Result<Page<ChoicenessAdDTO>> selectChoicenessAd(AdChoicenessParam adChoicenessParam);
}

package com.lawu.eshop.ad.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.constants.AdPlatformStatusEnum;
import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.param.AdPlatformExtendParam;
import com.lawu.eshop.ad.param.AdPlatformFindParam;
import com.lawu.eshop.ad.param.AdPlatformInternalParam;
import com.lawu.eshop.ad.param.AdPlatformParam;
import com.lawu.eshop.ad.param.AdPlatformUpdateParam;
import com.lawu.eshop.ad.srv.bo.AdPlatformBO;
import com.lawu.eshop.ad.srv.bo.AdPlatformFlatBO;
import com.lawu.eshop.ad.srv.bo.AdPlatformVideoBO;
import com.lawu.eshop.ad.srv.bo.AdPlatformVideoFlatBO;
import com.lawu.eshop.ad.srv.converter.AdPlatformConverter;
import com.lawu.eshop.ad.srv.domain.AdPlatformDO;
import com.lawu.eshop.ad.srv.domain.AdPlatformDOExample;
import com.lawu.eshop.ad.srv.domain.AdPlatformDOExample.Criteria;
import com.lawu.eshop.ad.srv.domain.extend.AdPlatformDOView;
import com.lawu.eshop.ad.srv.domain.extend.AdPlatformFlatView;
import com.lawu.eshop.ad.srv.domain.extend.AdPlatformVideoView;
import com.lawu.eshop.ad.srv.domain.extend.SelectAdEgainIdExample;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.AdPlatformDOMapper;
import com.lawu.eshop.ad.srv.mapper.extend.AdDOMapperExtend;
import com.lawu.eshop.ad.srv.mapper.extend.AdPlatformDOMapperExtend;
import com.lawu.eshop.ad.srv.service.AdPlatformService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

@Service
public class AdPlatformServiceImpl implements AdPlatformService {

    @Autowired
    private AdPlatformDOMapper adPlatformDOMapper;

    @Autowired
    private AdPlatformDOMapperExtend adPlatformDOMapperExtend;
    
    @Autowired
	private AdDOMapper adDOMapper;

	@Autowired
	private AdDOMapperExtend adDOMapperExtend;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveAdPlatform(AdPlatformParam adPlatformParam) {
        AdPlatformDO adPlatformDO = new AdPlatformDO();
        adPlatformDO.setTitle(adPlatformParam.getTitle());
        adPlatformDO.setMediaUrl(adPlatformParam.getMediaUrl());
        adPlatformDO.setType(adPlatformParam.getTypeEnum().val);
        adPlatformDO.setPosition(adPlatformParam.getPositionEnum().val);
        adPlatformDO.setAdId(adPlatformParam.getAdId());
        adPlatformDO.setLinkUrl(adPlatformParam.getLinkUrl());
        adPlatformDO.setProductId(adPlatformParam.getProductId());
        adPlatformDO.setMerchantStoreId(adPlatformParam.getMerchantStoreId());
        adPlatformDO.setRegionPath(adPlatformParam.getRegionPath());
        adPlatformDO.setStatus(AdPlatformStatusEnum.UP.val);
        adPlatformDO.setGmtCreate(new Date());
        adPlatformDO.setGmtModified(new Date());
        adPlatformDO.setRegionName(adPlatformParam.getRegionName());
        adPlatformDO.setContent(adPlatformParam.getContent());
        return adPlatformDOMapper.insert(adPlatformDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer removeAdPlatform(Long id) {
        AdPlatformDOExample example = new AdPlatformDOExample();
        example.createCriteria().andIdEqualTo(id);
        AdPlatformDO adPlatformDO = new AdPlatformDO();
        adPlatformDO.setStatus(AdPlatformStatusEnum.DELETE.val);
        return adPlatformDOMapper.updateByExampleSelective(adPlatformDO, example);
    }

    @Override
    public List<AdPlatformBO> selectByPosition(PositionEnum positionEnum) {
        AdPlatformDOExample example = new AdPlatformDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(AdPlatformStatusEnum.UP.val).andPositionEqualTo(positionEnum.val);
        List<AdPlatformDO> DOS = adPlatformDOMapper.selectByExample(example);
        return DOS.isEmpty() ? null : AdPlatformConverter.convertBOS(DOS);
    }

    @Override
    public Page<AdPlatformBO> selectList(AdPlatformFindParam param) {
        AdPlatformDOExample example = new AdPlatformDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatusNotEqualTo(AdPlatformStatusEnum.DELETE.val);
        if (param.getPositionEnum() != null) {
            criteria.andPositionEqualTo(param.getPositionEnum().val);
        }
        if (param.getTypeEnum() != null) {
            criteria.andTypeEqualTo(param.getTypeEnum().val);
        }
        if (StringUtils.isNotEmpty(param.getTitle())) {
            criteria.andTitleLike("%" + param.getTitle() + "%");
        }
        if(StringUtils.isNotEmpty(param.getBeginDate())){
            criteria.andGmtCreateGreaterThanOrEqualTo(DateUtil.stringToDate(param.getBeginDate() + " 00:00:00"));
        }
        if(StringUtils.isNotEmpty(param.getEndDate())){
            criteria.andGmtCreateLessThanOrEqualTo(DateUtil.stringToDate(param.getEndDate() + " 23:59:59"));
        }
        example.setOrderByClause("gmt_create desc");
        Long count=adPlatformDOMapper.countByExample(example);
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<AdPlatformDO> DOS = adPlatformDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<AdPlatformBO> bos = AdPlatformConverter.convertBOS(DOS);
        Page<AdPlatformBO> pageAd = new Page<AdPlatformBO>();
        pageAd.setCurrentPage(param.getCurrentPage());
        pageAd.setTotalCount(count.intValue());
        pageAd.setRecords(bos);
        return pageAd;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(Long id, AdPlatformParam adPlatformParam) {
        AdPlatformDO adPlatformDO = new AdPlatformDO();
        adPlatformDO.setId(id);
        adPlatformDO.setTitle(adPlatformParam.getTitle());
        adPlatformDO.setContent(adPlatformParam.getContent());
        adPlatformDO.setMediaUrl(adPlatformParam.getMediaUrl());
        adPlatformDO.setType(adPlatformParam.getTypeEnum().val);
        adPlatformDO.setLinkUrl(adPlatformParam.getLinkUrl());
        adPlatformDO.setProductId(adPlatformParam.getProductId());
        adPlatformDO.setMerchantStoreId(adPlatformParam.getMerchantStoreId());
        adPlatformDO.setPosition(adPlatformParam.getPositionEnum().val);
        adPlatformDO.setRegionPath(adPlatformParam.getRegionPath());
        adPlatformDO.setRegionName(adPlatformParam.getRegionName());
        adPlatformDO.setAdId(adPlatformParam.getAdId());
        adPlatformDO.setGmtModified(new Date()); 
        return adPlatformDOMapper.updateByPrimaryKeySelective(adPlatformDO);
    }

    @Override
    public AdPlatformBO select(Long id) {
        AdPlatformDO adPlatformDO = adPlatformDOMapper.selectByPrimaryKey(id);
        return AdPlatformConverter.convertBO(adPlatformDO);
    }

    @Override
    public void unShelve(Long id) {
        AdPlatformDO adPlatformDO = new AdPlatformDO();
        adPlatformDO.setId(id);
        adPlatformDO.setStatus(AdPlatformStatusEnum.DOWN.val);
        adPlatformDOMapper.updateByPrimaryKeySelective(adPlatformDO);
    }

    @Override
    public void onShelve(Long id) {
        AdPlatformDO adPlatformDO = new AdPlatformDO();
        adPlatformDO.setId(id);
        adPlatformDO.setStatus(AdPlatformStatusEnum.UP.val);
        adPlatformDOMapper.updateByPrimaryKeySelective(adPlatformDO);
    }

    @Override
    public List<AdPlatformBO> getAdPlatformByTypePositionRegionPath(TypeEnum typeEnum, PositionEnum positionEnum, String regionPath) {
        AdPlatformDOExample example = new AdPlatformDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(typeEnum.val).andPositionEqualTo(positionEnum.val).andStatusEqualTo(AdPlatformStatusEnum.UP.val).andRegionPathLike(regionPath + "%");
        List<AdPlatformDO> adPlatformDOS = adPlatformDOMapper.selectByExample(example);
        return AdPlatformConverter.convertBOS(adPlatformDOS);
    }

    @Override
    public List<AdPlatformBO> getAdPlatformByTypePosition(TypeEnum typeEnum, PositionEnum positionEnum) {
        AdPlatformExtendParam param = new AdPlatformExtendParam();
        
		if (typeEnum.val != TypeEnum.TYPE_ALL.val) {
			param.setType(typeEnum.val);
		}
        
        param.setPosition(positionEnum.val);
        param.setStatus(AdPlatformStatusEnum.UP.val);
        List<AdPlatformDOView> adPlatformDOViews = adPlatformDOMapperExtend.getAdPlatformByTypePosition(param);
        return AdPlatformConverter.convertBO(adPlatformDOViews);
    }

    @Override
    public boolean selectByProductIdAndStatus(Long productId) {
        AdPlatformDOExample example = new AdPlatformDOExample();
        example.createCriteria().andProductIdEqualTo(productId).andStatusEqualTo(AdPlatformStatusEnum.UP.val);
        List<AdPlatformDO> adPlatformDOS = adPlatformDOMapper.selectByExample(example);
        if(adPlatformDOS == null || adPlatformDOS.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

	@Override
	public  AdPlatformVideoFlatBO selAdPlatformPositionAd(AdPlatformInternalParam param) {
		// 组装查询参数
		SelectAdEgainIdExample example = new SelectAdEgainIdExample();
		
		if(param.getLatitude()!=null && param.getLongitude()!=null){
			example.setLatitude(new BigDecimal(param.getLatitude()));
			example.setLongitude(new BigDecimal(param.getLongitude()));
		}
		example.setMerchantIds(param.getMerchantIds());
		example.setAreas(param.getAreas());
		
		List<AdPlatformVideoView> list = adPlatformDOMapperExtend.selAdPlatformPositionAd(example);
		
		List<AdPlatformVideoBO> listVideo= new ArrayList<>();
		
		List<AdPlatformFlatBO> listOneFlat = new ArrayList<>();
		List<AdPlatformFlatBO> listTwoFlat = new ArrayList<>();
		List<AdPlatformFlatBO> listFiveFlat = new ArrayList<>();
		
		
		AdPlatformVideoFlatBO videoFlatBO = new AdPlatformVideoFlatBO();
		
		for (AdPlatformVideoView adPlatformVideoView : list) {
			
			if(adPlatformVideoView.getPosition()==PositionEnum.POSITON_AD_TOP.val){
				if(listTwoFlat.size()>2) continue;
				AdPlatformFlatBO bo = new  AdPlatformFlatBO();
				bo.setAdId(adPlatformVideoView.getAdId());
				bo.setMediaUrl(adPlatformVideoView.getMediaUrl());
				listTwoFlat.add(bo);
			}else if(adPlatformVideoView.getPosition()==PositionEnum.AD_POSITION_TWO.val){
				if(listVideo.size()>3) continue;
				AdPlatformVideoBO bo = new  AdPlatformVideoBO();
				bo.setAdId(adPlatformVideoView.getAdId());
				bo.setContent(adPlatformVideoView.getContent());
				bo.setId(adPlatformVideoView.getId());
				bo.setName(adPlatformVideoView.getName());
				bo.setTitle(adPlatformVideoView.getTitle());
				bo.setVideoImgUrl(adPlatformVideoView.getVideoImgUrl());
				bo.setLogoUrl(adPlatformVideoView.getLogoUrl());
				listVideo.add(bo);
			}else if(adPlatformVideoView.getPosition()==PositionEnum.AD_POSITION_THREE.val){
				if(listOneFlat.size()>1) continue;
				AdPlatformFlatBO bo = new  AdPlatformFlatBO();
				bo.setAdId(adPlatformVideoView.getAdId());
				bo.setMediaUrl(adPlatformVideoView.getMediaUrl());
				listOneFlat.add(bo);
			}else if(adPlatformVideoView.getPosition()==PositionEnum.AD_POSITION_FOUR.val){
				if(listFiveFlat.size()>5) continue;
				AdPlatformFlatBO bo = new  AdPlatformFlatBO();
				bo.setAdId(adPlatformVideoView.getAdId());
				bo.setMediaUrl(adPlatformVideoView.getMediaUrl());
				listFiveFlat.add(bo);
			}
			
		}
		videoFlatBO.setListFiveFlat(listFiveFlat);
		videoFlatBO.setListOneFlat(listOneFlat);
		videoFlatBO.setListTwoFlat(listTwoFlat);
		videoFlatBO.setListVideo(listVideo);
		
		return videoFlatBO;
	}

	@Override
	public List<AdPlatformBO> getAdPlatformByTypePosition() {
		 AdPlatformDOExample example = new AdPlatformDOExample();
		 List<Byte> bytes = new ArrayList<>();
		 bytes.add(PositionEnum.SHOPPING_INTEGRAL_INDIANA.val);
		 bytes.add(PositionEnum.ACTIVITY_PRODUCT.val);
		 bytes.add(PositionEnum.POSITON_SHOP_TOP.val);
		 example.createCriteria().andPositionIn(bytes).andStatusEqualTo(AdPlatformStatusEnum.UP.val);
		 List<AdPlatformDO> adPlatformDOS = adPlatformDOMapper.selectByExample(example);
		 return AdPlatformConverter.convertProductTopBO(adPlatformDOS);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePriority(Long id, AdPlatformUpdateParam param) {
		  AdPlatformDO adPlatformDO = new AdPlatformDO();
	      adPlatformDO.setId(id);
	      adPlatformDO.setPriority(param.getPriority());
	      adPlatformDO.setGmtModified(new Date());
	      adPlatformDOMapper.updateByPrimaryKeySelective(adPlatformDO);
	}

	@Override
	public int count(PositionEnum positionEnum) {
		AdPlatformDOExample example = new AdPlatformDOExample();
		example.createCriteria().andPositionEqualTo(positionEnum.val).andStatusEqualTo(AdPlatformStatusEnum.UP.val);
		int count = (int)adPlatformDOMapper.countByExample(example);
		return count;
	}


}

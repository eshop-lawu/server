package com.lawu.eshop.ad.srv.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.param.FavoriteAdCountParam;
import com.lawu.eshop.ad.param.FavoriteAdParam;
import com.lawu.eshop.ad.param.PraiseWarnParam;
import com.lawu.eshop.ad.srv.bo.FavoriteAdDOViewBO;
import com.lawu.eshop.ad.srv.bo.FavoriteAdPraiseWarnBO;
import com.lawu.eshop.ad.srv.converter.FavoriteAdConverter;
import com.lawu.eshop.ad.srv.domain.FavoriteAdDO;
import com.lawu.eshop.ad.srv.domain.FavoriteAdDOExample;
import com.lawu.eshop.ad.srv.domain.extend.FavoriteAdDOView;
import com.lawu.eshop.ad.srv.domain.extend.FavoriteAdExtendDOView;
import com.lawu.eshop.ad.srv.domain.extend.FavoriteAdPraiseWarnView;
import com.lawu.eshop.ad.srv.mapper.FavoriteAdDOMapper;
import com.lawu.eshop.ad.srv.mapper.extend.FavoriteAdDOMapperExtend;
import com.lawu.eshop.ad.srv.service.FavoriteAdService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * 广告收藏接口实现
 * @author zhangrc
 * @date 2017/4/8
 *
 */
@Service
public class FavoriteAdServiceImpl implements FavoriteAdService {
	
	@Autowired
	private FavoriteAdDOMapper favoriteAdDOMapper;
	
	@Autowired 
	private FavoriteAdDOMapperExtend favoriteAdDOMapperExtend;

	/**
	 * 广告收藏
	 */
	@Override
	public Integer save(Long memberId,Long adId,String userNum ) {
		FavoriteAdDOExample example=new FavoriteAdDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andAdIdEqualTo(adId);
		int count=favoriteAdDOMapper.countByExample(example);
		if(count==1){
			return 0;
		}
		FavoriteAdDO favoriteAd=new FavoriteAdDO();
		favoriteAd.setAdId(adId);
		favoriteAd.setMemberId(memberId);
		favoriteAd.setGmtCreate(new Date());
		favoriteAd.setMemberNum(userNum);
		favoriteAd.setIsSend(false);
		int row=favoriteAdDOMapper.insert(favoriteAd);
		return row;
	}

	/**
	 * 取消收藏
	 */
	@Override
	public void remove(Long adId,Long memberId) {
		FavoriteAdDOExample example = new FavoriteAdDOExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andAdIdEqualTo(adId);
        favoriteAdDOMapper.deleteByExample(example);
	}

	/**
	 * 我收藏的广告
	 */
	@Override
	public Page<FavoriteAdDOViewBO> selectMyFavoriteAd(FavoriteAdParam param,Long memberId) {
		
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        FavoriteAdDOView view=new FavoriteAdDOView();
        view.setMemberId(memberId);
        view.setType(param.getTypeEnum().val);
        List<FavoriteAdDOView> views = favoriteAdDOMapperExtend.selectMyFavoriteAdByRowbounds(view, rowBounds);
        
        FavoriteAdCountParam countParam = new FavoriteAdCountParam();
        countParam.setMemberId(memberId);
        countParam.setType(param.getTypeEnum().val);
        int count= favoriteAdDOMapperExtend.getFavoriteAdCount(countParam);
        Page<FavoriteAdDOViewBO> page=new Page<FavoriteAdDOViewBO>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount(count);
        page.setRecords(FavoriteAdConverter.convertBOS(views));
		return page;
	}

	@Override
	public Boolean isFavoriteAd(Long adId, Long memberId) {
		FavoriteAdDOExample fAexample = new FavoriteAdDOExample();
		fAexample.createCriteria().andAdIdEqualTo(adId).andMemberIdEqualTo(memberId);
		int count = favoriteAdDOMapper.countByExample(fAexample);
		if (count > 0) { // 是否收藏
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<FavoriteAdPraiseWarnBO> selectFavoriteAdPraise(PraiseWarnParam param) {
		FavoriteAdExtendDOView view = new FavoriteAdExtendDOView();
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE, +10);
		view.setWarnDate(DateUtil.getDateTimeFormat(nowTime.getTime()));
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<FavoriteAdPraiseWarnView> list=favoriteAdDOMapperExtend.selectFavoriteAdPraise(view,rowBounds);
		List<FavoriteAdPraiseWarnBO> listBO = new ArrayList<>();
		for (FavoriteAdPraiseWarnView favoriteAdPraiseWarnView : list) {
			FavoriteAdPraiseWarnBO bo = new FavoriteAdPraiseWarnBO();
			bo.setAdId(favoriteAdPraiseWarnView.getAdId());
			bo.setMemberId(favoriteAdPraiseWarnView.getMemberId());
			bo.setTitle(favoriteAdPraiseWarnView.getTitle());
			bo.setMemberNum(favoriteAdPraiseWarnView.getMemberNum());
			bo.setId(favoriteAdPraiseWarnView.getId());
			listBO.add(bo);
		}
		return listBO;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateIsSend(Long id) {
		FavoriteAdDO record = new FavoriteAdDO();
		record.setId(id);
		record.setIsSend(true);
		favoriteAdDOMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Integer getFavoriteCount(Long adId) {
		FavoriteAdDOExample example = new FavoriteAdDOExample();
		example.createCriteria().andAdIdEqualTo(adId);
		return favoriteAdDOMapper.countByExample(example);
	}

	@Override
	public Integer getFavoriteAdCount(Long memberId,Byte type) {
		FavoriteAdCountParam param = new FavoriteAdCountParam();
		param.setMemberId(memberId);
		param.setType(type);
		return favoriteAdDOMapperExtend.getFavoriteAdCount(param);
	}

}

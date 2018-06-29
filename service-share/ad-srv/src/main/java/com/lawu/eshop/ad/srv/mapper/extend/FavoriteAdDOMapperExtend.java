package com.lawu.eshop.ad.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.ad.param.FavoriteAdCountParam;
import com.lawu.eshop.ad.srv.domain.extend.FavoriteAdDOView;
import com.lawu.eshop.ad.srv.domain.extend.FavoriteAdExtendDOView;
import com.lawu.eshop.ad.srv.domain.extend.FavoriteAdPraiseWarnView;

/**
 * 广告收藏接口扩展
 * @author zhangrc
 * @date 2017/4/8
 *
 */
public interface FavoriteAdDOMapperExtend {
	
	/**
	 * 我收藏的广告
	 * @param memberId
	 * @param rowBounds
	 * @return
	 */
	List<FavoriteAdDOView> selectMyFavoriteAdByRowbounds(FavoriteAdDOView  param, RowBounds rowBounds);

	/**
	 * 收藏的抢赞十分钟提醒
	 * @return
	 */
	List<FavoriteAdPraiseWarnView> selectFavoriteAdPraise(FavoriteAdExtendDOView view, RowBounds rowBounds);

	int getFavoriteAdCount(FavoriteAdCountParam param);
}
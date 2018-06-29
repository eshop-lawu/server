package com.lawu.eshop.ad.srv.service;

import java.util.List;

import com.lawu.eshop.ad.dto.FavoriteAdCountDTO;
import com.lawu.eshop.ad.param.FavoriteAdParam;
import com.lawu.eshop.ad.param.PraiseWarnParam;
import com.lawu.eshop.ad.srv.bo.FavoriteAdDOViewBO;
import com.lawu.eshop.ad.srv.bo.FavoriteAdPraiseWarnBO;
import com.lawu.framework.core.page.Page;

/**
 * 广告收藏接口
 * @author zhangrc
 * @date 2017/4/8
 *
 */
public interface FavoriteAdService {
	
	/**
	 * 广告收藏
	 * @param adId
	 * @param memberId
	 * @return
	 */
	Integer save(Long adId,Long memberId,String userNum );
	
	/**
	 * 取消收藏
	 * @param id
	 */
	void remove(Long adId ,Long memberId);
	
	/**
	 * 我收藏的广告列表
	 * @param memberId
	 * @return
	 */
	Page<FavoriteAdDOViewBO> selectMyFavoriteAd(FavoriteAdParam param,Long memberId);
	
	/**
	 * 判断用户是否收藏广告
	 * @param adId
	 * @param memberId
	 * @return
	 */
	Boolean isFavoriteAd(Long adId,Long memberId);

	/**
	 * 收藏的广告抢赞十分钟提醒
	 */
	List<FavoriteAdPraiseWarnBO> selectFavoriteAdPraise(PraiseWarnParam param);
	
	/**
	 * 抢赞提示改为已发送
	 * @param id
	 */
	void updateIsSend(Long id);

	/**
	 * 查询广告关注人数
	 *
	 * @param adId
	 * @return
	 * @author meishuquan
	 */
	Integer getFavoriteCount(Long adId);

	Integer getFavoriteAdCount(Long memberId, Byte type);
}

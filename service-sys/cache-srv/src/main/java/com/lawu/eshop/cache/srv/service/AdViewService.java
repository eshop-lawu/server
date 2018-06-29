package com.lawu.eshop.cache.srv.service;

import java.util.Set;

/**
 * 存储广告浏览记录
 * @author zhangrc
 * @date 2014/4/27
 *
 */
public interface AdViewService {
	
	void setAdView(String adId, String memberId);
	
	
	Set<String> getAdviews(String adId);
	
	
	/**
	 * 用户看广告记录
	 * @param adId
	 * @param memberId
	 */
	Boolean setMemeberSeeDetail(String adId, String memberId);
	

}

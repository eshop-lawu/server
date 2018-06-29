package com.lawu.eshop.ad.srv.service;

import java.util.List;

import com.lawu.eshop.ad.srv.bo.AdLexiconBO;

/**
 * 广告词库
 * @author zhangrc
 * @date 2017/4/14
 *
 */
public interface AdLexiconService {
	
	/**
	 * 添加词
	 * @param title
	 * @return
	 */
	Integer save(String title);
	
	/**
	 * 查询词库
	 * @return
	 */
	List<AdLexiconBO> selectList(Long adId);

}

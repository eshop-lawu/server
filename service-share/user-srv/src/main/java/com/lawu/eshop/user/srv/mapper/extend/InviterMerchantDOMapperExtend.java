package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.user.srv.domain.extend.InviterMerchantDOView;

/**
 * 
 * 我推荐的商家扩展接口
 * @author zhangrc
 * @date 2017/03/30
 *
 */
public interface InviterMerchantDOMapperExtend {
	
	/**
	 * 我邀请的商家
	 * @param merchantInviter
	 * @return
	 */
	List<InviterMerchantDOView> selectInviterMerchantByRowbounds(InviterMerchantDOView inviterMerchantDO,RowBounds rowBounds);
	
	/**
	 * 我邀请的商家总数
	 * @param merchantInviter
	 * @return
	 */
	Integer selectInviterMerchantCount(InviterMerchantDOView inviterMerchantDO);
	
   
}
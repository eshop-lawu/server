package com.lawu.eshop.ad.srv.service;

import java.util.List;

import com.lawu.eshop.ad.srv.bo.GetRedPacketBO;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;

/**
 * 积分池
 * @author zhangrc
 * @date 2017/4/11
 *
 */
public interface PointPoolService {
	
	/**
	 * 根据广告id查询前三名用户
	 * @param adId
	 * @return
	 */
	List<PointPoolDO> selectMemberList(Long adId);
	
	
	/**
	 * 根据用户查询是否已经抢到赞
	 * @param memberId
	 * @return
	 */
	Boolean selectStatusByMember(Long adId,Long memberId);
	
	/**
	 * 判断用户是否已经领取过红包
	 * @param merchantId 商家id
	 * @param userNum 会员编号
	 * @return
	 */
	GetRedPacketBO isGetRedPacket(Long merchantId,String userNum);
	


	
	

}

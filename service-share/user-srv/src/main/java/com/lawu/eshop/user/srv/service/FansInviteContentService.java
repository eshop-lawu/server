package com.lawu.eshop.user.srv.service;

import java.util.Map;

import com.lawu.eshop.user.param.FansInviteContentExtendParam;
import com.lawu.eshop.user.srv.bo.FansInviteContentBO;

public interface FansInviteContentService {

	
	Long saveInviteContentService(FansInviteContentExtendParam inviteContentParam);
	
	Long saveInviteContentExtendService(FansInviteContentExtendParam inviteContentParam);
	
	FansInviteContentBO selectInviteContentById(Long id);

	/**
	 * 处理过期的粉丝邀请(更改过期状态，删除粉丝记录，退还商家积分)
	 *
	 * @return
	 * @author meishuquan
	 */
	void dealOverdueFansInvite(Integer pageSize);

	/**
	 * 获取邀请粉丝事务消息参数
	 *
	 * @return
	 * @author meishuquan
	 */
	Map<String, Object> getInviteFansMap();

	/**
	 * 更新邀请粉丝内容表粉丝邀请记录id
	 *
	 * @param id
	 * @param fansInviteDetailId
	 * @author meishuquan
	 */
	void updateFansInviteDetailId(Long id, Long fansInviteDetailId);
}

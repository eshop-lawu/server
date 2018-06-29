package com.lawu.eshop.user.srv.service;

import java.util.List;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.eshop.user.srv.bo.EFriendInviterBO;
import com.lawu.eshop.user.srv.bo.InviterBO;
import com.lawu.framework.core.page.Page;

/**
 * 公共服务接口
 *
 * @author meishuquan
 * @date 2017/3/23
 */
public interface CommonService {

    /**
     * 根据账号查询邀请人信息
     *
     * @param account 邀请人账号
     * @return
     */
    InviterBO getInviterByAccount(String account);

    /**
     * 根据被邀请人查询出该人所有level邀请人编号
     * @param invitedUserNum
     * @param level
	 * @param isLevel	是否需要查询上级级别
     * @return
     */
	List<CommissionInvitersUserDTO> selectHigherLevelInviters(String invitedUserNum, int level, boolean isLevel);

    /**
     * 我的E友
     * @param dataParam
     * @return
     */
    Page<EFriendInviterBO> selectEFriend(EFriendQueryDataParam dataParam);
}

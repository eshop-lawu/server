package com.lawu.eshop.user.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.user.constants.FreezeTypeEnum;
import com.lawu.eshop.user.srv.service.InviteService;
import com.lawu.eshop.user.srv.service.UserCommonService;

/**
 * 用户公用操作接口实现类
 * 
 * @author jiangxinjun
 * @createDate 2018年3月1日
 * @updateDate 2018年3月1日
 */
@Service
public class UserCommonServiceImpl implements UserCommonService {
    
    @Autowired
    private InviteService inviteService;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void freezeAccount(FreezeTypeEnum frozenType, boolean isFreeze, String userNum) {
        // 当天用户上下级关系操作
        if (FreezeTypeEnum.ROBOT_REGISTRATION.equals(frozenType)) {
            if (isFreeze) {
                inviteService.deleteRelationship(userNum);
            } else {
                inviteService.restoreRelationship(userNum);
            }
        }
    }

}

package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.constants.FreezeTypeEnum;

/**
 * 用户公用操作接口
 * 
 * @author jiangxinjun
 * @createDate 2018年3月1日
 * @updateDate 2018年3月1日
 */
public interface UserCommonService {
    
    /**
     * 冻结用户公用操作
     * 
     * @author jiangxinjun
     * @createDate 2018年3月1日
     * @updateDate 2018年3月1日
     */
    void freezeAccount(FreezeTypeEnum frozenType, boolean isFreeze, String userNum);
    
}

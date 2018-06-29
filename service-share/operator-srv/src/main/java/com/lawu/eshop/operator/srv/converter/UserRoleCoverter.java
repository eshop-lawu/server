package com.lawu.eshop.operator.srv.converter;

import com.lawu.eshop.operator.srv.bo.UserRoleBO;
import com.lawu.eshop.operator.srv.domain.UserRoleDO;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public class UserRoleCoverter {

    public static UserRoleBO cover(UserRoleDO userRoleDO) {
        if (userRoleDO == null){
            return  null;
        }
        UserRoleBO userRoleBO = new UserRoleBO();
        userRoleBO.setId(userRoleDO.getId());
        userRoleBO.setRoleId(userRoleDO.getRoleId());
        userRoleBO.setUserId(userRoleDO.getUserId());
        return userRoleBO;
    }
}

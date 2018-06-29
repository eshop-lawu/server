package com.lawu.eshop.operator.srv.mapper.extend;

import com.lawu.eshop.operator.srv.domain.extend.UserRoleDOView;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public interface UserRoleDOMapperExtend {
    /**
     * @author zhangyong
     * @date 2017/4/19.
     */
     List<UserRoleDOView> findUserRoleByUserId(Integer userId);
}

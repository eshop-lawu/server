package com.lawu.eshop.operator.srv.converter;

import com.lawu.eshop.operator.dto.RoleDTO;
import com.lawu.eshop.operator.srv.bo.RoleBO;
import com.lawu.eshop.operator.srv.domain.RoleDO;
import com.lawu.eshop.operator.srv.domain.extend.UserRoleDOView;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class RoleConverter {

    public static RoleBO coverBO(UserRoleDOView userRoleDOView) {
        if (userRoleDOView == null) {
            return null;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(userRoleDOView.getRoleName());
        roleBO.setRoleKey(userRoleDOView.getRoleKey());
        roleBO.setId(userRoleDOView.getId());
        return roleBO;
    }

    public static RoleBO cover(RoleDO roleDO) {
        if (roleDO == null) {
            return null;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setId(roleDO.getId());
        roleBO.setRoleName(roleDO.getRoleName());
        roleBO.setRoleKey(roleDO.getRoleKey());
        roleBO.setGmtCreate(roleDO.getGmtCreate());
        return roleBO;
    }

    public static RoleDTO coverDTO(RoleBO roleBO) {
        if (roleBO == null) {
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleBO.getId());
        roleDTO.setRoleName(roleBO.getRoleName());
        roleDTO.setRoleKey(roleBO.getRoleKey());
        roleDTO.setGmtCreate(roleBO.getGmtCreate());
        return roleDTO;
    }
}

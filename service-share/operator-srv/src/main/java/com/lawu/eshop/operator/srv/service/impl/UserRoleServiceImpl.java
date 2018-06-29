package com.lawu.eshop.operator.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.operator.srv.bo.UserRoleBO;
import com.lawu.eshop.operator.srv.converter.UserRoleCoverter;
import com.lawu.eshop.operator.srv.domain.RolePermissionDO;
import com.lawu.eshop.operator.srv.domain.RolePermissionDOExample;
import com.lawu.eshop.operator.srv.domain.UserRoleDO;
import com.lawu.eshop.operator.srv.domain.UserRoleDOExample;
import com.lawu.eshop.operator.srv.mapper.RolePermissionDOMapper;
import com.lawu.eshop.operator.srv.mapper.UserRoleDOMapper;
import com.lawu.eshop.operator.srv.service.UserRoleService;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDOMapper userRoleDOMapper;
    @Autowired
    private RolePermissionDOMapper rolePermissionDOMapper;

    @Override
    public UserRoleBO findUserRoleInfo(Integer userId, Integer roleId) {
        UserRoleDOExample example = new UserRoleDOExample();
        example.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
        List<UserRoleDO> userRoleDOList = userRoleDOMapper.selectByExample(example);
        if (userRoleDOList.isEmpty()) {
            return null;
        }
        return UserRoleCoverter.cover(userRoleDOList.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoles(Integer userId, String roleId) {
        UserRoleDOExample example = new UserRoleDOExample();
        example.createCriteria().andUserIdEqualTo(userId);

        userRoleDOMapper.deleteByExample(example);
        String[] idString = roleId.split(",");
        Integer[] id = new Integer[idString.length];
        for (int i = 0; i < idString.length; i++) {
            id[i] = Integer.valueOf(idString[i]);
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setUserId(userId);
            userRoleDO.setRoleId(id[i]);
            userRoleDO.setGmtCreate(new Date());
            userRoleDOMapper.insert(userRoleDO);
        }
    }

    @Override
    public List<UserRoleBO> findUserRoleByRoleId(Integer id) {
        UserRoleDOExample example = new UserRoleDOExample();
        example.createCriteria().andRoleIdEqualTo(id);
        List<UserRoleDO> list = userRoleDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserRoleBO> userRoleBOS = new ArrayList<>();
        for (UserRoleDO userRoleDO : list) {
            UserRoleBO userRoleBO = UserRoleCoverter.cover(userRoleDO);
            userRoleBOS.add(userRoleBO);
        }
        return userRoleBOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRolePermission(Integer roleId, String permissionIds) {
        RolePermissionDOExample example = new RolePermissionDOExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionDOMapper.deleteByExample(example);

        if(StringUtils.isNotEmpty(permissionIds)){
            String[] idString = permissionIds.split(",");
            Integer[] id = new Integer[idString.length];
            for (int i = 0; i < idString.length; i++) {
                id[i] = Integer.valueOf(idString[i]);
                RolePermissionDO rolePermissionDO = new RolePermissionDO();
                rolePermissionDO.setRoleId(roleId);
                rolePermissionDO.setPermissionId(id[i]);
                rolePermissionDO.setGmtCreate(new Date());
                rolePermissionDOMapper.insert(rolePermissionDO);
            }
        }
    }

    @Override
    public List<UserRoleBO> findRoleByUserId(Integer userId) {
        UserRoleDOExample example = new UserRoleDOExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UserRoleDO> list = userRoleDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserRoleBO> userRoleBOS = new ArrayList<>();
        for (UserRoleDO userRoleDO : list) {
            UserRoleBO userRoleBO = UserRoleCoverter.cover(userRoleDO);
            userRoleBOS.add(userRoleBO);
        }
        return userRoleBOS;
    }

    @Override
    public void delUserRole(Integer userId) {
        UserRoleDOExample example = new UserRoleDOExample();
        example.createCriteria().andUserIdEqualTo(userId);

        userRoleDOMapper.deleteByExample(example);
    }

}

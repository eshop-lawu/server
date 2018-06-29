package com.lawu.eshop.operator.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.LockedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.api.service.UserService;
import com.lawu.eshop.operator.dto.UserDTO;
import com.lawu.eshop.operator.dto.UserRoleDTO;
import com.lawu.framework.web.Result;
import com.lawu.shiro.AuthService;
import com.lawu.shiro.model.ShiroRole;
import com.lawu.shiro.model.ShiroUser;

/**
 * @author Leach
 * @date 2017/4/18
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Override
    public ShiroUser find(String account) {

        Result<UserDTO> result = userService.find(account);
        if(result.getModel() == null){
            return null;
        }
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(result.getModel().getAccount());
        shiroUser.setPassword(result.getModel().getPassword());
        shiroUser.setRolesKey(result.getModel().getRolesKey());
        List<ShiroRole> roles = new ArrayList<>();
        for (UserRoleDTO userRoleDTO : result.getModel().getRoles()) {
            ShiroRole shiroRole = new ShiroRole();
            shiroRole.setRoleKey(userRoleDTO.getRoleKey());
            shiroRole.setPermissionsKey(userRoleDTO.getPermissionsKey());
            roles.add(shiroRole);
        }
        shiroUser.setRoles(roles);
        shiroUser.setRolesKey(result.getModel().getRolesKey());
        return shiroUser;
    }

    @Override
    public ShiroUser find(String account, String password) {
        Result<UserDTO> result = userService.find(account, password);
        if(result.getRet() == ResultCode.USER_ACCOUNT_DISABLE){
            throw new LockedAccountException();
        }
        if(result.getRet() != ResultCode.SUCCESS){
            return null;
        }
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(result.getModel().getAccount());
        shiroUser.setPassword(result.getModel().getPassword());
        shiroUser.setRolesKey(result.getModel().getRolesKey());
        List<ShiroRole> roles = new ArrayList<>();
        for (UserRoleDTO userRoleDTO : result.getModel().getRoles()) {
            ShiroRole shiroRole = new ShiroRole();
            shiroRole.setRoleKey(userRoleDTO.getRoleKey());
            shiroRole.setPermissionsKey(userRoleDTO.getPermissionsKey());
            roles.add(shiroRole);
        }
        shiroUser.setRoles(roles);
        shiroUser.setRolesKey(result.getModel().getRolesKey());
        return shiroUser;
    }

}

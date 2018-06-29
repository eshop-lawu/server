package com.lawu.eshop.operator.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.constants.StatusEnum;
import com.lawu.eshop.operator.dto.UserDTO;
import com.lawu.eshop.operator.dto.UserListDTO;
import com.lawu.eshop.operator.dto.UserRoleDTO;
import com.lawu.eshop.operator.param.UserPageParam;
import com.lawu.eshop.operator.param.UserParam;
import com.lawu.eshop.operator.srv.bo.RoleBO;
import com.lawu.eshop.operator.srv.bo.UserBO;
import com.lawu.eshop.operator.srv.bo.UserListBO;
import com.lawu.eshop.operator.srv.converter.UserConverter;
import com.lawu.eshop.operator.srv.service.UserRoleService;
import com.lawu.eshop.operator.srv.service.UserService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
@RestController
@RequestMapping(value = "user/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 根据账号密码查询用户信息
     *
     * @param account
     * @param pwd
     * @return
     */
    @RequestMapping(value = "withPwd/{account}", method = RequestMethod.POST)
    public Result<UserDTO> find(@PathVariable("account") String account, @RequestParam(value = "pwd") String pwd) {
        UserBO userBO = userService.find(account, pwd);
        if (userBO == null || userBO.getStatus()== StatusEnum.STATUS_INVALID.val) {
            return successGet(ResultCode.MEMBER_WRONG_PWD);
        }
        if(userBO.getStatus()== StatusEnum.STATUS_DISABLE.val){
            return successGet(ResultCode.USER_ACCOUNT_DISABLE);
        }
        //查询user对应的roleKey
        List<RoleBO> roleBOList = userService.findUserRoleByUserId(userBO.getId());
        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        Set<String> rolesKeys = new HashSet<>();
        UserRoleDTO userRoleDTO;
        if (!roleBOList.isEmpty()) {
            //查询role对应的permission_key
            for (RoleBO roleBO : roleBOList) {
                rolesKeys.add(roleBO.getRoleKey());
                userRoleDTO = new UserRoleDTO();
                userRoleDTO.setRoleKey(roleBO.getRoleKey());
                List<String> permissionKeyList = userService.findRolePermission(roleBO.getId());
                userRoleDTO.setPermissionsKey(permissionKeyList);
                userRoleDTOS.add(userRoleDTO);
            }
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(userBO.getAccount());
        userDTO.setPassword(userBO.getPassword());
        userDTO.setRoles(userRoleDTOS);
        userDTO.setRolesKey(rolesKeys);
        return successGet(userDTO);
    }

    /**
     * 根据账号查询账户
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "findByAccount/{account}", method = RequestMethod.POST)
    public Result<UserDTO> find(@PathVariable("account") String account) {
        UserBO userBO = userService.find(account);
        if (userBO == null) {
            return successGet(ResultCode.USER_WRONG_ID);
        }
        //查询user对应的roleKey
        List<RoleBO> roleBOList = userService.findUserRoleByUserId(userBO.getId());
        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        Set<String> rolesKeys = new HashSet<>();
        if (!roleBOList.isEmpty()) {
            //查询role对应的permission_key
            for (RoleBO roleBO : roleBOList) {
                rolesKeys.add(roleBO.getRoleKey());
                UserRoleDTO userRoleDTO = new UserRoleDTO();
                userRoleDTO.setRoleKey(roleBO.getRoleKey());
                List<String> permissionKeyList = userService.findRolePermission(roleBO.getId());
                userRoleDTO.setPermissionsKey(permissionKeyList);
                userRoleDTOS.add(userRoleDTO);
            }
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(userBO.getAccount());
        userDTO.setPassword(userBO.getPassword());
        userDTO.setRoles(userRoleDTOS);
        userDTO.setRolesKey(rolesKeys);
        return successGet(userDTO);
    }

    /**
     * @param account
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Result addUser(@RequestParam(value = "account") String account,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "password") String password) {

        Integer id = userService.addUser(account, name, password);
        if (id == null || id <= 0) {
            return successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "editUser", method = RequestMethod.PUT)
    public Result editUser(@RequestBody UserParam userParam) {
        Integer row = userService.editUser(userParam);
        if (row == null || row <= 0) {
            return successCreated(ResultCode.UPDATE_FAIL);
        }
        return successCreated();
    }

    /**
     * 用户列表
     *
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "findUserList", method = RequestMethod.POST)
    Result<Page<UserListDTO>> findUserList(@RequestBody UserPageParam pageParam) {
        Page<UserListDTO> userListDTOPage = new Page<UserListDTO>();
        Page<UserListBO> boPage = userService.findUserList(pageParam);
        if (boPage.getRecords().isEmpty()) {
            userListDTOPage.setRecords(new ArrayList<>());
            userListDTOPage.setTotalCount(0);
            return successGet(userListDTOPage);
        }
        List<UserListDTO> userListDTOS = UserConverter.coverDTOS(boPage.getRecords());
        userListDTOPage.setTotalCount(boPage.getTotalCount());
        userListDTOPage.setCurrentPage(boPage.getCurrentPage());
        userListDTOPage.setRecords(userListDTOS);
        return successGet(userListDTOPage);
    }

    @RequestMapping(value = "assignRoles", method = RequestMethod.POST)
    public Result assignRoles(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "roleId") String roleId) {
        if (roleId == null || userId == null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        userRoleService.assignRoles(userId, roleId);
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "delUser/{id}", method = RequestMethod.PUT)
    public Result delUser(@PathVariable(value = "id") Integer id){
        userService.delUser(id);
        return successCreated();
    }

    /**
     * 禁用用户
     * @param id
     * @return
     */
    @RequestMapping(value = "userDisabled/{id}", method = RequestMethod.PUT)
    public Result userDisabled(@PathVariable(value = "id") Integer id){
        Integer row = userService.userDisabled(id);
        if(row == null || row <=0){
            return successCreated(ResultCode.UPDATE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 启用用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "userEnable/{id}", method = RequestMethod.PUT)
    public Result userEnable(@PathVariable(value = "id") Integer id){
        Integer row = userService.userEnable(id);
        if(row == null || row <=0){
            return successCreated(ResultCode.UPDATE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "findUserById/{id}", method = RequestMethod.GET)
    public Result<UserListDTO> findUserById(@PathVariable(value = "id") Integer id){
        UserListBO userListBO = userService.finUserById(id);
        if(userListBO == null){
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        UserListDTO userListDTO = UserConverter.coverDTO(userListBO);
        return successGet(userListDTO);
    }

    /**
     * 根据账号查询用户
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "getUser/{account}", method = RequestMethod.GET)
    public Result<UserListDTO> getUser(@PathVariable(value = "account") String account){
        UserListBO userListBO = userService.getUserByAccount(account);
        if(userListBO == null){
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(UserConverter.coverDTO(userListBO));
    }

}

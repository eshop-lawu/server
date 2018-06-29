package com.lawu.eshop.operator.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.dto.RoleDTO;
import com.lawu.eshop.operator.param.RoleInfoParam;
import com.lawu.eshop.operator.param.RoleParam;
import com.lawu.eshop.operator.srv.bo.RoleBO;
import com.lawu.eshop.operator.srv.bo.UserRoleBO;
import com.lawu.eshop.operator.srv.converter.RoleConverter;
import com.lawu.eshop.operator.srv.service.RoleService;
import com.lawu.eshop.operator.srv.service.UserRoleService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
@RestController
@RequestMapping(value = "role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 查询角色信息列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "findroleList", method = RequestMethod.POST)
    public Result<Page<RoleDTO>> findroleList(@RequestBody RoleParam param) {

        Page<RoleDTO> page = new Page<>();
        Page<RoleBO> boPage = roleService.findroleList(param);
        if (boPage.getRecords().isEmpty()) {
            page.setRecords(new ArrayList<>());
            return successGet(page);
        }
        List<RoleDTO> list = new ArrayList<>();
        for (RoleBO roleBO : boPage.getRecords()) {
            RoleDTO roleDTO = RoleConverter.coverDTO(roleBO);
            list.add(roleDTO);
        }
        page.setRecords(list);
        page.setTotalCount(boPage.getTotalCount());
        page.setCurrentPage(boPage.getCurrentPage());
        return successGet(page);
    }

    /**
     * 新增角色信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public Result addRole(@RequestBody RoleInfoParam param) {
        Integer roleId = roleService.addRole(param);
        if (roleId <= 0) {
            return successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 修改角色
     *
     * @param id
     * @param param
     * @return
     */
    @RequestMapping(value = "updateRole/{id}", method = RequestMethod.PUT)
    public Result updateRole(@PathVariable(value = "id") Integer id, @RequestBody RoleInfoParam param) {
        Integer row = roleService.updateRole(id, param);
        if (row <= 0) {
            return successCreated(ResultCode.UPDATE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delRole/{id}", method = RequestMethod.PUT)
    public Result delRole(@PathVariable(value = "id") Integer id) {
        if (id == null || id <= 0) {
            return successCreated(ResultCode.FAIL);
        }
        //查询角色下的用户
        List<UserRoleBO> userRoleBOS = userRoleService.findUserRoleByRoleId(id);
        if (userRoleBOS.isEmpty()) {
            roleService.delRole(id);
            return successCreated();
        }else{
            return  successCreated(ResultCode.ROLE_HAS_USER_RELATE);
        }

    }

    /**
     * 权限关联
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @RequestMapping(value = "addRolePermission", method = RequestMethod.POST)
    public Result addRolePermission(@RequestParam(value = "roleId") Integer roleId, @RequestParam(value = "permissionIds") String permissionIds) {
        userRoleService.addRolePermission(roleId, permissionIds);
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping(value = "findroleListAll",method = RequestMethod.GET)
    public Result<List<RoleDTO>> findroleListAll(){

        List<RoleBO> list = roleService.findroleListAll();
        if (list==null) {
            return successGet(new ArrayList<>());
        }
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (RoleBO roleBO : list) {
            RoleDTO roleDTO = RoleConverter.coverDTO(roleBO);
            roleDTOS.add(roleDTO);
        }
        return successGet(roleDTOS);
    }

    @RequestMapping(value = "findRoleByUserId/{userId}",method = RequestMethod.GET)
    public List<RoleDTO> findRoleByUserId(@PathVariable(value = "userId") Integer userId){

        List<UserRoleBO> userRoleBOS = userRoleService.findRoleByUserId(userId);
        if(userRoleBOS == null){
          return   new ArrayList<>();
        }
        List<RoleDTO> list = new ArrayList<>();
        for(UserRoleBO userRoleBO : userRoleBOS){
            RoleDTO roleDTO =new RoleDTO();
            roleDTO.setId(userRoleBO.getRoleId());
            list.add(roleDTO);
        }
        return list;
    }

    @RequestMapping(value = "findRoleById/{id}",method = RequestMethod.GET)
    public RoleDTO findRoleById(@PathVariable(value = "id") Integer id){
        RoleBO roleBO = roleService.findRoleById(id);
        RoleDTO roleDTO = RoleConverter.coverDTO(roleBO);
        return roleDTO;
    }

    @RequestMapping(value = "delUserRole",method = RequestMethod.PUT)
    public Result delUserRole(@RequestParam("userId") Integer userId){
        userRoleService.delUserRole(userId);
        return successCreated();
    }

}

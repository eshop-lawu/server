package com.lawu.eshop.operator.api.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.RoleService;
import com.lawu.eshop.operator.dto.RoleDTO;
import com.lawu.eshop.operator.param.RoleInfoParam;
import com.lawu.eshop.operator.param.RoleParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
@Api(tags = "role")
@RestController
@RequestMapping(value = "role/")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询角色信息记录", notes = "查询角色信息记录 [1004，1000]（章勇）", httpMethod = "GET")
    @RequiresPermissions("role:list")
    @PageBody
    @RequestMapping(value = "findroleList", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<Page<RoleDTO>> findroleList(@ModelAttribute RoleParam param) {
        if (param == null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Result<Page<RoleDTO>> pageResult = roleService.findroleList(param);
        return pageResult;
    }

    @ApiOperation(value = "查询所有角色信息记录", notes = "查询所有角色信息记录 [1000]（章勇）", httpMethod = "GET")
    @PageBody
    @RequestMapping(value = "findroleListAll", method = RequestMethod.GET)
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<List<RoleDTO>> findroleListAll() {

        Result<List<RoleDTO>> pageResult = roleService.findroleListAll();
        return pageResult;
    }


    @ApiOperation(value = "添加角色信息", notes = "添加角色信息 [1004,1000]（章勇）", httpMethod = "POST")
    @RequiresPermissions("role:add")
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result addRole(@ModelAttribute RoleInfoParam param) {
        if (param == null || StringUtils.isEmpty(param.getRoleName()) || StringUtils.isEmpty(param.getRoleKey())) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Result result = roleService.addRole(param);
        return result;
    }

    @ApiOperation(value = "编辑角色信息", notes = "编辑角色信息 [1004,1000]（章勇）", httpMethod = "PUT")
    @RequiresPermissions("role:edit")
    @RequestMapping(value = "editRole/{id}", method = RequestMethod.PUT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result updateRole(@PathVariable(value = "id") @ApiParam(value = "角色ID", required = true) Integer id, @ModelAttribute RoleInfoParam param) {
        if (param == null ) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Result result = roleService.updateRole(id, param);
        return result;
    }

    @ApiOperation(value = "删除角色信息", notes = "删除角色信息 [1004，8102,1000]（章勇）", httpMethod = "PUT")
    @RequiresPermissions("role:del")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "delRole/{id}", method = RequestMethod.PUT)
    public Result delRole(@PathVariable(value = "id") Integer id) {
        if (id == null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Result result = roleService.delRole(id);
        return result;
    }


    /**
     * 权限关联
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @ApiOperation(value = "权限关联", notes = "权限关联 [1004，2103,1000]（章勇）", httpMethod = "POST")
    @RequiresPermissions("role:addPermission")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "addRolePermission", method = RequestMethod.POST)
    Result addRolePermission(@RequestParam(value = "roleId")  Integer roleId,
                             @RequestParam(value = "permissionIds")  String permissionIds) {
        Result result = roleService.addRolePermission(roleId, permissionIds);
        return result;
    }

    @ApiOperation(value = "根据userId查询关联的角色ID", notes = "根据userId查询关联的角色ID [1000]（章勇）", httpMethod = "GET")
    @PageBody
    @RequestMapping(value = "findRoleByUserId/{userId}",method = RequestMethod.GET)
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<List<RoleDTO>> findRoleByUserId(@PathVariable(value = "userId") Integer userId){
        List<RoleDTO> list = roleService.findRoleByUserId(userId);
        return successGet(list);
    }


    @ApiOperation(value = "根据id查询角色", notes = "根据id查询角色 [1000]（章勇）", httpMethod = "GET")
    @RequestMapping(value = "findRoleById/{id}",method = RequestMethod.GET)
    @RequiresAuthentication
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<RoleDTO> findRoleById(@PathVariable(value = "id") Integer id){
        RoleDTO roleDTO = roleService.findRoleById(id);
        return successGet(roleDTO);

    }

}

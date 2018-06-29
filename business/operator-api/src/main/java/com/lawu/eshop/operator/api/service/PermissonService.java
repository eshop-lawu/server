package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.operator.dto.PermissionDTO;
import com.lawu.eshop.operator.dto.PermissionListDTO;
import com.lawu.eshop.operator.param.PermissionParam;
import com.lawu.eshop.operator.param.PerssionParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
@FeignClient(value = "operator-srv")
public interface PermissonService {
    /**
     * 查询当前用户的权限信息
     * @param account
     * @return
     */
    @RequestMapping(value = "permission/findPermissionByAccount/{account}",method = RequestMethod.GET)
    Result<List<PermissionDTO>>findPermissionByAccount(@PathVariable("account") String account);

    /**
     * 增加权限记录
     * @param perssionParam
     * @return
     */
    @RequestMapping(value = "permission/addPerssion",method = RequestMethod.POST)
    Result addPerssion(@ModelAttribute PerssionParam perssionParam);

    /**
     * 查询权限记录列表
     * @param param
     * @return
     */
    @RequestMapping(value = "permission/findPermissionList", method = RequestMethod.POST)
    Result<Page<PermissionListDTO>> findPermissionList(@ModelAttribute PermissionParam param);

    @RequestMapping(value = "permission/findAllPermissionList", method = RequestMethod.GET)
    List<PermissionListDTO> findAllPermissionList();

    @RequestMapping(value = "permission/findPermissionListByRoleId/{roleId}", method = RequestMethod.GET)
    List<PermissionListDTO> findPermissionListByRoleId(@PathVariable(value = "roleId") Integer roleId);

    @RequestMapping(value = "permission/delPermission",method = RequestMethod.POST)
    Result delPermission(@RequestParam(value = "permissionIds") String permissionIds);

    @RequestMapping(value = "permission/findPermissionInfoById/{id}",method = RequestMethod.GET)
    PermissionListDTO findPermissionInfoById(@PathVariable(value = "id")Integer id);

    @RequestMapping(value = "permission/editPermission/{id}", method = RequestMethod.PUT)
    Result editPermission(@PathVariable(value = "id") Integer id,@ModelAttribute PerssionParam perssionParam);
}

package com.lawu.eshop.operator.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.operator.dto.PermissionDTO;
import com.lawu.eshop.operator.dto.PermissionListDTO;
import com.lawu.eshop.operator.param.PermissionParam;
import com.lawu.eshop.operator.param.PerssionParam;
import com.lawu.eshop.operator.srv.bo.PermissionBO;
import com.lawu.eshop.operator.srv.bo.PerssionInfoListBO;
import com.lawu.eshop.operator.srv.bo.UserBO;
import com.lawu.eshop.operator.srv.converter.PermissionConverter;
import com.lawu.eshop.operator.srv.service.PermissonService;
import com.lawu.eshop.operator.srv.service.UserService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
@RestController
@RequestMapping(value = "permission")
public class PermissonController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissonService perssionService;

    /**
     * 查询权限信息
     *
     * @return
     */
    @RequestMapping(value = "findPermissionByAccount/{account}", method = RequestMethod.GET)
    public Result<List<PermissionDTO>> findPermissionByAccount(@PathVariable("account") String account) {
        UserBO userBO = userService.find(account);
        if (userBO == null) {
            return successGet(ResultCode.USER_WRONG_ID);
        }
        List<PerssionInfoListBO> perssionInfoListBOS = userService.findRolePermissionList(userBO.getId());
        if (perssionInfoListBOS == null) {
            return successGet(ResultCode.ROLE_HAS_NOPERMISSION);
        }
        List<PermissionDTO> perssionDTOS = PermissionConverter.coverDTOS(perssionInfoListBOS);
        return successGet(perssionDTOS);
    }

    @RequestMapping(value = "addPerssion", method = RequestMethod.POST)
    public Result addPerssion(@RequestBody PerssionParam perssionParam) {
        Integer id = perssionService.addPerssion(perssionParam);
        if (id == null || id <= 0) {
            return successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 查询权限列表记录
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "findPermissionList", method = RequestMethod.POST)
    public Result<Page<PermissionListDTO>> findPermissionList(@RequestBody PermissionParam param) {

        Page<PermissionListDTO> pages = new Page<>();
        Page<PermissionBO> boPage = perssionService.findPerminnionList(param);
        if (boPage == null || boPage.getRecords().isEmpty()) {
            pages.setRecords(new ArrayList<>());
            return successGet(pages);
        }
        List<PermissionListDTO> listDTOList = new ArrayList<>();
        for (PermissionBO permissionBO : boPage.getRecords()) {
            PermissionListDTO permissionListDTO = PermissionConverter.coverDTO(permissionBO);
            listDTOList.add(permissionListDTO);
        }
        pages.setTotalCount(boPage.getTotalCount());
        pages.setCurrentPage(boPage.getCurrentPage());
        pages.setRecords(listDTOList);
        return successGet(pages);
    }

    /**
     * 查询所有的权限记录
     *
     * @return
     */
    @RequestMapping(value = "findAllPermissionList", method = RequestMethod.GET)
    public List<PermissionListDTO> findAllPermissionList() {

        List<PermissionBO> permissionBOS = perssionService.findAllPermissionList();
        if (permissionBOS == null) {
            return new ArrayList<>();
        }
        List<PermissionListDTO> listDTOList = new ArrayList<>();
        for (PermissionBO permissionBO : permissionBOS) {
            PermissionListDTO permissionListDTO = PermissionConverter.coverDTO(permissionBO);
            listDTOList.add(permissionListDTO);
        }
        return listDTOList;
    }

    @RequestMapping(value = "findPermissionListByRoleId/{roleId}", method = RequestMethod.GET)
    public List<PermissionListDTO> findPermissionListByRoleId(@PathVariable(value = "roleId") Integer roleId) {

        List<PermissionBO> permissionBOS = perssionService.findPermissionListByRoleId(roleId);
        if (permissionBOS == null) {
            return new ArrayList<>();
        }
        List<PermissionListDTO> listDTOList = new ArrayList<>();
        for (PermissionBO permissionBO : permissionBOS) {
            PermissionListDTO permissionListDTO = new PermissionListDTO();
            permissionListDTO.setId(permissionBO.getId());
            listDTOList.add(permissionListDTO);
        }
        return listDTOList;
    }

    @RequestMapping(value = "delPermission", method = RequestMethod.POST)
    public Result delPermission(@RequestParam(value = "permissionIds") String permissionIds) {
        perssionService.delPermission(permissionIds);
        return successCreated(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "findPermissionInfoById/{id}", method = RequestMethod.GET)
    public PermissionListDTO findPermissionInfoById(@PathVariable(value = "id") Integer id) {
        PermissionBO permissionBO = perssionService.findPermissionInfoById(id);
        if (permissionBO == null) {
            return null;
        }
        PermissionListDTO permissionListDTO = PermissionConverter.coverDTO(permissionBO);
        return permissionListDTO;
    }

    @RequestMapping(value = "editPermission/{id}", method = RequestMethod.PUT)
    public Result editPermission(@PathVariable(value = "id") Integer id,@RequestBody PerssionParam perssionParam) {
         perssionService.editPermission(id,perssionParam);
        return successCreated(ResultCode.SUCCESS);
    }

}

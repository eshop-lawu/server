package com.lawu.eshop.operator.srv.service;

import com.lawu.eshop.operator.param.PermissionParam;
import com.lawu.eshop.operator.param.PerssionParam;
import com.lawu.eshop.operator.srv.bo.PermissionBO;
import com.lawu.framework.core.page.Page;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
public interface PermissonService {
     Integer addPerssion(PerssionParam perssionParam);

     Page<PermissionBO> findPerminnionList(PermissionParam param);

    List<PermissionBO> findAllPermissionList();

    List<PermissionBO> findPermissionListByRoleId(Integer roleId);

    void delPermission(String permissionIds);

    PermissionBO findPermissionInfoById(Integer id);

    void editPermission(Integer id, PerssionParam perssionParam);
}

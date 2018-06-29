package com.lawu.eshop.operator.srv.service;

import com.lawu.eshop.operator.param.RoleInfoParam;
import com.lawu.eshop.operator.param.RoleParam;
import com.lawu.eshop.operator.srv.bo.RoleBO;
import com.lawu.framework.core.page.Page;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/21.
 */
public interface RoleService {
    Page<RoleBO> findroleList(RoleParam param);

    Integer addRole(RoleInfoParam param);

    Integer updateRole(Integer id, RoleInfoParam param);

    void delRole(Integer id);

    List<RoleBO> findroleListAll();


    RoleBO findRoleById(Integer id);
}

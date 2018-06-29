package com.lawu.eshop.operator.srv.mapper.extend;

import com.lawu.eshop.operator.srv.domain.extend.RolePermissionDOView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public interface RolePermissionDOMapperExtend {

    List<RolePermissionDOView>  findRolePermission(Integer roleId);

    List<RolePermissionDOView>  findRolePermissionList(@Param("roleids") List<Integer> roleids);
}

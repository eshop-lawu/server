package com.lawu.eshop.operator.srv.converter;

import com.lawu.eshop.operator.dto.PermissionListDTO;
import com.lawu.eshop.operator.dto.PermissionDTO;
import com.lawu.eshop.operator.srv.bo.PermissionBO;
import com.lawu.eshop.operator.srv.bo.PerssionInfoListBO;
import com.lawu.eshop.operator.srv.domain.PermissionDO;
import com.lawu.eshop.operator.srv.domain.RolePermissionDO;
import com.lawu.eshop.operator.srv.domain.extend.RolePermissionDOView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class PermissionConverter {
    public static PermissionBO coverBO(RolePermissionDOView rolePermissionDOView) {
        if (rolePermissionDOView == null) {
            return null;
        }
        PermissionBO permissionBO = new PermissionBO();
        permissionBO.setPermissionName(rolePermissionDOView.getPermissionName());
        permissionBO.setPermissionKey(rolePermissionDOView.getPermissionKey());
        permissionBO.setId(rolePermissionDOView.getId());
        return permissionBO;
    }

    public static PermissionBO cover(PermissionDO p) {
        if (p == null) {
            return null;
        }
        PermissionBO permissionBO = new PermissionBO();
        permissionBO.setId(p.getId());
        permissionBO.setGmtCreate(p.getGmtCreate());
        permissionBO.setPermissionName(p.getPermissionName());
        permissionBO.setPermissionKey(p.getPermissionKey());
        permissionBO.setPermissionUrl(p.getPermissionUrl());
        permissionBO.setParentId(p.getParentId());
        return permissionBO;
    }

    public static PermissionListDTO coverDTO(PermissionBO permissionBO) {
        if (permissionBO == null) {
            return null;
        }
        PermissionListDTO permissionListDTO = new PermissionListDTO();
        permissionListDTO.setId(permissionBO.getId());
        permissionListDTO.setGmtCreate(permissionBO.getGmtCreate());
        permissionListDTO.setPermissionName(permissionBO.getPermissionName());
        permissionListDTO.setPermissionKey(permissionBO.getPermissionKey());
        permissionListDTO.setPermissionUrl(permissionBO.getPermissionUrl());
        permissionListDTO.setParentId(permissionBO.getParentId());
        return permissionListDTO;
    }

    public static List<PermissionDTO> coverDTOS(List<PerssionInfoListBO> perssionInfoListBOS) {
        if (perssionInfoListBOS == null) {
            return null;
        }
        List<PermissionDTO> list = new ArrayList<>();

        for (PerssionInfoListBO perssionInfoListBO : perssionInfoListBOS) {
            PermissionDTO permissionDTO = new PermissionDTO();
            permissionDTO.setPermissionUrl(perssionInfoListBO.getPermissionUrl());
            permissionDTO.setId(perssionInfoListBO.getId());
            permissionDTO.setPermissionName(perssionInfoListBO.getPermissionName());
            permissionDTO.setPermissionKey(perssionInfoListBO.getPermissionKey());
            permissionDTO.setParentId(perssionInfoListBO.getParentId());
            list.add(permissionDTO);
        }
        return list;
    }

    public static List<PermissionBO> coverRolePermissionDTOS(List<RolePermissionDO> permissionDOS) {
        if (permissionDOS.isEmpty()) {
            return null;
        }
        List<PermissionBO> list = new ArrayList<>();
        for (RolePermissionDO rolePermissionDO : permissionDOS) {
            PermissionBO permissionBO = new PermissionBO();
            permissionBO.setId(rolePermissionDO.getPermissionId());
            list.add(permissionBO);
        }

        return list;
    }
}

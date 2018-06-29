package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;
import com.lawu.eshop.mall.dto.AppPatchVersionOperatorDTO;
import com.lawu.eshop.mall.srv.bo.AppPatchVersionBO;
import com.lawu.eshop.mall.srv.domain.AppPatchVersionDO;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public class AppPatchVersionConverter {

    public static AppPatchVersionBO converBO(AppPatchVersionDO versionDO) {
        if (versionDO == null) {
            return null;
        }

        AppPatchVersionBO versionBO = new AppPatchVersionBO();
        versionBO.setId(versionDO.getId());
        versionBO.setAppVersionId(versionDO.getAppVersionId());
        versionBO.setAppVersion(versionDO.getAppVersion());
        versionBO.setPatchVersion(versionDO.getPatchVersion());
        versionBO.setUpdateDesc(versionDO.getUpdateDesc());
        versionBO.setStatus(versionDO.getStatus());
        versionBO.setGmtModified(versionDO.getGmtModified());
        versionBO.setGmtCreate(versionDO.getGmtCreate());
        return versionBO;
    }

    public static List<AppPatchVersionBO> converBOS(List<AppPatchVersionDO> versionDOS) {
        List<AppPatchVersionBO> versionBOS = new ArrayList<>();
        if (versionDOS == null || versionDOS.isEmpty()) {
            return versionBOS;
        }

        for (AppPatchVersionDO versionDO : versionDOS) {
            versionBOS.add(converBO(versionDO));
        }
        return versionBOS;
    }

    public static List<AppPatchVersionOperatorDTO> converOperatorDTOS(List<AppPatchVersionBO> versionBOS) {
        List<AppPatchVersionOperatorDTO> operatorDTOS = new ArrayList<>();
        if (versionBOS == null || versionBOS.isEmpty()) {
            return operatorDTOS;
        }

        for (AppPatchVersionBO versionBO : versionBOS) {
            AppPatchVersionOperatorDTO operatorDTO = new AppPatchVersionOperatorDTO();
            operatorDTO.setId(versionBO.getId());
            operatorDTO.setAppVersion(versionBO.getAppVersion());
            operatorDTO.setPatchVersion(versionBO.getPatchVersion());
            operatorDTO.setUpdateDesc(versionBO.getUpdateDesc());
            operatorDTO.setStatusEnum(AppPatchVersionStatusEnum.getEnum(versionBO.getStatus()));
            operatorDTO.setStatusDes(AppPatchVersionStatusEnum.getEnum(versionBO.getStatus()).getName());
            operatorDTO.setGmtModified(versionBO.getGmtModified());
            operatorDTO.setGmtCreate(versionBO.getGmtCreate());
            operatorDTOS.add(operatorDTO);
        }
        return operatorDTOS;
    }

}

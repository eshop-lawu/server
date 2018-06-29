package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.constants.MobileTypeEnum;
import com.lawu.eshop.mall.dto.AppVersionOperatorDTO;
import com.lawu.eshop.mall.srv.bo.AppVersionOperatorBO;
import com.lawu.eshop.mall.srv.domain.AppVersionDO;

/**
 * app 版本控制转换
 * @author zhangrc
 * @date 2017/09/25
 *
 */
public class AppVersionConverter {
	
	
	
	public static List<AppVersionOperatorBO> converterBOS(List<AppVersionDO> appVersionDOS) {
        if (appVersionDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<AppVersionOperatorBO> appVersionOperatorBOS = new ArrayList<>();
        for (AppVersionDO appVersionDO : appVersionDOS) {
        	
        	AppVersionOperatorBO bo = new AppVersionOperatorBO();
        	
			bo.setAppBigVersion(appVersionDO.getAppBigVersion());
			bo.setAppVersion(appVersionDO.getAppVersion());
			bo.setStatusEnum(AppStatusEnum.getEnum(appVersionDO.getStatus()));
			bo.setAppType(AppTypeEnum.getEnum(appVersionDO.getAppType()));
			bo.setUpdateDesc(appVersionDO.getUpdateDesc());
			bo.setIsForce(appVersionDO.getIsForce());
			bo.setGmtCreate(appVersionDO.getGmtCreate());
			bo.setMobileType(MobileTypeEnum.getEnum(appVersionDO.getPlatform()));
			bo.setId(appVersionDO.getId());
			appVersionOperatorBOS.add(bo);
			
        }
        return appVersionOperatorBOS;
    }
	
	public static List<AppVersionOperatorDTO> converterDTOS(List<AppVersionOperatorBO> appVersionBOS) {
        if (appVersionBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<AppVersionOperatorDTO> appVersionOperatorDTOS = new ArrayList<>();
        for (AppVersionOperatorBO appVersionOperatorBO : appVersionBOS) {
        	
        	AppVersionOperatorDTO dto = new AppVersionOperatorDTO();
        	
        	dto.setAppBigVersion(appVersionOperatorBO.getAppBigVersion());
        	dto.setAppVersion(appVersionOperatorBO.getAppVersion());
        	dto.setStatusEnum(appVersionOperatorBO.getStatusEnum());
        	dto.setAppType(appVersionOperatorBO.getAppType());
        	dto.setUpdateDesc(appVersionOperatorBO.getUpdateDesc());
        	dto.setIsForce(appVersionOperatorBO.getIsForce());
        	dto.setGmtCreate(appVersionOperatorBO.getGmtCreate());
        	dto.setMobileType(appVersionOperatorBO.getMobileType());
        	dto.setId(appVersionOperatorBO.getId());
        	
        	appVersionOperatorDTOS.add(dto);
			
        }
        return appVersionOperatorDTOS;
    }

}

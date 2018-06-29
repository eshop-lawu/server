package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.dto.AppPatchVersionOperatorDTO;
import com.lawu.eshop.mall.srv.bo.AppPatchVersionBO;
import com.lawu.eshop.mall.srv.domain.AppPatchVersionDO;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public class AppPatchVersionConverterTest {

    @Test
    public void converBO() {
        AppPatchVersionDO versionDO = new AppPatchVersionDO();
        versionDO.setId(1L);
        versionDO.setAppVersionId(2L);
        versionDO.setAppVersion("2.6.0");
        versionDO.setPatchVersion(1);
        versionDO.setUpdateDesc("test");
        versionDO.setStatus((byte) 1);
        versionDO.setGmtModified(new Date());
        versionDO.setGmtCreate(new Date());
        AppPatchVersionBO versionBO = AppPatchVersionConverter.converBO(versionDO);
        Assert.assertNotNull(versionBO);
        Assert.assertEquals(versionDO.getId(), versionBO.getId());
    }

    @Test
    public void converBOS() {
        List<AppPatchVersionDO> versionDOS = new ArrayList<>();
        AppPatchVersionDO versionDO = new AppPatchVersionDO();
        versionDO.setId(1L);
        versionDO.setAppVersionId(2L);
        versionDO.setAppVersion("2.6.0");
        versionDO.setPatchVersion(1);
        versionDO.setUpdateDesc("test");
        versionDO.setStatus((byte) 1);
        versionDO.setGmtModified(new Date());
        versionDO.setGmtCreate(new Date());
        versionDOS.add(versionDO);

        List<AppPatchVersionBO> versionBOS = AppPatchVersionConverter.converBOS(versionDOS);
        Assert.assertNotNull(versionBOS);
        Assert.assertEquals(1, versionBOS.size());
        Assert.assertEquals(versionDO.getId(), versionBOS.get(0).getId());
    }

    @Test
    public void converOperatorDTOS() {
        List<AppPatchVersionBO> versionBOS = new ArrayList<>();
        AppPatchVersionBO versionBO = new AppPatchVersionBO();
        versionBO.setId(1L);
        versionBO.setAppVersionId(2L);
        versionBO.setAppVersion("2.6.0");
        versionBO.setPatchVersion(1);
        versionBO.setUpdateDesc("test");
        versionBO.setStatus((byte) 1);
        versionBO.setGmtModified(new Date());
        versionBO.setGmtCreate(new Date());
        versionBOS.add(versionBO);

        List<AppPatchVersionOperatorDTO> versionOperatorDTOS = AppPatchVersionConverter.converOperatorDTOS(versionBOS);
        Assert.assertNotNull(versionOperatorDTOS);
        Assert.assertEquals(1, versionOperatorDTOS.size());
        Assert.assertEquals(versionBO.getId(), versionOperatorDTOS.get(0).getId());
    }
}

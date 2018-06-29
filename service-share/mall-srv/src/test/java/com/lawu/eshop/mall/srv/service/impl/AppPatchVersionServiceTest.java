package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;
import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.param.AppPatchVersionParam;
import com.lawu.eshop.mall.query.OperatorAppPatchVersionQuery;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.AppPatchVersionBO;
import com.lawu.eshop.mall.srv.domain.AppPatchVersionDO;
import com.lawu.eshop.mall.srv.domain.AppVersionDO;
import com.lawu.eshop.mall.srv.mapper.AppPatchVersionDOMapper;
import com.lawu.eshop.mall.srv.mapper.AppVersionDOMapper;
import com.lawu.eshop.mall.srv.service.AppPatchVersionService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class AppPatchVersionServiceTest {

    @Autowired
    private AppPatchVersionService appPatchVersionService;

    @Autowired
    private AppPatchVersionDOMapper appPatchVersionDOMapper;

    @Autowired
    private AppVersionDOMapper appVersionDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveAppPatchVersion() {
        AppPatchVersionParam param = new AppPatchVersionParam();
        param.setAppVersionId(1L);
        param.setAppVersion("2.6.0");
        param.setPatchVersion(1);
        param.setUpdateDesc("test");
        param.setStatusEnum(AppPatchVersionStatusEnum.ENABLE);
        appPatchVersionService.saveAppPatchVersion(param);

        List<AppPatchVersionDO> versionDOS = appPatchVersionDOMapper.selectByExample(null);
        Assert.assertNotNull(versionDOS);
        Assert.assertEquals(1, versionDOS.size());
        Assert.assertEquals(param.getAppVersion(), versionDOS.get(0).getAppVersion());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateAppPatchVersionStatus() {
        AppPatchVersionDO versionDO = new AppPatchVersionDO();
        versionDO.setAppVersionId(1L);
        versionDO.setAppVersion("2.6.0");
        versionDO.setPatchVersion(1);
        versionDO.setUpdateDesc("test");
        versionDO.setStatus(AppPatchVersionStatusEnum.NOT_ENABLED.getVal());
        versionDO.setGmtCreate(new Date());
        appPatchVersionDOMapper.insertSelective(versionDO);

        appPatchVersionService.updateAppPatchVersionStatus(versionDO.getId(), AppPatchVersionStatusEnum.ENABLE);
        AppPatchVersionDO appPatchVersionDO = appPatchVersionDOMapper.selectByPrimaryKey(versionDO.getId());
        Assert.assertNotNull(appPatchVersionDO);
        Assert.assertEquals(AppPatchVersionStatusEnum.ENABLE.getVal(), appPatchVersionDO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listOperatorAppPatchVersion() {
        AppPatchVersionDO versionDO = new AppPatchVersionDO();
        versionDO.setAppVersionId(1L);
        versionDO.setAppVersion("2.6.0");
        versionDO.setPatchVersion(1);
        versionDO.setUpdateDesc("test");
        versionDO.setStatus(AppPatchVersionStatusEnum.NOT_ENABLED.getVal());
        versionDO.setGmtCreate(new Date());
        appPatchVersionDOMapper.insertSelective(versionDO);

        OperatorAppPatchVersionQuery query = new OperatorAppPatchVersionQuery();
        query.setAppVersionId(versionDO.getAppVersionId());
        Page<AppPatchVersionBO> versionBOPage = appPatchVersionService.listOperatorAppPatchVersion(query);
        Assert.assertNotNull(versionBOPage);
        Assert.assertEquals(1, versionBOPage.getTotalCount().intValue());
        Assert.assertEquals(versionDO.getAppVersion(), versionBOPage.getRecords().get(0).getAppVersion());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAppPatchVersion() {
        AppVersionDO appVersionDO = new AppVersionDO();
        appVersionDO.setAppVersion("2.6.0");
        appVersionDO.setAppBigVersion("2");
        appVersionDO.setUpdateDesc("test");
        appVersionDO.setPlatform((byte) 2);
        appVersionDO.setAppType(AppTypeEnum.MEMBER.getVal());
        appVersionDO.setStatus(AppStatusEnum.ENABLE.getVal());
        appVersionDO.setIsForce(false);
        appVersionDOMapper.insertSelective(appVersionDO);

        AppPatchVersionDO versionDO = new AppPatchVersionDO();
        versionDO.setAppVersionId(Long.valueOf(appVersionDO.getId()));
        versionDO.setAppVersion(appVersionDO.getAppVersion());
        versionDO.setPatchVersion(1);
        versionDO.setUpdateDesc("test");
        versionDO.setStatus(AppPatchVersionStatusEnum.NOT_ENABLED.getVal());
        versionDO.setGmtCreate(new Date());
        appPatchVersionDOMapper.insertSelective(versionDO);

        Integer patchVersion = appPatchVersionService.getAppPatchVersion(AppTypeEnum.MEMBER, appVersionDO.getPlatform(), appVersionDO.getAppVersion());
        Assert.assertNotNull(patchVersion);
        Assert.assertEquals(versionDO.getPatchVersion(), patchVersion);
    }

}

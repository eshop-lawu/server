package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.param.AppPatchVersionParam;
import com.lawu.eshop.mall.query.OperatorAppPatchVersionQuery;
import com.lawu.eshop.mall.srv.bo.AppPatchVersionBO;
import com.lawu.eshop.mall.srv.converter.AppPatchVersionConverter;
import com.lawu.eshop.mall.srv.domain.AppPatchVersionDO;
import com.lawu.eshop.mall.srv.domain.AppPatchVersionDOExample;
import com.lawu.eshop.mall.srv.domain.AppVersionDO;
import com.lawu.eshop.mall.srv.domain.AppVersionDOExample;
import com.lawu.eshop.mall.srv.mapper.AppPatchVersionDOMapper;
import com.lawu.eshop.mall.srv.mapper.AppVersionDOMapper;
import com.lawu.eshop.mall.srv.service.AppPatchVersionService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
@Service
public class AppPatchVersionServiceImpl implements AppPatchVersionService {

    @Autowired
    private AppPatchVersionDOMapper appPatchVersionDOMapper;

    @Autowired
    private AppVersionDOMapper appVersionDOMapper;

    @Override
    public void saveAppPatchVersion(AppPatchVersionParam param) {
        AppPatchVersionDO versionDO = new AppPatchVersionDO();
        versionDO.setAppVersionId(param.getAppVersionId());
        versionDO.setAppVersion(param.getAppVersion());
        versionDO.setPatchVersion(param.getPatchVersion());
        versionDO.setUpdateDesc(param.getUpdateDesc());
        versionDO.setStatus(param.getStatusEnum().getVal());
        versionDO.setGmtCreate(new Date());
        appPatchVersionDOMapper.insertSelective(versionDO);
    }

    @Override
    public void updateAppPatchVersionStatus(Long id, AppPatchVersionStatusEnum statusEnum) {
        AppPatchVersionDO versionDO = new AppPatchVersionDO();
        versionDO.setId(id);
        versionDO.setStatus(statusEnum.getVal());
        versionDO.setGmtModified(new Date());
        appPatchVersionDOMapper.updateByPrimaryKeySelective(versionDO);
    }

    @Override
    public Page<AppPatchVersionBO> listOperatorAppPatchVersion(OperatorAppPatchVersionQuery query) {
        AppPatchVersionDOExample example = new AppPatchVersionDOExample();
        example.createCriteria().andAppVersionIdEqualTo(query.getAppVersionId());
        example.setOrderByClause("id desc");

        RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
        List<AppPatchVersionDO> versionDOS = appPatchVersionDOMapper.selectByExampleWithRowbounds(example, rowBounds);

        Page<AppPatchVersionBO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount(appPatchVersionDOMapper.countByExample(example));
        page.setRecords(AppPatchVersionConverter.converBOS(versionDOS));
        return page;
    }

    @Override
    public Integer getAppPatchVersion(AppTypeEnum typeEnum, Byte platform, String appVersion) {
        AppVersionDOExample example = new AppVersionDOExample();
        example.createCriteria().andAppVersionEqualTo(appVersion).andPlatformEqualTo(platform).andAppTypeEqualTo(typeEnum.getVal());
        List<AppVersionDO> versionDOS = appVersionDOMapper.selectByExample(example);
        if (versionDOS.isEmpty()) {
            return 0;
        }

        AppPatchVersionDOExample versionDOExample = new AppPatchVersionDOExample();
        versionDOExample.createCriteria().andAppVersionIdEqualTo(Long.valueOf(versionDOS.get(0).getId())).andStatusEqualTo(AppPatchVersionStatusEnum.ENABLE.getVal());
        versionDOExample.setOrderByClause("id desc");
        List<AppPatchVersionDO> patchVersionDOS = appPatchVersionDOMapper.selectByExample(versionDOExample);
        if (patchVersionDOS.isEmpty()) {
            return 0;
        }
        return patchVersionDOS.get(0).getPatchVersion();
    }

}

package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.param.AppPatchVersionParam;
import com.lawu.eshop.mall.query.OperatorAppPatchVersionQuery;
import com.lawu.eshop.mall.srv.bo.AppPatchVersionBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
public interface AppPatchVersionService {

    /**
     * 新增APP补丁版本记录
     *
     * @param param
     * @author meishuquan
     */
    void saveAppPatchVersion(AppPatchVersionParam param);

    /**
     * 更新APP补丁版本状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateAppPatchVersionStatus(Long id, AppPatchVersionStatusEnum statusEnum);

    /**
     * 运营平台查询补丁版本列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<AppPatchVersionBO> listOperatorAppPatchVersion(OperatorAppPatchVersionQuery query);

    /**
     * 获取APP补丁版本号
     *
     * @param typeEnum
     * @param platform
     * @param appVersion
     * @return
     * @author meishuquan
     */
    Integer getAppPatchVersion(AppTypeEnum typeEnum, Byte platform, String appVersion);

}

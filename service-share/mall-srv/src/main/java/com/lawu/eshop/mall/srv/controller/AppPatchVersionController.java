package com.lawu.eshop.mall.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.dto.AppPatchVersionOperatorDTO;
import com.lawu.eshop.mall.param.AppPatchVersionParam;
import com.lawu.eshop.mall.query.OperatorAppPatchVersionQuery;
import com.lawu.eshop.mall.srv.bo.AppPatchVersionBO;
import com.lawu.eshop.mall.srv.converter.AppPatchVersionConverter;
import com.lawu.eshop.mall.srv.service.AppPatchVersionService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
@RestController
@RequestMapping(value = "appPatchVersion/")
public class AppPatchVersionController extends BaseController {

    @Autowired
    private AppPatchVersionService appPatchVersionService;

    /**
     * 新增APP补丁版本记录
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveAppPatchVersion", method = RequestMethod.POST)
    public Result saveAppPatchVersion(@RequestBody AppPatchVersionParam param) {
        appPatchVersionService.saveAppPatchVersion(param);
        return successCreated();
    }

    /**
     * 更新APP补丁版本状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateAppPatchVersionStatus/{id}", method = RequestMethod.PUT)
    public Result updateAppPatchVersionStatus(@PathVariable Long id, @RequestParam AppPatchVersionStatusEnum statusEnum) {
        appPatchVersionService.updateAppPatchVersionStatus(id, statusEnum);
        return successCreated();
    }

    /**
     * 运营平台查询补丁版本列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorAppPatchVersion", method = RequestMethod.POST)
    public Result<Page<AppPatchVersionOperatorDTO>> listOperatorAppPatchVersion(@RequestBody OperatorAppPatchVersionQuery query) {
        Page<AppPatchVersionBO> versionBOPage = appPatchVersionService.listOperatorAppPatchVersion(query);
        Page<AppPatchVersionOperatorDTO> page = new Page<>();
        page.setCurrentPage(query.getCurrentPage());
        page.setTotalCount(versionBOPage.getTotalCount());
        page.setRecords(AppPatchVersionConverter.converOperatorDTOS(versionBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 获取APP补丁版本号
     *
     * @param typeEnum
     * @param platform
     * @param appVersion
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getAppPatchVersion", method = RequestMethod.GET)
    public Result<Integer> getAppPatchVersion(@RequestParam AppTypeEnum typeEnum, @RequestParam Byte platform, @RequestParam String appVersion) {
        Integer patchVersion = appPatchVersionService.getAppPatchVersion(typeEnum, platform, appVersion);
        return successGet(patchVersion);
    }

}

package com.lawu.eshop.member.api.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.mall.dto.AppPatchVersionDTO;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.AppPatchVersionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.framework.web.util.HeaderUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
@Api(tags = "appPatchVersion")
@RestController
@RequestMapping(value = "appPatchVersion/")
public class AppPatchVersionController extends BaseController {

    @Autowired
    private AppPatchVersionService appPatchVersionService;

    @Autowired
    private MemberApiConfig memberApiConfig;

    @Audit(date = "2017-12-13", reviewer = "孙林青")
    @ApiOperation(value = "获取APP补丁版本", notes = "获取APP补丁版本。[1036|8115]（梅述全）", httpMethod = "GET")
    @RequestMapping(value = "getAppPatchVersion", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<AppPatchVersionDTO> getAppPatchVersion() {
        String platform = HeaderUtil.getRequestPlatform(getRequest());
        String appVersion = HeaderUtil.getRequestAppVersion(getRequest());
        if (StringUtils.isEmpty(platform) || StringUtils.isEmpty(appVersion)) {
            return successGet(ResultCode.GET_HEADER_ERROR);
        }

        Result<Integer> result = appPatchVersionService.getAppPatchVersion(AppTypeEnum.MEMBER, Byte.valueOf(platform), appVersion);
        if (result.getModel() == 0) {
            return successGet(ResultCode.NOT_PATCH_VERSION);
        }

        String suffix = appVersion.substring(appVersion.length() - 1, appVersion.length());
        if (StringUtils.isNumeric(suffix)) {
            appVersion = appVersion + "." + result.getModel();
        } else {
            appVersion = appVersion.substring(0, appVersion.length() - 1) + result.getModel() + "." + suffix;
        }
        String downUrl = memberApiConfig.getDownloadUrl();
        downUrl = downUrl.replace("-{channel}", "").replace("{version}", appVersion);
        downUrl = downUrl.substring(0, downUrl.length() - 4);
        AppPatchVersionDTO versionDTO = new AppPatchVersionDTO();
        versionDTO.setDownloadUrl(downUrl);
        return successGet(versionDTO);
    }

}

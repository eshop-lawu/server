package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.AppPatchVersionStatusEnum;
import com.lawu.eshop.mall.dto.AppPatchVersionOperatorDTO;
import com.lawu.eshop.mall.param.AppPatchVersionParam;
import com.lawu.eshop.mall.query.OperatorAppPatchVersionQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/12/12.
 */
@FeignClient(value = "mall-srv")
public interface AppPatchVersionService {

    /**
     * 新增APP补丁版本记录
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "appPatchVersion/saveAppPatchVersion")
    Result saveAppPatchVersion(@RequestBody AppPatchVersionParam param);

    /**
     * 更新APP补丁版本状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.PUT, value = "appPatchVersion/updateAppPatchVersionStatus/{id}")
    Result updateAppPatchVersionStatus(@PathVariable("id") Long id, @RequestParam("statusEnum") AppPatchVersionStatusEnum statusEnum);

    /**
     * 运营平台查询补丁版本列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "appPatchVersion/listOperatorAppPatchVersion")
    Result<Page<AppPatchVersionOperatorDTO>> listOperatorAppPatchVersion(@RequestBody OperatorAppPatchVersionQuery query);
}

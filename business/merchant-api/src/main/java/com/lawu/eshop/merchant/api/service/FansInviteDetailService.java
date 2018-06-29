package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.property.dto.FansInviteDetailDTO;
import com.lawu.eshop.property.param.ListFansInviteDetailParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author meishuquan
 * @date 2017/4/24.
 */
@FeignClient(value = "property-srv")
public interface FansInviteDetailService {

    /**
     * 商家邀请粉丝记录列表
     *
     * @param merchantId
     * @param listFansInviteDetailParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "fansInviteDetail/listFansInviteDetail/{merchantId}")
    Result<Page<FansInviteDetailDTO>> listFansInviteDetail(@PathVariable("merchantId") Long merchantId, @ModelAttribute ListFansInviteDetailParam listFansInviteDetailParam);

    /**
     * 根据积分编号查询邀请粉丝详情
     *
     * @param pointNum
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "fansInviteDetail/getFansInviteDetail/{pointNum}")
    Result<FansInviteDetailDTO> getFansInviteDetailByPointNum(@PathVariable("pointNum") String pointNum);
}

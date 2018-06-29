package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.user.dto.EFriendInviterDTO;
import com.lawu.eshop.user.dto.InviterDTO;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author meishuquan
 * @date 2017/3/30.
 */
@FeignClient(value = "user-srv")
public interface InviterService {

    /**
     * 根据邀请人账号查询邀请人信息
     *
     * @param account 邀请人账号
     */
    @RequestMapping(method = RequestMethod.GET, value = "user/common/getInviter/{account}")
    Result<InviterDTO> getInviterByAccount(@PathVariable("account") String account);

    /**
     * 我的E友
     * @since v2.3.0
     * @param dataParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "user/common/selectEFriend")
    Result<Page<EFriendInviterDTO>> selectEFriend(@RequestBody EFriendQueryDataParam dataParam);
}

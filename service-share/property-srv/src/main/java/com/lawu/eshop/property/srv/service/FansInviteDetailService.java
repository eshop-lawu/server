package com.lawu.eshop.property.srv.service;

import com.lawu.eshop.property.param.ListFansInviteDetailParam;
import com.lawu.eshop.property.srv.bo.FansInviteDetailBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/4/24.
 */
public interface FansInviteDetailService {

    /**
     * 商家邀请粉丝记录列表
     *
     * @param merchantId
     * @param listFansInviteDetailParam
     * @return
     */
    Page<FansInviteDetailBO> listFansInviteDetail(Long merchantId, ListFansInviteDetailParam listFansInviteDetailParam);

    /**
     * 根据积分编号查询邀请粉丝详情
     *
     * @param pointNum
     * @return
     */
    FansInviteDetailBO getFansInviteDetailByPointNum(String pointNum);
}

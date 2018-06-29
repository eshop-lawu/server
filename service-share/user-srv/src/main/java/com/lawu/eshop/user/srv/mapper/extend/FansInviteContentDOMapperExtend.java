package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.user.param.UserFansParam;
import com.lawu.eshop.user.srv.domain.extend.FansInviteContentDOView;

public interface FansInviteContentDOMapperExtend {

    List<FansInviteContentDOView> listOverdueFansInvite(UserFansParam param);

}

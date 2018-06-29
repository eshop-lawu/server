package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.framework.web.Result;

public interface FreezeService {
    
    /**
     * 冻结用户账号
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月18日
     * @updateDate 2018年1月18日
     */
    @SuppressWarnings("rawtypes")
    Result freeze(FreezeParam param);
    
    /**
     * 清除token
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月5日
     * @updateDate 2018年3月5日
     */
    @SuppressWarnings("rawtypes")
    Result forcedExit(UserTypeEnum userType, String account);
}

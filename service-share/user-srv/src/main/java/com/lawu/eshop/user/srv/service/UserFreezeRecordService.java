package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.UserFreezeRecordParam;

/**
 * @author meishuquan
 * @date 2017/9/11.
 */
public interface UserFreezeRecordService {

    /**
     * 保存用户冻结记录
     *
     * @param param
     * @author meishuquan
     */
    void saveUserFreezeRecord(UserFreezeRecordParam param);
}

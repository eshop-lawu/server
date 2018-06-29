package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.user.param.UserFreezeRecordParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/9/11.
 */
@FeignClient(value = "user-srv")
public interface UserFreezeRecordService {

    /**
     * 保存用户冻结记录
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "userFreezeRecord/saveUserFreezeRecord", method = RequestMethod.POST)
    Result saveUserFreezeRecord(@RequestBody UserFreezeRecordParam param);
}

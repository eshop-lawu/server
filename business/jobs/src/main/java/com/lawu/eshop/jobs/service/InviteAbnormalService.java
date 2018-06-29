package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/1/19.
 */
@FeignClient(value = "beh-analyze-srv")
public interface InviteAbnormalService {
    @RequestMapping(value = "abnormal/editAbnormalRecord", method = RequestMethod.POST)
    Result editAbnormalRecord(@ModelAttribute AbnormalJobAddParam param);

    @RequestMapping(value = "abnormal/addActiveAbnormalInfo", method = RequestMethod.POST)
    Result addActiveAbnormalInfo(@ModelAttribute AbnormalJobAddParam addParam);
}

package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.beh.analyze.dto.AbnormalDTO;
import com.lawu.eshop.beh.analyze.param.AbnormalParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
@FeignClient(value = "beh-analyze-srv")
public interface AbnormalRecordService {

    @RequestMapping(value = "abnormal/getAbnormalRecord", method = RequestMethod.POST)
    Result<Page<AbnormalDTO>> getAbnormalRecord(@ModelAttribute AbnormalParam param);
}

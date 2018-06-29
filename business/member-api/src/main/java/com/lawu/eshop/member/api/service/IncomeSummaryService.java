package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.BroadcastDTO;
import com.lawu.eshop.property.dto.BroadcastListDTO;
import com.lawu.eshop.property.param.BroadcastListParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "property-srv")
public interface IncomeSummaryService {

    @RequestMapping(method = RequestMethod.GET, value = "incomeSummary/getIncomeSummary/{userNum}")
    Result<BroadcastDTO> getIncomeSummary(@PathVariable("userNum") String userNum);

    @RequestMapping(method = RequestMethod.POST, value = "incomeSummary/getBroadcastList/{userNum}")
    Result<Page<BroadcastListDTO>> getBroadcastList(@PathVariable("userNum") String userNum, @RequestBody BroadcastListParam broadcastListParam);
}

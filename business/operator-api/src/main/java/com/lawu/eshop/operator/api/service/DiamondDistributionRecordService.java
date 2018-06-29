package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.activity.dto.DiamondDistributionRecordDTO;
import com.lawu.eshop.activity.query.DiamondDistributionRecordQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 钻石分配记录服务接口
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
@FeignClient(value = "activity-srv", path = "diamondDistributionRecord/")
public interface DiamondDistributionRecordService {
    

    /**
     * 分页查询钻石分配记录
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月8日
     * @updateDate 2018年5月8日
     */
    @RequestMapping(value = "page",method = RequestMethod.PUT)
    Result<Page<DiamondDistributionRecordDTO>> page(@RequestBody DiamondDistributionRecordQueryParam param);
}

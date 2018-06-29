package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.activity.dto.RichDiamondRecordDTO;
import com.lawu.eshop.activity.query.DiamondRecordQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 钻石记录服务接口
 * @author jiangxinjun
 * @createDate 2018年6月7日
 * @updateDate 2018年6月7日
 */
@FeignClient(value = "activity-srv", path = "richDiamondRecord/")
public interface RichDiamondRecordService {
    
    /**
     * 分页查询钻石分配记录
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年6月7日
     * @updateDate 2018年6月7日
     */
    @RequestMapping(value = "page",method = RequestMethod.PUT)
    Result<Page<RichDiamondRecordDTO>> page(@RequestBody @Validated DiamondRecordQueryParam param);
}

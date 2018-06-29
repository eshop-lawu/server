package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.activity.dto.PointLotteryActivityRecordPageDTO;
import com.lawu.eshop.activity.query.PointLotteryActivityQueryParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 积分夺宝活动参与记录服务接口
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
@FeignClient(value = "activity-srv", path = "pointLotteryActivityRecord/")
public interface PointLotteryActivityRecordService {

    /**
     * 分页查询当前活动的参与记录
     * @param param 查询参数
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    @RequestMapping(value = "page/{pointLotteryActivityId}", method = RequestMethod.PUT)
    Result<Page<PointLotteryActivityRecordPageDTO>> page(@PathVariable("pointLotteryActivityId") Long pointLotteryActivityId, @RequestBody PointLotteryActivityQueryParam param);

}

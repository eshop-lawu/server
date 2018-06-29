package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.AttendCountDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryActivityAttendRecordDTO;
import com.lawu.eshop.activity.dto.PointLotteryRollDTO;
import com.lawu.eshop.activity.param.PointLotteryActivityRecordParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
@FeignClient(value = "activity-srv", path = "pointLotteryActivityRecord/")
public interface PointLotteryActivityRecordService {

    /**
     * 最新20条中奖信息
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listLatestLotteryInfo", method = RequestMethod.GET)
    Result<List<PointLotteryRollDTO>> listLatestLotteryInfo();
    
    /**
	 * 积分夺宝 --我的抽奖列表
	 * 
	 * @param param
	 * @return
	 * @author zhangrc
	 */
	@RequestMapping(value = "attendPrizePage", method = RequestMethod.POST)
    Result<Page<PointLotteryActivityAttendRecordDTO>> attendPrizePage(@RequestBody PointLotteryActivityRecordParam param);
	
	
	/**
	 * 我的抽奖详情
	 * 
	 * @param userNum
	 * @param id
	 * @return
	 * @author zhangrc
	 */
	@RequestMapping(value = "getPointLotteryActivityAttendDetail", method = RequestMethod.GET)
    Result<PointLotteryActivityAttendDetailDTO> getPointLotteryActivityAttendDetail(@RequestParam("userNum") String userNum, @RequestParam("id") Long id);
	
	
	/**
	 * 统计总参与次数
	 * @param userNum
	 * @return
	 */
	@RequestMapping(value = "getAttendCount", method = RequestMethod.GET)
    Result<AttendCountDTO> getAttendCount(@RequestParam("userNum") String userNum);

}

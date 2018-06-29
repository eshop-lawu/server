package com.lawu.eshop.jobs.service;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "user-srv")
public interface CommissionCommonService {

	/**
	 * 根据被邀请人查询出该人所有level邀请人编号
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "user/common/selectHigherLevelInviters/{invitedUserNum}")
	List<CommissionInvitersUserDTO> selectHigherLevelInviters(@PathVariable("invitedUserNum") String invitedUserNum,
										    @RequestParam("level") int level, @RequestBody boolean isLevel);

}

package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.dto.GamePointReportDTO;
import com.lawu.framework.web.Result;

/**
 * 游戏积分统计
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
@FeignClient(value = "game-srv", path = "gamePointReport/")
public interface GamePointReportService {
	
	@RequestMapping(value = "getGamePointReport", method = RequestMethod.GET)
    Result<List<GamePointReportDTO>> getGamePointReport(@RequestParam("today") String today);

}

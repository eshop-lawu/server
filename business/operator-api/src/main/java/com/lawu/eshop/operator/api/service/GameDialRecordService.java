package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.game.dto.GameDialRecordDTO;
import com.lawu.eshop.game.query.GameDailRecordPageQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
@FeignClient(value = "game-srv", path = "gameDialRecord/")
public interface GameDialRecordService {
	
	/**
	 * 中奖列表
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	Result<Page<GameDialRecordDTO>> page(@RequestBody GameDailRecordPageQuery query);

	/**
	 * 发放奖品
	 * @param id
	 */
	@RequestMapping(value = "sendPrize/{id}", method = RequestMethod.PUT)
	Result sendPrize(@PathVariable("id") Long id);

}

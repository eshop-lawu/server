package com.lawu.eshop.operator.api.service;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.dto.GameDialPrizeAttendDTO;
import com.lawu.eshop.game.param.GameDailPrizeParam;
import com.lawu.eshop.game.query.GameDailPrizeQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
@FeignClient(value = "game-srv", path = "gameDialPrize/")
public interface GameDialPrizeService {
	
	/**
	 * 单个查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getGameDialPrize/{id}", method = RequestMethod.GET)
    Result<GameDialPrizeAttendDTO> getGameDialPrize(@PathVariable("id") Long id) ;
    
    /**
     * 商品列表
     * @param query
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    Result<Page<GameDialPrizeAttendDTO>> page(@RequestBody GameDailPrizeQuery query);
    
    /**
     * 保存
     * @param param
     * @return
     */
    @RequestMapping(value = "updateGameDialPrize", method = RequestMethod.POST)
    Result updateGameDialPrize(@RequestBody GameDailPrizeParam param) ;
    
    /**
     * 启用，禁用
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "setGameDialPrizeStatus/{id}", method = RequestMethod.POST)
    Result setGameDialPrizeStatus(@PathVariable("id") Long id ,@RequestParam("status") GameDialPrizeStatusEnum status);

    /**
     * 剩余奖品概率
     * 
     * @param gameDialPrizeId
     * @return
     */
    @RequestMapping(value = "getLeftPrizeRate/{id}", method = RequestMethod.GET)
	Result<BigDecimal> getLeftPrizeRate(@PathVariable("id") Long gameDialId);


}

package com.lawu.eshop.game.srv.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.dto.GameDialPrizeAttendDTO;
import com.lawu.eshop.game.dto.GameDialPrizeDetailDTO;
import com.lawu.eshop.game.dto.GameDialPrizeLotteryDTO;
import com.lawu.eshop.game.param.GameDailPrizeParam;
import com.lawu.eshop.game.query.GameDailPrizeQuery;
import com.lawu.eshop.game.srv.bo.GameDialPrizeBO;
import com.lawu.eshop.game.srv.converter.GameDialPrizeConverter;
import com.lawu.eshop.game.srv.service.GameDialPrizeService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
@RestController
@RequestMapping(value = "gameDialPrize/")
public class GameDialPrizeController extends BaseController {

    @Autowired
    private GameDialPrizeService gameDialPrizeService;

    /**
     * 查询可参与抽奖的奖品列表
     *
     * @param gameDialId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listValidGameDialPrize", method = RequestMethod.GET)
    public Result<List<GameDialPrizeLotteryDTO>> listValidGameDialPrize(@RequestParam Long gameDialId) {
        List<GameDialPrizeBO> prizeBOS = gameDialPrizeService.listGameDialPrize(gameDialId, 0);
        return successGet(GameDialPrizeConverter.converPrizeLotteryDTOS(prizeBOS));
    }

    /**
     * 查询抽中的奖品信息
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getLotteryPrizeInfo/{id}", method = RequestMethod.GET)
    public Result<GameDialPrizeDetailDTO> getLotteryPrizeInfo(@PathVariable Long id) {
        GameDialPrizeBO prizeBO = gameDialPrizeService.getGameDialPrize(id);
        return successGet(GameDialPrizeConverter.converLotteryPrizeDTO(prizeBO));
    }
    
    /**
     * 单个查询
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "getGameDialPrize/{id}", method = RequestMethod.GET)
    public Result<GameDialPrizeAttendDTO> getGameDialPrize(@PathVariable Long id) {
        GameDialPrizeBO dialBO = gameDialPrizeService.getGameDialPrize(id);
        if (dialBO == null) {
            return successGet(ResultCode.GAME_DIAL_DISABLED);
        }
        GameDialPrizeAttendDTO gameDialDTO =GameDialPrizeConverter.converDTO(dialBO);
        return successGet(gameDialDTO);
    }
    
    /**
     * 商品分页列表
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Result<Page<GameDialPrizeAttendDTO>> page(@RequestBody GameDailPrizeQuery query) {
        Page<GameDialPrizeBO> page = gameDialPrizeService.page(query);
        Page<GameDialPrizeAttendDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(query.getCurrentPage());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setRecords(GameDialPrizeConverter.convertGameDialPrizeDTOS(page.getRecords()));
		return successCreated(pageDTO);
    }
    
    /**
     * 保存，修改
     * @param param
     * @return
     */
    @RequestMapping(value = "updateGameDialPrize", method = RequestMethod.POST)
    public Result updateGameDialPrize(@RequestBody GameDailPrizeParam param) {
    	try {
    		gameDialPrizeService.updateGameDialPrize(param);
			return successCreated();
	    } catch (WrongOperationException e) {
	         return successCreated(ResultCode.GAME_DIAL_PRIZE_COUNT_OVER, e.getMessage());

	    }
    }
    
    /**
     * 商品禁用启用
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "setGameDialPrizeStatus/{id}", method = RequestMethod.POST)
    public Result setGameDialPrizeStatus(@PathVariable Long id ,@RequestParam GameDialPrizeStatusEnum status) {
        try {
        	gameDialPrizeService.setGameDialPrizeStatus(id, status);
			return successCreated();
	    } catch (WrongOperationException e) {
	         return successCreated(ResultCode.GAME_DIAL_PRIZE_COUNT_OVER, e.getMessage());

	    }
    }
    
    
    /**
     * 查询奖品剩余中奖率
     *
     * @param gameDialPrizeId
     * @return
     * @author zhangrc
     */
    @RequestMapping(value = "getLeftPrizeRate/{gameDialId}", method = RequestMethod.GET)
    public Result<BigDecimal> getLeftPrizeRate(@PathVariable Long gameDialId) {
        BigDecimal leftPrizeRate = gameDialPrizeService.getLeftPrizeRate(gameDialId);
        return successGet(leftPrizeRate);
    }

}

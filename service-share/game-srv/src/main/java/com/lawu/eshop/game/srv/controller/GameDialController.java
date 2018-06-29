package com.lawu.eshop.game.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.dto.GameDialDTO;
import com.lawu.eshop.game.dto.GameDialDetailDTO;
import com.lawu.eshop.game.param.GameDialParam;
import com.lawu.eshop.game.query.GameDialQuery;
import com.lawu.eshop.game.srv.bo.GameDialBO;
import com.lawu.eshop.game.srv.bo.GameDialPrizeBO;
import com.lawu.eshop.game.srv.converter.GameDialConverter;
import com.lawu.eshop.game.srv.converter.GameDialPrizeConverter;
import com.lawu.eshop.game.srv.service.GameDialPrizeService;
import com.lawu.eshop.game.srv.service.GameDialService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
@RestController
@RequestMapping(value = "gameDial/")
public class GameDialController extends BaseController {

    @Autowired
    private GameDialService gameDialService;

    @Autowired
    private GameDialPrizeService gameDialPrizeService;

    /**
     * 转盘游戏详情
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameDialDetail", method = RequestMethod.GET)
    public Result<GameDialDetailDTO> getGameDialDetail() {
        GameDialBO dialBO = gameDialService.getGameDial();
        if (dialBO == null) {
            return successGet(ResultCode.GAME_DIAL_DISABLED);
        }
        List<GameDialPrizeBO> prizeBOS = gameDialPrizeService.listGameDialPrize(dialBO.getId(), null);
        GameDialDetailDTO detailDTO = new GameDialDetailDTO();
        detailDTO.setId(dialBO.getId());
        detailDTO.setPoint(dialBO.getPoint());
        detailDTO.setShareAttendCount(dialBO.getShareAttendCount());
        detailDTO.setPrizeInfoDTOS(GameDialPrizeConverter.converPrizeInfoDTOS(prizeBOS));
        return successGet(detailDTO);
    }

    /**
     * 转盘游戏
     *
     * @return
     * @author zhangrc
     */
    @RequestMapping(value = "getGameDialById/{id}", method = RequestMethod.GET)
    public Result<GameDialDTO> getGameDialById(@PathVariable Long id) {
        GameDialBO dialBO = gameDialService.getGameDialById(id);
        GameDialDTO gameDialDTO =GameDialConverter.converDTO(dialBO);
        return successGet(gameDialDTO);
    }
    
    /**
     * 修改游戏设置
     * @param param
     * @return
     */
    @RequestMapping(value = "updateGameDial/{id}", method = RequestMethod.POST)
    public Result updateGameDial(@PathVariable Long id,@RequestBody GameDialParam param) {
        gameDialService.updateGameDial(id,param);
        return successCreated();
    }
    
    /**
     * 保存游戏设置
     * @param param
     * @return
     */
    @RequestMapping(value = "saveGameDial", method = RequestMethod.POST)
    public Result saveGameDial(@RequestBody GameDialParam param) {
        gameDialService.saveGameDial(param);
        return successCreated();
    }

    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Result<Page<GameDialDTO>> page(@RequestBody GameDialQuery query){
		Page<GameDialBO> page = gameDialService.page(query);
		Page<GameDialDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(query.getCurrentPage());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setRecords(GameDialConverter.converDTOS(page.getRecords()));
		return successCreated(pageDTO);
    }
    
    @RequestMapping(value = "setGameDial/{id}", method = RequestMethod.PUT)
    public Result setGameDial(@PathVariable Long id, @RequestParam GameDialStatusEnum statusEnum){
    	gameDialService.setGameDial(id, statusEnum);
    	return successCreated();
    }

}

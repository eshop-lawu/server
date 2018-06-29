package com.lawu.eshop.game.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.dto.GameDialRecordAttendDTO;
import com.lawu.eshop.game.dto.GameDialRecordDTO;
import com.lawu.eshop.game.dto.GameDialRecordInfoDTO;
import com.lawu.eshop.game.dto.TakePartLotteryInfoDTO;
import com.lawu.eshop.game.param.TakeLotteryParam;
import com.lawu.eshop.game.param.TakePartLotteryParam;
import com.lawu.eshop.game.query.GameDailRecordPageQuery;
import com.lawu.eshop.game.query.GameDialRecordUserQuery;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.bo.GameDialRecordInfoBO;
import com.lawu.eshop.game.srv.converter.GameDialRecordConverter;
import com.lawu.eshop.game.srv.service.GameDialRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
@RestController
@RequestMapping(value = "gameDialRecord/")
public class GameDialRecordController extends BaseController {

    @Autowired
    private GameDialRecordService gameDialRecordService;

    /**
     * 中奖记录
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listGameDialRecord", method = RequestMethod.POST)
    public Result<Page<GameDialRecordInfoDTO>> listGameDialRecord(@RequestBody GameDialRecordUserQuery query) {
        Page<GameDialRecordInfoBO> recordBOPage = gameDialRecordService.listGameDialRecord(query);
        Page<GameDialRecordInfoDTO> page = new Page<>();
        page.setCurrentPage(recordBOPage.getCurrentPage());
        page.setTotalCount(recordBOPage.getTotalCount());
        page.setRecords(GameDialRecordConverter.converInfoDTOS(recordBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 根据状态查询用户抽奖记录
     *
     * @param gameDialId
     * @param userNum
     * @param statusEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getTakePartLottery/{gameDialId}", method = RequestMethod.GET)
    public Result<TakePartLotteryInfoDTO> getGameDialRecord(@PathVariable Long gameDialId, @RequestParam String userNum, @RequestParam GameDialRecordStatusEnum statusEnum) {
        Long recordId = gameDialRecordService.getTakePartLottery(gameDialId, userNum, statusEnum);
        TakePartLotteryInfoDTO infoDTO = new TakePartLotteryInfoDTO();
        infoDTO.setId(recordId);
        return successGet(infoDTO);
    }

    /**
     * 保存抽奖记录
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveGameDialRecord", method = RequestMethod.POST)
    public Result<TakePartLotteryInfoDTO> saveGameDialRecord(@RequestBody TakePartLotteryParam param) {
        try {
            Long recordId = gameDialRecordService.saveGameDialRecord(param);
            TakePartLotteryInfoDTO infoDTO = new TakePartLotteryInfoDTO();
            infoDTO.setId(recordId);
            return successCreated(infoDTO);
        } catch (Exception e) {
            return successCreated(ResultCode.TAKE_PART_LOTTERY_ERROR);
        }
    }

    /**
     * 根据id查询抽奖记录参与信息
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameDialRecordAttend/{id}", method = RequestMethod.GET)
    public Result<GameDialRecordAttendDTO> getGameDialRecordAttend(@PathVariable Long id) {
        GameDialRecordBO recordBO = gameDialRecordService.getGameDialRecord(id);
        if (recordBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        GameDialRecordAttendDTO attendDTO = new GameDialRecordAttendDTO();
        attendDTO.setGameDialId(recordBO.getGameDialId());
        attendDTO.setStatusEnum(GameDialRecordStatusEnum.getEnum(recordBO.getStatus()));
        return successGet(attendDTO);
    }

    /**
     * 领奖
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "takeLottery", method = RequestMethod.PUT)
    public Result takeLottery(@RequestBody TakeLotteryParam param) {
        gameDialRecordService.takeLottery(param);
        return successCreated();
    }

    /**
     * 未中奖
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "notLottery/{id}", method = RequestMethod.PUT)
    public Result notLottery(@PathVariable Long id) {
        gameDialRecordService.updateGameDialRecordStatus(id, GameDialRecordStatusEnum.NOT_LOTTERY);
        return successCreated();
    }

    /**
     * 中奖
     *
     * @param id
     * @param gameDialId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "winLottery/{id}", method = RequestMethod.PUT)
    public Result winLottery(@PathVariable Long id, @RequestParam Long gameDialId) {
        int retCode = ResultCode.FAIL;
        try {
            retCode = gameDialRecordService.winLottery(id, gameDialId);
        } catch (Exception e) {
            return successCreated(ResultCode.LOTTERY_PRIZE_INVENTORY_SHORTAGE);
        }
        return successCreated(retCode);
    }
    
    
     
    /**
     * 运营平台中奖列表
     * 
     * @param query
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
	public Result<Page<GameDialRecordDTO>> page(@RequestBody GameDailRecordPageQuery query){
    	 Page<GameDialRecordBO> recordBOPage = gameDialRecordService.page(query);
         Page<GameDialRecordDTO> page = new Page<>();
         page.setCurrentPage(recordBOPage.getCurrentPage());
         page.setTotalCount(recordBOPage.getTotalCount());
         page.setRecords(GameDialRecordConverter.converDTOS(recordBOPage.getRecords()));
         return successCreated(page);
    }
    
    
    /**
     * 发放奖品
     *
     * @param id
     * @author zhangrc
     */
    @RequestMapping(value = "sendPrize/{id}", method = RequestMethod.PUT)
    public Result sendPrize(@PathVariable Long id) {
    	gameDialRecordService.updateGameDialRecordStatus(id, GameDialRecordStatusEnum.SEND_LOTTERY);
        return successCreated();
    }

}

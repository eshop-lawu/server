package com.lawu.eshop.game.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.game.dto.GameRoomCreateDTO;
import com.lawu.eshop.game.dto.GameRoomDTO;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.GameRoomParam;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.UpdateGameRoomPlayerReadyStatusParam;
import com.lawu.eshop.game.query.GameRoomQuery;
import com.lawu.eshop.game.srv.bo.GameRoomBO;
import com.lawu.eshop.game.srv.bo.GameRoomCreateBO;
import com.lawu.eshop.game.srv.converter.GameRoomConverter;
import com.lawu.eshop.game.srv.service.GameRoomService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@RestController
@RequestMapping(value = "gameRoom/")
public class GameRoomController extends BaseController {

    @Autowired
    private GameRoomService gameRoomService;

    /**
     * 创建房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveGameRoom", method = RequestMethod.POST)
    public Result<GameRoomCreateDTO> saveGameRoom(@RequestBody GameRoomParam param) {
        GameRoomCreateBO bo = gameRoomService.saveGameRoom(param);
        GameRoomCreateDTO dto = new GameRoomCreateDTO();
        dto.setRoomId(bo.getRoomId());
        dto.setRoomNum(bo.getRoomNum());
        return successCreated(dto);
    }

    /**
     * 房间列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listGameRoom", method = RequestMethod.POST)
    public Result<Page<GameRoomDTO>> listGameRoom(@RequestBody GameRoomQuery query) {
        Page<GameRoomBO> roomBOPage = gameRoomService.listGameRoom(query);
        Page<GameRoomDTO> page = new Page<>();
        page.setCurrentPage(roomBOPage.getCurrentPage());
        page.setTotalCount(roomBOPage.getTotalCount());
        page.setRecords(GameRoomConverter.converDTOS(roomBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 根据id查询房间
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoom/{id}", method = RequestMethod.GET)
    public Result<GameRoomDTO> getGameRoom(@PathVariable Long id) {
        GameRoomBO roomBO = gameRoomService.getGameRoom(id);
        GameRoomDTO roomDTO = GameRoomConverter.converDTO(roomBO);
        return successGet(roomDTO);
    }

    /**
     * 密码校验
     *
     * @param id
     * @param pwd
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "verifyGameRoomPwd/{id}", method = RequestMethod.GET)
    public Result<Boolean> verifyGameRoomPwd(@PathVariable Long id, @RequestParam String pwd) {
        Boolean result = gameRoomService.verifyGameRoomPwd(id, pwd);
        return successGet(result);
    }

    /**
     * 加入房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "joinGameRoom", method = RequestMethod.PUT)
    public Result<GameRoomDetailsDTO> joinGameRoom(@RequestBody JoinGameRoomParam param) {
        try {
            GameRoomDetailsDTO model = gameRoomService.joinGameRoom(param);
            return successCreated(model);
        } catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }

    /**
     * 退出房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "exitGameRoom", method = RequestMethod.PUT)
    public Result<GameRoomDetailsDTO> exitGameRoom(@RequestBody ExitGameRoomParam param) {
        try {
            GameRoomDetailsDTO model = gameRoomService.exitGameRoom(param);
            return successCreated(model);
        } catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }

    /**
     * 更新房间用户准备状态
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "updateGameRoomPlayerReadyStatus", method = RequestMethod.PUT)
    public Result<GameRoomDetailsDTO> updateGameRoomPlayerReadyStatus(@RequestBody UpdateGameRoomPlayerReadyStatusParam param) {
        try {
            GameRoomDetailsDTO model = gameRoomService.updateGameRoomPlayerReadyStatus(param);
            return successCreated(model);
        } catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }

    /**
     * 根据房间号和房间类型查询房间
     *
     * @param roomNum
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoomByRoomNum", method = RequestMethod.GET)
    public Result<GameRoomDTO> getGameRoomByRoomNum(@RequestParam String roomNum, @RequestParam GameTypeEnum typeEnum) {
        GameRoomBO roomBO = gameRoomService.getGameRoomByRoomNum(roomNum, typeEnum);
        GameRoomDTO roomDTO = GameRoomConverter.converDTO(roomBO);
        return successGet(roomDTO);
    }

    /**
     * 根据用户编号和房间类型查询房间
     *
     * @param userNum
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGameRoomByUserNum", method = RequestMethod.GET)
    public Result<GameRoomDTO> getGameRoomByUserNum(@RequestParam String userNum, @RequestParam GameTypeEnum typeEnum) {
        GameRoomBO roomBO = gameRoomService.getGameRoomByUserNum(userNum, typeEnum);
        GameRoomDTO roomDTO = GameRoomConverter.converDTO(roomBO);
        return successGet(roomDTO);
    }

}

package com.lawu.eshop.game.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzleUserPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleUserPicOperatorDTO;
import com.lawu.eshop.game.dto.GamePuzzleUserPicUploadNumberDTO;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.GamePuzzleUserPicRealQuery;
import com.lawu.eshop.game.query.OperatorGamePuzzleUserPicQuery;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicUploadNumberBO;
import com.lawu.eshop.game.srv.converter.GamePuzzleUserPicConverter;
import com.lawu.eshop.game.srv.service.GamePuzzleUserPicService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
@RestController
@RequestMapping(value = "gamePuzzleUserPic/")
public class GamePuzzleUserPicController extends BaseController {

    @Autowired
    private GamePuzzleUserPicService gamePuzzleUserPicService;

    /**
     * 保存用户提交的拼图
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveGamePuzzleUserPic", method = RequestMethod.POST)
    public Result saveGamePuzzleUserPic(@RequestBody GamePuzzleParam param) {
        gamePuzzleUserPicService.saveGamePuzzleUserPic(param);
        return successCreated();
    }

    /**
     * 查询用户提交的拼图数量
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGamePuzzleUserPicUploadNumber", method = RequestMethod.GET)
    public Result<GamePuzzleUserPicUploadNumberDTO> getGamePuzzleUserPicUploadNumber(@RequestParam String userNum) {
        GamePuzzleUserPicUploadNumberBO numberBO = gamePuzzleUserPicService.getGamePuzzleUserPicUploadNumber(userNum);
        GamePuzzleUserPicUploadNumberDTO numberDTO = new GamePuzzleUserPicUploadNumberDTO();
        numberDTO.setUploadNumber(numberBO.getUploadNumber());
        numberDTO.setUsedNumber(numberBO.getUsedNumber());
        return successGet(numberDTO);
    }

    /**
     * 查询用户提交的拼图记录
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listGamePuzzleUserPic", method = RequestMethod.POST)
    public Result<Page<GamePuzzleUserPicDTO>> listGamePuzzleUserPic(@RequestBody GamePuzzleUserPicRealQuery query) {
        Page<GamePuzzleUserPicBO> picBOPage = gamePuzzleUserPicService.listGamePuzzleUserPic(query);
        Page<GamePuzzleUserPicDTO> page = new Page<>();
        page.setCurrentPage(picBOPage.getCurrentPage());
        page.setTotalCount(picBOPage.getTotalCount());
        page.setRecords(GamePuzzleUserPicConverter.converDTOS(picBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 运营平台审核用户供图列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorGamePuzzleUserPic", method = RequestMethod.POST)
    public Result<Page<GamePuzzleUserPicOperatorDTO>> listOperatorGamePuzzleUserPic(@RequestBody OperatorGamePuzzleUserPicQuery query) {
        Page<GamePuzzleUserPicBO> picBOPage = gamePuzzleUserPicService.listOperatorGamePuzzleUserPic(query);
        Page<GamePuzzleUserPicOperatorDTO> page = new Page<>();
        page.setCurrentPage(picBOPage.getCurrentPage());
        page.setTotalCount(picBOPage.getTotalCount());
        page.setRecords(GamePuzzleUserPicConverter.converOperatorDTOS(picBOPage.getRecords()));
        return successCreated(page);
    }

    /**
     * 更新用户供图记录状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateGamePuzzleUserPicStatus/{id}", method = RequestMethod.PUT)
    public Result updateGamePuzzleUserPicStatus(@PathVariable Long id, @RequestParam GamePuzzleUserPicStatusEnum statusEnum) {
        gamePuzzleUserPicService.updateGamePuzzleUserPicStatus(id, statusEnum);
        return successCreated();
    }

}

package com.lawu.eshop.game.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzlePicOperatorDTO;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.OperatorGamePuzzlePicQuery;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.eshop.game.srv.converter.GamePuzzlePicConverter;
import com.lawu.eshop.game.srv.service.GamePuzzlePicService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
@RestController
@RequestMapping(value = "gamePuzzlePic/")
public class GamePuzzlePicController extends BaseController {

    @Autowired
    private GamePuzzlePicService gamePuzzlePicService;

    /**
     * 保存游戏拼图
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveGamePuzzlePic", method = RequestMethod.POST)
    public Result saveGamePuzzlePic(@RequestBody GamePuzzleParam param) {
        gamePuzzlePicService.saveGamePuzzlePic(param);
        return successCreated();
    }

    /**
     * 根据id查询游戏拼图
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGamePuzzlePic/{id}", method = RequestMethod.GET)
    public Result<GamePuzzlePicOperatorDTO> getGamePuzzlePic(@PathVariable Long id) {
        GamePuzzlePicBO picBO = gamePuzzlePicService.getGamePuzzlePic(id);
        GamePuzzlePicOperatorDTO operatorDTO = GamePuzzlePicConverter.converOperatorDTO(picBO);
        return successGet(operatorDTO);
    }

    /**
     * 更新游戏拼图状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateGamePuzzlePicStatus/{id}", method = RequestMethod.PUT)
    public Result updateGamePuzzlePicStatus(@PathVariable Long id, @RequestParam GamePuzzlePicStatusEnum statusEnum) {
        gamePuzzlePicService.updateGamePuzzlePicStatus(id, statusEnum);
        return successCreated();
    }

    /**
     * 游戏拼图运营平台列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorGamePuzzlePic", method = RequestMethod.POST)
    public Result<Page<GamePuzzlePicOperatorDTO>> listOperatorGamePuzzlePic(@RequestBody OperatorGamePuzzlePicQuery query) {
        Page<GamePuzzlePicBO> picBOPage = gamePuzzlePicService.listOperatorGamePuzzlePic(query);
        Page<GamePuzzlePicOperatorDTO> page = new Page<>();
        page.setCurrentPage(picBOPage.getCurrentPage());
        page.setTotalCount(picBOPage.getTotalCount());
        page.setRecords(GamePuzzlePicConverter.converOperatorDTOS(picBOPage.getRecords()));
        return successCreated(page);
    }

}

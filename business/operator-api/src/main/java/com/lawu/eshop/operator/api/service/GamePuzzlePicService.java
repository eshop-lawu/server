package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzlePicOperatorDTO;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.OperatorGamePuzzlePicQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
@FeignClient(value = "game-srv", path = "gamePuzzlePic/")
public interface GamePuzzlePicService {

    /**
     * 保存游戏拼图
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveGamePuzzlePic", method = RequestMethod.POST)
    Result saveGamePuzzlePic(@RequestBody GamePuzzleParam param);

    /**
     * 根据id查询游戏拼图
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGamePuzzlePic/{id}", method = RequestMethod.GET)
    Result<GamePuzzlePicOperatorDTO> getGamePuzzlePic(@PathVariable("id") Long id);

    /**
     * 更新游戏拼图状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateGamePuzzlePicStatus/{id}", method = RequestMethod.PUT)
    Result updateGamePuzzlePicStatus(@PathVariable("id") Long id, @RequestParam("statusEnum") GamePuzzlePicStatusEnum statusEnum);

    /**
     * 游戏拼图运营平台列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorGamePuzzlePic", method = RequestMethod.POST)
    Result<Page<GamePuzzlePicOperatorDTO>> listOperatorGamePuzzlePic(@RequestBody OperatorGamePuzzlePicQuery query);

}

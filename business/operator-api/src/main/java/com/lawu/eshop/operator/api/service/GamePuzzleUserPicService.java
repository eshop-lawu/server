package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzleUserPicOperatorDTO;
import com.lawu.eshop.game.query.OperatorGamePuzzleUserPicQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
@FeignClient(value = "game-srv", path = "gamePuzzleUserPic/")
public interface GamePuzzleUserPicService {

    /**
     * 运营平台审核用户供图列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listOperatorGamePuzzleUserPic", method = RequestMethod.POST)
    Result<Page<GamePuzzleUserPicOperatorDTO>> listOperatorGamePuzzleUserPic(@RequestBody OperatorGamePuzzleUserPicQuery query);

    /**
     * 更新用户供图记录状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    @RequestMapping(value = "updateGamePuzzleUserPicStatus/{id}", method = RequestMethod.PUT)
    Result updateGamePuzzleUserPicStatus(@PathVariable("id") Long id, @RequestParam("statusEnum") GamePuzzleUserPicStatusEnum statusEnum);

}

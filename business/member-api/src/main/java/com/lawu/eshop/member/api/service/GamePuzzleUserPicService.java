package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.dto.GamePuzzleUserPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleUserPicUploadNumberDTO;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.GamePuzzleUserPicRealQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
@FeignClient(value = "game-srv", path = "gamePuzzleUserPic/")
public interface GamePuzzleUserPicService {

    /**
     * 保存用户提交的拼图
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveGamePuzzleUserPic", method = RequestMethod.POST)
    Result saveGamePuzzleUserPic(@RequestBody GamePuzzleParam param);

    /**
     * 查询用户提交的拼图数量
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getGamePuzzleUserPicUploadNumber", method = RequestMethod.GET)
    Result<GamePuzzleUserPicUploadNumberDTO> getGamePuzzleUserPicUploadNumber(@RequestParam("userNum") String userNum);

    /**
     * 查询用户提交的拼图记录
     *
     * @param query
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listGamePuzzleUserPic", method = RequestMethod.POST)
    Result<Page<GamePuzzleUserPicDTO>> listGamePuzzleUserPic(@RequestBody GamePuzzleUserPicRealQuery query);

}

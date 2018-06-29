package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.dto.GamePuzzleDifficultyDTO;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@FeignClient(value = "game-srv", path = "gamePuzzleDifficulty/")
public interface GamePuzzleDifficultyService {

	@RequestMapping(value = "getPuzzleDifficulty", method = RequestMethod.GET)
    Result<List<GamePuzzleDifficultyDTO>> getPuzzleDifficulty();

    @RequestMapping(value = "getPuzzleDifficultyById", method = RequestMethod.GET)
    Result<GamePuzzleDifficultyDTO> getPuzzleDifficultyById(@RequestParam(name = "id") Long id);

}

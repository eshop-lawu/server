package com.lawu.eshop.game.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.game.dto.GamePuzzleDifficultyDTO;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.converter.GamePuzzleDiffcultyConverter;
import com.lawu.eshop.game.srv.service.GamePuzzleDifficultyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 困难级别
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@RestController
@RequestMapping(value = "gamePuzzleDifficulty/")
public class GamePuzzleDifficultyController extends BaseController{
	
	@Autowired
	private GamePuzzleDifficultyService gamePuzzleDifficultyService;
	
	@RequestMapping(value = "getPuzzleDifficulty", method = RequestMethod.GET)
    public Result<List<GamePuzzleDifficultyDTO>> getPuzzleDifficulty() {
        List<GamePuzzleDifficultyBO> list = gamePuzzleDifficultyService.getPuzzleDifficulty();
        return successGet( GamePuzzleDiffcultyConverter.convertDTOS(list));
    }

    @RequestMapping(value = "getPuzzleDifficultyById", method = RequestMethod.GET)
    public Result<GamePuzzleDifficultyDTO> getPuzzleDifficultyById(@RequestParam(name = "id") Long id){
        GamePuzzleDifficultyBO bo = gamePuzzleDifficultyService.getPuzzleDifficultyById(id);
        GamePuzzleDifficultyDTO dto = GamePuzzleDiffcultyConverter.convertDTO(bo);
        return successGet(dto);
    }

}

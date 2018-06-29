package com.lawu.eshop.game.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.GameMindQuestionCategoryDTO;
import com.lawu.eshop.game.dto.GameMindQuestionDTO;
import com.lawu.eshop.game.dto.GameMindQuestionDetailDTO;
import com.lawu.eshop.game.dto.GameMindQuestionResultDTO;
import com.lawu.eshop.game.param.GameMindQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.game.query.GameMindQuestionQuery;
import com.lawu.eshop.game.srv.bo.GameMindQuestionBO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionCategoryBO;
import com.lawu.eshop.game.srv.bo.GameQuestionResultBO;
import com.lawu.eshop.game.srv.converter.GameMindQuestionConverter;
import com.lawu.eshop.game.srv.service.GameMindQuestionService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
@RestController
@RequestMapping(value = "gameMindQuestion/")
public class GameMindQuestionController extends BaseController{
	
	@Autowired
	private GameMindQuestionService gameMindQuestionService;
	
	/**
	 * 添加题目
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveGameMindQuestion", method = RequestMethod.POST)
	public Result saveGameMindQuestion(@RequestBody GameMindQuestionParam param){
		gameMindQuestionService.saveGameMindQuestion(param);
		return successCreated();
	}
	
	/**
	 * 题目禁用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "saveGameMindQuestion/{id}", method = RequestMethod.PUT)
	public Result<Page<GameMindQuestionDTO>> setGameMindQuestion(@PathVariable Long id){
		gameMindQuestionService.setGameMindQuestion(id);
		return successCreated();
	}
	
	/**
	 * 题库列表查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "page", method = RequestMethod.POST)
	public Result<Page<GameMindQuestionDTO>> page(@RequestBody GameMindQuestionQuery query){
		Page<GameMindQuestionBO> page = gameMindQuestionService.page(query);
		Page<GameMindQuestionDTO> pageDTO = new Page<>();
		pageDTO.setCurrentPage(query.getCurrentPage());
		pageDTO.setTotalCount(page.getTotalCount());
		pageDTO.setRecords(GameMindQuestionConverter.convertGameMindQuestionDTOS(page.getRecords()));
		return successCreated(pageDTO);
	}
	
	 /**
     * 获取随机题目，并初始化游戏参与记录
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "getMindQuestionList", method = RequestMethod.POST)
    public Result<GameMindQuestionResultDTO> getMindQuestionList(@RequestBody MindAttendParam param) {
        GameQuestionResultBO resultBO = gameMindQuestionService.getMindQuestionList(param);
        if (resultBO == null) {
            return successGet(ResultCode.GAME_MIND_QUESTION_ERROR);
        }
        GameMindQuestionResultDTO resultDTO = new GameMindQuestionResultDTO();
        resultDTO.setAttendIds(resultBO.getAttendIds());
        resultDTO.setQuestionDTOS(GameMindQuestionConverter.converterDTOS(resultBO.getQuestionBOS()));
		resultDTO.setCountDown(resultBO.getCountDown());
		resultDTO.setScore(resultBO.getScore());
		resultDTO.setSuccessScore(resultBO.getSuccessScore());
        return successGet(resultDTO);
    }
	
    
    /**
     * 查询题目类型
     * 
     * @return
     */
    @RequestMapping(value = "getGameMindQuestionCategory", method = RequestMethod.GET)
	public Result<List<GameMindQuestionCategoryDTO>> getGameMindQuestionCategory(){
    	List<GameMindQuestionCategoryBO> list = gameMindQuestionService.getGameMindQuestionCategory();
    	List<GameMindQuestionCategoryDTO> dtos = new ArrayList<>();
    	for (GameMindQuestionCategoryBO gameMindQuestionCategoryBO : list) {
    		GameMindQuestionCategoryDTO dto = new GameMindQuestionCategoryDTO();
    		dto.setId(gameMindQuestionCategoryBO.getId());
    		dto.setName(gameMindQuestionCategoryBO.getName());
    		dtos.add(dto);
		}
    	 return successGet(dtos);
    }
    
    
    /**
     * 更新缓存题库
     * @return
     */
    @RequestMapping(value = "rebuildQuestion", method = RequestMethod.POST)
	public Result rebuildQuestion(){
		gameMindQuestionService.rebuildQuestion();
		return successCreated();
	}
    
    
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<GameMindQuestionDetailDTO> get(@PathVariable Long id){
    	GameMindQuestionBO bo = gameMindQuestionService.get(id);
    	GameMindQuestionDetailDTO dto = GameMindQuestionConverter.convertGameMindQuestionDTO(bo);
		return successGet(dto);
	}

}

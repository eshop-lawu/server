package com.lawu.eshop.operator.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.game.dto.GameMindQuestionCategoryDTO;
import com.lawu.eshop.game.dto.GameMindQuestionDTO;
import com.lawu.eshop.game.dto.GameMindQuestionDetailDTO;
import com.lawu.eshop.game.param.GameMindQuestionParam;
import com.lawu.eshop.game.query.GameMindQuestionQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 题库管理
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
@FeignClient(value = "game-srv", path = "gameMindQuestion/")
public interface GameMindQuestionService {
	
	
	@RequestMapping(value = "saveGameMindQuestion", method = RequestMethod.POST)
	Result saveGameMindQuestion(@RequestBody GameMindQuestionParam param);
	
	
	@RequestMapping(value = "saveGameMindQuestion/{id}", method = RequestMethod.PUT)
	Result<Page<GameMindQuestionDTO>> setGameMindQuestion(@PathVariable("id") Long id);
	
	
	@RequestMapping(value = "page", method = RequestMethod.POST)
	Result<Page<GameMindQuestionDTO>> page(@RequestBody GameMindQuestionQuery query);
	
	
	@RequestMapping(value = "getGameMindQuestionCategory", method = RequestMethod.GET)
	Result<List<GameMindQuestionCategoryDTO>> getGameMindQuestionCategory();

	@RequestMapping(value = "rebuildQuestion", method = RequestMethod.POST)
	Result rebuildQuestion();
	
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	Result<GameMindQuestionDetailDTO> get(@PathVariable("id") Long id);
}

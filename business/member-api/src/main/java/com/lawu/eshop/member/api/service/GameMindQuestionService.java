package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.game.dto.GameMindQuestionResultDTO;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
@FeignClient(value = "game-srv")
public interface GameMindQuestionService {

    @RequestMapping(value = "gameMindQuestion/getMindQuestionList", method = RequestMethod.POST)
    Result<GameMindQuestionResultDTO> getMindQuestionList(@ModelAttribute MindAttendParam param);
}

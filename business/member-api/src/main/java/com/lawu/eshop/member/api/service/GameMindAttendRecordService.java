package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.game.dto.GameAttendStatusDTO;
import com.lawu.eshop.game.dto.GameMindParticipateGameDTO;
import com.lawu.eshop.game.dto.GameMindResultDTO;
import com.lawu.eshop.game.param.GameMindAnswerQuestionParam;
import com.lawu.eshop.game.param.ParticipateGameMindParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
@FeignClient(value = "game-srv", path = "gameMindAttendRecord/")
public interface GameMindAttendRecordService {

    @RequestMapping(value = "getGameAttendStatus/{attendId}", method = RequestMethod.GET)
    Result<GameAttendStatusDTO> getGameAttendStatus(@PathVariable("attendId") Long attendId
            , @RequestParam("userNum") String userNum);

    @RequestMapping(value = "answerQuestion", method = RequestMethod.POST)
    Result<GameMindResultDTO> answerQuestion(@ModelAttribute GameMindAnswerQuestionParam param);

    /**
     * 检查匹配状态
     *
     * @param param
     * @author jiangxinjun
     * @createDate 2018年5月12日
     * @updateDate 2018年5月12日
     */
    @RequestMapping(value = "checkMatch", method = RequestMethod.PUT)
    Result<GameMatchResultDTO> checkMatch(@RequestBody CheckCacheMatchParam param);

    @RequestMapping(value = "participateGame", method = RequestMethod.POST)
    Result<GameMindParticipateGameDTO> participateGame(@RequestBody ParticipateGameMindParam participateGameMindParam);

    @RequestMapping(value = "initRobotAttendRecordResult", method = RequestMethod.GET)
    Result<String> initRobotAttendRecordResult(@RequestParam("groupNum") String groupNum);

}


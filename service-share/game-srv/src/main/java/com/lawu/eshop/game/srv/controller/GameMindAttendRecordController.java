package com.lawu.eshop.game.srv.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.GameAttendStatusDTO;
import com.lawu.eshop.game.dto.GameMindParticipateGameDTO;
import com.lawu.eshop.game.dto.GameMindParticipateGameQuestionDTO;
import com.lawu.eshop.game.dto.GameMindResultDTO;
import com.lawu.eshop.game.param.GameMindAnswerQuestionParam;
import com.lawu.eshop.game.param.ParticipateGameMindParam;
import com.lawu.eshop.game.srv.bo.GameMindAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GameMindParticipateGameBO;
import com.lawu.eshop.game.srv.bo.GameMindParticipateGameQuestionBO;
import com.lawu.eshop.game.srv.bo.GameMindResultBO;
import com.lawu.eshop.game.srv.converter.GameMindAttendRecordConverter;
import com.lawu.eshop.game.srv.service.GameMindAttendRecordService;
import com.lawu.eshop.game.srv.service.GameMindCacheService;
import com.lawu.eshop.game.srv.service.RandomMatchService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 头脑PK参与记录控制器
 *
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
@RestController
@RequestMapping(value = "gameMindAttendRecord/")
public class GameMindAttendRecordController extends BaseController {

    @Autowired
    private GameMindAttendRecordService gameMindAttendRecordService;

    @Autowired
    private GameMindCacheService gameMindCacheService;

    @Autowired
    private RandomMatchService randomMatchService;

    /**
     * 根据参与ID，用户编号查询参与状态
     * @param attendId
     * @param userNum
     * @return
     */
    @RequestMapping(value = "getGameAttendStatus/{attendId}", method = RequestMethod.GET)
    public Result<GameAttendStatusDTO> getGameAttendStatus(@PathVariable("attendId") Long attendId
            , @RequestParam("userNum") String userNum) {
        GameAttendRecordStatusEnum status = gameMindAttendRecordService.getGameAttendStatus(attendId, userNum);
        if (status == null) {
            return successGet(ResultCode.GAME_MIND_ATTEND_FAIL);
        }
        GameAttendStatusDTO attendStatusDTO = new GameAttendStatusDTO();
        attendStatusDTO.setAttendStatus(status);
        return successGet(attendStatusDTO);
    }


    @RequestMapping(value = "answerQuestion", method = RequestMethod.POST)
    public Result<GameMindResultDTO> answerQuestion(@RequestBody GameMindAnswerQuestionParam param) {
        //获取缓存的题目
        String[] idString = param.getQuestionIds().split(",");
        List<String> idList = Arrays.asList(idString);
        if (!idList.contains(param.getQuestionId().toString())) {
            return successGet(ResultCode.GAME_MIND_ANSWER_QUESTION_ERROR);
        }
        GameMindAttendRecordBO recordBO = gameMindAttendRecordService.queryAttendRecordByIdAndNum(param.getAttendId(),param.getUserNum());
        if(recordBO == null){
            return successGet(ResultCode.GAME_MIND_ANSWER_QUESTION_ERROR);
        }

        Result<List<GameMindAttendRecordResultParam>> record = gameMindCacheService.getAttendRecordResult(param.getAttendId());
        if (record.getModel().isEmpty() && !idList.get(0).equals(param.getQuestionId().toString())) {
            return successGet(ResultCode.GAME_MIND_ANSWER_QUESTION_ERROR);
        }

        GameMindResultBO resultBO = gameMindAttendRecordService.answerQuestion(param, idList, record.getModel());
        if (resultBO == null) {
            return successGet(ResultCode.GAME_MIND_ANSWER_QUESTION_ERROR);
        }
        return successGet(GameMindAttendRecordConverter.converterDTO(resultBO));
    }

    /**
     * 两人或多人参与游戏生成参与记录,并且扣除积分
     *
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @RequestMapping(value = "participateGame", method = RequestMethod.POST)
    public Result<GameMindParticipateGameDTO> participateGame(@RequestBody ParticipateGameMindParam param) {
        try {
            GameMindParticipateGameBO gameMindParticipateGameBO = gameMindAttendRecordService.participateGame(param);
            GameMindParticipateGameDTO model = new GameMindParticipateGameDTO();
            model.setUserNums(gameMindParticipateGameBO.getUserNums());
            model.setMaximumScore(gameMindParticipateGameBO.getMaximumScore());
            model.setQuestions(new ArrayList<>());
            for (GameMindParticipateGameQuestionBO item : gameMindParticipateGameBO.getQuestions()) {
                GameMindParticipateGameQuestionDTO gameMindParticipateGameQuestionDTO = new GameMindParticipateGameQuestionDTO();
                gameMindParticipateGameQuestionDTO.setAnswers(item.getAnswers());
                gameMindParticipateGameQuestionDTO.setCategoryName(item.getCategoryName());
                gameMindParticipateGameQuestionDTO.setId(item.getId());
                gameMindParticipateGameQuestionDTO.setTitle(item.getTitle());
                model.getQuestions().add(gameMindParticipateGameQuestionDTO);
            }
            return successCreated(model);
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }  catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }
    
    /**
     * 同步缓存中的数据到数据库
     *
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月24日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "synchronizeDataFormCache", method = RequestMethod.POST)
    public Result synchronizeDataFormCache(@RequestParam("attendNum") String attendNum) {
        try {
            gameMindAttendRecordService.synchronizeDataFormCache(attendNum);
            return successCreated();
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }
    
    /**
     * 用户退出, 清除数据库中和缓存中的数据<p>
     * 1.用户主动退出<p>
     * 2.用户杀掉进程<p>
     * 
     * @param userNum
     * @param groupNum
     * @author jiangxinjun
     * @createDate 2018年3月20日
     * @updateDate 2018年3月20日
     */
    @RequestMapping(value = "quit", method = RequestMethod.PUT)
    public Result<GameRoomDetailsDTO> quit(@RequestParam("userNum") String userNum, @RequestParam("groupNum") String groupNum) {
        try {
            return successCreated(gameMindAttendRecordService.quit(userNum, groupNum));
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        } catch (DataNotExistException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }
    
    /**
     * 检查匹配状态
     * 
     * @param param
     * @author jiangxinjun
     * @createDate 2018年5月12日
     * @updateDate 2018年5月12日
     */
    @RequestMapping(value = "checkMatch", method = RequestMethod.PUT)
    public Result<GameMatchResultDTO> checkMatch(@RequestBody CheckCacheMatchParam param) {
        try {
            return successCreated(randomMatchService.checkMatch(param));
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        }
    }

    /**
     * 获取机器人答题结果
     * @return
     */
    @RequestMapping(value = "initRobotAttendRecordResult", method = RequestMethod.GET)
    public Result<String> initRobotAttendRecordResult(@RequestParam("groupNum") String groupNum) {
        String result = gameMindAttendRecordService.initRobotAttendRecordResult(groupNum);
        return successGet(result);
    }

    /**
     * 根据分组编号查询参与游戏的用户编号(完成动力任务)
     *
     * @param groupNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getAttendRecordUserNums", method = RequestMethod.GET)
    public Result<List<String>> getAttendRecordUserNums(@RequestParam String groupNum) {
        List<String> userNumList = gameMindAttendRecordService.getAttendRecordUserNums(groupNum);
        return successGet(userNumList);
    }

}

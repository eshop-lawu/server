package com.lawu.eshop.game.srv.mock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.dto.GameMindRoomDetailsCacheDTO;
import com.lawu.eshop.cache.dto.GameMindUserDetailsCacheDTO;
import com.lawu.eshop.cache.dto.StartTheGameUserDTO;
import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameParam;
import com.lawu.eshop.cache.param.GameMindRandomMatchUserInformationParam;
import com.lawu.eshop.game.srv.service.GameMindCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
@Service
public class MockGameMindCacheService extends BaseController implements GameMindCacheService {
    @Override
    public Result setGameTime(@PathVariable("attendId") Long attendId, @RequestParam("times") Long times) {
        return null;
    }

    @Override
    public Result setGameMindQuestionCache(@RequestParam("attendIds") String attendIds, @RequestParam("questionIds") String questionIds) {
        return null;
    }

    @Override
    public Result addAttendRecordResult(@RequestParam("attendId") Long attendId, @RequestParam("record") String record) {
        return null;
    }

    @Override
    public Result<List<GameMindAttendRecordResultParam>> getAttendRecordResult(@RequestParam("attendId") Long attendId) {
        List<GameMindAttendRecordResultParam> record = new ArrayList<>();
        GameMindAttendRecordResultParam  resultParam = new GameMindAttendRecordResultParam();
        resultParam.setMindAttendId(1L);
        resultParam.setGmtModified(new Date());
        resultParam.setUseTime(1);
        resultParam.setQuestionId(1L);
        resultParam.setRightAnswer(1);
        resultParam.setAnswer("test");
        record.add(resultParam);

        return successGet(record);
    }

    @Override
    public Result addAnswerQuestionPoint(@RequestParam("attendId") Long attendId, @RequestParam("point") Integer point) {
        return null;
    }

    @Override
    public Result<Integer> getAnswerQuestionPoint(@RequestParam("attendId") Long attendId) {
        Integer score = 100;
        return successGet(score);
    }

    @Override
    public Result delSingleGameMindCache(@RequestParam("attendId") Long attendId) {
        return null;
    }

    @Override
    public Result<Boolean> findingMatchOpponents(@RequestBody GameMindRandomMatchUserInformationParam param) {
        return null;
    }

    @Override
    public Result participateGame(@RequestBody GameMindParticipateGameParam param) {
        return null;
    }

    @Override
    public Result<List<StartTheGameUserDTO>> getGroupUserNums(@RequestParam("attendNum") String attendNum) {
        List<StartTheGameUserDTO> userInfos = new ArrayList<>();
        StartTheGameUserDTO item = new StartTheGameUserDTO();
        item.setUserNum("M000001");
        item.setIsHomeowner(false);
        userInfos.add(item);
        return successGet(userInfos);
    }

    @Override
    public Result changeAttendStatus(@RequestParam("userNum") String userNum, @RequestParam("attendStatus") GameAttendRecordStatusEnum attendStatus) {
        return null;
    }

    @Override
    public Result<List<GameMindUserDetailsCacheDTO>> allInformation(@RequestParam("attendNum") String attendNum) {
        return null;
    }

    @Override
    public Result clearAllCache(@RequestParam("attendNum") String attendNum) {
        return null;
    }

    @Override
    public Result quit(String userNum) {
        return null;
    }

    @Override
    public Result<GameMindUserDetailsCacheDTO> getSingleUserInformation(String userNum) {
        return null;
    }


	@Override
	public Result setGameSimpleMindQuestionIds(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result setGameDifficultyMindQuestionIds(List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result removeMindQuestion() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Result<List<Long>> getMindCacheQuestionIds(@RequestParam("configCount") Integer configCount, @RequestParam("easyCount") Integer easyCount) {
        return null;
    }

    @Override
    public Result<GameMindRoomDetailsCacheDTO> getRobotMindQuestions(@RequestParam("groupNum") String groupNum) {
        return null;
    }
}

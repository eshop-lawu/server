package com.lawu.eshop.game.srv.mock.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.param.GameDifficultyParam;
import com.lawu.eshop.common.param.GamePointAllotParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.common.param.SecScoreParam;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
@Service
public class MockGameConfigCacheService extends BaseController implements GameConfigCacheService {
    
    @Override
    public Result<GameMindConfigDTO> getGameMindConfig() {
        GameMindConfigDTO configDTO = new GameMindConfigDTO();
        configDTO.setCountDown(10);
        List<SecScoreParam> params = new ArrayList<>();
        SecScoreParam param = new SecScoreParam();
        param.setPoint(100);
        param.setSecond(10);
        params.add(param);
        configDTO.setSecScore(params);
        configDTO.setLastScoreMultiple(2);
        configDTO.setSuccessScore(100);
        configDTO.setAttendPoint(10);
        configDTO.setAwardStar(1);
        configDTO.setAwardPoint(2);
        configDTO.setDeductStar(2);
        return successGet(configDTO);
    }

    @Override
    public Result<GamePuzzleConfigCacheDTO> getGamePuzzleConfig() {
    	GamePuzzleConfigCacheDTO dto =new GamePuzzleConfigCacheDTO();
        dto.setAttendPoint(2);
        dto.setRoomMaxNum(5);
    	dto.setAttendMaxPoint(3);
    	dto.setFreeCount(0);
        dto.setShareAttendCount(1);
        dto.setCountDown(100);
        dto.setAwardPoint(10);
        dto.setAwardStar(20);
        dto.setDeductStar(2);
        dto.setPicCount(3);
        dto.setSuccessScore(100);
        dto.setStatusEnum(GameConfigStatusEnum.ENABLE);
        dto.setForbiddenRemark("123");
        List<SecScoreParam> listScore =initListScore();
        List<GamePointAllotParam> listPoint =initPoint();
        List<GameDifficultyParam> listGameDiff=initGameDiff();
        dto.setSecScore(listScore);
        dto.setAllotList(listPoint);
        dto.setDifficultys(listGameDiff);
        return successGet(dto);
    }

    private List<SecScoreParam> initListScore() {
        List<SecScoreParam> list = new ArrayList<SecScoreParam>();
        SecScoreParam sec =new SecScoreParam();
        sec.setSecond(1);
        sec.setPoint(90);
        SecScoreParam sec1 =new SecScoreParam();
        sec1.setSecond(20);
        sec1.setPoint(80);
        list.add(sec1);
        SecScoreParam sec2 =new SecScoreParam();
        sec2.setSecond(30);
        sec2.setPoint(70);
        list.add(sec2);
        SecScoreParam sec3 =new SecScoreParam();
        sec3.setSecond(40);
        sec3.setPoint(60);
        list.add(sec3);
        SecScoreParam sec4 =new SecScoreParam();
        sec4.setSecond(50);
        sec4.setPoint(50);
        list.add(sec4);
        return list;
    }

    private List<GamePointAllotParam> initPoint(){
        List<GamePointAllotParam> list =new ArrayList<>();
        GamePointAllotParam point5 =new GamePointAllotParam();
        point5.setAttendCount(5);
        point5.setRankPoint(Arrays.asList("0.48","0.28","0.1","0.05","0"));
        point5.setRankStar(Arrays.asList("1","1","0","0","0"));
        list.add(point5);
        GamePointAllotParam point4 =new GamePointAllotParam();
        point4.setAttendCount(4);
        point4.setRankPoint(Arrays.asList("0.55","0.3","0.06","0"));
        point4.setRankStar(Arrays.asList("1","1","0","0"));
        list.add(point4);
        GamePointAllotParam point3 =new GamePointAllotParam();
        point3.setAttendCount(3);
        point3.setRankPoint(Arrays.asList("0.67","0.25","0"));
        point3.setRankStar(Arrays.asList("1","0","0"));
        list.add(point3);
        GamePointAllotParam point2 =new GamePointAllotParam();
        point2.setAttendCount(3);
        point2.setRankPoint(Arrays.asList("0.9","0"));
        point2.setRankStar(Arrays.asList("1","0"));
        list.add(point2);
        return list;
    }

    private List<GameDifficultyParam> initGameDiff(){
        List<GameDifficultyParam> list =new ArrayList<>();
        GameDifficultyParam gd =new GameDifficultyParam();
        gd.setCoefficient(3);
        gd.setChallengeTime(60);
        gd.setLeverEnum(GameHardLevelEnum.SIMPLE);
        gd.setSecScore(initListScore());
        list.add(gd);
        GameDifficultyParam gd1 =new GameDifficultyParam();
        gd1.setCoefficient(4);
        gd1.setChallengeTime(80);
        gd1.setLeverEnum(GameHardLevelEnum.COMMONLY);
        gd1.setSecScore(initListScore());
        list.add(gd1);
        GameDifficultyParam gd2 =new GameDifficultyParam();
        gd2.setCoefficient(4);
        gd2.setChallengeTime(80);
        gd2.setLeverEnum(GameHardLevelEnum.DIFFICULTY);
        gd2.setSecScore(initListScore());
        list.add(gd2);
        return  list;
    }
    @Override
    public Result<Boolean> setGamePuzzleUserKeyValue(@RequestParam("key") String key, @RequestParam("val") String val) {
        return null;
    }

    @Override
    public Result<String> getGamePuzzleUserKey(@RequestParam("key") String key) {
        return null;
    }

    @Override
    public Result<Long> delGamePuzzleUserKeyKey(@RequestParam("key") String key) {
        return null;
    }

    @Override
    public Result setGamePuzzleStartTimeValue(@RequestParam("userNum") String userNum, @RequestParam("date") String date) {
        return null;
    }

    @Override
    public Result<String> getGamePuzzleStartTimeValue(@RequestParam("userNum") String userNum) {
        return null;
    }

    @Override
    public Result<String> getRedisKeyGamePuzzleStartType(@RequestParam("key") String key) {
        return null;
    }

    @Override
    public Result setRedisKeyGamePuzzleStartType(@RequestParam("key") String key, @RequestParam("date") String date) {
        return null;
    }

    @Override
    public Result<List<String>> getLikeRedisKeyGamePuzzleStartType(@RequestParam("key") String key) {
        return null;
    }

    @Override
    public Result delRedisKeyGamePuzzleStartType(@RequestParam("key") String key) {
        return null;
    }

    @Override
    public Result removeStartCacheData(String attendNum, String userNum) {
        return null;
    }

	@Override
	public Result removeMyPuzzleCacheData(String userNum) {
		// TODO Auto-generated method stub
		return null;
	}
}

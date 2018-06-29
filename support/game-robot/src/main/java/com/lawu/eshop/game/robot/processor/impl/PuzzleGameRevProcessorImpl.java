package com.lawu.eshop.game.robot.processor.impl;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.game.robot.GameRobotConfig;
import com.lawu.eshop.game.robot.channel.ChannelManager;
import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.game.robot.constants.AttendTypeEnum;
import com.lawu.eshop.game.robot.constants.GameHardLevelEnum;
import com.lawu.eshop.game.robot.constants.GameMsgType;
import com.lawu.eshop.game.robot.dto.GameDifficultyParam;
import com.lawu.eshop.game.robot.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.game.robot.dto.GamePuzzleGetPicDTO;
import com.lawu.eshop.game.robot.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.robot.dto.Result;
import com.lawu.eshop.game.robot.param.Message;
import com.lawu.eshop.game.robot.param.PuzzlePKStartParam;
import com.lawu.eshop.game.robot.param.PuzzlePKSubmitParam;
import com.lawu.eshop.game.robot.param.PuzzlePkOnlineParam;
import com.lawu.eshop.game.robot.processor.GameRevProcessor;
import com.lawu.eshop.game.robot.processor.SendProcessor;
import com.lawu.eshop.game.robot.util.HttpUtil;
import com.lawu.eshop.game.robot.util.JsonUtil;
import com.lawu.eshop.game.robot.util.RandomPicUtil;

@Service("puzzleGameRevProcessorImpl")
public class PuzzleGameRevProcessorImpl implements GameRevProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(PuzzleGameRevProcessorImpl.class);
	
	@Autowired
    private GameRobotConfig gameRobotConfig;
	
    @Autowired
    private SendProcessor sendProcessor;
	 
    @Override
    public void online(String userNum) {
    	PuzzlePkOnlineParam puzzleContent =new PuzzlePkOnlineParam();
    	puzzleContent.setAttendType(AttendTypeEnum.RANDOM);
    	puzzleContent.setIsHomeowner(false);
    	Message<PuzzlePkOnlineParam> message =new Message<PuzzlePkOnlineParam>();
    	message.setContent(puzzleContent);
    	message.setMsgType(GameMsgType.ONLINE);
    	sendProcessor.send(message, userNum);
    }
    
    @Override
    public void start(String userNum, Object model) {
    	PuzzlePKStartParam puzzleStart =new PuzzlePKStartParam();
    	puzzleStart.setAttendType(AttendTypeEnum.RANDOM);
    	puzzleStart.setGameLevel(GameHardLevelEnum.RANDOM);
    	Map<String, String> params =new HashMap(); 
    	GameNioSocketChannel channel = ((GameNioSocketChannel)ChannelManager.getClient(userNum).getChannel());
    	params.put("attendNum", channel.getGroupNum());
    	Map<String, String> headersMap =new HashMap();
    	headersMap.put("authorization", channel.getToken());
    	String result = HttpUtil.doPost(gameRobotConfig.getMemberApiAddress()+"gamePuzzleRobot/getPuzzleInfo",params,headersMap);
    	Result resultReturn = JsonUtil.read(result, Result.class);
    	if(resultReturn.getRet()!=1000){
    		logger.error("查询缓存数据异常,{}",resultReturn.getMsg());
    	}
    	GamePuzzleGetPicReturnDTO dto = JsonUtil.read(resultReturn.getModel().toString(), GamePuzzleGetPicReturnDTO.class);
    	channel.setContent((JSON.toJSON(dto).toString()));
    	channel.setAnswerIndex(0);
		Map<String, String> param =new HashMap();
		String cache = HttpUtil.doPost(gameRobotConfig.getMemberApiAddress()+"gamePuzzleRobot/getGamePuzzleConfig",param,headersMap);
    	Result resultCache = JsonUtil.read(cache, Result.class);
    	if(resultCache.getRet()!=1000){
    		logger.error("查询拼图缓存数据异常,{}",resultReturn.getMsg());
    	}
    	GamePuzzleConfigCacheDTO configDTO = JsonUtil.read(resultCache.getModel().toString(), GamePuzzleConfigCacheDTO.class);
		channel.setCacheInfo((JSON.toJSON(configDTO).toString()));
    }

    @Override
    public void submit(String userNum, Object model) {
    	GameNioSocketChannel channel = ((GameNioSocketChannel)ChannelManager.getClient(userNum).getChannel());
    	GamePuzzleGetPicReturnDTO puzzle = JsonUtil.read(channel.getContent(),GamePuzzleGetPicReturnDTO.class);
    	GamePuzzleConfigCacheDTO cacheDto = JsonUtil.read(channel.getCacheInfo(),GamePuzzleConfigCacheDTO.class);
		int setSecond=0;//设置的拼图时间
		for(GamePuzzleGetPicDTO puzzleGet:puzzle.getPuzzleInfo()){
			for(GameDifficultyParam game :cacheDto.getDifficultys()){
				if(game.getCoefficient()==puzzleGet.getCoefficient()){
					setSecond=setSecond+ThreadLocalRandom.current().nextInt(game.getRobotMinSecond(), game.getRobotMaxSecond()+1);
				}
			}
			PuzzlePKSubmitParam submitParam =new PuzzlePKSubmitParam();
			submitParam.setGameId(String.valueOf(puzzleGet.getId()));
			String succKey = RandomPicUtil.getSuccessKey(puzzleGet.getId(), puzzleGet.getKey(), puzzleGet.getCoefficient());
			submitParam.setPicNum(succKey);
			submitParam.setAttendNum(channel.getGroupNum());
			Message<PuzzlePKSubmitParam> message =new Message<PuzzlePKSubmitParam>();
			message.setContent(submitParam);
			message.setMsgType(GameMsgType.SUBMIT);
			logger.info("提交答案设置的时间是"+setSecond);
			sendProcessor.sendAsync(message, userNum, setSecond);
		}
    /*	int index = channel.getAnswerIndex();

    	GamePuzzleGetPicDTO puzzleGet = puzzle.getPuzzleInfo().get(index);//单个图片
    	int setSecond=1;//设置的拼图时间
    	for(GameDifficultyParam game :cacheDto.getDifficultys()){
    		if(game.getCoefficient()==puzzleGet.getCoefficient()){
    			setSecond=ThreadLocalRandom.current().nextInt(game.getRobotMinSecond(), game.getRobotMaxSecond()+1);
    		}
    	}*/
    }
 

}

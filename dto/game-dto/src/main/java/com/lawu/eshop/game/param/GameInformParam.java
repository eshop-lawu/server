package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameTypeEnum;

import io.swagger.annotations.ApiParam;

/**
 * 用户举报游戏
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public class GameInformParam {

	/**
	 *
	 * 游戏类型 1-头脑pk 2拼图 game_inform.game_type
	 *
	 * @mbg.generated
	 */
	@ApiParam(name = "gameType", value = "MIND 头脑pk PUZZLE ")
	private GameTypeEnum gameType;

	/**
	 *
	 * 游戏编号 game_inform.attend_num
	 *
	 * @mbg.generated
	 */
	@ApiParam(name = "attendNum", required = true, value = "游戏编号")
	private String attendNum;

	/**
	 *
	 * 结果错误 game_inform.result_error
	 *
	 * @mbg.generated
	 */
	@ApiParam(name = "resultError", value = "结果错误 选中为true 否则为false")
	private Boolean resultError;

	/**
	 *
	 * 题目错误 game_inform.question_error
	 *
	 * @mbg.generated
	 */
	@ApiParam(name = "questionError", value = "题目错误 选中为true 否则为false")
	private Boolean questionError;

	/**
	 *
	 * 对方作弊 game_inform.cheat
	 *
	 * @mbg.generated
	 */
	@ApiParam(name = "cheat", value = "对方作弊 选中为true 否则为false")
	private Boolean cheat;

	public GameTypeEnum getGameType() {
		return gameType;
	}

	public void setGameType(GameTypeEnum gameType) {
		this.gameType = gameType;
	}

	public String getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}

	public Boolean getResultError() {
		return resultError;
	}

	public void setResultError(Boolean resultError) {
		this.resultError = resultError;
	}

	public Boolean getQuestionError() {
		return questionError;
	}

	public void setQuestionError(Boolean questionError) {
		this.questionError = questionError;
	}

	public Boolean getCheat() {
		return cheat;
	}

	public void setCheat(Boolean cheat) {
		this.cheat = cheat;
	}

}

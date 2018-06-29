package com.lawu.eshop.game.param;

import java.util.List;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;

/**
 * 用户星星操作实体
 * 
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月14日
 */
public class GameAccountStarParam {
	
	/**
	 * 游戏类型
	 */
	private GameTypeEnum gameType;
	
	/**
	 * 参与编号
	 */
	private String attendNum;
	
	/**
	 * 参与游戏结果状态
	 */
	private GameAttendRecordStatusEnum recordEnum;
	
	/**
	 * 排名集合
	 */
	private List<GameAccountAllotParam> list;

	public GameTypeEnum getGameType() {
		return gameType;
	}

	public void setGameType(GameTypeEnum gameType) {
		this.gameType = gameType;
	}

	public List<GameAccountAllotParam> getList() {
		return list;
	}

	public void setList(List<GameAccountAllotParam> list) {
		this.list = list;
	}

	public GameAttendRecordStatusEnum getRecordEnum() {
		return recordEnum;
	}

	public void setRecordEnum(GameAttendRecordStatusEnum recordEnum) {
		this.recordEnum = recordEnum;
	}

	public String getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}
	
	

}

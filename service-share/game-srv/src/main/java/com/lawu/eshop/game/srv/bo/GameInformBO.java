package com.lawu.eshop.game.srv.bo;

import java.util.Date;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.GameInformStatusEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月13日
 */
public class GameInformBO {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 游戏类型
	 */
	private GameTypeEnum gameType;

	/**
	 * 游戏编号
	 */
	private String attendNum;

	/**
	 * 用户编号
	 */
	private String userNum;

	/**
	 * 是否结果错误
	 */
	private Boolean resultError;

	/**
	 * 是否题目错误
	 */
	private Boolean questionError;

	/**
	 * 是否对方作弊
	 */
	private Boolean cheat;

	/**
	 * 审核人ID
	 */
	private Integer auditorId;

	/**
	 * 审核人名称
	 */
	private String auditorName;

	/**
	 * 审核备注
	 */
	private String remark;

	/**
	 * 审核时间
	 */
	private Date auditTime;

	/**
	 * 处理状态
	 */
	private GameInformStatusEnum status;

	/**
	 * 举报时间
	 */
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
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

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public GameInformStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameInformStatusEnum status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

}

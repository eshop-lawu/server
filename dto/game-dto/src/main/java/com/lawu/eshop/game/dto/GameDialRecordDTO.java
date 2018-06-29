package com.lawu.eshop.game.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
public class GameDialRecordDTO {

	private Long id;

	private Long userId;

	private String userNum;

	private String userAccount;

	private Long gameDialPrizeId;

	private GameDialRecordStatusEnum status;

	private BigDecimal payPoint;

	private String consigneeName;

	private String consigneeMobile;

	private String consigneeAddress;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;
	
	private String prizeName;
	
	private String nickName;
	
	private String statusDes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Long getGameDialPrizeId() {
		return gameDialPrizeId;
	}

	public void setGameDialPrizeId(Long gameDialPrizeId) {
		this.gameDialPrizeId = gameDialPrizeId;
	}

	public GameDialRecordStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameDialRecordStatusEnum status) {
		this.status = status;
	}

	public BigDecimal getPayPoint() {
		return payPoint;
	}

	public void setPayPoint(BigDecimal payPoint) {
		this.payPoint = payPoint;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getStatusDes() {
		return statusDes;
	}

	public void setStatusDes(String statusDes) {
		this.statusDes = statusDes;
	}

	
	
	

}

package com.lawu.eshop.game.query;

import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
public class GameDailRecordPageQuery extends AbstractPageParam{
	
	private String userAccount;

    private String prizeName;

    private String consigneeName;

    private String consigneeMobile;

    private Long drawLotteryActivityId;

    private GameDialRecordStatusEnum statusEnum;
	
	private String sortOrder;

	private Integer payPoint;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
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

	public Long getDrawLotteryActivityId() {
		return drawLotteryActivityId;
	}

	public void setDrawLotteryActivityId(Long drawLotteryActivityId) {
		this.drawLotteryActivityId = drawLotteryActivityId;
	}

	public GameDialRecordStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(GameDialRecordStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getPayPoint() {
		return payPoint;
	}

	public void setPayPoint(Integer payPoint) {
		this.payPoint = payPoint;
	}
}

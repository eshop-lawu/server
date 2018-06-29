package com.lawu.eshop.game.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.cache.constants.GameTypeEnum;

public class GamePointReportBO {

	private GameTypeEnum gameType;

	/**
	 *
	 * 单机支出收益 report_game_point_daily.stand_alone_expend_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal standAloneExpendPoint;

	/**
	 *
	 * 单机收入收益 report_game_point_daily.stand_alone_income_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal standAloneIncomePoint;

	/**
	 *
	 * 随机支出收益 report_game_point_daily.random_expend_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal randomExpendPoint;

	/**
	 *
	 * 随机收入收益 report_game_point_daily.random_income_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal randomIncomePoint;

	/**
	 *
	 * 多人支出收益 report_game_point_daily.many_people_expend_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal manyPeopleExpendPoint;

	private BigDecimal totalExpendPoint;

	private BigDecimal totalIncomePoint;

	/**
	 *
	 * 多人收入收益 report_game_point_daily.many_people_income_point
	 *
	 * @mbg.generated
	 */
	private BigDecimal manyPeopleIncomePoint;

	public GameTypeEnum getGameType() {
		return gameType;
	}

	public void setGameType(GameTypeEnum gameType) {
		this.gameType = gameType;
	}

	public BigDecimal getStandAloneExpendPoint() {
		return standAloneExpendPoint;
	}

	public void setStandAloneExpendPoint(BigDecimal standAloneExpendPoint) {
		this.standAloneExpendPoint = standAloneExpendPoint;
	}

	public BigDecimal getStandAloneIncomePoint() {
		return standAloneIncomePoint;
	}

	public void setStandAloneIncomePoint(BigDecimal standAloneIncomePoint) {
		this.standAloneIncomePoint = standAloneIncomePoint;
	}

	public BigDecimal getRandomExpendPoint() {
		return randomExpendPoint;
	}

	public void setRandomExpendPoint(BigDecimal randomExpendPoint) {
		this.randomExpendPoint = randomExpendPoint;
	}

	public BigDecimal getRandomIncomePoint() {
		return randomIncomePoint;
	}

	public void setRandomIncomePoint(BigDecimal randomIncomePoint) {
		this.randomIncomePoint = randomIncomePoint;
	}

	public BigDecimal getManyPeopleExpendPoint() {
		return manyPeopleExpendPoint;
	}

	public void setManyPeopleExpendPoint(BigDecimal manyPeopleExpendPoint) {
		this.manyPeopleExpendPoint = manyPeopleExpendPoint;
	}

	public BigDecimal getManyPeopleIncomePoint() {
		return manyPeopleIncomePoint;
	}

	public void setManyPeopleIncomePoint(BigDecimal manyPeopleIncomePoint) {
		this.manyPeopleIncomePoint = manyPeopleIncomePoint;
	}

	public BigDecimal getTotalExpendPoint() {
		return totalExpendPoint;
	}

	public void setTotalExpendPoint(BigDecimal totalExpendPoint) {
		this.totalExpendPoint = totalExpendPoint;
	}

	public BigDecimal getTotalIncomePoint() {
		return totalIncomePoint;
	}

	public void setTotalIncomePoint(BigDecimal totalIncomePoint) {
		this.totalIncomePoint = totalIncomePoint;
	}
}

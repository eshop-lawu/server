package com.lawu.eshop.game.param;

import java.math.BigDecimal;

import com.lawu.eshop.game.constants.GameDialPrizeTypeEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
public class GameDailPrizeParam {
	
	private Long id;
	/**
	 *
	 * 转盘游戏id game_dial_prize.game_dial_id
	 *
	 * @mbg.generated
	 */
	private Long gameDialId;

	/**
	 *
	 * 名称 game_dial_prize.name
	 *
	 * @mbg.generated
	 */
	private String name;

	/**
	 *
	 * 图片 game_dial_prize.img_path
	 *
	 * @mbg.generated
	 */
	private String imgPath;

	/**
	 *
	 * 价格 game_dial_prize.price
	 *
	 * @mbg.generated
	 */
	private BigDecimal price;

	/**
	 *
	 * 数量 game_dial_prize.number
	 *
	 * @mbg.generated
	 */
	private Integer number;


	/**
	 *
	 * 是否需要填写地址 game_dial_prize.is_address
	 *
	 * @mbg.generated
	 */
	private Boolean isAddress;

	/**
	 *
	 * 是否立即发放奖品 game_dial_prize.is_send_prize
	 *
	 * @mbg.generated
	 */
	private Boolean isSendPrize;

	/**
	 *
	 * 奖品类型 game_dial_prize.prize_type
	 *
	 * @mbg.generated
	 */
	private GameDialPrizeTypeEnum prizeType;

	/**
	 *
	 * 运费 game_dial_prize.freight_price
	 *
	 * @mbg.generated
	 */
	private BigDecimal freightPrice;

	/**
	 *
	 * 中奖概率 game_dial_prize.rate
	 *
	 * @mbg.generated
	 */
	private BigDecimal rate;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGameDialId() {
		return gameDialId;
	}

	public void setGameDialId(Long gameDialId) {
		this.gameDialId = gameDialId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getIsAddress() {
		return isAddress;
	}

	public void setIsAddress(Boolean isAddress) {
		this.isAddress = isAddress;
	}

	public Boolean getIsSendPrize() {
		return isSendPrize;
	}

	public void setIsSendPrize(Boolean isSendPrize) {
		this.isSendPrize = isSendPrize;
	}

	public GameDialPrizeTypeEnum getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(GameDialPrizeTypeEnum prizeType) {
		this.prizeType = prizeType;
	}

	public BigDecimal getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(BigDecimal freightPrice) {
		this.freightPrice = freightPrice;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}

package com.lawu.eshop.activity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeTypeEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/30.
 */
public class DrawLotteryActivityRecordPageDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;
    
    @ApiModelProperty(value = "奖品图片")
    private String prizeImg;

    @ApiModelProperty(value = "抽奖记录状态  TAKE_LOTTERY 已领取（未发货|未到账）  SEND_LOTTERY 已发放（已到账|已发货）")
    private DrawLotteryActivityRecordStatusEnum statusEnum;
    
    @ApiModelProperty(value = "物流号")
    private String expressNum;
    
    @ApiModelProperty(value = "中奖时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;
    
    @ApiModelProperty(value = "奖品类型 MONEY 金额  POINT 积分 PRODUCT 商品")
    private DrawLotteryActivityPrizeTypeEnum prizeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public DrawLotteryActivityRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

	public String getPrizeImg() {
		return prizeImg;
	}

	public void setPrizeImg(String prizeImg) {
		this.prizeImg = prizeImg;
	}

	public String getExpressNum() {
		return expressNum;
	}

	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public DrawLotteryActivityPrizeTypeEnum getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(DrawLotteryActivityPrizeTypeEnum prizeType) {
		this.prizeType = prizeType;
	}
    
    
}

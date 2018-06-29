package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public class PointLotteryAttentBO {

    private String lotteryNum;

    private Integer lotteryCnt;

    private Date lotteryTime;

    public String getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(String lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public Integer getLotteryCnt() {
        return lotteryCnt;
    }

    public void setLotteryCnt(Integer lotteryCnt) {
        this.lotteryCnt = lotteryCnt;
    }

    public Date getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }
}

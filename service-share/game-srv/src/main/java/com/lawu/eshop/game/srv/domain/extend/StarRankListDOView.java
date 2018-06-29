package com.lawu.eshop.game.srv.domain.extend;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
public class StarRankListDOView implements Serializable {
    private static final long serialVersionUID = 8034063978879728656L;

    private String account;

    private Integer monthStarCount;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getMonthStarCount() {
        return monthStarCount;
    }

    public void setMonthStarCount(Integer monthStarCount) {
        this.monthStarCount = monthStarCount;
    }
}

package com.lawu.eshop.ad.param;

/**
 * @author zhangyong
 * @date 2018/6/7.
 */
public class FavoriteAdCountParam {

    private Byte type;

    private Long memberId;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}

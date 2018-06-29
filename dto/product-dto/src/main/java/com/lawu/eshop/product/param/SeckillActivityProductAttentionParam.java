package com.lawu.eshop.product.param;

/**
 * 抢购活动关注参数
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
public class SeckillActivityProductAttentionParam {
    
    /**
     * 用户id
     */
    private Long memberId;
    
    /**
     * 用户编号
     */
    private String memberNum;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }
    
}

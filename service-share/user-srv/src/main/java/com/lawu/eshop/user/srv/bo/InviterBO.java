package com.lawu.eshop.user.srv.bo;

/**
 * 邀请人BO
 *
 * @author meishuquan
 * @date 2017/3/23
 */
public class InviterBO {

    private Long inviterId;

    private String userNum;

    private String inviterName;
    
    /**
     * 是否冻结
     */
    private Boolean isFreeze;
    
    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getInviterName() {
        return inviterName;
    }

    public void setInviterName(String inviterName) {
        this.inviterName = inviterName;
    }

    public Boolean getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Boolean isFreeze) {
        this.isFreeze = isFreeze;
    }
    
}

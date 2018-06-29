package com.lawu.eshop.cache.dto;

/**
 * 检查积分扣除情况
 * 
 * @author jiangxinjun
 * @createDate 2018年3月19日
 * @updateDate 2018年3月19日
 */
public class CheckPointsDeductionStatusDTO {
    
    /**
     * 当前用户积分是否扣除成功
     */
    private Boolean isDeductedSuccess;
    
    /**
     * 是否所有人积分扣除完成
     */
    private Boolean isAllComplete;
    
    public Boolean getIsDeductedSuccess() {
        return isDeductedSuccess;
    }

    public void setIsDeductedSuccess(Boolean isDeductedSuccess) {
        this.isDeductedSuccess = isDeductedSuccess;
    }

    public Boolean getIsAllComplete() {
        return isAllComplete;
    }

    public void setIsAllComplete(Boolean isAllComplete) {
        this.isAllComplete = isAllComplete;
    }

}

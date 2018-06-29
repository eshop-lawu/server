package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;

/**
 * @author zhangrc
 * @date 2018/01/29
 */
public class AdDownnNoticeNotification implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 广告id
	 */
	private Long adId;
	
    /**
    * 商家编号
    */
    private String merchantNum;
   
    /**
     * 广告类型
     */
    private String adType;
    
    /**
     * 广告标题
     */
    private String adTitle;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public String getAdType() {
		return adType;
	}

	public void setAdType(String adType) {
		this.adType = adType;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}


    
    
    
    
    
	
	
}

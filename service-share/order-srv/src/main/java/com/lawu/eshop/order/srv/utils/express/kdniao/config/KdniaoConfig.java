package com.lawu.eshop.order.srv.utils.express.kdniao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "express.kauidiniao")
public class KdniaoConfig {
	
	/**
	 * 电商ID
	 */
    private String eBusinessId;
    
    /**
     * 电商加密私钥
     */
    private String appKey;
    
    /**
     * 请求路径
     */
    private String reqUrl;

	public String getEBusinessId() {
		return eBusinessId;
	}

	public void setEBusinessId(String eBusinessId) {
		this.eBusinessId = eBusinessId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
    
}

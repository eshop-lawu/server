package com.lawu.eshop.order.srv.utils.express.kuaidi100.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "express.kuaidi100")
public class KuaiDi100Config {
	
    /**
     * 身份授权key
     */
    private Boolean isTest;
	
    /**
     * 测试接口
     * 身份授权key
     */
    private String testKey;
    
    /**
     * 企业级接口
     * 身份授权key
     */
    private String key;
    
    /**
     * 公司编号
     */
    private String customer;
    
    /**
     * 秘钥
     */
    private String secret;
    
    /**
     * 用户ID
     */
    private String userId;
    
    /**
     * 请求路径
     */
    private String reqUrl;
    
    /**
     * 快递查询接口访问地址
     */
    private String inquiriesUrl;
    
    /**
     * 单号识别接口访问地址
     */
    private String recognitionUrl;

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public String getTestKey() {
		return testKey;
	}

	public void setTestKey(String testKey) {
		this.testKey = testKey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getInquiriesUrl() {
		return inquiriesUrl;
	}

	public void setInquiriesUrl(String inquiriesUrl) {
		this.inquiriesUrl = inquiriesUrl;
	}

	public String getRecognitionUrl() {
		return recognitionUrl;
	}

	public void setRecognitionUrl(String recognitionUrl) {
		this.recognitionUrl = recognitionUrl;
	}
    
}

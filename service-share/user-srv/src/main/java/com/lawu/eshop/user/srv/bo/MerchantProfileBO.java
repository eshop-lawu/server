package com.lawu.eshop.user.srv.bo;

/**
 * 商家扩展信息表
 * Created by zhangyong on 2017/3/23.
 */
public class MerchantProfileBO {
    private Long id;

    private Integer inviteMemberCount;

    private Integer inviteMerchantCount;
    
    /**
    *
    * 官网链接
    * merchant_profile.website_url
    *
    * @mbg.generated
    */
   private String websiteUrl;

   /**
    *
    * 淘宝链接
    * merchant_profile.taobao_url
    *
    * @mbg.generated
    */
   private String taobaoUrl;

   /**
    *
    * 天猫链接
    * merchant_profile.tmall_url
    *
    * @mbg.generated
    */
   private String tmallUrl;

   /**
    *
    * 京东链接
    * merchant_profile.jd_url
    *
    * @mbg.generated
    */
   private String jdUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInviteMemberCount() {
        return inviteMemberCount;
    }

    public void setInviteMemberCount(Integer inviteMemberCount) {
        this.inviteMemberCount = inviteMemberCount;
    }

    public Integer getInviteMerchantCount() {
        return inviteMerchantCount;
    }

    public void setInviteMerchantCount(Integer inviteMerchantCount) {
        this.inviteMerchantCount = inviteMerchantCount;
    }

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getTaobaoUrl() {
		return taobaoUrl;
	}

	public void setTaobaoUrl(String taobaoUrl) {
		this.taobaoUrl = taobaoUrl;
	}

	public String getTmallUrl() {
		return tmallUrl;
	}

	public void setTmallUrl(String tmallUrl) {
		this.tmallUrl = tmallUrl;
	}

	public String getJdUrl() {
		return jdUrl;
	}

	public void setJdUrl(String jdUrl) {
		this.jdUrl = jdUrl;
	}
    
    
}

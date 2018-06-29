package com.lawu.eshop.merchant.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2017/4/26
 */
@Component
public class MerchantApiConfig {

    @Value(value="${alipay.env}")
    private String alipayEnv;

    @Value(value="${image.url}")
    private String imageUrl;
    @Value(value="${video.url}")
    private String videoUrl;

    @Value(value="${image.upload.url}")
    private String imageUploadUrl;

    @Value(value="${video.upload.url}")
    private String videoUploadUrl;

    @Value(value="${sms.valid.minutes}")
    private Integer smsValidMinutes;

    @Value(value="${merchant.qr.code}")
    private String merchantQrCode;

    @Value(value="${share.redpacket.url}")
    private String shareRedpacketUrl;

    @Value(value="${share.register.url}")
    private String shareRegisterUrl;

    @Value(value="${default_headimg}")
    private String defaultHeadimg;
    
    @Value(value="${ffmpeg.url}")
    private String ffmpegUrl;
    
    @Value(value="${inviter.merchant.url}")
    private String inviterMerchantUrl;
    
    @Value(value="${inviter.member.url}")
    private String inviterMemberUrl;

    @Value(value="${fastdfs.trackerServers}")
    private String trackerServers;
    
    @Value(value="${fastdfs.trackerHttpPort}")
    private Integer trackerHttpPort;

    @Value(value="${visit.time.interval}")
    private Integer visitTimeInterval;

    @Value(value="${visit.frequency.count}")
    private Integer visitFrequencyCount;

    @Value(value="${visit.frequency.count.expire.time}")
    private Integer expireTime;
    
    @Value(value="${download_url}")
    private String downloadUrl;
    
    @Value(value = "${ad_praise_allot_prob}")
    private double adPraiseAllotProb;

    @Value(value = "${lawu.member.sms.version.flag}")
    private Boolean smsVersionFlag;

    @Value(value = "${lawu.member.sms.frequency.limit}")
    private Integer frequencyLimit;

    @Value(value = "${lawu.member.sms.frequency.expireTime}")
    private Integer frequencyExpireTime;

    @Value(value = "${lawu.member.picCode.verify.expireTime}")
    private Integer picCodeVerifyExpireTime;

    @Value(value = "${lawu.member.picCode.expireTime}")
    private Integer picCodeExpireTime;

    @Value(value = "${lawu.weixinApi.mp.auth.appid}")
    private String wxMpAppid;

    @Value(value = "${lawu.qqApi.mp.auth.appKey}")
    private String qqAuthAppKey;

    public String getAlipayEnv() {
        return alipayEnv;
    }

    public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUploadUrl() {
        return imageUploadUrl;
    }

    public String getVideoUploadUrl() {
        return videoUploadUrl;
    }

    public Integer getSmsValidMinutes() {
        return smsValidMinutes;
    }

    public String getMerchantQrCode() {
        return merchantQrCode;
    }

    public String getShareRedpacketUrl() {
        return shareRedpacketUrl;
    }

    public String getShareRegisterUrl() {
        return shareRegisterUrl;
    }

    public String getDefaultHeadimg() {
        return defaultHeadimg;
    }

	public String getFfmpegUrl() {
		return ffmpegUrl;
	}

	public String getInviterMerchantUrl() {
		return inviterMerchantUrl;
	}

	public String getInviterMemberUrl() {
		return inviterMemberUrl;
	}

	/**
	 * @return the trackerServers
	 */
	public String getTrackerServers() {
		return trackerServers;
	}

	/**
	 * @param trackerServers the trackerServers to set
	 */
	public void setTrackerServers(String trackerServers) {
		this.trackerServers = trackerServers;
	}

	public Integer getTrackerHttpPort() {
		return trackerHttpPort;
	}

	public void setTrackerHttpPort(Integer trackerHttpPort) {
		this.trackerHttpPort = trackerHttpPort;
	}

    public Integer getVisitTimeInterval() {
        return visitTimeInterval;
    }

    public Integer getVisitFrequencyCount() {
        return visitFrequencyCount;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

	public double getAdPraiseAllotProb() {
		return adPraiseAllotProb;
	}

    public Boolean getSmsVersionFlag() {
        return smsVersionFlag;
    }

    public Integer getFrequencyLimit() {
        return frequencyLimit;
    }

    public Integer getFrequencyExpireTime() {
        return frequencyExpireTime;
    }

    public Integer getPicCodeVerifyExpireTime() {
        return picCodeVerifyExpireTime;
    }

    public Integer getPicCodeExpireTime() {
        return picCodeExpireTime;
    }

    public String getWxMpAppid() {
        return wxMpAppid;
    }

    public String getQqAuthAppKey() {
        return qqAuthAppKey;
    }
}

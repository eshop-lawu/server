package com.lawu.eshop.member.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2017/4/26
 */
@Component
public class MemberApiConfig {

	@Value(value = "${image.url}")
	private String imageUrl;
	@Value(value = "${video.url}")
	private String videoUrl;

	@Value(value = "${image.upload.url}")
	private String imageUploadUrl;

	@Value(value = "${video.upload.url}")
	private String videoUploadUrl;

	@Value(value = "${sms.valid.minutes}")
	private Integer smsValidMinutes;

	@Value(value = "${default_headimg}")
	private String defaultHeadimg;

	@Value(value = "${click.praise.ad.times}")
	private Integer clickPraiseAdTimes;

	@Value(value = "${click.praise.prob}")
	private Integer clickPraiseProb;

	@Value(value = "${click.praise.sum.prob}")
	private Integer clickPraiseSumProb;

	@Value(value = "${inviter.merchant.url}")
	private String inviterMerchantUrl;

	@Value(value = "${inviter.member.url}")
	private String inviterMemberUrl;

	@Value(value = "${fastdfs.trackerServers}")
	private String trackerServers;

	@Value(value = "${fastdfs.trackerHttpPort}")
	private Integer trackerHttpPort;

	@Value(value = "${member.qr.code}")
	private String memberQrCode;

	@Value(value = "${member.share.redpacket.url}")
	private String memberShareRedpacketUrl;

	@Value(value = "${member.share.over.url}")
	private String memberShareOverUrl;
	
	@Value(value="${ffmpeg.url}")
    private String ffmpegUrl;
	
	@Value(value="${member.h5.ip}")
    private String memberH5Ip;

	@Value(value="${visit.time.interval}")
	private Integer visitTimeInterval;

	@Value(value="${visit.frequency.count}")
	private Integer visitFrequencyCount;

	@Value(value="${visit.frequency.count.expire.time}")
	private Integer expireTime;
	
	@Value(value="${share.ad.e-praise-default-image-path}")
	private String shareEPraiseAdDefaultImagePath;
	
	@Value(value="${share.ad.flat-default-image-path}")
	private String shareFlatAdDefaultImagePath;
	
	@Value(value="${share.ad.video-default-image-path}")
	private String shareVideoAdDefaultImagePath;
	
	@Value(value="${share.ad.logo-image-path}")
	private String shareAdLogoPath;
	
	@Value(value="${share.red-packet.default-image}")
	private String shareRedPacketDefaultImagePath;

	@Value(value="${download_url}")
	private String downloadUrl;
	
	@Value(value = "${lawu.weixinApi.mp.auth.appKey}")
	private String wxAuthAppKey;

	@Value(value = "${lawu.weixinApi.mp.auth.redirectUrl}")
	private String wxAuthRedirectUrl;

	@Value(value = "${lawu.weixinApi.mp.auth.getUserInfoUrl}")
	private String wxAuthGetUserInfoUrl;
	
	@Value(value = "${lawu.weixin.SubScriptionUrl}")
	private String wxSubScriptionUrl;
	
	@Value(value = "${lawu.weixinApi.mp.auth.appid}")
	private String wxMpAppid;
	
	@Value(value = "${lawu.qqApi.mp.auth.appKey}")
	private String qqAuthAppKey;

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
	
	@Value(value = "${share.game-mind.default-image}")
	private String gameMindShareImg;
	
	@Value(value = "${share.game-puzzle.default-image}")
	private String gamePuzzleShareImg;
	
	@Value(value = "${share.game-dial.default-image}")
	private String gameDialShareImg;
	
	@Value(value = "${lawu.app.h5.member.url}")
	private String appMemberUrl;
	
	public String getWxMpAppid() {
		return wxMpAppid;
	}

	public String getQqAuthAppKey() {
		return qqAuthAppKey;
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

	public String getDefaultHeadimg() {
		return defaultHeadimg;
	}

	public Integer getClickPraiseAdTimes() {
		return clickPraiseAdTimes;
	}

	public void setClickPraiseAdTimes(Integer clickPraiseAdTimes) {
		this.clickPraiseAdTimes = clickPraiseAdTimes;
	}

	public Integer getClickPraiseProb() {
		return clickPraiseProb;
	}

	public void setClickPraiseProb(Integer clickPraiseProb) {
		this.clickPraiseProb = clickPraiseProb;
	}

	public Integer getClickPraiseSumProb() {
		return clickPraiseSumProb;
	}

	public void setClickPraiseSumProb(Integer clickPraiseSumProb) {
		this.clickPraiseSumProb = clickPraiseSumProb;
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
	 * @param trackerServers
	 *            the trackerServers to set
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

	/**
	 * @return the memberQrCode
	 */
	public String getMemberQrCode() {
		return memberQrCode;
	}

	/**
	 * @param memberQrCode the memberQrCode to set
	 */
	public void setMemberQrCode(String memberQrCode) {
		this.memberQrCode = memberQrCode;
	}

	/**
	 * @return the memberShareRedpackerUrl
	 */
	public String getMemberShareRedpacketUrl() {
		return memberShareRedpacketUrl;
	}

	public void setMemberShareRedpacketUrl(String memberShareRedpacketUrl) {
		this.memberShareRedpacketUrl = memberShareRedpacketUrl;
	}

	/**
	 * @return the memberShareOverUrl
	 */
	public String getMemberShareOverUrl() {
		return memberShareOverUrl;
	}

	/**
	 * @param memberShareOverUrl the memberShareOverUrl to set
	 */
	public void setMemberShareOverUrl(String memberShareOverUrl) {
		this.memberShareOverUrl = memberShareOverUrl;
	}

	public String getFfmpegUrl() {
		return ffmpegUrl;
	}

	public String getMemberH5Ip() {
		return memberH5Ip;
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

	public String getShareEPraiseAdDefaultImagePath() {
		return shareEPraiseAdDefaultImagePath;
	}

	public String getShareFlatAdDefaultImagePath() {
		return shareFlatAdDefaultImagePath;
	}

	public String getShareVideoAdDefaultImagePath() {
		return shareVideoAdDefaultImagePath;
	}

	public String getShareAdLogoPath() {
		return shareAdLogoPath;
	}

	public String getShareRedPacketDefaultImagePath() {
		return shareRedPacketDefaultImagePath;
	}

	public String getWxAuthAppKey() {
		return wxAuthAppKey;
	}

	public String getWxAuthRedirectUrl() {
		return wxAuthRedirectUrl;
	}

	public String getWxAuthGetUserInfoUrl() {
		return wxAuthGetUserInfoUrl;
	}

	public String getWxSubScriptionUrl() {
		return wxSubScriptionUrl;
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

	public String getGameMindShareImg() {
		return gameMindShareImg;
	}

	public String getGamePuzzleShareImg() {
		return gamePuzzleShareImg;
	}

	public String getGameDialShareImg() {
		return gameDialShareImg;
	}

	public String getAppMemberUrl() {
		return appMemberUrl;
	}
	
	
}

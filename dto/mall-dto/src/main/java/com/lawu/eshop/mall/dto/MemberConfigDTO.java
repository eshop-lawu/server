package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 会员端API配置DTO
 * @author jiangxinjun
 * @date 2017年9月14日
 */
public class MemberConfigDTO extends ConfigDTO {

    @ApiModelProperty(value = "h5Ip")
    private String memberH5Ip;

    /**
     * E赞广告分享默认图片路径
     */
    @ApiModelProperty(value = "E赞广告分享默认图片路径", required = true)
    private String shareEPraiseAdDefaultImagePath;
    
    /**
     * 平面广告分享默认图片路径
     */
    @ApiModelProperty(value = "平面广告分享默认图片路径", required = true)
    private String shareFlatAdDefaultImagePath;
    
    /**
     * 视频广告分享默认图片路径
     */
    @ApiModelProperty(value = "视频广告分享默认图片路径", required = true)
    private String shareVideoAdDefaultImagePath;
    
    /**
     * 广告分享Logo路径
     */
    @ApiModelProperty(value = "广告分享Logo路径", required = true)
    private String shareAdLogoPath;
    
    /**
     * 红包分享默认图片路径
     */
    @ApiModelProperty(value = "红包分享默认图片路径", required = true)
	private String shareRedPacketDefaultImagePath;
    
    /**
     * 头脑分享默认图片路径
     */
    @ApiModelProperty(value = "头脑分享默认图片路径", required = true)
    private String gameMindShareImg;
    
    /**
     * 拼图分享默认图片路径
     */
    @ApiModelProperty(value = "拼图分享默认图片路径", required = true)
    private String gamePuzzleShareImg;
    
    /**
     * 转盘分享默认图片路径
     */
    @ApiModelProperty(value = "转盘分享默认图片路径", required = true)
    private String gameDialShareImg;
    
    
    @ApiModelProperty(value = "H5路径（瑞奇岛）", required = true)
    private String appMemberUrl;
    
	public String getMemberH5Ip() {
		return memberH5Ip;
	}
	public void setMemberH5Ip(String memberH5Ip) {
		this.memberH5Ip = memberH5Ip;
	}
	public String getShareEPraiseAdDefaultImagePath() {
		return shareEPraiseAdDefaultImagePath;
	}
	public void setShareEPraiseAdDefaultImagePath(String shareEPraiseAdDefaultImagePath) {
		this.shareEPraiseAdDefaultImagePath = shareEPraiseAdDefaultImagePath;
	}
	public String getShareFlatAdDefaultImagePath() {
		return shareFlatAdDefaultImagePath;
	}
	public void setShareFlatAdDefaultImagePath(String shareFlatAdDefaultImagePath) {
		this.shareFlatAdDefaultImagePath = shareFlatAdDefaultImagePath;
	}
	public String getShareVideoAdDefaultImagePath() {
		return shareVideoAdDefaultImagePath;
	}
	public void setShareVideoAdDefaultImagePath(String shareVideoAdDefaultImagePath) {
		this.shareVideoAdDefaultImagePath = shareVideoAdDefaultImagePath;
	}
	public String getShareAdLogoPath() {
		return shareAdLogoPath;
	}
	public void setShareAdLogoPath(String shareAdLogoPath) {
		this.shareAdLogoPath = shareAdLogoPath;
	}
	public String getShareRedPacketDefaultImagePath() {
		return shareRedPacketDefaultImagePath;
	}
	public void setShareRedPacketDefaultImagePath(String shareRedPacketDefaultImagePath) {
		this.shareRedPacketDefaultImagePath = shareRedPacketDefaultImagePath;
	}
	public String getGameMindShareImg() {
		return gameMindShareImg;
	}
	public void setGameMindShareImg(String gameMindShareImg) {
		this.gameMindShareImg = gameMindShareImg;
	}
	public String getGamePuzzleShareImg() {
		return gamePuzzleShareImg;
	}
	public void setGamePuzzleShareImg(String gamePuzzleShareImg) {
		this.gamePuzzleShareImg = gamePuzzleShareImg;
	}
	public String getGameDialShareImg() {
		return gameDialShareImg;
	}
	public void setGameDialShareImg(String gameDialShareImg) {
		this.gameDialShareImg = gameDialShareImg;
	}
	public String getAppMemberUrl() {
		return appMemberUrl;
	}
	public void setAppMemberUrl(String appMemberUrl) {
		this.appMemberUrl = appMemberUrl;
	}
	
    
}

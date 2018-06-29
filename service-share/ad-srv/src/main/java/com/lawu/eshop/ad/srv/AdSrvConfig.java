package com.lawu.eshop.ad.srv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2017/3/13
 */
@Component
public class AdSrvConfig {

    @Value(value = "${image.red_packet_common}")
    private String redPacketCommonMediaUrl;

    @Value(value = "${image.red_packet_luck}")
    private String redPacketLuckMediaUrl;
    
    @Value(value = "${is_cut_praise_point}")
    private Boolean isCutPraisePoint;

    @Value(value = "${inviter_bounty_can_get_count}")
    private int inviterBountyCount;
    
    /** 抢赞分配  **/
    @Value(value = "${praise_min_multiple}")
    private String praiseMinMultiple;
    
    @Value(value = "${praise_max_multiple}")
    private String praiseMaxMultiple;
    
    @Value(value = "${praise_big_amount_min_multiple}")
    private String praiseBigAmountMinMultiple;
    
    @Value(value = "${praise_big_amount_max_multiple}")
    private String praiseBigAmountMaxMultiple;
    
    @Value(value = "${praise_big_amount_min_percent}")
    private String praiseBigAmountMinPercent;
    
    @Value(value = "${praise_big_amount_max_percent}")
    private String praiseBigAmountMaxPercent;
    
    @Value(value = "${praise_big_amount_count}")
    private int praiseBigAmountCount;
    
    /** 商家红包分配  **/
    @Value(value = "${merchant_redpacket_min_multiple}")
    private String merchantRedpacketMinMultiple;
    
    @Value(value = "${merchant_redpacket_max_multiple}")
    private String merchantRedpacketMaxMultiple;
    
    @Value(value = "${merchant_redpacket_big_amount_min_multiple}")
    private String merchantRedpacketBigAmountMinMultiple;
    
    @Value(value = "${merchant_redpacket_big_amount_max_multiple}")
    private String merchantRedpacketBigAmountMaxMultiple;
    
    @Value(value = "${merchant_redpacket_big_amount_min_percent}")
    private String merchantRedpacketAmountMinPercent;
    
    @Value(value = "${merchant_redpacket_big_amount_max_percent}")
    private String merchantRedpacketAmountMaxPercent;
    
    @Value(value = "${merchant_redpacket_big_amount_count}")
    private int merchantRedpacketBigAmountCount;
    
    
    /** 用户红包分配  **/
    @Value(value = "${member_redpacket_min_multiple}")
    private String memberRedpacketMinMultiple;
    
    @Value(value = "${member_redpacket_max_multiple}")
    private String memberRedpacketMaxMultiple;
    
    @Value(value = "${member_redpacket_big_amount_min_multiple}")
    private String memberRedpacketBigAmountMinMultiple;
    
    @Value(value = "${member_redpacket_big_amount_max_multiple}")
    private String memberRedpacketBigAmountMaxMultiple;
    
    @Value(value = "${member_redpacket_big_amount_min_percent}")
    private String memberRedpacketAmountMinPercent;
    
    @Value(value = "${member_redpacket_big_amount_max_percent}")
    private String memberRedpacketAmountMaxPercent;
    
    @Value(value = "${member_redpacket_big_amount_count}")
    private int memberRedpacketBigAmountCount;
    
    
    /**算法使用id分线**/
    @Value(value = "${strategy_praise_id}")
    private Long strategyPraiseId;
    
    @Value(value = "${strategy_merchant_redpacket_id}")
    private Long strategyMerchantRedpacketId;
    
    @Value(value = "${strategy_member_redpacket_id}")
    private Long strategyMemberRedpacketId;
    
    @Value(value = "${ad_platform_shop_choose_tody_count}")
    private Integer adPlatformChooseTodyCount;
    
    @Value(value = "${ad_platform_shop_hot_count}")
    private Integer adPlatformHotCount;
    

	public String getRedPacketCommonMediaUrl() {
		return redPacketCommonMediaUrl;
	}

	public String getRedPacketLuckMediaUrl() {
		return redPacketLuckMediaUrl;
	}


	public Boolean getIsCutPraisePoint() {
		return isCutPraisePoint;
	}

	public int getInviterBountyCount() {
		return inviterBountyCount;
	}

	public String getPraiseMinMultiple() {
		return praiseMinMultiple;
	}

	public String getPraiseMaxMultiple() {
		return praiseMaxMultiple;
	}

	public String getPraiseBigAmountMinMultiple() {
		return praiseBigAmountMinMultiple;
	}

	public String getPraiseBigAmountMaxMultiple() {
		return praiseBigAmountMaxMultiple;
	}

	public String getPraiseBigAmountMaxPercent() {
		return praiseBigAmountMaxPercent;
	}

	public int getPraiseBigAmountCount() {
		return praiseBigAmountCount;
	}

	public String getMerchantRedpacketMinMultiple() {
		return merchantRedpacketMinMultiple;
	}

	public String getMerchantRedpacketMaxMultiple() {
		return merchantRedpacketMaxMultiple;
	}

	public String getMerchantRedpacketBigAmountMinMultiple() {
		return merchantRedpacketBigAmountMinMultiple;
	}

	public String getMerchantRedpacketBigAmountMaxMultiple() {
		return merchantRedpacketBigAmountMaxMultiple;
	}

	public String getMerchantRedpacketAmountMaxPercent() {
		return merchantRedpacketAmountMaxPercent;
	}

	public int getMerchantRedpacketBigAmountCount() {
		return merchantRedpacketBigAmountCount;
	}

	public String getMemberRedpacketMinMultiple() {
		return memberRedpacketMinMultiple;
	}

	public String getMemberRedpacketMaxMultiple() {
		return memberRedpacketMaxMultiple;
	}

	public String getMemberRedpacketBigAmountMinMultiple() {
		return memberRedpacketBigAmountMinMultiple;
	}

	public String getMemberRedpacketBigAmountMaxMultiple() {
		return memberRedpacketBigAmountMaxMultiple;
	}

	public String getMemberRedpacketAmountMaxPercent() {
		return memberRedpacketAmountMaxPercent;
	}

	public int getMemberRedpacketBigAmountCount() {
		return memberRedpacketBigAmountCount;
	}

	public Long getStrategyPraiseId() {
		return strategyPraiseId;
	}

	public Long getStrategyMerchantRedpacketId() {
		return strategyMerchantRedpacketId;
	}

	public Long getStrategyMemberRedpacketId() {
		return strategyMemberRedpacketId;
	}

	public String getPraiseBigAmountMinPercent() {
		return praiseBigAmountMinPercent;
	}

	public String getMerchantRedpacketAmountMinPercent() {
		return merchantRedpacketAmountMinPercent;
	}

	public String getMemberRedpacketAmountMinPercent() {
		return memberRedpacketAmountMinPercent;
	}

	public Integer getAdPlatformChooseTodyCount() {
		return adPlatformChooseTodyCount;
	}

	public Integer getAdPlatformHotCount() {
		return adPlatformHotCount;
	}

	

}

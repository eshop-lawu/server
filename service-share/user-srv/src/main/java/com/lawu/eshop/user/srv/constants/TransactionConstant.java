package com.lawu.eshop.user.srv.constants;

/**
 * 分布式事务业务类型
 *
 * @author Leach
 * @date 2017/3/29
 */
public class TransactionConstant {

    /**
     * 会员注册
     */
    public static final byte MEMBER_REGISTER = 0x01;

    /**
     * 商家注册
     */
    public static final byte MERCHANT_REGISTER = 0x02;

    /**
     * 成为粉丝
     */
    public static final byte MEMBER_FANS = 0x03;

    /**
     * 邀请粉丝退还积分
     */
    public static final byte MERCHANT_FANS = 0x04;

    /**
     * 邀请粉丝
     */
    public static final byte INVITE_FANS = 0x05;

    /**
     * 新年红包注册助力
     */
    public static final byte HELP_PACKET = 0x06;

    /**
     * 新年红包注册助力，修改用户头像刷新助力明细
     */
    public static final byte HELP_PACKET_REFLASH_HEADIMG = 0x07;
    
    /**
     * 新年红包修改微信openId
     */
    public static final byte HELP_PACKET_REFLASH_WX_OPENID = 0x08;
}

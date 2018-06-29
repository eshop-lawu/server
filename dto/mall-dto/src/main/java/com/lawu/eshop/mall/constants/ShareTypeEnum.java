package com.lawu.eshop.mall.constants;

/**
 * 分享消息类型，对应MessageTypeEnum枚举值类型
 *
 * @author meishuquan
 * @date 2018/5/14.
 */
public enum ShareTypeEnum {

    PRODUCT((byte) 0x3A, "商品"),//分享商品58
    AD_FLAT((byte) 0x3B, "猜一猜"),//分享平面广告59
    AD_VIDEO((byte) 0x3C, "看一看"),//分享视频广告60
    AD_PRAISE((byte) 0x3D, "咻一咻"),//分享E咻广告61
    GAME_MIND((byte) 0x3E, "知识王者"),//分享头脑游戏62
    REDPACKET_MEMBER((byte) 0x3F, "用户红包"),//用户分享红包63
    REDPACKET_MERCHANT((byte) 0x40, "商家红包"),//商家分享红包64
    SECKILL((byte) 0x41, "抢购"),//分享抢购65
    GAME_PUZZLE((byte) 0x42, "拼图挑战"),//分享拼图游戏66
    GAME_DIAL((byte) 0x43, "幸运转盘");//分享转盘游戏67
    private Byte val;
    private String name;

    ShareTypeEnum(Byte val, String name) {
        this.val = val;
        this.name = name;
    }

    public static ShareTypeEnum getEnum(Byte val) {
        ShareTypeEnum[] values = ShareTypeEnum.values();
        for (ShareTypeEnum object : values) {
            if (object.val.equals(val)) {
                return object;
            }
        }
        return null;
    }

    public Byte getVal() {
        return val;
    }

    public String getName() {
        return name;
    }
}

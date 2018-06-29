package com.lawu.eshop.user.constants;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年5月2日 下午2:30:22
 *
 */
public enum FansMerchantChannelEnum {
	//(1-注册|2-邀请|3-买单消费|4-订单消费|5-抢红包|6-关注店铺)
    REGISTER((byte) 0x01,"注册"),
    INVITE((byte) 0x02,"邀请"),
    PAY((byte) 0x03,"买单消费"),
    ORDER_PAY((byte) 0x04,"订单消费"),
    REDPACKET((byte) 0x05,"抢红包"),
    FOLLOW((byte) 0x06,"关注");
	
    private Byte value;
    private String name;
    public Byte getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	FansMerchantChannelEnum(Byte value,String name) {
        this.value = value;
		this.name = name;
    }

    public static FansMerchantChannelEnum getEnum(Byte value) {
        FansMerchantChannelEnum[] values = FansMerchantChannelEnum.values();
        for (FansMerchantChannelEnum object : values) {
            if (object.getValue().equals(value)) {
                return object;
            }
        }
        return null;
    }
}

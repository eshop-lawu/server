package com.lawu.eshop.product.constant;


/**
 * 商品参入活动状态
 * 
 * @author zhangrc
 * @date 2017/11/24
 *
 */
public enum SeckillActivityProductEnum {
	
	
	/**
     * 1-未审核
     */
    UNAUDIT((byte) 0x01),

    /**
     * 2-已审核
     */
    AUDIT((byte) 0x02),
    
    /**
     * 3-未通过
     */
    FAIL((byte) 0x03);
    
    
    private Byte value;

    SeckillActivityProductEnum(Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static SeckillActivityProductEnum getEnum(Byte value) {
        for (SeckillActivityProductEnum item : SeckillActivityProductEnum.values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }

}

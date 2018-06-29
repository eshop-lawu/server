package com.lawu.eshop.activity.srv.constants;


/**
 * 分布式事务业务类型
 *
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
public class TransactionConstant {
	
	/**
     * 隐藏默认的构造函数
     */
    private TransactionConstant() {
        throw new IllegalAccessError("Utility class");
    }
	
    /**
     * 积分抽奖
     */
    public static final byte DRAW_LOTTERY = 0x01;
    
    /**
     * 买单
     */
    public static final byte RED_PACKET_SEND = 0x02;

    /**
     * 积分夺宝
     */
    public static final byte POINT_LOTTERY = 0x03;

    /**
     * 领奖--余额/积分
     */
    public static final byte TAKE_LOTTERY = 0x04;

}

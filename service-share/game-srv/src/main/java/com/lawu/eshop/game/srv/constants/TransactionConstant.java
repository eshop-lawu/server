package com.lawu.eshop.game.srv.constants;

/**
 * 补偿事务类型
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
public class TransactionConstant {

    /**
     * 隐藏默认的构造函数
     */
    private TransactionConstant() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 头脑PK参与活动扣除积分
     */
    public static final byte GAME_MIND_DEDUCTION_POINTS = 0x01;
    
	/**
	 * 头脑PK参与活动增加积分
	 */
	public static final byte GAME_MIND_INCREASE_POINTS = 0x02;
	
	/**
	 * 拼图游戏成功增加积分
	 */
	public static final byte PUZZLE_GAME_INCREASE_POINT = 0x03;

	/**
	 * 拼图游戏成功扣除积分
	 */
	public static final byte PUZZLE_GAME_DEDUCTION_POINT = 0x04;

	/**
	 * 转盘游戏扣除积分
	 */
	public static final byte GAME_DIAL_DEDUCTION_POINT = 0x05;

	/**
	 * 转盘游戏领奖
	 */
	public static final byte GAME_DIAL_TAKE_LOTTERY = 0x06;
	
	   /**
     * 头脑PK参与活动退还积分
     */
    public static final byte GAME_MIND_REFUND_POINTS = 0x07;
    
    /**
     * 拼图游戏积分退还
     */
    public static final byte PUZZLE_GAME_REFUND_POINTS=0x08;
	
}

package com.lawu.eshop.property.constants;

public class PropertyType {

	// 提现比例
	public final static String CASH_SCALE = "cash_scale";
	public final static String CASH_SCALE_DEFAULT = "0.95";

	// 自然月提现次数大于1时，需要扣除5月
	public final static String CASH_GREATER_ONE_MINUS_MONEY = "cash_greater_one_minus_money";
	public final static String CASH_GREATER_ONE_MINUS_MONEY_DEFAULT = "5";

	// 提现最小金额
	public final static String CASH_MIN_MONEY = "cash_min_money";
	public final static String CASH_MIN_MONEY_DEFAULT = "10";

	// 用户余额冲积分比例
	public final static String MEMBER_BALANCE_PAY_POINT_SCALE = "m_balance_pay_point";

	// 商家余额冲积分比例
	public final static String MERCHANT_BALANCE_PAY_POINT_SCALE = "b_balance_pay_point";

	// 保证金
	public final static String MERCHANT_BONT = "merchant_bont";
	public final static String MERCHANT_BONT_DEFAULT = "1000";

	// 商家第三方充值积分比例
	public final static String MERCHANT_THIRD_PAY_POINT = "b_third_pay_point";

	// 用户第三方充值积分比例
	public final static String MEMBER_THIRD_PAY_POINT = "m_third_pay_point";
	public final static String MEMBER_THIRD_PAY_POINT_DEFAULT = "10";

	// 确认收货后订单进入计入商家冻结资金，资金冻结天数
	public final static String PRODUCT_ORDER_MONEY_FREEZE_DAYS = "product_order_money_freeze_days";
	public final static String PRODUCT_ORDER_MONEY_FREEZE_DAYS_DEFAULT = "7";

	// 商家退款保证金至少存入系统天数后，才运行商家申请退
	public final static String DEPOSIT_REFUND_DIFF_DAYS = "deposit_refund_diff_days";
	public final static String DEPOSIT_REFUND_DIFF_DAYS_DEFAULT = "90";
	
	// 广告提成比例
	public final static String ad_commission_ = "ad_commission_";
	public final static String ad_commission_0 = "ad_commission_0";
	public final static String ad_commission_1 = "ad_commission_1";
	public final static String ad_commission_2 = "ad_commission_2";
	public final static String ad_commission_3 = "ad_commission_3";
	public final static String ad_commission_0_default = "0.5";
	public final static String ad_commission_1_default = "0.3";
	public final static String ad_commission_2_default = "0.2";
	public final static String ad_commission_3_default = "0.1";
	// 爱心账户比例
	public final static String love_account_scale = "love_account_scale";
	public final static String love_account_scale_default = "0.003";
	
	//销售和营业额提成比例
	public final static String sale_commission_ = "sale_commission_";
	public final static String sale_commission_0 = "sale_commission_0";
	public final static String sale_commission_1 = "sale_commission_1";
	public final static String sale_commission_2 = "sale_commission_2";
	public final static String sale_commission_3 = "sale_commission_3";
	public final static String sale_commission_0_default = "1";
	public final static String sale_commission_1_default = "0.004";
	public final static String sale_commission_2_default = "0.003";
	public final static String sale_commission_3_default = "0.001";
	public final static String sale_commission_add_scope = "sale_commission_add_scope";
	public final static String sale_commission_add_scope_default = "0.0005";

	//会员成为商家粉丝获得积分比例
	public final static String MEMBER_FANS_POINT = "member_fans_point";
	
}

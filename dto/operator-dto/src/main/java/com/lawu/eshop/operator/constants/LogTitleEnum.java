package com.lawu.eshop.operator.constants;

/**
 * @author meishuquan
 * @date 2017/5/3.
 */
public enum LogTitleEnum {
	
	//广告模块
    AD_DOWN(ModuleEnum.AD, "广告下架"),
    AD_DELETE(ModuleEnum.AD, "广告删除"),
    AD_VIDEO_AUDIT_SUCCESS(ModuleEnum.AD, "视频广告审核通过"),
    AD_VIDEO_AUDIT_FAIL(ModuleEnum.AD, "视频广告审核不通过"),
    AD_PLATE_FORM_ADD(ModuleEnum.AD, "广告位添加"),
    AD_PLATE_FORM_DOWN(ModuleEnum.AD, "广告位下架"),
    AD_PLATE_FORM_UP(ModuleEnum.AD, "广告位上架"),
    AD_PLATE_FORM_UPDATE(ModuleEnum.AD, "广告位修改"),
    AD_PLATE_FORM_DELETE(ModuleEnum.AD, "广告位删除"),
    AD_PLATE_PRIORITY_UPDATE(ModuleEnum.AD, "广告位排序修改"),

    //商品模块
    PRODUCT_DOWN(ModuleEnum.PRODUCT, "商品下架"),
    PRODUCT_DELETE(ModuleEnum.PRODUCT, "商品删除"),
    PRODUCT_CATEGORY_ADD(ModuleEnum.PRODUCT, "新增商品类别"),
    PRODUCT_CATEGORY_UPDATE(ModuleEnum.PRODUCT, "修改商品类别"),
    ACTIVITY_ADD(ModuleEnum.ACTIVITY, "新增抢购活动"),
    ACTIVITY_DOWN(ModuleEnum.ACTIVITY, "下架抢购活动"),
    ACTIVITY_PUBLISG(ModuleEnum.ACTIVITY, "发布抢购活动"),
    ACTIVITY_DELETE(ModuleEnum.ACTIVITY, "删除抢购活动"),
    ACTIVITY_UPDATE(ModuleEnum.ACTIVITY, "更新抢购活动"),
    ACTIVITY_AUTH(ModuleEnum.ACTIVITY, "审核抢购活动"),
    ACTIVITY_PRODUCT_NOT_PASS(ModuleEnum.ACTIVITY, "抢购活动商品不通过"),
    ACTIVITY_PRODUCT_PASS(ModuleEnum.ACTIVITY, "抢购活动商品通过"),
    ACTIVITY_PRODUCT_UPDATE(ModuleEnum.ACTIVITY, "更新抢购活动商品"),

    //订单模块
    ORDER_PENDING(ModuleEnum.ORDER, "待处理"),
    ORDER_PENDING_PAYMENT(ModuleEnum.ORDER, "待付款"),
    ORDER_BE_SHIPPED(ModuleEnum.ORDER, "待发货"),
    ORDER_TO_BE_RECEIVED(ModuleEnum.ORDER, "待收货"),
    ORDER_TRADING_SUCCESS(ModuleEnum.ORDER, "交易成功"),
    ORDER_CANCEL_TRANSACTION(ModuleEnum.ORDER, "交易关闭"),
    ORDER_REFUNDING(ModuleEnum.ORDER, "退款中"),
    ORDER_REFUNDING_AGREE(ModuleEnum.ORDER, "退款给买家"),
    ORDER_REFUNDING_CANCEL(ModuleEnum.ORDER, "买家撤销退款"),
    ORDER_INFO_UPDATE(ModuleEnum.ORDER, "更新订单信息"),
    ORDER_CANCLE(ModuleEnum.ORDER, "取消购物订单"),

    //门店模块
    STORE_AUDIT_SUCCESS(ModuleEnum.STORE, "门店审核通过|不通过"),
    STORE_AUDIT_FAIL(ModuleEnum.STORE, "门店审核不通过"),
    ACCOUNT_FREEZE(ModuleEnum.STORE, "冻结|解冻账号"),
    FORCED_EXIT(ModuleEnum.STORE, "强制退出账号"),
    ACCOUNT_NOT_DEAL(ModuleEnum.STORE, "异常账号不处理"),
	
    //资产模块
    INVITER_BOUNTY_ABLE(ModuleEnum.PROPERTY, "奖励金启用"),
    INVITER_BOUNTY_DISABLE(ModuleEnum.PROPERTY, "奖励金禁用"),
    PROPERTY_INFO_FREEZE_UPDATE(ModuleEnum.PROPERTY, "资金冻结/解冻"),
    BUSINESS_DEPOSIT_UPDATE(ModuleEnum.PROPERTY, "保准金处理"),
    POINT_UPDATE(ModuleEnum.PROPERTY, "积分充值"),
    BALANCE_UPDATE(ModuleEnum.PROPERTY, "余额充值"),
    WITHDRAW_CASH_UPDATE(ModuleEnum.PROPERTY, "提现操作"),
    PLAT_REDPACKET_ADD(ModuleEnum.PROPERTY, "新增平台红包"),
    PLAT_REDPACKET_DISABLE(ModuleEnum.PROPERTY, "禁用平台红包"),
    THIED_PAY_REFUND(ModuleEnum.PROPERTY, "第三方退款"),
    
    //活动模块
    LOTTER_ACTIVITY_ADD(ModuleEnum.ACTIVITY, "新增抽奖活动"),
    LOTTER_ACTIVITY_DOWN(ModuleEnum.ACTIVITY, "下架抽奖活动"),
    LOTTER_ACTIVITY_PUBLISG(ModuleEnum.ACTIVITY, "发布抽奖活动"),
    LOTTER_ACTIVITY_DRAW(ModuleEnum.ACTIVITY, "抽奖活动开奖"),
	LOTTER_ACTIVITY_DETELE(ModuleEnum.ACTIVITY, "删除抽奖活动"),
	LOTTER_ACTIVITY_PRIZE_DETELE(ModuleEnum.ACTIVITY, "删除奖品"),
	LOTTER_ACTIVITY_PRIZE_ADD(ModuleEnum.ACTIVITY, "新增奖品"),
    LOTTER_ACTIVITY_PRIZE_SEND(ModuleEnum.ACTIVITY, "发放奖品"),
    LOTTER_ACTIVITY_GENERATE_BASIC_NUMBER(ModuleEnum.ACTIVITY, "生成基础号码"),
    LOTTER_ACTIVITY_SAVE_WINNING_NUMBER(ModuleEnum.ACTIVITY, "保存中奖号码"),
	REDPACKET_ACTIVITY_ADD(ModuleEnum.ACTIVITY, "新增红包活动"),
	REDPACKET_ACTIVITY_UPDATE(ModuleEnum.ACTIVITY, "更新红包活动"),
	REDPACKET_ACTIVITY_GENERATE_LARGE_REDPACKET(ModuleEnum.ACTIVITY, "生成大额红包"),
	REDPACKET_ACTIVITY_SAVE_LARGE_REDPACKET(ModuleEnum.ACTIVITY, "保存大额红包"),
	REDPACKET_ACTIVITY_GENERATE_NORMAL_REDPACKET(ModuleEnum.ACTIVITY, "生成普通红包"),
	REDPACKET_ACTIVITY_AGAIN_GENERATE_NORMAL_REDPACKET(ModuleEnum.ACTIVITY, "重新生成普通红包"),
	REDPACKET_ACTIVITY_CONTINUE_GENERATE_NORMAL_REDPACKET(ModuleEnum.ACTIVITY, "继续生成普通红包"),
	RICH_CONFIG_ADD(ModuleEnum.ACTIVITY, "动力任务配置新增"),
    DIAMOND_CONFIG_ADD(ModuleEnum.ACTIVITY, "E钻配置新增"),

    //商城模块
    WINDOW_MESSAGE_ADD(ModuleEnum.MALL, "新增弹窗消息"),
    WINDOW_MESSAGE_UPDATE(ModuleEnum.MALL, "更新弹窗消息"),

    //游戏模块
    GAME_PUZZLE_ADD(ModuleEnum.GAME, "新增游戏拼图"),
    GAME_PUZZLE_ENABLE(ModuleEnum.GAME, "启用游戏拼图"),
    GAME_PUZZLE_DISABLE(ModuleEnum.GAME, "禁用游戏拼图"),
    GAME_PUZZLE_USER_AUDIT(ModuleEnum.GAME, "审核用户供图"),
    GAME_USER_STAR_ADD(ModuleEnum.GAME, "设置排行榜星星数量"),
    GAME_USER_STAR_HIDDEN(ModuleEnum.GAME, "屏蔽星星排行榜"),

	PERMSION_ADD(ModuleEnum.OPERATOR, "新增权限"),
	PERMSION_DELETE(ModuleEnum.OPERATOR, "删除权限"),
	PERMSION_UPDATE(ModuleEnum.OPERATOR, "修改权限"),
	PROPERTY_ADD(ModuleEnum.OPERATOR, "新增系统参数"),
	PROPERTY_DELETE(ModuleEnum.OPERATOR, "删除权限"),
	PROPERTY_UPDATE(ModuleEnum.OPERATOR, "修改权限"),
	USER_GRADE_ADD(ModuleEnum.OPERATOR, "新增会员等级"),
	USER_GRADE_UPDATE(ModuleEnum.OPERATOR, "修改会员等级"),
	APP_PATCH_VERSION_ADD(ModuleEnum.OPERATOR, "新增app补丁版本"),
	APP_PATCH_VERSION_ABLE(ModuleEnum.OPERATOR, "启用app补丁版本"),
	
	
	GAME_MIND_CONFIG_ADD(ModuleEnum.GAME, "新增头脑PK游戏"),
	GAME_MIND_CONFIG_UPDATE(ModuleEnum.GAME, "修改头脑PK游戏"),
	GAME_MIND_CONFIG_ABLE(ModuleEnum.GAME, "禁用|启用头脑PK游戏"),
	GAME_PUZZLE_CONFIG_ADD(ModuleEnum.GAME, "新增拼图游戏"),
	GAME_PUZZLE_CONFIG_UPDATE(ModuleEnum.GAME, "修改拼图游戏"),
	GAME_PUZZLE_CONFIG_ABLE(ModuleEnum.GAME, "禁用|启用拼图游戏"),
	GAME_MIND_QUESTION_ADD(ModuleEnum.GAME, "添加题目"),
	GAME_MIND_QUESTION_DISABLE(ModuleEnum.GAME, "禁用题目"),
	GAME_MIND_QUESTION_CACHE(ModuleEnum.GAME, "更新题库缓存"),
	GAME_INFORM_DEAL_WITH(ModuleEnum.GAME, "举报处理"),
	GAME_DIAL_ADD(ModuleEnum.GAME, "新增转盘游戏"),
	GAME_DIAL_UPDATE(ModuleEnum.GAME, "修改转盘游戏"),
	GAME_DIAL_PRAIZE_UPDATE(ModuleEnum.GAME, "修改转盘游戏奖品"),
	GAME_DIAL_PRAIZE_ADD(ModuleEnum.GAME, "新增转盘游戏奖品"),
	GAME_DIAL_PRAIZE_DEL(ModuleEnum.GAME, "删除转盘游戏奖品"),
	GAME_DIAL_PRAIZE_SEND(ModuleEnum.GAME, "发送奖品"),
	ADD_SYS_MAINTAIN_CONFIG(ModuleEnum.OPERATOR, "添加系统维护配置");

    private ModuleEnum model;
    private String name;

    public ModuleEnum getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    LogTitleEnum(ModuleEnum model, String name) {
        this.model = model;
        this.name = name;
    }
}

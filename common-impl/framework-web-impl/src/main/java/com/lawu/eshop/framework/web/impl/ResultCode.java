package com.lawu.eshop.framework.web.impl;

import com.lawu.framework.web.BaseResultCode;

/**
 * 返回码
 *
 * @author Leach
 * @date 2017/3/13
 */
public class ResultCode extends BaseResultCode {
    
    // 公共代码
    public static final int NOT_FOUND_DATA = 1100;
    public static final int RESOURCE_NOT_FOUND = 1002;
    public static final int ID_EMPTY = 1003;
    public static final int REQUIRED_PARM_EMPTY = 1004;
    public static final int SAVE_FAIL = 1005;
    public static final int SMS_SEND_HOUR_LIMIT = 1006;
    public static final int SMS_SEND_IP_LIMIT = 1007;
    public static final int SMS_SEND_MOBILE_LIMIT = 1008;
    public static final int VERIFY_PWD_FAIL = 1009;
    public static final int IMAGE_WRONG_UPLOAD = 1010;
    public static final int IMAGE_FORMAT_WRONG_UPLOAD = 1011;
    public static final int RECORD_EXIST = 1012;
    public static final int VERIFY_SMS_CODE_FAIL = 1013;
    public static final int VERIFY_PIC_CODE_FAIL = 1014;
    public static final int IMAGE_SIZE_ERROR = 1015;
    public static final int NOT_SEND_SMS_MOBILE = 1016;
    public static final int IMAGE_IS_NULL = 1017;
    public static final int MONEY_IS_POINT_2 = 1018;
    public static final int UPDATE_FAIL = 1019;
    public static final int UPLOAD_VEDIO_FAIL = 1020;
    public static final int UPLOAD_SIZE_BIGER = 1021;
    public static final int PAY_PWD_NULL = 1022;
    public static final int PAY_PWD_ERROR = 1023;
    public static final int ILLEGAL_OPERATION = 1024;
    public static final int VERIFY_SMS_CODE_OVERTIME = 1025;
    public static final int INVITER_NO_EXIST = 1026;
    public static final int ACCOUNT_EXIST = 1027;
    public static final int SMS_SEND_FAIL = 1028;
    

    public static final int ID_CARD_RECORD_EXIST = 1029;
    public static final int REG_NUMBER_RECORD_EXIST = 1030;
    public static final int QUEUE_FAILED = 1033;

    //FastDFS上传图片异常
    public static final int FD_FILE_ERROR =1031;
    public static final int FD_FILE_IMG_BIG =1032;
    public static final int FD_FILE_CUT_ERROR=1034;

    //邀请粉丝异常
    public static final int INVITE_FANS_NOT_EXIST=1035;
    public static final int NOT_PATCH_VERSION = 1036;

    public static final int WRONG_OPERATION = 1037;
    public static final int WRONG_PASSWORD = 1038;
    public static final int FD_FILE_VIDEO_CODE_ERROR = 1039;

    //阿里短信限制
    public static final int ONE_MINUTE_TIME = 1040;
    public static final int ONE_HOUR_TIME = 1041;
    public static final int ONE_DAY_TIME = 1042;
    public static final int MOBILE_NUMBER_ILLEGAL = 1043;
    public static final int WX_AUTH_FAIL = 1044;
    public static final int SMS_SEND_MOBILE_LIMIT_MINUTE = 1045;
    public static final int REPEAT_OPERATE = 1046;

    //图形验证码限制
    public static final int SMS_INTERFACE_UPDATE = 1047;
    public static final int PIC_CODE_VERIFY = 1048;
    public static final int PIC_CODE_OVERTIME = 1049;

    // 用户模块代码 2xxx
    public static final int MEMBER_WRONG_PWD = 2000;
    public static final int USER_WRONG_ID = 2001;
    public static final int USER_WRONG_IDCARD = 2002;
    public static final int IMAGE_WRONG_UPLOAD_STORE = 2003;
    public static final int IMAGE_WRONG_UPLOAD_STORE_ENVIRONMENT = 2004;
    public static final int IMAGE_WRONG_UPLOAD_LICENSE = 2005;
    public static final int IMAGE_WRONG_UPLOAD_IDCARD = 2006;
    public static final int USER_POINT_NOT_ENOUGH = 2007;
    public static final int MERCHANT_STORE_AUDIT_EXIST = 2008;
    public static final int MERCHANT_STORE_NO_EXIST = 2009;
    public static final int MEMBER_NO_EXIST = 2010;
    public static final int BANK_CASH_EXIST = 2011;
    public static final int FANS_MERCHANT = 2012;
    public static final int MOBILE_IS_NOT_EXIST = 2013;
    public static final int MERCHANT_STORE_IS_FAVORITE = 2014;
    public static final int ACCOUNT_IS_FREEZE = 2015;
    public static final int MAX_USERREDPACKET_COUNT=2016;
    public static final int MAX_USERREDPACKET_MONTY=2017;
    public static final int MIN_USERREDPACKET_MONTY=2018;
    public static final int ACCOUNT_IS_INVALID = 2019;
    public static final int NO_LOTTERY_COUNT = 2020;
    public static final int HAVE_TAKE_PART = 2021;
    public static final int GRADE_NOT_MATCH = 2022;
    public static final int NOT_LOTTERYING = 2023;
    public static final int WEI_XIN_IS_NOT_ATTEND = 2024;
    public static final int WEI_XIN_LOGIN_IS_NOT_BIND = 2025;
    public static final int QQ_LOGIN_IS_NOT_BIND = 2026;
    public static final int FANS_NOT_MERCHANT = 2027;


    // 商品模块代码 3xxx
    public static final int IMAGE_WRONG_UPLOAD_PRODUCT_HEAD = 3000;
    public static final int IMAGE_WRONG_UPLOAD_PRODUCT_DETAIL = 3001;
    public static final int GOODS_PRODUCT_FACORITE_EXIST = 3003;
    public static final int GOODS_PRODUCT_INVENTORY = 3004;
    public static final int GOODS_PRODUCT_EXIST_ADFLAT = 3005;
    public static final int SECKILL_ACTIVITY_COUNT_OVER = 3006;
    public static final int SECKILL_ACTIVITY_TIME_OVER = 3007;
    public static final int SECKILL_ACTIVITY_JOIN_STATUS = 3008;
    public static final int SECKILL_ACTIVITY_PRODUCT_STATUS = 3009;
    public static final int SECKILL_ACTIVITY_PRODUCT_EXISTS = 3010;
    public static final int PRODUCT_MODEL_SPEC_NOT_EMPTY = 3011;
    public static final int PRODUCT_TITLE_IMAGE_NOT_EMPTY = 3012;
    public static final int PRODUCT_MODEL_SPEC_MISMATCH = 3013;
    public static final int PRODUCT_MODEL_SPEC_OUT_SIZE = 3014;
    public static final int PRODUCT_MODEL_PRICE_STOCK_INVALID = 3015;

    // 订单模块代码 4xxx
    public static final int PRODUCT_EVALUATE_TRUE = 4001;
    public static final int ORDER_NOT_CANCELED = 4002;
    public static final int ORDER_NOT_DELETE = 4003;
    public static final int ORDER_NOT_PENDING_PAYMENT = 4004;
    public static final int ORDER_NOT_RECEIVED = 4005;
    public static final int ORDER_NOT_REFUND = 4006;
    public static final int EXCEEDS_RETURN_TIME = 4007;
    public static final int NOT_SHIPPING_STATUS = 4008;
    public static final int NOT_RETURNED_STATE = 4009;
    public static final int NOT_AGREE_TO_APPLY = 4010;
    public static final int NOT_REFUNDING = 4011;
    public static final int ORDER_NOT_FILL_RETURN_ADDRESS = 4012;
    public static final int ORDER_NOT_TO_BE_REFUNDED = 4013;
    public static final int ORDER_NOT_REFUND_FAILED = 4014;
    public static final int ORDER_NOT_COMPLETE_STATUS = 4015;
    public static final int PAY_ORDER_FAVORED_AMOUNT_UNEQUAL = 4016;
    public static final int ORDER_HAS_BEEN_REFUNDED = 4017;
    public static final int INVENTORY_SHORTAGE = 4018;
    public static final int PRODUCT_HAS_EXPIRED = 4019;
    public static final int THE_ORDER_IS_BEING_PROCESSED = 4020;
    public static final int ORDER_CREATION_FAILED = 4021;
    public static final int MAX_SHOPPING_CART_QUANTITY = 4022;
    public static final int CAN_NOT_FILL_OUT_THE_RETURN_LOGISTICS = 4023;
    public static final int CAN_NOT_APPLY_FOR_PLATFORM_INTERVENTION = 4024;
    public static final int CAN_NOT_CANCEL_APPLICATION = 4025;
    public static final int CAN_NOT_FILL_IN_SHIPPING_LOGISTICS = 4026;
    public static final int CAN_NOT_AGREE_TO_APPLY = 4027;
    public static final int CAN_NOT_AGREE_TO_A_REFUND = 4028;
    public static final int CAN_NOT_FILL_IN_THE_RETURN_ADDRESS = 4029;
    public static final int THIRD_PARTY_LOGISTICS_INTERFACE_EXCEPTION = 4030;

    // 广告模块代码 5xxx
    public static final int AD_POINT_NOT_ENOUGH = 5000;
    public static final int AD_PUT_NOT_TIME = 5001;
    public static final int AD_FACORITE_EXIST = 5002;
    public static final int AD_RED_PACKGE_EXIST = 5003;
    public static final int AD_RED_PACKGE_GET = 5004;
    public static final int AD_PRAISE_PUTED = 5005;
    public static final int AD_CLICK_EXIST = 5006;
    public static final int AD_PRAISE_POINT_GET = 5007;
    public static final int AD_CLICK_PUTED = 5008;
    public static final int AD_RED_PACKGE_PUTED=5009;
    public static final int AD_BEGIN_TIME_NOT_EXIST=5010;
    public static final int AD_RED_PACKET_COUNT_ERROR=5011;
    public static final int AD_RED_PACKET_POINT_ERROR=5012;
    public static final int AD_CLICK_SYS_WORDS=5013;
    public static final int AD_PRAISE_NOT_RATE=5014;
    public static final int AD_PLATFORM_LESS_COUNT=5015;


    // 资产模块代码 6xxx
    public static final int BANK_ACCOUNT_ERROR = 6000;
    public static final int CASH_MORE_NUM_MAX_MONEY_ERROR = 6001;
    public static final int PROPERTY_INFO_NULL = 6002;
    public static final int PROPERTY_INFO_OUT_INDEX = 6003;
    public static final int PROPERTY_INFO_BALANCE_LESS = 6004;
    public static final int PROPERTY_CASH_SCALE_NULL = 6005;
    public static final int PROPERTY_CASH_PAY_PWD_ERROR = 6006;
    public static final int PROPERTY_CASH_BANK_NOT_EXIST = 6007;
    public static final int PROPERTY_CASH_BANK_NOT_MATCH = 6008;
    public static final int PROPERTY_CASH_USER_INFO_NULL = 6009;
    public static final int PROPERTY_INFO_POINT_LESS = 6010;
    public static final int BIZ_TYPE_NULL = 6011;

    public static final int CASH_BACKAGE_FAILURE_REASON_NULL = 6012;
    public static final int FREEZE_NULL = 6013;
//    public static final int FREEZE_ROWS_OUT = 6014;
    public static final int FREEZE_MONEY_LESS_REFUND_MONEY = 6015;
    public static final int DEPOSIT_IN_SYSTEM_DAYS = 6016;
    public static final int DEPOSIT_EXIST_ING_ORDER = 6017;
    public static final int MONEY_IS_ZERO = 6018;
    public static final int PAY_ORDER_NULL = 6019;
    public static final int PAY_ORDER_IS_SUCCESS = 6020;
    public static final int BANK_ACCOUNT_IS_EXIST = 6021;
    public static final int STORE_REGION_PATH_ERROR = 6022;
    public static final int NOTIFY_MONEY_ERROR = 6023;
    public static final int PROPERTYINFO_FREEZE_YES = 6024;
    public static final int PROCESSED_RETURN_SUCCESS = 6025;
    public static final int PROPERTYINFO_FREEZE_EXCEPITON = 6026;
    public static final int DEPOSIT_EXIST_UP_PRODUCT = 6027;
    public static final int BANK_ACCOUNT_LENTH_OUT_OF_RANGE = 6028;
    public static final int ERROR_BALANCE_NEGATIVE = 6029;
    public static final int ERROR_POINT_NEGATIVE = 6030;
    public static final int ERROR_SAVE_POINT_DETAIL = 6031;
    public static final int PROPERTYINFO_FREEZE_YES_WORD = 6032;
    public static final int BANK_ACCOUNT_THIRD_IS_EXIST = 6033;
    public static final int BANK_ACCOUNT_THIRD_TYPE_IS_EXIST = 6034;
    public static final int BANK_ACCOUNT_THIRD_ACCOUNT_FORMAT = 6035;
    public static final int CASH_MAX = 6036;
    public static final int BANK_ACCOUNT_THIRD_TYPE_WX_IS_EXIST = 6037;
    public static final int CASH_MAX_WX = 6038;







    // 商城模块代码 7xxx
    public static final int MESSAGE_HAS_NO_TEMPLATE = 7001;
    public static final int COMMENT_REPEAT_REPLY = 7002;

    //运营 8xxx
    public static final int USER_NOT_LOGIN = 8100;
    public static final int USER_ROLE_EXIST = 8101;
    public static final int ROLE_HAS_USER_RELATE = 8102;
    public static final int ROLE_HAS_PERMISSION = 8103;
    public static final int PUSH_HAS_NOUSER = 8104;
    public static final int ROLE_HAS_NOPERMISSION = 8105;
    public static final int USER_ACCOUNT_DISABLE = 8106;
    public static final int USER_ACCOUNT_EXIST = 8107;

    public static final int STORE_AUDIT_RECORD_NOT_EXIST = 8108;

    public static final int STORE_AUDIT_RECORD_AUDITED = 8109;
    public static final int AD_AUDITED = 8110;

    public static final int AGENT_ACCOUNT_EXIST = 8111;
    public static final int AGENT_MOBILE_EXIST = 8112;
    public static final int USER_UNAUTHORIZED = 8113;
    public static final int EXISTS_ENABLE_APP_VERSION= 8114;
    public static final int GET_HEADER_ERROR= 8115;
    public static final int GRADE_WEIGHT_REPEAT= 8116;
    public static final int GRADE_LOTTERY_ACTIVITY_POINT_LESSTHAN_ERROR= 8117;
    public static final int GRADE_MIN_GROWTH_VALUE_LESSTHAN_ERROR= 8118;
    public static final int GRADE_LOTTERY_ACTIVITY_POINT_GRETERTHAN_ERROR= 8119;
    public static final int GRADE_MIN_GROWTH_VALUE_GRETERTHAN_ERROR= 8120;


    //新年红包
    public static final int HELP_MEMBER_IS_EMPTY = 9000;
    public static final int HELP_REG_END = 9001;
    public static final int HELP_ONLY_ONE_TIME = 9002;
    public static final int HELP_ACTIVITY_STATUS_NOT_REGISTING= 9003;
    public static final int HELP_ACTIVITY_STATUS_ATTEND= 9004;
    public static final int WEIXIN_OPENDID_NOT_BIND = 9005;
    public static final int HELP_ACTIVITY_STATUS_NOT_START = 9006;
    public static final int HELP_RED_PACKET_GET = 9007;
    public static final int HELP_SELF_ERROR = 9008;
    public static final int HELP_REG_OUT_TIME = 9009;
    public static final int HELP_ACTIVITY_STATUS_END = 9010;
    public static final int HELP_ACTIVITY_GET_ERROR = 9011;


    public static final int TAKE_PART_LOTTERY_ERROR = 9100;
    public static final int LOTTERY_NOT_ING = 9101;
    public static final int FREE_LOTTERY_COUNT_OVER = 9102;
    public static final int LOTTERY_PRIZE_ERROR = 9103;
    public static final int LOTTERY_PRIZE_INVENTORY_SHORTAGE = 9104;
    public static final int LOTTERY_ACTIVITY_FINISHED = 9105;
    public static final int LOTTERY_RECORD_HANDLE = 9106;
    public static final int LOTTERY_RECORD_USED = 9107;
    public static final int LOTTERY_RECORD_HAVE_DEAL = 9108;
    
    // 游戏模块代码 10xxx
    public static final int GAME_INFORM_ALREADY = 10000;

    public static final int GAME_MIND_QUESTION_ERROR = 10001;
    public static final int GAME_MIND_ATTEND_FAIL = 10002;

    public static final int GAME_MATCH_LOADING=10003;
    public static final int GAME_PUZZLE_DEDUCTION_POINT_FAIL=10004;
    public static final int GAME_MIND_ANSWER_QUESTION_ERROR = 10005;
    public static final int GAME_ROOM_POINT_ERROR = 10006;
    public static final int GAME_ROOM_JOIN_FAIL = 10007;
    public static final int GAME_ROOM_EXIT_FAIL = 10008;
    public static final int GAME_ROOM_PLAYER_READY_FAIL = 10009;
    public static final int GAME_ROOM_PWD_ERROR = 10010;
    public static final int GAME_DIAL_DISABLED = 10011;
    public static final int GAME_DIAL_NOT_LOTTERY = 10012;
    public static final int GAME_DIAL_PRIZE_COUNT_OVER = 10013;
    public static final int GAME_SHAREL_IS_GET = 10014;
    public static final int GAME_SETTING_DISABLE = 10015;
    public static final int GAME_ROOM_FULL = 10016;
    public static final int GAME_ROOM_CREATED = 10017;
    public static final int GAME_ROOM_FORBID_JOIN = 10018;

    // member-ws模块代码 11xxx
    public static final int GAME_WS_USER_ONLINE = 11000;
    public static final int GAME_WS_USER_READY = 11001;
    public static final int GAME_WS_USER_START = 11002;
    public static final int GAME_WS_USER_CHECK_NOTDONE = 11003;
    public static final int GAME_WS_USER_CHECK_DONE = 11004;
    public static final int GAME_WS_USER_SUBMIT_SINGLE = 11005;
    public static final int GAME_WS_USER_SUBMIT_MULTI = 11006;
    public static final int GAME_WS_USER_SUBMIT_DONE = 11007;
    public static final int GAME_WS_USER_OFFLINE = 11008;
    public static final int GAME_WS_USER_SUBMIT_OTHER = 11009;
    public static final int GAME_WS_REJECT_FAIL = 11011;
    public static final int GAME_WS_ABANDON_FAIL = 11012;
    public static final int GAME_WS_USER_PUZZLE_SUCCESS = 11013;
    public static final int GAME_WS_USER_ONLINE_ERROR = 11014;
    public static final int GAME_ATTEND_SUCCESS = 11015;
    public static final int GAME_ATTEND_FAIL = 11016;
    public static final int GAME_ATTEND_REPEAT = 11017;
    
    public static final int GAME_WS_HEARTBEAT = 11100;

    
    // 初始化状态码与文字说明
    static {

        // 公共代码 1xxx
        messageMap.put(NOT_FOUND_DATA, "数据不存在");
        messageMap.put(RESOURCE_NOT_FOUND, "ID对应数据不存在");
        messageMap.put(ID_EMPTY, "ID不能为空");
        messageMap.put(REQUIRED_PARM_EMPTY, "非法参数");
        messageMap.put(SAVE_FAIL, "保存失败");
        messageMap.put(UPDATE_FAIL, "更新失败");
        messageMap.put(IMAGE_WRONG_UPLOAD, "上传图片失败");
        messageMap.put(IMAGE_FORMAT_WRONG_UPLOAD, "上传图片格式错误");
        messageMap.put(RECORD_EXIST, "该记录已经存在");
        messageMap.put(IMAGE_SIZE_ERROR, "上传图片应小于500K");
        messageMap.put(NOT_SEND_SMS_MOBILE, "手机号与验证码不匹配");
        messageMap.put(IMAGE_IS_NULL, "图片格式不正确");
        messageMap.put(MONEY_IS_POINT_2, "金额请保留两位小数");

        messageMap.put(UPLOAD_VEDIO_FAIL, "上传视频失败");
        messageMap.put(UPLOAD_SIZE_BIGER, "上传文件应小于50M");
        messageMap.put(INVITE_FANS_NOT_EXIST, "可邀请的粉丝不存在");
        messageMap.put(NOT_PATCH_VERSION, "没有可更新的补丁版本");

        messageMap.put(SMS_SEND_HOUR_LIMIT, "同一手机号1小时内只能发送5次");
        messageMap.put(SMS_SEND_IP_LIMIT, "同一设备24小时内只能发送5次");
        messageMap.put(SMS_SEND_MOBILE_LIMIT, "同一手机号24小时内只能发送5次");
        messageMap.put(VERIFY_PWD_FAIL, "原始密码错误，请重新输入");
        messageMap.put(VERIFY_SMS_CODE_FAIL, "短信验证码错误");
        messageMap.put(VERIFY_PIC_CODE_FAIL, "图形验证码错误");
        messageMap.put(PAY_PWD_NULL, "尚未设置支付密码");
        messageMap.put(PAY_PWD_ERROR, "支付密码错误");
        messageMap.put(ILLEGAL_OPERATION, "非法操作");
        messageMap.put(VERIFY_SMS_CODE_OVERTIME, "短信验证码已失效");
        messageMap.put(INVITER_NO_EXIST, "邀请人不存在，请重新输入");
        messageMap.put(ACCOUNT_EXIST, "该账号已存在");
        messageMap.put(SMS_SEND_FAIL, "短信验证码发送失败，请稍后重试！");
        messageMap.put(SMS_SEND_MOBILE_LIMIT_MINUTE, "操作过于频繁，请稍后重试！");

        messageMap.put(ONE_MINUTE_TIME, "操作过于频繁，请60秒重试！");
        messageMap.put(ONE_HOUR_TIME, "操作过于频繁，请1小时后重试！");
        messageMap.put(ONE_DAY_TIME, "今天获取验证信息已超过上限，请明天重试！");
        messageMap.put(MOBILE_NUMBER_ILLEGAL, "非法手机号码，请重新输入！");

        messageMap.put(WX_AUTH_FAIL, "授权失败，请重新授权");

        messageMap.put(ID_CARD_RECORD_EXIST, "该身份证号已经创建过门店");
        messageMap.put(REG_NUMBER_RECORD_EXIST, "该执照已经创建过门店");
        messageMap.put(REPEAT_OPERATE, "重复操作");
        messageMap.put(WRONG_OPERATION, "异常操作");
        messageMap.put(QUEUE_FAILED, "排队失败");
        messageMap.put(WRONG_PASSWORD, "密码校验失败（必填且长度大于6个字符）");
        messageMap.put(FD_FILE_VIDEO_CODE_ERROR, "视频编码不支持，您可转换为H264编码重新上传");

        //FastDFS error info
        messageMap.put(FD_FILE_ERROR, "获取上传文件信息异常");
        messageMap.put(FD_FILE_IMG_BIG, "上传图片应小于5M");
        messageMap.put(FD_FILE_CUT_ERROR, "获取视频帧转图片异常");

        messageMap.put(SMS_INTERFACE_UPDATE, "请更新APP版本");
        messageMap.put(PIC_CODE_VERIFY, "需要校验图形验证码");
        messageMap.put(PIC_CODE_OVERTIME, "图形验证码超时");


        // 用户模块 2xxx
        messageMap.put(MEMBER_WRONG_PWD, "用户名或密码错误");
        messageMap.put(USER_WRONG_ID, "ID不存在");
        messageMap.put(USER_WRONG_IDCARD, "身份证号不正确");
        messageMap.put(IMAGE_WRONG_UPLOAD_STORE, "请上传门店照");
        messageMap.put(IMAGE_WRONG_UPLOAD_STORE_ENVIRONMENT, "请上传门店环境照");
        messageMap.put(IMAGE_WRONG_UPLOAD_LICENSE, "请上传营业执照");
        messageMap.put(IMAGE_WRONG_UPLOAD_IDCARD, "手持身份证照");
        messageMap.put(USER_POINT_NOT_ENOUGH, "用户积分不足");
        messageMap.put(MERCHANT_STORE_AUDIT_EXIST, "已经存在未审核记录");
        messageMap.put(MERCHANT_STORE_NO_EXIST, "未查询到门店");
        messageMap.put(MEMBER_NO_EXIST, "用户不存在");
        messageMap.put(BANK_CASH_EXIST, "存在提现申请");
        messageMap.put(FANS_MERCHANT, "已经是商家粉丝");
        messageMap.put(MOBILE_IS_NOT_EXIST, "该手机号码尚未注册");
        messageMap.put(MERCHANT_STORE_IS_FAVORITE, "门店已被收藏");
        messageMap.put(ACCOUNT_IS_FREEZE, "账户已被冻结");
        messageMap.put(MAX_USERREDPACKET_COUNT, "单个红包个数不能大于1000");
        messageMap.put(MAX_USERREDPACKET_MONTY, "单个红包金额不能大于50000");
        messageMap.put(MIN_USERREDPACKET_MONTY, "单个红包金额不能小于0.01");
        messageMap.put(ACCOUNT_IS_INVALID, "账号已被封");
        messageMap.put(NO_LOTTERY_COUNT, "只能增加一次抽奖机会");
        messageMap.put(HAVE_TAKE_PART, "已参与抽奖");
        messageMap.put(GRADE_NOT_MATCH, "用户等级不符合");
        messageMap.put(NOT_LOTTERYING, "抽奖活动无效");
        messageMap.put(WEI_XIN_IS_NOT_ATTEND, "当前没有通过微信授权");
        messageMap.put(WEI_XIN_LOGIN_IS_NOT_BIND, "当前没有绑定微信登录，请授权绑定");
        messageMap.put(QQ_LOGIN_IS_NOT_BIND, "当前没有绑定QQ登录，请授权绑定");
        messageMap.put(FANS_NOT_MERCHANT, "您尚未关注此商家");


        //运营
        messageMap.put(USER_NOT_LOGIN, "用户未登录");
        messageMap.put(USER_ROLE_EXIST, "用户已经存在该权限");
        messageMap.put(ROLE_HAS_USER_RELATE, "角色下面存在关联用户");
        messageMap.put(ROLE_HAS_PERMISSION, "角色下面存在该权限");
        messageMap.put(ROLE_HAS_NOPERMISSION, "没有相关联权限");
        messageMap.put(PUSH_HAS_NOUSER, "没有可推送用户");
        messageMap.put(USER_ACCOUNT_DISABLE, "账号已停用，请联系管理员");
        messageMap.put(USER_ACCOUNT_EXIST, "账号已存在");
        messageMap.put(STORE_AUDIT_RECORD_NOT_EXIST, "审核记录不存在");
        messageMap.put(STORE_AUDIT_RECORD_AUDITED, "该门店已经审核过");
        messageMap.put(AD_AUDITED, "该广告已经审核过");
        messageMap.put(AGENT_ACCOUNT_EXIST, "该账号已经存在");
        messageMap.put(AGENT_MOBILE_EXIST, "该手机号已经存在");
        messageMap.put(USER_UNAUTHORIZED, "未授权");
        messageMap.put(EXISTS_ENABLE_APP_VERSION, "存在启用的版本");
        messageMap.put(GET_HEADER_ERROR, "获取请求头失败");
        messageMap.put(GRADE_WEIGHT_REPEAT, "等级权值重复！");
        messageMap.put(GRADE_LOTTERY_ACTIVITY_POINT_LESSTHAN_ERROR, "抽奖活动兑换积分不能小于或等于上一级！");
        messageMap.put(GRADE_MIN_GROWTH_VALUE_LESSTHAN_ERROR, "最小成长值不能小于或等于上一级！");
        messageMap.put(GRADE_LOTTERY_ACTIVITY_POINT_GRETERTHAN_ERROR, "抽奖活动兑换积分不能大于或等于下一级！");
        messageMap.put(GRADE_MIN_GROWTH_VALUE_GRETERTHAN_ERROR, "最小成长值不能大于或等于下一级！");


        // 商品模块 3xxx
        messageMap.put(IMAGE_WRONG_UPLOAD_PRODUCT_HEAD, "请上传商品图片");
        messageMap.put(IMAGE_WRONG_UPLOAD_PRODUCT_DETAIL, "请上传商品描述图片");
        messageMap.put(GOODS_PRODUCT_FACORITE_EXIST, "商品已经被收藏");
        messageMap.put(GOODS_PRODUCT_INVENTORY, "存在商品库存为0无法上架");
        messageMap.put(GOODS_PRODUCT_EXIST_ADFLAT, "商品存在广告位中无法下架，请联系后台管理员。");
        messageMap.put(SECKILL_ACTIVITY_COUNT_OVER, "抢购活动报名数量已满");
        messageMap.put(SECKILL_ACTIVITY_TIME_OVER, "抢购活动报名时间已到");
        messageMap.put(SECKILL_ACTIVITY_JOIN_STATUS, "该活动当前不能报名");
        messageMap.put(SECKILL_ACTIVITY_PRODUCT_STATUS, "该商品不存在");
        messageMap.put(SECKILL_ACTIVITY_PRODUCT_EXISTS, "该商品已参与活动");
        messageMap.put(PRODUCT_MODEL_SPEC_NOT_EMPTY, "商品型号不能为空");
        messageMap.put(PRODUCT_TITLE_IMAGE_NOT_EMPTY, "商品头部图片不能为空");
        messageMap.put(PRODUCT_MODEL_SPEC_MISMATCH, "规格和选项数据不匹配");
        messageMap.put(PRODUCT_MODEL_SPEC_OUT_SIZE, "规格数量不能超过5个");
        messageMap.put(PRODUCT_MODEL_PRICE_STOCK_INVALID, "型号价格和库存不能为空");


        // 订单模块 4xxx
        messageMap.put(PRODUCT_EVALUATE_TRUE, "该订单已评价");
        messageMap.put(ORDER_NOT_CANCELED, "订单不能被取消");
        messageMap.put(ORDER_NOT_DELETE, "订单不能被删除");
        messageMap.put(ORDER_NOT_PENDING_PAYMENT, "订单不是待支付状态");
        messageMap.put(ORDER_NOT_RECEIVED, "订单不能确认收货");
        messageMap.put(ORDER_NOT_REFUND, "订单不能被退款");
        messageMap.put(EXCEEDS_RETURN_TIME, "订单超过退货时间");
        messageMap.put(NOT_SHIPPING_STATUS, "订单不是待发货状态");
        messageMap.put(NOT_RETURNED_STATE, "订单退款状态不是待退货状态");
        messageMap.put(NOT_AGREE_TO_APPLY, "订单退款状态不是待确认状态");
        messageMap.put(NOT_REFUNDING, "订单不是退款中状态");
        messageMap.put(ORDER_NOT_FILL_RETURN_ADDRESS, "订单退款状态不是填写退货地址状态");
        messageMap.put(ORDER_NOT_TO_BE_REFUNDED, "订单退款状态不是待退款状态");
        messageMap.put(ORDER_NOT_REFUND_FAILED, "订单退款状态不是退款失败");
        messageMap.put(ORDER_NOT_COMPLETE_STATUS, "订单不是完成状态");
        messageMap.put(PAY_ORDER_FAVORED_AMOUNT_UNEQUAL, "买单优惠金额不正确");
        messageMap.put(ORDER_HAS_BEEN_REFUNDED, "订单已经是退款状态");
        messageMap.put(INVENTORY_SHORTAGE, "库存不足");
        messageMap.put(PRODUCT_HAS_EXPIRED, "商品已经失效");
        messageMap.put(THE_ORDER_IS_BEING_PROCESSED, "订单正在处理");
        messageMap.put(ORDER_CREATION_FAILED, "订单创建失败");
        messageMap.put(MAX_SHOPPING_CART_QUANTITY, "购物车数量已达上限");
        messageMap.put(CAN_NOT_FILL_OUT_THE_RETURN_LOGISTICS, "不能填写退货物流");
        messageMap.put(CAN_NOT_APPLY_FOR_PLATFORM_INTERVENTION, "不能申请平台介入");
        messageMap.put(CAN_NOT_CANCEL_APPLICATION, "不能撤销申请");
        messageMap.put(CAN_NOT_FILL_IN_SHIPPING_LOGISTICS, "不能填写发货物流");
        messageMap.put(CAN_NOT_AGREE_TO_APPLY, "不能同意退款申请");
        messageMap.put(CAN_NOT_AGREE_TO_A_REFUND, "不能同意退款");
        messageMap.put(CAN_NOT_FILL_IN_THE_RETURN_ADDRESS, "不能填写退货地址");
        messageMap.put(THIRD_PARTY_LOGISTICS_INTERFACE_EXCEPTION, "第三方物流接口异常");

        // 广告模块 5xxx
        messageMap.put(AD_POINT_NOT_ENOUGH, "积分不足，请充值");
        messageMap.put(AD_PUT_NOT_TIME, "投放时间没有超过两个星期");
        messageMap.put(AD_FACORITE_EXIST, "广告已被收藏");
        messageMap.put(AD_RED_PACKGE_EXIST, "请等待红包下架");
        messageMap.put(AD_RED_PACKGE_GET, "红包已经领取");
        messageMap.put(AD_PRAISE_PUTED, "抢赞已经结束");
        messageMap.put(AD_CLICK_EXIST, "今天已经看过该广告");
        messageMap.put(AD_PRAISE_POINT_GET, "你已经抢到赞了");
        messageMap.put(AD_CLICK_PUTED, "广告已点击完");
        messageMap.put(AD_RED_PACKGE_PUTED, "红包已下架");
        messageMap.put(AD_BEGIN_TIME_NOT_EXIST, "投放时间不能为空");
        messageMap.put(AD_RED_PACKET_COUNT_ERROR, "红包数量不能超过100万");
        messageMap.put(AD_RED_PACKET_POINT_ERROR, "金额不合法");
        messageMap.put(AD_CLICK_SYS_WORDS, "系统繁忙,请稍后再试");
        messageMap.put(AD_PRAISE_NOT_RATE, "E咻没有抢到");
        messageMap.put(AD_PLATFORM_LESS_COUNT, "广告位数量不能低于");
        
        // 资产模块 6xxx
        messageMap.put(BANK_ACCOUNT_ERROR, "请重新输入你的银行卡号");
        messageMap.put(CASH_MORE_NUM_MAX_MONEY_ERROR, "提现金额必须大于等于10元");
        messageMap.put(PROPERTY_INFO_NULL, "用户对应财产记录为空");
        messageMap.put(PROPERTY_INFO_OUT_INDEX, "用户对应财产记录错误，存在大于1条记录");
        messageMap.put(PROPERTY_INFO_BALANCE_LESS, "余额不足");
        messageMap.put(PROPERTY_INFO_POINT_LESS, "积分不足，请充值");
        messageMap.put(PROPERTY_CASH_SCALE_NULL, "提现比例系统参数未配置");
        messageMap.put(PROPERTY_CASH_PAY_PWD_ERROR, "支付密码错误");
        messageMap.put(PROPERTY_CASH_BANK_NOT_EXIST, "提交的银行卡ID不存在");
        messageMap.put(PROPERTY_CASH_BANK_NOT_MATCH, "提交的银行卡与用户不匹配");
        messageMap.put(PROPERTY_CASH_USER_INFO_NULL, "提交查询用户冗余信息为空");

        messageMap.put(CASH_BACKAGE_FAILURE_REASON_NULL, "操作失败时原因不能为空");
        messageMap.put(BIZ_TYPE_NULL, "业务类型不能为空");
        messageMap.put(FREEZE_NULL, "冻结资金记录为空");
//        messageMap.put(FREEZE_ROWS_OUT, "冻结资金记录条数已大于1");
        messageMap.put(FREEZE_MONEY_LESS_REFUND_MONEY, "冻结金额不能小于退款金额");
        messageMap.put(DEPOSIT_IN_SYSTEM_DAYS, "不满足申请退保证金要求(保证金核实后90天)");
        messageMap.put(DEPOSIT_EXIST_ING_ORDER, "有未完成订单，完成后才可申请。");
        messageMap.put(MONEY_IS_ZERO, "查询出金额为0");
        messageMap.put(PAY_ORDER_NULL, "买单记录为空");
        messageMap.put(PAY_ORDER_IS_SUCCESS, "重复买单");
        messageMap.put(BANK_ACCOUNT_IS_EXIST, "该银行卡已被其他账号绑定");
        messageMap.put(STORE_REGION_PATH_ERROR, "门店所属区域不正确（需要精确到区）");
        messageMap.put(NOTIFY_MONEY_ERROR, "回调金额与表中的金额不一致");
        messageMap.put(PROPERTYINFO_FREEZE_YES, "您的资金账户已被冻结,有疑问请联系E店QQ293412362");
        messageMap.put(PROCESSED_RETURN_SUCCESS, "重复处理");
        messageMap.put(PROPERTYINFO_FREEZE_EXCEPITON, "资金出现异常");
        messageMap.put(DEPOSIT_EXIST_UP_PRODUCT, "有未下架商品，完成后才可申请。");
        messageMap.put(BANK_ACCOUNT_LENTH_OUT_OF_RANGE, "银行卡长度有误。");
        messageMap.put(ERROR_POINT_NEGATIVE, "非法操作，积分减到负数");
        messageMap.put(ERROR_BALANCE_NEGATIVE, "非法操作，余额减到负数");
        messageMap.put(ERROR_SAVE_POINT_DETAIL, "保存积分明细异常");
        messageMap.put(PROPERTYINFO_FREEZE_YES_WORD, "您的资金账户已被冻结,有疑问请联系E店QQ293412362");
        messageMap.put(BANK_ACCOUNT_THIRD_IS_EXIST, "绑定失败，账户已被其他用户绑定");
        messageMap.put(BANK_ACCOUNT_THIRD_TYPE_IS_EXIST, "绑定失败，你已绑定过支付宝账户");
        messageMap.put(BANK_ACCOUNT_THIRD_ACCOUNT_FORMAT, "账号格式不正确");
        messageMap.put(CASH_MAX, "支付宝单日提现额度不得超过50,000元");
        messageMap.put(BANK_ACCOUNT_THIRD_TYPE_WX_IS_EXIST, "绑定失败，你已绑定过微信账户");
        messageMap.put(CASH_MAX_WX, "微信单日提现额度不得超过20,000元");

        // 商城模块 7xxx
        messageMap.put(MESSAGE_HAS_NO_TEMPLATE, "未配置对应的消息模板");
        messageMap.put(COMMENT_REPEAT_REPLY, "已经回复了该评价");


        messageMap.put(HELP_MEMBER_IS_EMPTY, "无效账号，请先注册");
        messageMap.put(HELP_REG_OUT_TIME, "你的账号属于活动期间外注册，助力无效");
        messageMap.put(HELP_ONLY_ONE_TIME, "你已助力过好友，不得再次助力");
        messageMap.put(HELP_SELF_ERROR, "不能帮自己助力，助力无效");
        messageMap.put(HELP_REG_END, "助力无效，活动通道已关闭");
        messageMap.put(HELP_ACTIVITY_STATUS_NOT_REGISTING, "不在报名时间之内");
        messageMap.put(HELP_ACTIVITY_STATUS_ATTEND, "你已成功报名，请留意派送红包时间");
        messageMap.put(WEIXIN_OPENDID_NOT_BIND, "因新年红包通过公众号发放，请先绑定微信公众号");
        messageMap.put(HELP_ACTIVITY_STATUS_NOT_START, "红包正在路上，请稍后再试");
        messageMap.put(HELP_RED_PACKET_GET, "红包已经领取");
        messageMap.put(HELP_ACTIVITY_STATUS_END, "活动已结束");
        messageMap.put(HELP_ACTIVITY_GET_ERROR, "当前不能领取红包，请稍后再试");
        

        messageMap.put(TAKE_PART_LOTTERY_ERROR, "参与抽奖异常");
        messageMap.put(LOTTERY_NOT_ING, "抽奖活动状态异常");
        messageMap.put(FREE_LOTTERY_COUNT_OVER, "免费抽奖次数已用完");
        messageMap.put(LOTTERY_PRIZE_ERROR, "没有抽奖奖品");
        messageMap.put(LOTTERY_PRIZE_INVENTORY_SHORTAGE, "奖品库存不足");
        messageMap.put(LOTTERY_ACTIVITY_FINISHED, "抽奖活动已结束");
        messageMap.put(LOTTERY_RECORD_HANDLE, "存在正在处理的抽奖记录");
        messageMap.put(LOTTERY_RECORD_USED, "抽奖记录已开奖");
        messageMap.put(LOTTERY_RECORD_HAVE_DEAL, "中奖记录已处理");
        
        
        messageMap.put(GAME_INFORM_ALREADY, "您已经举报过该场游戏");
        messageMap.put(GAME_MIND_QUESTION_ERROR, "获取题目失败");
        messageMap.put(GAME_MIND_ATTEND_FAIL, "参与游戏失败");
        messageMap.put(GAME_PUZZLE_DEDUCTION_POINT_FAIL, "参与拼图积分扣除失败");
        messageMap.put(GAME_MATCH_LOADING, "匹配中请稍后");
        messageMap.put(GAME_MIND_ANSWER_QUESTION_ERROR, "答题异常");
        messageMap.put(GAME_ROOM_POINT_ERROR, "游戏房间入场积分不在范围内");
        messageMap.put(GAME_ROOM_JOIN_FAIL, "加入游戏房间失败");
        messageMap.put(GAME_ROOM_EXIT_FAIL, "退出游戏房间失败");
        messageMap.put(GAME_ROOM_PLAYER_READY_FAIL, "游戏房间玩家准备失败");
        messageMap.put(GAME_ROOM_PWD_ERROR, "游戏房间密码错误");
        messageMap.put(GAME_DIAL_DISABLED, "转盘游戏未开启");
        messageMap.put(GAME_DIAL_NOT_LOTTERY, "未中奖");
        messageMap.put(GAME_DIAL_PRIZE_COUNT_OVER, "启用的商品只能为7个，请先禁用其他商品,再添加");
        messageMap.put(GAME_SHAREL_IS_GET, "已经分享获取免费次数");
        messageMap.put(GAME_SETTING_DISABLE, "游戏正在维护中...");
        messageMap.put(GAME_ROOM_FULL, "游戏房间人数已满");
        messageMap.put(GAME_ROOM_CREATED, "游戏房间已创建");
        messageMap.put(GAME_ROOM_FORBID_JOIN, "游戏房间正在进行中或已结束");


        messageMap.put(GAME_WS_USER_ONLINE, "用户上线");
        messageMap.put(GAME_WS_USER_READY, "准备游戏");
        messageMap.put(GAME_WS_USER_START, "开始游戏");
        messageMap.put(GAME_WS_USER_CHECK_NOTDONE, "积分扣除未完成");
        messageMap.put(GAME_WS_USER_CHECK_DONE, "积分扣除完成");
        messageMap.put(GAME_WS_USER_SUBMIT_SINGLE, "提交单关单人成绩完成");
        messageMap.put(GAME_WS_USER_SUBMIT_MULTI, "提交单关多人成绩完成");
        messageMap.put(GAME_WS_USER_SUBMIT_DONE, "提交所有关卡成绩完成");
        messageMap.put(GAME_WS_USER_OFFLINE, "用户离线");
        messageMap.put(GAME_WS_USER_SUBMIT_OTHER, "提交单关其他人成绩完成");
        messageMap.put(GAME_WS_REJECT_FAIL, "踢人失败");
        messageMap.put(GAME_WS_ABANDON_FAIL, "放弃失败");

        messageMap.put(GAME_ATTEND_SUCCESS, "参与成功");
        messageMap.put(GAME_ATTEND_FAIL, "参与失败");
        messageMap.put(GAME_ATTEND_REPEAT, "重复参与");
        messageMap.put(GAME_WS_USER_ONLINE_ERROR, "用户上线异常");

        messageMap.put(GAME_WS_HEARTBEAT, "heartbeat");
    }
}

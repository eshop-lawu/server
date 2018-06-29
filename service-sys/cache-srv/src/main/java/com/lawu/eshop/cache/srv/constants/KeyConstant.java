package com.lawu.eshop.cache.srv.constants;

/**
 * 缓存key常量
 *
 * @author Leach
 * @date 2017/3/19
 */
public class KeyConstant {

    /**
     * 会员账号-token
     */
    @Deprecated
    public static final String REDIS_KEY_MEMBER_PREFIX = "AUTHORIZATION_KEY_MEMBER_";

    /**
     * 会员token-账号
     */
    @Deprecated
    public static final String REDIS_TOKEN_MEMBER_PREFIX = "AUTHORIZATION_TOKEN_MEMBER_";

    /**
     * 商家账号-token
     */
    @Deprecated
    public static final String REDIS_KEY_MERCHANT_PREFIX = "AUTHORIZATION_KEY_MERCHANT_";

    /**
     * 商家token-账号
     */
    @Deprecated
    public static final String REDIS_TOKEN_MERCHANT_PREFIX = "AUTHORIZATION_TOKEN_MERCHANT_";

    /**
     * 账号-token
     */
    public static final String REDIS_ACCOUNT_PREFIX = "AUTH_ACCOUNT_";

    /**
     * token-账号
     */
    public static final String REDIS_TOKEN_PREFIX = "AUTH_TOKEN_";

    /**
     * token-删除原因
     */
    public static final String REDIS_TOKEN_CLEAR_PREFIX = "AUTH_TOKEN_DEL_";

    /**
     * 广告ID
     */
    public static final String REDIS_KEY_AD = "AD_KEY_MEMBER_ID_";

    /**
     * 点广告记录
     */
    public static final String REDIS_KEY_CLICK_AD = "AD_KEY_MEMBER_CLICK_AD_ID_";

    /**
     * 会员接口访问次数
     */
    public static final String REDIS_KEY_USER_VISIT_COUNT = "USER_VISIT_COUNT_";

    /**
     * 会员接口访问次数
     */
    public static final String REDIS_KEY_MERCHANT_VISIT_COUNT = "MERCHANT_VISIT_COUNT_";

    /**
     * 新店推荐
     */
    public static final String REDIS_KEY_MERCHANT_NEW_STORE = "MERCHANT_NEW_STORE_";

    /**
     * 优选美食-人气最高
     */
    public static final String REDIS_KEY_MERCHANT_CONSUME_STORE = "MERCHANT_CONSUME_STORE_";

    /**
     * 优选美食-评价最高
     */
    public static final String REDIS_KEY_MERCHANT_COMMENT_STORE = "MERCHANT_COMMENT_STORE_";

    /**
     * 广告可领取数量
     */
    public static final String REDIS_KEY_AD_COUNT = "AD_KEY_MEMBER_COUNT_";

    /**
     * 大额抢赞记录
     */
    public static final String REDIS_KEY_AD_PRAISE_POINT_RECORD_ = "AD_KEY_PRAISE_POINT_RECORD_";

    /**
     * 用户红包可领取数量
     */
    public static final String REDIS_KEY_USER_RED_PACKET_COUNT = "AD_KEY_MEMBER_RED_RACKET_COUNT_";

    /**
     * 会员上次访问接口时间
     */
    public static final String REDIS_KEY_MEMBER_VISIT_TIME = "MEMBER_VISIT_TIME_";

    /**
     * 会员时间周期内访问接口频率
     */
    public static final String REDIS_KEY_MEMBER_VISIT_FREQUENCY = "MEMBER_VISIT_FREQUENCY_";

    /**
     * 商家上次访问接口时间
     */
    public static final String REDIS_KEY_MERCHANT_VISIT_TIME = "MERCHANT_VISIT_TIME_";

    /**
     * 商家时间周期内访问接口频率
     */
    public static final String REDIS_KEY_MERCHANT_VISIT_FREQUENCY = "MERCHANT_VISIT_FREQUENCY_";

    /**
     * 业务并发缓存
     */
    public static final String REDIS_KEY_BUSINESS_INVENTORY_SYN = "BUSINESS_INVENTORY_SYN_";

    /**
     * 短高频注册总数
     */
    public static final String REDIS_KEY_SHORT_FREQUENCY_REG = "SHORT_FREQUENCY_REG_";

    /**
     * 多次短高频注册总数
     */
    public static final String REDIS_KEY_MANY_SHORT_FREQUENCY_REG = "MANY_SHORT_FREQUENCY_REG_";

    /**
     * 长高频注册总数
     */
    public static final String REDIS_KEY_LONG_FREQUENCY_REG = "LONG_FREQUENCY_REG_";
    /**
     * 多次长高频注册总数
     */
    public static final String REDIS_KEY_MANY_LONG_FREQUENCY_REG = "MANY_LONG_FREQUENCY_REG_";

    /**
     * 一天内一级下线注册总数
     */
    public static final String REDIS_KEY_ONE_DAY_FREQUENCY_REG = "ONE_DAY_FREQUENCY_REG_";

    /**
     * 凌晨注册
     */
    public static final String REDIS_KEY_EARLY_FREQUENCY_REG = "EARLY_FREQUENCY_REG_";

    /**
     * 点广告
     */
    public static final String AD_CLICK_KEY = "AD_CLICK_KEY_";

    /**
     * 积分抽奖活动
     */
    public static final String REDIS_KEY_POINT_LOTTERY_ACTIVITY = "POINT_LOTTERY_ACTIVITY_";

    /**
     * 短信接口时间内访问频率
     */
    public static final String REDIS_KEY_SMS_VISIT_FREQUENCY = "SMS_VISIT_FREQUENCY_";

    /**
     * 是否需要图形验证码标记
     */
    public static final String REDIS_KEY_PIC_CODE_FLAG = "PIC_CODE_FLAG_";

    /**
     * 图形验证码是否开启
     */
    public static final String REDIS_KEY_PIC_CODE_IS_OPEN = "PIC_CODE_IS_OPEN";

    /**
     * 头脑游戏配置
     */
    public static final String REDIS_KEY_GAME_MIND_CONFIG = "GAME_MIND_CONFIG_";

    /**
     * 拼图游戏配置
     */
    public static final String REDIS_KEY_GAME_PUZZLE_CONFIG = "GAME_PUZZLE_CONFIG_";

    /**
     * 用户参与拼图获取到的信息
     */
    public static final String REDIS_KEY_GAME_PUZZLE_USER_KEY = "REDIS_KEY_GAME_PUZZLE_USER_KEY_";

    /**
     * 设置用户拼图开始时间
     */
    public static final String REDIS_KEY_GAME_PUZZLE_START_TIME = "REDIS_KEY_GAME_PUZZLE_START_TIME_";

    /**
     * 拼图游戏开始类型(单机，随机，好友对战)
     */
    public static final String REDIS_KEY_GAME_PUZZLE_START_TYPE = "REDIS_KEY_GAME_PUZZLE_START_TYPE_";

    /**
     * 可用房间号
     */
    public static final String REDIS_KEY_GAME_ROOM_USABLE_INDEX = "_GAME_ROOM_USABLE_INDEX_";

    /**
     * 不可用房间号
     */
    public static final String REDIS_KEY_GAME_ROOM_DISABLE_INDEX = "_GAME_ROOM_DISABLE_INDEX_";

    /**
     * 房间号下标
     */
    public static final String REDIS_KEY_GAME_ROOM_PAGE_INDEX = "_GAME_ROOM_PAGE_INDEX";

    /**
     * 使用中的房间号
     */
    public static final String REDIS_KEY_GAME_ROOM_READY = "_GAME_ROOM_READY_";

    /**
     * 设置头脑PK开始时间
     */
    public static final String REDIS_KEY_GAME_MIND_ATTEND_START_TIME = "REDIS_KEY_GAME_MIND_ATTEND_START_TIME_";

    /**
     * 设置头脑PK问题ID缓存
     */
    public static final String REDIS_KEY_GAME_MIND_ATTEND_QUESTION = "REDIS_KEY_GAME_MIND_ATTEND_QUESTION_";

    /**
     * 设置头脑PK问题答题记录
     */
    public static final String REDIS_KEY_GAME_MIND_ANSWER_QUESTION = "REDIS_KEY_GAME_MIND_ANSWER_QUESTION_";

    public static final String REDIS_KEY_GAME_MIND_ANSWER_QUESTION_SCORE = "REDIS_KEY_GAME_MIND_ANSWER_QUESTION_SCORE";

    /**
     * 头脑PK用户缓存(游戏参与状态,以及答题的结果)
     */
    public static final String REDIS_KEY_GAME_MIND_USER_DETAILS = "GAME_MIND_USER_DETAILS_";

    /**
     * 头脑PK房间缓存,已参与编号为key(随机生成的题目)
     */
    public static final String REDIS_KEY_GAME_MIND_ROOM_DETAILS = "GAME_MIND_ROOM_DETAILS_";

    /**
     * 头脑随机
     */
    public static final String GAME_MIND_RANDOM = "GAME_MIND_RANDOM_";
    /**
     * 头脑参与编号
     */
    public static final String GAME_MIND_RANDOM_ATTENDNUM = "GAME_MIND_RANDOM_ATTENDNUM_";

    /**
     * 拼图随机
     */
    public static final String GAME_PUZZLE_RANDOM = "GAME_PUZZLE_RANDOM_";
    
    /**
     * 随机匹配时间
     */
    public static final String GAME_RANDOM_MATCH_TIME = "GAME_RANDOM_MATCH_TIME_";
    
    /**
     * 拼图在线
     */
    public static final String GAME_PUZZLE_ONLINE="GAME_PUZZLE_ONLINE_";
    /**
     * 拼图随机参与编号
     */
    public static final String GAME_PUZZLE_RANDOM_ATTENDNUM = "GAME_PUZZLE_RANDOM_ATTENDNUM_";

    /**
     * 并发控制标记
     */
    public static final String REDIS_KEY_CONCURRENCY_CONTROL_MARK = "CONCURRENCY_CONTROL_MARK_";

    public static final String GAME_PUZZLE_CALLBACK_CACHE = "GAME_PUZZLE_CALLBACK_CACHE_";
    
    /**
     * 题库简单缓存
     */
    public static final String GAME_MIND_QUESTION_SIMPLE_USE_CACHE = "GAME_MIND_QUESTION_SIMPLE_USE_CACHE";
    
    
    /**
     * 题库简单临时缓存
     */
    public static final String GAME_MIND_QUESTION_SIMPLE_TEMP_CACHE = "GAME_MIND_QUESTION_SIMPLE_TEMP_CACHE";
    
    
    /**
     * 题库困难缓存
     */
    public static final String GAME_MIND_QUESTION_DIFFICULTY_USE_CACHE = "GAME_MIND_QUESTION_DIFFICULTY_USE_CACHE";
    
    
    /**
     * 题库困难临时缓存
     */
    public static final String GAME_MIND_QUESTION_DIFFICULTY_TEMP_CACHE = "GAME_MIND_QUESTION_DIFFICULTY_TEMP_CACHE";
    
    /**
     * 动力任务配置
     */
    public static final String RICH_POWER_TASK_CONFIG_MAIN_CACHE = "RICH_POWER_TASK_CONFIG_MAIN_CACHE";
    
    /**
     * 动力任务配置详情
     */
    public static final String RICH_POWER_TASK_CONFIG_DETAIL_CACHE = "RICH_POWER_TASK_CONFIG_DETAIL_CACHE_";

    /**
     * E钻配置
     */
    public static final String REDIS_KEY_RICH_DIAMOND_CONFIG = "RICH_DIAMOND_CONFIG";
    
    
    /**
     * 用户看广告记录
     */
    public static final String REDIS_KEY_MEMBER_SEE_AD_DETAIL = "MEMBER_SEE_AD_DETAIL_";

 	/**
     * 开通E钻账户自动递增序列
     */
    public static final String REDIS_KEY_INCREASEMEMBERNUM_COUNT="redis_key_increasemembernum_count";
    /**
     * 当天访问接口次数
     */
    public static final String REDIS_KEY_DAILY_VISIT_TIMES = "DAILY_VISIT_TIMES_";
    
    /**
     * 系统配置维护key
     */
    public static final String REDIS_SYS_MAINTAIN_CONFIG="REDIS_SYS_MAINTAIN_CONFIG";
    
    
    /**
     * 邀请E友动力任务数
     */
    public static final String REDIS_INVITE_TASKS_COUNT="REDIS_INVITE_TASKS_COUNT_";
    
    
    /**
     * 购物获取动力任务
     */
    public static final String REDIS_SHOPING_TASKS_COUNT="REDIS_SHOPING_TASKS_COUNT_";

    /**
     * 商家动力任务配置
     */
    public static final String RICH_POWER_TASK_CONFIG_MAIN_CACHE_MERCHANT = "RICH_POWER_TASK_CONFIG_MAIN_CACHE_MERCHANT";

    /**
     * 商家动力任务配置详情
     */
    public static final String RICH_POWER_TASK_CONFIG_DETAIL_CACHE_MERCHANT = "RICH_POWER_TASK_CONFIG_DETAIL_CACHE_MERCHANT_";
    
    
    /**
     * 今日是否获取动力
     */
    public static final String REDIS_GET_TASKS_TODAY_COUNT="REDIS_GET_TASKS_TODAY_COUNT_";
    
}

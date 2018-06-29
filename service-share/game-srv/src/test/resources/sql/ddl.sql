/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.22
Source Server Version : 50717
Source Host           : 192.168.1.22:3306
Source Database       : eshop_game

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-19 10:17:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for follow_transaction_record
-- ----------------------------
DROP TABLE IF EXISTS `follow_transaction_record`;
CREATE TABLE `follow_transaction_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `transation_id` bigint(20) unsigned NOT NULL COMMENT '事务记录id',
  `topic` varchar(30) NOT NULL COMMENT 'MQ消息的topic',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_account
-- ----------------------------
DROP TABLE IF EXISTS `game_account`;
CREATE TABLE `game_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL COMMENT '账号',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `star_count` int(5) DEFAULT '0' COMMENT '星星总数量',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_dial
-- ----------------------------
DROP TABLE IF EXISTS `game_dial`;
CREATE TABLE `game_dial` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '游戏名称',
  `img_path` varchar(200) DEFAULT NULL COMMENT '游戏图片',
  `point` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '参与积分',
  `free_count` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '免费参与次数',
  `share_attend_count` INT(5) NULL DEFAULT '0' COMMENT '分享可免费次数',
  `status` tinyint(2) unsigned NOT NULL COMMENT '1--启用，2--禁用',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_dial_account
-- ----------------------------
DROP TABLE IF EXISTS `game_dial_account`;
CREATE TABLE `game_dial_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `free_count` int(5) unsigned DEFAULT '0' COMMENT '剩余免费次数',
  `share_attend_count` INT(5) NULL DEFAULT '0' COMMENT '分享免费次数',
  `is_get_free` tinyint(1) unsigned DEFAULT '0' COMMENT '是否分享获得免费次数',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_dial_prize
-- ----------------------------
DROP TABLE IF EXISTS `game_dial_prize`;
CREATE TABLE `game_dial_prize` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `game_dial_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '转盘游戏id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `img_path` varchar(200) NOT NULL COMMENT '图片',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '价格',
  `number` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '数量',
  `inventory` int(5) unsigned NOT NULL DEFAULT '0' COMMENT '库存',
  `status` tinyint(2) unsigned NOT NULL COMMENT '1--有效，2--无效',
  `is_address` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否需要填写地址',
  `is_send_prize` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否立即发放奖品',
  `prize_type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '奖品类型',
  `freight_price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '运费',
  `rate` decimal(5,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '中奖概率',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_dial_record
-- ----------------------------
DROP TABLE IF EXISTS `game_dial_record`;
CREATE TABLE `game_dial_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `user_account` varchar(20) NOT NULL COMMENT '用户账号',
  `game_dial_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '转盘游戏id',
  `game_dial_prize_id` bigint(20) unsigned DEFAULT '0' COMMENT '奖品id',
  `status` tinyint(2) unsigned NOT NULL COMMENT '0--待处理，1--已参与，2--未中奖，3--已中奖，4--放弃领奖，5--已领奖，6--奖品已发放，7--积分扣除失败',
  `pay_point` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '兑换积分',
  `consignee_name` varchar(25) DEFAULT NULL COMMENT '收货人姓名',
  `consignee_mobile` varchar(15) DEFAULT NULL COMMENT '收货人手机号',
  `consignee_address` varchar(180) DEFAULT NULL COMMENT '收货人地址',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_inform
-- ----------------------------
DROP TABLE IF EXISTS `game_inform`;
CREATE TABLE `game_inform` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `game_type` tinyint(2) NOT NULL COMMENT '游戏类型 1-头脑pk 2拼图',
  `attend_num` varchar(19) NOT NULL COMMENT '游戏编号',
  `user_num` varchar(19) NOT NULL COMMENT '举报人编号',
  `result_error` tinyint(1) DEFAULT NULL COMMENT '结果错误',
  `question_error` tinyint(1) DEFAULT NULL COMMENT '题目错误',
  `cheat` tinyint(1) DEFAULT NULL COMMENT '对方作弊',
  `auditor_id` int(11) DEFAULT '0' COMMENT '审核人员ID',
  `auditor_name` varchar(50) DEFAULT NULL COMMENT '审核人员名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `status` tinyint(2) unsigned NOT NULL COMMENT '0：待处理，1：已处理，2：不处理',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for game_mind_account
-- ----------------------------
DROP TABLE IF EXISTS `game_mind_account`;
CREATE TABLE `game_mind_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `free_count` int(5) DEFAULT '0' COMMENT '剩余免费次数',
  `share_attend_count` int(5) DEFAULT '0' COMMENT '分享免费次数',
  `is_get_free` tinyint(1) DEFAULT '0' COMMENT '是否分享获得免费次数',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_mind_attend_record
-- ----------------------------
DROP TABLE IF EXISTS `game_mind_attend_record`;
CREATE TABLE `game_mind_attend_record` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) NOT NULL COMMENT '参与用户编号',
  `attend_type` tinyint(3) DEFAULT NULL COMMENT '参与类型1单机、2随机对战、3好友对战多人',
  `attend_num` varchar(30) DEFAULT NULL COMMENT '参与编号(同一个房间编号相同)',
  `room_num` varchar(8) DEFAULT NULL COMMENT '房间号',
  `attend_count` int(11) DEFAULT NULL COMMENT '参与人数',
  `attend_point` int(8) DEFAULT NULL COMMENT '参与扣除积分',
  `attend_star` int(8) DEFAULT NULL COMMENT '参与扣除星星',
  `status` tinyint(2) DEFAULT NULL COMMENT '0-初始化 1-参与成功 2-参与失败 3-游戏成功 4-游戏失败',
  `question_use_time` int(3) DEFAULT NULL COMMENT '答题用时单位秒',
  `game_score` int(8) DEFAULT '0' COMMENT '游戏分数',
  `game_rank` int(8) DEFAULT '0' COMMENT '游戏名次',
  `reward_point` int(8) DEFAULT NULL COMMENT '参与成功奖励积分',
  `reward_star` int(8) DEFAULT NULL COMMENT '参与成功奖励星星',
  `question_ids` varchar(50) DEFAULT NULL COMMENT '问题id集合逗号隔开',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for game_mind_attend_record_result
-- ----------------------------
DROP TABLE IF EXISTS `game_mind_attend_record_result`;
CREATE TABLE `game_mind_attend_record_result` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `mind_attend_id` bigint(32) NOT NULL COMMENT '参与表id',
  `question_id` bigint(32) DEFAULT NULL COMMENT '题目id',
  `right_answer` int(2) NOT NULL COMMENT '答案(答案下标)',
  `answer` varchar(50) DEFAULT NULL COMMENT '答案',
  `flag` tinyint(1) DEFAULT NULL COMMENT '是否正确',
  `point` int(11) DEFAULT NULL COMMENT '题目得分',
  `use_time` int(11) DEFAULT NULL COMMENT '用时',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for game_mind_config
-- ----------------------------
DROP TABLE IF EXISTS `game_mind_config`;
CREATE TABLE `game_mind_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `attend_point` int(8) DEFAULT NULL COMMENT '每次参与花费积分',
  `attend_max_point` int(8) DEFAULT NULL COMMENT '每次参与最大花费积分',
  `room_max_num` int(5) DEFAULT '0' COMMENT '每个房间最大可参入人数',
  `success_score` int(5) NOT NULL COMMENT '挑战成功分数',
  `free_count` int(5) DEFAULT '0',
  `share_attend_count` int(5) DEFAULT '0' COMMENT '分享可免费次数',
  `question_count` int(5) DEFAULT '0' COMMENT '单场游戏题目数量',
  `count_down` int(5) DEFAULT '0' COMMENT '单题游戏倒计时',
  `last_score_multiple` int(5) DEFAULT '0' COMMENT '最后题目积分倍数',
  `award_point` int(5) DEFAULT '0' COMMENT '挑战成功奖励积分',
  `award_star` int(5) DEFAULT '0' COMMENT '挑战成功奖励星星',
  `deduct_star` int(5) DEFAULT '0' COMMENT '挑战失败扣除星星',
  `sec_score` varchar(500) DEFAULT NULL COMMENT '倒计时对应评分{"list":[{"second":3,"score":200},{"second":5,"score":150},{"second":10,"score":100}]}',
  `status` tinyint(2) DEFAULT '1' COMMENT '游戏状态  0-禁用  1-启用',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_mind_question
-- ----------------------------
DROP TABLE IF EXISTS `game_mind_question`;
CREATE TABLE `game_mind_question` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `question_num` varchar(19) NOT NULL COMMENT '题目编号',
  `category_id` bigint(20) unsigned NOT NULL COMMENT '题目类型ID',
  `category_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `title` varchar(250) DEFAULT NULL COMMENT '题目标题',
  `answers` varchar(500) NOT NULL COMMENT '题目答案{"answer":["answer1","answer2","answer3","answer4"]}',
  `right_answer` int(2) NOT NULL COMMENT '正确答案(答案下标)',
  `difficulty_level` tinyint(2) DEFAULT '1' COMMENT '难度系数 1-简单 2困难',
  `status` tinyint(2) DEFAULT '1' COMMENT '题目状态  0-删除  1-正常',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_mind_question_category
-- ----------------------------
DROP TABLE IF EXISTS `game_mind_question_category`;
CREATE TABLE `game_mind_question_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '题目类型名称',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

INSERT INTO `game_mind_question_category` (`id`, `name`, `gmt_create`) VALUES ('2', '基础题', '2018-03-16 10:05:16');
INSERT INTO `game_mind_question_category` (`id`, `name`, `gmt_create`) VALUES ('3', '游戏题', '2018-03-16 10:05:27');
INSERT INTO `game_mind_question_category` (`id`, `name`, `gmt_create`) VALUES ('4', '历史题', '2018-03-16 10:05:39');
INSERT INTO `game_mind_question_category` (`id`, `name`, `gmt_create`) VALUES ('5', '文学题', '2018-03-16 10:06:02');
INSERT INTO `game_mind_question_category` (`id`, `name`, `gmt_create`) VALUES ('6', '饮食题', '2018-03-16 10:43:42');
INSERT INTO `game_mind_question_category` (`id`, `name`, `gmt_create`) VALUES ('7', '健身题', '2018-03-16 10:43:57');


-- ----------------------------
-- Table structure for game_point_allot
-- ----------------------------
DROP TABLE IF EXISTS `game_point_allot`;
CREATE TABLE `game_point_allot` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `game_type` tinyint(2) DEFAULT '1' COMMENT '游戏类型 1-头脑pk 2拼图',
  `attend_count` int(5) DEFAULT '0' COMMENT '参与人数范围（小）',
  `win_num` int(5) DEFAULT '1' COMMENT '胜利人数',
  `rank_star` varchar(500) NOT NULL COMMENT '星星分配["1.5","1","0.5"]',
  `rank_point` varchar(500) NOT NULL COMMENT '名次对应比例["1","0","-1"]',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态  0-删除  1-正常',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for game_puzzle_account
-- ----------------------------
DROP TABLE IF EXISTS `game_puzzle_account`;
CREATE TABLE `game_puzzle_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `free_count` int(5) DEFAULT '0' COMMENT '剩余免费次数',
  `share_attend_count` int(5) DEFAULT '0' COMMENT '分享免费次数',
  `is_get_free` tinyint(1) DEFAULT '0' COMMENT '是否分享获得免费次数',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_puzzle_attend_record
-- ----------------------------
DROP TABLE IF EXISTS `game_puzzle_attend_record`;
CREATE TABLE `game_puzzle_attend_record` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(19) NOT NULL COMMENT '参与用户编号',
  `attend_num` varchar(32) DEFAULT NULL COMMENT '同一场游戏编号相同',
  `attend_type` tinyint(3) DEFAULT NULL COMMENT '参与类型1单机、2随机对战、3好友对战多人',
  `room_num` varchar(8) DEFAULT NULL COMMENT '房间号',
  `attend_count` int(11) DEFAULT NULL COMMENT '参与人数',
  `difficulty` tinyint(3) DEFAULT NULL COMMENT '难度 1、简单，2一般，3困难',
  `attend_point` int(8) DEFAULT NULL COMMENT '参与扣除积分',
  `attend_star` int(8) DEFAULT NULL COMMENT '参与扣除星星',
  `puzzle_pic_id` bigint(32) DEFAULT NULL COMMENT '拼图图库id',
  `status` tinyint(2) DEFAULT NULL COMMENT '0-初始化 1-参与成功 2-参与失败 3-游戏成功 4-游戏失败',
  `game_use_time` int(3) DEFAULT NULL COMMENT '游戏用时单位秒',
  `game_score` int(8) DEFAULT '0' COMMENT '游戏分数',
  `game_rank` int(8) DEFAULT '0' COMMENT '游戏名次',
  `reward_point` int(8) DEFAULT NULL COMMENT '参与成功奖励积分',
  `reward_star` int(8) DEFAULT NULL COMMENT '参与成功奖励星星',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for game_puzzle_config
-- ----------------------------
DROP TABLE IF EXISTS `game_puzzle_config`;
CREATE TABLE `game_puzzle_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `attend_max_point` int(8) DEFAULT '0' COMMENT '参入最大积分限制设置',
  `attend_point` int(8) DEFAULT NULL COMMENT '每次参与花费积分',
  `room_max_num` int(5) DEFAULT '0' COMMENT '每个房间最大可参入人数',
  `free_count` int(5) DEFAULT '0',
  `share_attend_count` int(5) DEFAULT '0' COMMENT '分享可免费次数',
  `count_down` int(5) DEFAULT '0' COMMENT '游戏倒计时',
  `pic_count` int(5) NOT NULL COMMENT '拼图张数',
  `award_point` int(5) DEFAULT '0' COMMENT '挑战成功奖励积分',
  `award_star` int(5) DEFAULT '0' COMMENT '挑战成功奖励星星',
  `deduct_star` int(5) DEFAULT '0' COMMENT '挑战失败扣除星星',
  `sec_score` varchar(500) DEFAULT NULL COMMENT '倒计时对应评分{"list":[{"second":3,"score":200},{"second":5,"score":150},{"second":10,"score":100}]}',
  `status` tinyint(2) DEFAULT '1' COMMENT '游戏状态  0-禁用  1-启用',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_puzzle_difficulty
-- ----------------------------
DROP TABLE IF EXISTS `game_puzzle_difficulty`;
CREATE TABLE `game_puzzle_difficulty` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) DEFAULT NULL COMMENT '难度系数  1-简单 2-一般 3-困难',
  `coefficient` int(5) NOT NULL COMMENT '拼图系数3|4|5（3x3|4x4|5x5）',
  `star` int(5) DEFAULT '0' COMMENT '困难对应星星',
  `point` int(8) DEFAULT NULL COMMENT '对应积分',
  `sec_score` varchar(500) NOT NULL COMMENT '倒计时对应评分{"list":[{"second":3,"score":200},{"second":5,"score":150},{"second":10,"score":100}]}',
  `challenge_time` int(5) NOT NULL COMMENT '挑战时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for game_puzzle_pic
-- ----------------------------
DROP TABLE IF EXISTS `game_puzzle_pic`;
CREATE TABLE `game_puzzle_pic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) DEFAULT '' COMMENT '用户编号',
  `user_nickname` varchar(50) DEFAULT '' COMMENT '用户昵称',
  `img_path` varchar(200) NOT NULL COMMENT '图片路径',
  `type` tinyint(2) unsigned NOT NULL COMMENT '类型',
  `is_simple` tinyint(1) unsigned NOT NULL COMMENT '简单',
  `is_common` tinyint(1) unsigned NOT NULL COMMENT '一般',
  `is_hard` tinyint(1) unsigned NOT NULL COMMENT '困难',
  `status` tinyint(2) unsigned NOT NULL COMMENT '1--启用，2--禁用',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for game_puzzle_user_pic
-- ----------------------------
DROP TABLE IF EXISTS `game_puzzle_user_pic`;
CREATE TABLE `game_puzzle_user_pic` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `user_nickname` varchar(50) DEFAULT '' COMMENT '用户昵称',
  `img_path` varchar(200) NOT NULL COMMENT '图片路径',
  `type` tinyint(2) unsigned NOT NULL COMMENT '类型',
  `is_simple` tinyint(1) unsigned NOT NULL COMMENT '简单',
  `is_common` tinyint(1) unsigned NOT NULL COMMENT '一般',
  `is_hard` tinyint(1) unsigned NOT NULL COMMENT '困难',
  `status` tinyint(2) unsigned NOT NULL COMMENT '1--待审核，2--已采用，3--未采用',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for game_room
-- ----------------------------
DROP TABLE IF EXISTS `game_room`;
CREATE TABLE `game_room` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '房主用户编号',
  `account` varchar(20) NOT NULL COMMENT '房主用户账号',
  `room_num` varchar(20) NOT NULL COMMENT '房间号',
  `player_info` varchar(2000) NOT NULL COMMENT '玩家信息',
  `point` decimal(10,2) unsigned NOT NULL COMMENT '入场积分',
  `pwd` char(57) DEFAULT '' COMMENT '密码',
  `type` tinyint(2) unsigned NOT NULL COMMENT '类型：1--头脑PK，2--拼图',
  `status` tinyint(2) unsigned NOT NULL COMMENT '状态：1--等待中，2--进行中，3--已结束',
  `hard_level` tinyint(2) unsigned DEFAULT '0' COMMENT '难易程度：1--简单，2--一般，3--困难，4--随机',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

-- ----------------------------
-- Table structure for transaction_record
-- ----------------------------
DROP TABLE IF EXISTS `transaction_record`;
CREATE TABLE `transaction_record` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `relate_id` bigint(20) unsigned NOT NULL COMMENT '关联ID',
  `type` tinyint(3) unsigned NOT NULL COMMENT '事务类型',
  `is_processed` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '已处理，0否，1是',
  `times` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '执行次数',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for user_star_record
-- ----------------------------
DROP TABLE IF EXISTS `user_star_record`;
CREATE TABLE `user_star_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(20) NOT NULL COMMENT '用户编号',
  `account` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `month_star_count` int(11) unsigned DEFAULT '0' COMMENT '当月星星增加总数',
  `gmt_report` date NOT NULL COMMENT '记录时间',
  `status` tinyint(1) DEFAULT '1' COMMENT '0:无效1：有效',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ;

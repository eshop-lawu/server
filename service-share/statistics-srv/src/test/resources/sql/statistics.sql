
SET FOREIGN_KEY_CHECKS=0;

drop table `report_ad_earnings`;
CREATE TABLE `report_ad_earnings` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`ad_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '广告id',
	`merchant_num` VARCHAR(19) NOT NULL COMMENT '商家编号',
	`merchant_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '商家名' ,
	`ad_title` VARCHAR(100) NULL DEFAULT NULL COMMENT '广告名' ,
	`ad_type` TINYINT(2) NOT NULL COMMENT '广告类型(1-平面2-视频3-抢赞4-红包)',
	`ad_create_time` DATETIME NOT NULL COMMENT '广告创建时间',
	`ad_status` TINYINT(2) NOT NULL COMMENT '状态(1-上架2-投放中3-投放结束)',
	`ad_total_point` DECIMAL(10,6) UNSIGNED NOT NULL COMMENT '广告总投放积分',
	`user_total_point` DECIMAL(10,6) UNSIGNED NOT NULL COMMENT '用户总收益',
	`love_total_point` DECIMAL(10,6) UNSIGNED NOT NULL COMMENT '爱心账户收益',
	`status` TINYINT(2) NOT NULL DEFAULT '1' COMMENT '状态(0异常1正常)',
	`is_processed` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否处理',
	`auditor_id` BIGINT(20) UNSIGNED NULL DEFAULT NULL COMMENT '操作人员ID',
	`auditor_account` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '操作人员用户名',
	`remark` VARCHAR(200) NULL DEFAULT NULL COMMENT '操作备注',
	`audit_time` DATETIME NULL DEFAULT NULL COMMENT '操作时间',
	`gmt_modified` DATETIME NOT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_earnings_daily`;
CREATE TABLE `report_earnings_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`ad_point` DECIMAL(10,6) UNSIGNED NOT NULL DEFAULT '0.000000' COMMENT '广告总投放积分',
	`user_point` DECIMAL(10,6) UNSIGNED NOT NULL DEFAULT '0.000000' COMMENT '用户总收益',
	`love_point` DECIMAL(10,6) UNSIGNED NOT NULL DEFAULT '0.000000' COMMENT '爱心账户总收益',
	`platform_point` DECIMAL(10,6) UNSIGNED NOT NULL DEFAULT '0.000000' COMMENT '平台总收益',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_earnings_month`;
CREATE TABLE `report_earnings_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`ad_point` DECIMAL(10,6) UNSIGNED NOT NULL DEFAULT '0.000000' COMMENT '广告总投放积分',
	`user_point` DECIMAL(10,6) UNSIGNED NOT NULL DEFAULT '0.000000' COMMENT '用户总收益',
	`love_point` DECIMAL(10,6) UNSIGNED NOT NULL DEFAULT '0.000000' COMMENT '爱心账户总收益',
	`platform_point` DECIMAL(10,6) UNSIGNED NOT NULL DEFAULT '0.000000' COMMENT '平台总收益',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_point_consume_daily`;
CREATE TABLE `report_point_consume_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员积分消费',
	`merchant_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家积分消费',
	`total_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总积分消费',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_point_consume_month`;
CREATE TABLE `report_point_consume_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员积分消费',
	`merchant_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家积分消费',
	`total_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总积分消费',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_recharge_balance_daily`;
CREATE TABLE `report_recharge_balance_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员充值余额',
	`merchant_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家充值余额',
	`total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总充值余额',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_recharge_balance_month`;
CREATE TABLE `report_recharge_balance_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员充值余额',
	`merchant_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家充值余额',
	`total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总充值余额',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_sales_daily`;
CREATE TABLE `report_sales_daily` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`pay_order_amount` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '买单金额',
	`shopping_order_amount` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '购物订单金额',
	`total_amount` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总金额',
	`gmt_report` DATETIME NOT NULL COMMENT '统计时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_sales_month`;
CREATE TABLE `report_sales_month` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`pay_order_amount` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '买单金额',
	`shopping_order_amount` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '购物订单金额',
	`total_amount` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总金额',
	`gmt_report` DATE NOT NULL COMMENT '统计日期',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_user_active_area_daily`;
CREATE TABLE `report_user_active_area_daily` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '活跃会员数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '活跃商家数',
	`city_id` INT(8) UNSIGNED NOT NULL DEFAULT '0' COMMENT '市级区域ID',
	`city_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '市级名称',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_user_active_area_month`;
CREATE TABLE `report_user_active_area_month` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '活跃会员数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '活跃商家数',
	`city_id` INT(8) UNSIGNED NOT NULL DEFAULT '0' COMMENT '市级区域ID',
	`city_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '市级名称',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属年月',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_user_active_daily`;
CREATE TABLE `report_user_active_daily` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '活跃会员数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '活跃商家数',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;


drop table `report_user_active_month`;
CREATE TABLE `report_user_active_month` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '活跃会员数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '活跃商家数',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属年月',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_user_income_expenditure`;
CREATE TABLE `report_user_income_expenditure` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
	`account` VARCHAR(20) NOT NULL COMMENT '用户账号',
	`user_type` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户类型，1是商家，2是会员',
	`income` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '收入金额',
	`expenditure` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '支出金额',
	`difference` DECIMAL(20,6) NOT NULL COMMENT '差值金额',
	`gmt_report` DATE NOT NULL COMMENT '统计日期',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_user_reg_area`;
CREATE TABLE `report_user_reg_area` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '会员总数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '商家总数',
	`merchant_common_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '普通商家数',
	`merchant_entity_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '实体商家数',
	`city_id` INT(8) UNSIGNED NOT NULL COMMENT '市级区域ID',
	`city_name` VARCHAR(50) NOT NULL COMMENT '市级名称',
	`gmt_update` DATETIME NOT NULL COMMENT '更新时间',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
;


drop table `report_user_reg_daily`;
CREATE TABLE `report_user_reg_daily` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册会员数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册商家数',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_user_reg_month`;
CREATE TABLE `report_user_reg_month` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册会员数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册商家数',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_withdraw_daily`;
CREATE TABLE `report_withdraw_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员提现成功金额(含手续费)',
	`merchant_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家提现成功金额(含手续费)',
	`total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总提现成功金额(含手续费)',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_withdraw_month`;
CREATE TABLE `report_withdraw_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员提现成功金额(含手续费)',
	`merchant_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家提现成功金额(含手续费)',
	`total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总提现成功金额(含手续费)',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;


drop table `user_visit_record`;
CREATE TABLE `user_visit_record` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_num` VARCHAR(19) NOT NULL COMMENT '用户编号',
	`account` VARCHAR(20) NOT NULL COMMENT '用户账号',
	`user_type` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户类型，1是会员，2是商家',
	`visit_count` INT(8) UNSIGNED NULL DEFAULT NULL COMMENT '访问次数',
	`city_id` INT(8) UNSIGNED NULL DEFAULT '0' COMMENT '市级区域ID',
	`city_name` VARCHAR(50) NULL DEFAULT '' COMMENT '市级名称',
	`visit_date` DATE NOT NULL COMMENT '访问日期',
	`gmt_create` DATETIME NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_ad_point_daily`;
CREATE TABLE `report_area_ad_point_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`report_total_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家发广告消费积分总和',
	`province_id` INT(8) NULL DEFAULT NULL COMMENT '省编号',
	`city_id` INT(8) NULL DEFAULT NULL COMMENT '市编号',
	`area_id` INT(8) NULL DEFAULT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_ad_point_month`;
CREATE TABLE `report_area_ad_point_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`report_total_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家发广告消费积分总和',
	`province_id` INT(8) NULL DEFAULT NULL COMMENT '省编号',
	`city_id` INT(8) NULL DEFAULT NULL COMMENT '市编号',
	`area_id` INT(8) NULL DEFAULT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_point_consume_daily`;
CREATE TABLE `report_area_point_consume_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员充值积分',
	`merchant_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家充值积分',
	`total_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总充值积分',
	`member_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员积分消费',
	`merchant_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家积分消费',
	`total_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总积分消费',
	`province_id` INT(11) NOT NULL COMMENT '省编号',
	`city_id` INT(11) NOT NULL COMMENT '市编号',
	`area_id` INT(11) NOT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_point_consume_month`;
CREATE TABLE `report_area_point_consume_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员充值积分',
	`merchant_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家充值积分',
	`total_recharge_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总充值积分',
	`member_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员积分消费',
	`merchant_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家积分消费',
	`total_point` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总积分消费',
	`province_id` INT(11) NOT NULL COMMENT '省编号',
	`city_id` INT(11) NOT NULL COMMENT '市编号',
	`area_id` INT(11) NOT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_recharge_daily`;
CREATE TABLE `report_area_recharge_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`merchant_recharge_balance` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '商家充值余额',
	`merchant_recharge_point` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '商家充值积分',
	`member_recharge_balance` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '用户充值余额',
	`member_recharge_point` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '用户充值积分',
	`total_recharge_balance` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '充值总余额',
	`total_recharge_point` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '充值总积分',
	`province_id` INT(8) NULL DEFAULT NULL COMMENT '省编号',
	`city_id` INT(8) NULL DEFAULT NULL COMMENT '市编号',
	`area_id` INT(8) NULL DEFAULT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_recharge_month`;
CREATE TABLE `report_area_recharge_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`merchant_recharge_balance` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '商家充值余额',
	`merchant_recharge_point` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '商家值积分',
	`member_recharge_balance` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '用户值余额',
	`member_recharge_point` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '用户充值积分',
	`total_recharge_balance` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '充值总余额',
	`total_recharge_point` DECIMAL(20,6) NOT NULL DEFAULT '0.000000' COMMENT '充值总积分',
	`province_id` INT(8) NULL DEFAULT NULL COMMENT '省编号',
	`city_id` INT(8) NULL DEFAULT NULL COMMENT '市编号',
	`area_id` INT(8) NULL DEFAULT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期(yyyy-mm-01)',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_user_reg_daily`;
CREATE TABLE `report_area_user_reg_daily` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '会员总数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '商家总数',
	`merchant_normal_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '普通商家数',
	`merchant_entity_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '实体商家数',
	`city_id` INT(8) UNSIGNED NOT NULL COMMENT '市级区域ID',
	`city_name` VARCHAR(50) NOT NULL COMMENT '市级名称',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;


drop table `report_area_user_reg_month`;
CREATE TABLE `report_area_user_reg_month` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '会员总数',
	`merchant_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '商家总数',
	`merchant_normal_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '普通商家数',
	`merchant_entity_count` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '实体商家数',
	`city_id` INT(8) UNSIGNED NOT NULL COMMENT '市级区域ID',
	`city_name` VARCHAR(50) NOT NULL COMMENT '市级名称',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;


drop table `report_area_volume_daily`;
CREATE TABLE `report_area_volume_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`report_total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '销售总额',
	`type` TINYINT(3) NOT NULL COMMENT '消费类型: 1--买单, 2--购物',
	`province_id` INT(8) NULL DEFAULT NULL COMMENT '省编号',
	`city_id` INT(8) NULL DEFAULT NULL COMMENT '市编号',
	`area_id` INT(8) NULL DEFAULT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_volume_month`;
CREATE TABLE `report_area_volume_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`report_total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '销售总额',
	`type` TINYINT(3) NOT NULL COMMENT '消费类型: 1--买单, 2--购物',
	`province_id` INT(8) NULL DEFAULT NULL COMMENT '省编号',
	`city_id` INT(8) NULL DEFAULT NULL COMMENT '市编号',
	`area_id` INT(8) NULL DEFAULT NULL COMMENT '区编号',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;


drop  table `report_area_withdraw_daily`;
CREATE TABLE `report_area_withdraw_daily` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员提现成功金额(含手续费)',
	`merchant_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家提现成功金额(含手续费)',
	`total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总提现成功金额(含手续费)',
	`city_id` INT(8) UNSIGNED NOT NULL COMMENT '市级区域ID',
	`city_name` VARCHAR(50) NOT NULL COMMENT '名称',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;

drop table `report_area_withdraw_month`;
CREATE TABLE `report_area_withdraw_month` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`member_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '会员提现成功金额(含手续费)',
	`merchant_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '商家提现成功金额(含手续费)',
	`total_money` DECIMAL(20,6) UNSIGNED NOT NULL COMMENT '总提现成功金额(含手续费)',
	`city_id` INT(8) UNSIGNED NOT NULL COMMENT '市级区域ID',
	`city_name` VARCHAR(50) NOT NULL COMMENT '名称',
	`gmt_report` DATE NOT NULL COMMENT '统计数据所属日期',
	`gmt_create` DATETIME NOT NULL COMMENT '统计时间',
	PRIMARY KEY (`id`)
)
;


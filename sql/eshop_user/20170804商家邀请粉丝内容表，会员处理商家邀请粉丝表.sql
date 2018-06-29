CREATE TABLE `fans_invite_content` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`merchant_id` BIGINT(20) NOT NULL COMMENT '商家id',
	`merchant_num` VARCHAR(19) NOT NULL COMMENT '商家编号',
	`url` VARCHAR(150) NOT NULL COMMENT '邀请内容里的图片',
	`logo_url` VARCHAR(150) NULL DEFAULT NULL COMMENT '商家logo',
	`merchant_store_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '店铺名称',
	`invite_content` VARCHAR(400) NOT NULL DEFAULT '' COMMENT '邀请内容',
	`merchant_store_intro` VARCHAR(500) NULL DEFAULT NULL COMMENT '店铺介绍',
	`fans_invite_detail_id` BIGINT(20) NOT NULL COMMENT '邀请粉丝记录的id',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='商家邀请粉丝内容表'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=9
;


CREATE TABLE `fans_invite_result` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`merchant_id` BIGINT(20) NOT NULL COMMENT '商家id',
	`member_id` BIGINT(20) NOT NULL COMMENT '会员id',
	`message_id` BIGINT(20) NOT NULL COMMENT '消息编号',
	`status` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '处理结果:0--拒绝，1--同意',
	`gmt_modified` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`gmt_create` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='用户粉丝邀请处理结果表'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;

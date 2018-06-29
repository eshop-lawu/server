-- 添加凭证图片和退款描述
ALTER TABLE `shopping_refund_detail` ADD COLUMN `voucher_picture` varchar(150) DEFAULT NULL COMMENT '凭证图片' AFTER `reason`;
ALTER TABLE `shopping_refund_detail` ADD COLUMN `description` varchar(200) DEFAULT NULL COMMENT '退款描述' AFTER `reason`;
ALTER TABLE `shopping_refund_detail` ADD COLUMN `refusal_reasons` varchar(200) DEFAULT NULL COMMENT '拒绝退款理由' AFTER `voucher_picture`;
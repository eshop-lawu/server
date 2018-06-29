ALTER TABLE pay_order ADD COLUMN `not_favored_amount` decimal(10,2) DEFAULT '0.00' COMMENT '不参与优惠金额' AFTER `favored_amount`;

ALTER TABLE pay_order ADD COLUMN `order_num` varchar(30) NOT NULL COMMENT '买单编号' AFTER `merchant_num`;

ALTER TABLE pay_order ADD COLUMN `third_number` varchar(50) DEFAULT NULL COMMENT '第三方支付编号' AFTER `order_num`;
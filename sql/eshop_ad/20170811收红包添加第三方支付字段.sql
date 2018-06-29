alter table user_red_packet add column  `order_num`  varchar(30) not Null  COMMENT '买单编号' after user_account;
alter table user_red_packet add column  `third_number`  varchar(50) COMMENT '第三方支付编号' after order_num;
alter table user_red_packet add column  `pay_type`  tinyint(2) COMMENT '支付类型' after third_number;
alter table user_red_packet add column  `refund_money`  decimal(18,4) DEFAULT '0' COMMENT '退款金额' after total_money;
ALTER TABLE user_red_packet ADD UNIQUE KEY(order_num, third_number);  
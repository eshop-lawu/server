-- 订单新增创建订单失败和订单备注
ALTER TABLE `shopping_order` MODIFY COLUMN `remark`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单备注' AFTER `consignee_mobile`;
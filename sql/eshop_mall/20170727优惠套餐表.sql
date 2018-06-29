-- 添加提前预约时间
ALTER TABLE `discount_package` ADD COLUMN `advance_booking_time` varchar(10) DEFAULT NULL COMMENT '提前预约时间(xx小时|xx分钟|)' AFTER `is_reservation`;
ALTER TABLE `discount_package` ADD COLUMN `purchase_notes` varchar(100) DEFAULT NULL COMMENT '购买须知' AFTER `advance_booking_time`;

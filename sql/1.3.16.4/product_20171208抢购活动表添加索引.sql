-- 添加索引，优化查询
use eshop_product;
ALTER TABLE `seckill_activity`
ADD KEY `idx_start_date_activity_status_status` (`start_date`,`activity_status`,`status`) USING BTREE;

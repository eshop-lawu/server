-- 添加索引，优化查询
use eshop_product;
ALTER TABLE `seckill_activity_product`
ADD UNIQUE KEY `uk_activity_id_product_id` (`activity_id`, `product_id`);
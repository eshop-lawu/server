use eshop_activity;
-- 更改E钻分配表
rename table diamond_distribution_record to rich_diamond_distribution_record;

-- 插入瑞奇岛信息初始化记录
INSERT INTO `eshop_activity`.`rich_diamond_info`(`id`, `diamond_sent`, `diamond_lucky_sent`, `gmt_modified`, `gmt_create`) VALUES (1, 0.000000, 0.000000, '2018-05-21 15:09:15', '2018-05-21 15:09:17');
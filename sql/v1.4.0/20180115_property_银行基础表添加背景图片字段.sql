alter table eshop_property.bank add column `bg_url` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '背景图路径' after icon_url;

update eshop_property.bank set bg_url = CONCAT(left(icon_url,LENGTH(icon_url)-4) , '_bg.png');
-- 新增is_show,用于判断是否在快递列表中显示列表
ALTER TABLE `express_company` ADD COLUMN `is_show` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否显示在列表' AFTER `ordinal`;
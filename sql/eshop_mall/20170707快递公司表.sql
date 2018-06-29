-- 现在按照name首字母排序,对应ASCII编码的十进制数
ALTER TABLE `express_company` MODIFY COLUMN `ordinal`  int(11) UNSIGNED NOT NULL COMMENT '排序(对应name首字母ASCII编码的十进制)' AFTER `tel`;
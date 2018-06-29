ALTER TABLE `eshop_operator`.`log` 
MODIFY COLUMN `change_content` varchar(1000)  COMMENT '变更内容(JSON数据)' AFTER `change_title`;
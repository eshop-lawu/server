-- 增加topic的长度
ALTER TABLE `follow_transaction_record` MODIFY COLUMN `topic`  varchar(30) NOT NULL COMMENT 'MQ消息的topic' AFTER `transation_id`;
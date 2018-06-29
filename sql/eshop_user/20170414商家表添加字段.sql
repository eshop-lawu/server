ALTER TABLE merchant ADD COLUMN `gt_cid` varchar(100) DEFAULT NULL COMMENT '个推CID' AFTER `level`;
ALTER TABLE merchant ADD COLUMN `ry_token` varchar(100) DEFAULT NULL COMMENT '融云token' AFTER `gt_cid`;
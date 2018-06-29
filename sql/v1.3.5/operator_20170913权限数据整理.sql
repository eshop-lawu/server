update permission set permission_key='property:freeze' where id=74;
update permission set permission_key='property:unfreeze' where id=77;
INSERT INTO `eshop_operator`.`permission` (`permission_name`, `permission_key`, `permission_url`, `parent_id`, `gmt_modified`, `gmt_create`) VALUES ('会员管理/冻结/解冻', 'account:freezeOper', NULL, '101', '2017-09-13 15:46:41', '2017-09-13 15:46:45');
INSERT INTO `eshop_operator`.`permission` (`permission_name`, `permission_key`, `permission_url`, `parent_id`, `gmt_modified`, `gmt_create`) VALUES ('举报管理/下架/冻结/不予处理', 'inform:edit', NULL, '97', '2017-09-13 16:19:03', '2017-09-13 16:19:06');
INSERT INTO `eshop_operator`.`permission` (`permission_name`, `permission_key`, `permission_url`, `parent_id`, `gmt_modified`, `gmt_create`) VALUES ('工单列表/处理/不予处理', 'workOrder:edit', NULL, '99', '2017-09-13 16:53:45', '2017-09-13 16:53:47');


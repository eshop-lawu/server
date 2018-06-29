USE `eshop_operator`;
INSERT INTO `permission` VALUES (185, '积分抽奖', 'point_lottery:list', 'lottery/point-activity-list.html', 137, 999, '2018-02-06 10:37:00', '2018-02-06 10:37:01');
INSERT INTO `permission` VALUES (186, '新增/编辑积分抽奖', 'point_lottery:add', NULL, 185, 999, '2018-02-06 10:38:11', '2018-02-06 10:38:11');
INSERT INTO `permission` VALUES (187, '发布积分抽奖', 'point_lottery:publish', NULL, 185, 999, '2018-02-06 10:38:51', '2018-02-06 10:38:52');
INSERT INTO `permission` VALUES (188, '删除积分抽奖', 'point_lottery:del', NULL, 185, 999, '2018-02-06 10:39:19', '2018-02-06 10:39:19');
INSERT INTO `eshop_operator`.`permission`(`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES (189, '生成基础号码', 'pointLotteryActivity:generateBasicNumber', '', 185, 999, '2018-02-07 11:51:59', '2018-02-07 11:52:01');
INSERT INTO `eshop_operator`.`permission`(`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES (190, '保存中奖号码', 'pointLotteryActivity:saveWinningNumber', NULL, 185, 999, '2018-02-07 11:52:24', '2018-02-07 11:52:26');
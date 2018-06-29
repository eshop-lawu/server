USE `eshop_operator`;
UPDATE `eshop_operator`.`permission` SET `permission_name` = '助力红包管理', `permission_key` = 'helpRedpacketActivity:list', `permission_url` = 'help-redpacket-activity/help-redpacket-activity-list.html', `parent_id` = 137, `sort_id` = 999, `gmt_modified` = '2018-01-15 13:49:38', `gmt_create` = '2017-12-28 18:22:49' WHERE `id` = 168;
INSERT INTO `eshop_operator`.`permission`(`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES (172, '助力红包详情', 'helpRedpacketActivity:detail', '', 168, 999, '2018-01-15 13:49:38', '2018-01-15 13:49:38');
INSERT INTO `eshop_operator`.`permission`(`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES (173, '新增红包活动', 'helpRedpacketActivity:save', '', 168, 999, '2018-01-15 14:15:45', '2018-01-15 14:15:51');

INSERT INTO `permission` VALUES (174, '抽奖活动', 'draw_lottery:list', 'lottery/draw-activity-list.html', 137, 999, '2018-01-17 10:08:53', '2018-01-17 10:08:56');
INSERT INTO `permission` VALUES (175, '新增/编辑抽奖', 'draw_lottery:add', NULL, 174, 999, '2018-01-17 10:10:47', '2018-01-17 10:10:50');
INSERT INTO `permission` VALUES (176, '发布抽奖', 'draw_lottery:publish', NULL, 174, 999, '2018-01-17 10:11:45', '2018-01-17 10:11:50');
INSERT INTO `permission` VALUES (177, '下架抽奖', 'draw_lottery:cancel', NULL, 174, 999, '2018-01-17 10:12:16', '2018-01-17 10:12:18');
INSERT INTO `permission` VALUES (178, '删除抽奖', 'draw_lottery:del', NULL, 174, 999, '2018-01-17 10:12:44', '2018-01-17 10:12:46');
INSERT INTO `permission` VALUES (179, '抽奖记录', 'draw_record:list', NULL, 174, 999, '2018-01-17 10:46:56', '2018-01-17 10:46:57');
INSERT INTO `permission` VALUES (180, '奖品详情', 'draw_prize:list', NULL, 174, 999, '2018-01-17 10:47:17', '2018-01-17 10:47:21');
INSERT INTO `permission` VALUES (181, '新增/编辑奖品', 'draw_prize:add', NULL, 174, 999, '2018-01-17 11:24:18', '2018-01-17 11:24:19');
INSERT INTO `permission` VALUES (182, '删除奖品', 'draw_prize:del', NULL, 174, 999, '2018-01-17 14:18:17', '2018-01-17 14:18:18');
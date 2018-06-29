-- 初始化会员等级
INSERT INTO `eshop_user`.`user_grade` ( `grade_name`, `grade_value`, `grade_weight`, `min_growth_value`, `lottery_activity_point`, `gmt_modified`, `gmt_create`) VALUES ('白银会员', '1', '10', '0', '10', '2017-11-27 17:16:58', '2017-11-24 10:37:16');
INSERT INTO `eshop_user`.`user_grade` ( `grade_name`, `grade_value`, `grade_weight`, `min_growth_value`, `lottery_activity_point`, `gmt_modified`, `gmt_create`) VALUES ('黄金会员', '2', '20', '1000', '20', '2017-11-27 16:56:43', '2017-11-24 10:37:42');
INSERT INTO `eshop_user`.`user_grade` ( `grade_name`, `grade_value`, `grade_weight`, `min_growth_value`, `lottery_activity_point`, `gmt_modified`, `gmt_create`) VALUES ('‌铂金会员', '3', '30', '5000', '30', '2017-11-24 10:38:14', '2017-11-24 10:38:16');
INSERT INTO `eshop_user`.`user_grade` ( `grade_name`, `grade_value`, `grade_weight`, `min_growth_value`, `lottery_activity_point`, `gmt_modified`, `gmt_create`) VALUES ('钻石会员', '4', '40', '15000', '40', '2017-11-24 10:38:49', '2017-11-24 10:38:51');
INSERT INTO `eshop_user`.`user_grade` ( `grade_name`, `grade_value`, `grade_weight`, `min_growth_value`, `lottery_activity_point`, `gmt_modified`, `gmt_create`) VALUES ('皇冠会员', '5', '50', '30000', '50', '2017-11-24 10:39:17', '2017-11-24 10:39:19');

-- 初始化会员等级权限
INSERT INTO `eshop_operator`.`permission` (`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES ('144', '等级编辑', 'grade:edit', NULL, '143', '999', '2017-11-27 14:37:39', '2017-11-27 14:37:42');
INSERT INTO `eshop_operator`.`permission` (`id`, `permission_name`, `permission_key`, `permission_url`, `parent_id`, `sort_id`, `gmt_modified`, `gmt_create`) VALUES ('143', '等级管理', 'grade:list', 'member/grade-list.html', '100', '999', '2017-11-27 14:35:33', '2017-11-27 14:35:35');

-- 初始化超级管理员拥有会员等级模块权限
INSERT INTO `eshop_operator`.`role_permission` (`id`, `role_id`, `permission_id`, `gmt_create`) VALUES ('5642', '1', '144', '2017-11-27 14:40:45');
INSERT INTO `eshop_operator`.`role_permission` (`id`, `role_id`, `permission_id`, `gmt_create`) VALUES ('5641', '1', '143', '2017-11-27 14:40:45');
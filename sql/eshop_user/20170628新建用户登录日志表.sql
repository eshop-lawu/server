CREATE TABLE `user_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` varchar(19) NOT NULL COMMENT '用户编号',
  `account` varchar(20) NOT NULL COMMENT '用户账号',
  `user_type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户类型，1是商家，2是会员',
  `imei` varchar(20)  COMMENT '设备imei',
  `platform` tinyint(2) NOT NULL COMMENT '请求类型平台。iOS、Android、pcweb、h5',
  `platform_ver` varchar(20)  COMMENT '设备的版本号',
  `app_ver` varchar(12)  COMMENT '请求来源的version，指app的版本号',
  `city_id` int(8) unsigned COMMENT '定位到的区域id',
  `channel` varchar(15)  COMMENT '分发渠道标识',
  `ip_addr` varchar(15)  COMMENT '请求IP',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='用户登录日志';
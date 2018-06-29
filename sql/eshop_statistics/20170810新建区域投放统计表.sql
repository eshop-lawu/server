CREATE  TABLE  `report_area_ad_point_daily`  (
    `id`  BIGINT(20)  NOT  NULL  AUTO_INCREMENT  COMMENT  '主键',
    `report_total_point`  DECIMAL(20,6)  UNSIGNED  NOT  NULL  COMMENT  '商家发广告消费积分总和',
    `province_id`  INT(8)  COMMENT  '省编号',
    `city_id`  INT(8)  COMMENT  '市编号',
    `area_id`  INT(8)  COMMENT  '区编号',
    `gmt_report`  DATE  NOT  NULL  COMMENT  '统计数据所属日期',
    `gmt_create`  DATETIME  NOT  NULL  COMMENT  '统计时间',
    PRIMARY  KEY  (`id`)
)
COMMENT='区域商家投放积分总额统计(按天)'
;

CREATE  TABLE  `report_area_ad_point_month`  (
    `id`  BIGINT(20)  NOT  NULL  AUTO_INCREMENT  COMMENT  '主键',
    `report_total_point`  DECIMAL(20,6)  UNSIGNED  NOT  NULL  COMMENT  '商家发广告消费积分总和',
    `province_id`  INT(8)  COMMENT  '省编号',
    `city_id`  INT(8)  COMMENT  '市编号',
    `area_id`  INT(8)  COMMENT  '区编号',
    `gmt_report`  DATE  NOT  NULL  COMMENT  '统计数据所属日期',
    `gmt_create`  DATETIME  NOT  NULL  COMMENT  '统计时间',
    PRIMARY  KEY  (`id`)
)
COMMENT='区域商家投放积分总额统计(按月)'
;
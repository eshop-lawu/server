alter table `point_pool`  modify column  `status` tinyint(2) NOT NULL COMMENT '状态(0-未分配1-已分配2-过期)' after point;


alter table member_ad_record add original_point decimal(10,2)  COMMENT '原始积分' after point;

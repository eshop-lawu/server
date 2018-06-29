alter table `point_pool`  modify column  `status` tinyint(2) NOT NULL COMMENT '状态(0-未分配1-已分配2-过期)' after point;

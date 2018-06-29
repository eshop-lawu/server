
alter table merchant_favored modify column `entire_begin_time` date NOT NULL COMMENT '总有效期：开始时间';

alter table merchant_favored modify column `entire_end_time` date NOT NULL COMMENT '总有效期：结束时间';
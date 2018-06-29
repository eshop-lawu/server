alter table invite_relation drop column user_id;
alter table invite_relation drop column invited_user_id;
alter table invite_relation add column `invite_user_num` VARCHAR(19) NOT NULL COMMENT '被邀请用户编号' after `id`;
alter table invite_relation add column `user_num` VARCHAR(19) NOT NULL COMMENT '用户编号' after `id`;

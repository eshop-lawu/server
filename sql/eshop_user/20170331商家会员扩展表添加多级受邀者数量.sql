alter table member_profile modify column `invite_member_count` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请会员数(一级)';
alter table member_profile add column `invite_member_count2` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请会员数(二级)' after `invite_member_count`;
alter table member_profile add column `invite_member_count3` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请会员数(三级)' after `invite_member_count2`;
alter table member_profile modify column `invite_merchant_count` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请商家数(一级)';
alter table member_profile add column `invite_merchant_count2` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请商家数(二级)' after `invite_merchant_count`;
alter table member_profile add column `invite_merchant_count3` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请商家数(三级)' after `invite_merchant_count2`;

alter table merchant_profile modify column `invite_member_count` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请会员数(一级)';
alter table merchant_profile add column `invite_member_count2` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请会员数(二级)' after `invite_member_count`;
alter table merchant_profile add column `invite_member_count3` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请会员数(三级)' after `invite_member_count2`;
alter table merchant_profile modify column `invite_merchant_count` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请商家数(一级)';
alter table merchant_profile add column `invite_merchant_count2` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请商家数(二级)' after `invite_merchant_count`;
alter table merchant_profile add column `invite_merchant_count3` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '邀请商家数(三级)' after `invite_merchant_count2`;
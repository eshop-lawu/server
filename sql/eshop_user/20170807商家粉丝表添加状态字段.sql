alter table `fans_merchant` add column `status` tinyint(2) not null default '1' comment '粉丝状态: 0--邀请了未成为粉丝, 1--成为粉丝'  after `member_id`;


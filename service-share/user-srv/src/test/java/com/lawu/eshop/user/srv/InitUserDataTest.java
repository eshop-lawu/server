package com.lawu.eshop.user.srv;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author jiangxinjun
 * @date 2017年9月26日
 */
public class InitUserDataTest {
	
	@Ignore
	@Test
	public void test() {
		long start_id = 45;
		String start_num = "M9088680090979532";
		String start_account = "156161759";
		String member_recored = "INSERT INTO `eshop_user`.`member` (`id`, `num`, `account`, `pwd`, `mobile`, `name`, `nickname`, `region_path`, `region_name`, `sex`, `birthday`, `headimg`, `status`, `is_freeze`, `freeze_reason`, `inviter_id`, `inviter_type`, `level`, `gt_cid`, `ry_token`, `gmt_modified`, `gmt_create`, `property_id`) VALUES ('%d', '%s', '%s', '23d84747f83a59910801d70f18171206958d487976196d31', '15616175989', NULL, NULL, '44/4403/440305', '广东省深圳市南山区', '0', '2017-09-16', 'default/user_headimg.png', '1', '0', NULL, '0', '0', '1', 'f153b49323cb91bcc1f396d48e20cdd2', 'pKgQcUQ7TRCUT1SQAARYNuQDGfBgD7/u3A6QOX9LRN4wFvqxsZOD5GlqksYO3gkEk3gRyNjA1eB12goXrnuqMNh3ox5QcNve3+FUprf5gTr2wZtDFi490A==', NULL, NOW(), NULL);%n";
		String member_profile_recored = "INSERT INTO `eshop_user`.`member_profile` (`id`, `invite_member_count`, `invite_member_count2`, `invite_member_count3`, `invite_merchant_count`, `invite_merchant_count2`, `invite_merchant_count3`, `gmt_modified`, `gmt_create`) VALUES ('%d', '0', '0', '0', '0', '0', '0', NULL, NOW());%n";
		String invite_relation_recored = "INSERT INTO `eshop_user`.`invite_relation` (`user_num`, `invite_user_num`, `depth`, `type`) VALUES ('%s', '%s', '0', '1');%n";
		String property_info_recored = "INSERT INTO `eshop_property`.`property_info` (`user_num`, `balance`, `point`, `love_account`, `freeze_money`, `pay_password`, `freeze`, `gmt_modified`, `gmt_create`) VALUES ('%s', '0.000000', '0.000000', '0.000000', '0.000000', NULL, '0', NULL, NOW());%n";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 50; i++) {
			Long id = start_id++;
			String num = start_num + (i < 10 ? "0" + i : i);
			String accout = start_account + (i < 10 ? "0" + i : i);
			sb.append(String.format(member_recored, id, num, accout));
			sb.append(String.format(member_profile_recored, id));
			sb.append(String.format(property_info_recored, num));
			sb.append(String.format(invite_relation_recored, num, num));
		}
		System.out.println(sb.toString());
	}
}

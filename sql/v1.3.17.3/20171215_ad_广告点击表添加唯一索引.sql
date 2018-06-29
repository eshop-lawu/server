alter table eshop_ad.point_pool add UNIQUE KEY uk_member_ad(member_id, ad_id);
alter table eshop_ad.member_ad_record add UNIQUE KEY uk_member_ad_date(member_id, ad_id, click_date);
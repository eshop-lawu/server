-- ad
alter table ad add KEY idx_merchant_id_status(merchant_id, status);
alter table ad add KEY idx_merchant_id_type_status(merchant_id, type, status);
alter table ad add KEY idx_merchant_num(merchant_num);

alter table ad_region add KEY idx_ad_id(ad_id);

alter table favorite_ad add KEY idx_member_id(member_id);

alter table member_ad_record add KEY idx_member_num(member_num);
alter table member_ad_record add KEY idx_ad_id(ad_id);
alter table member_ad_record add KEY idx_member_id_click_date(member_id, click_date);

alter table point_pool add KEY idx_member_id(member_id);
alter table point_pool add KEY idx_member_num(member_num);
alter table point_pool add KEY idx_ad_id_status(ad_id, status);

alter table transaction_record add UNIQUE KEY uk_relate_id_type(relate_id, type);

-- mall
alter table comment_image add KEY idx_comment_id_type_status(comment_id, type, status);

alter table comment_merchant add KEY idx_merchant_id_status(merchant_id, status);

alter table comment_product add KEY idx_product_id_status(product_id, status);

alter table express_company add UNIQUE KEY uk_code(code);

alter table merchant_favored add KEY idx_merchant_id_status(merchant_id, status);

alter table message add KEY idx_user_num_status(user_num, status);

alter table sms_record add KEY idx_mobile(mobile);

alter table verify_code add KEY idx_mobile_purpose(mobile, purpose);

alter table transaction_record add UNIQUE KEY uk_relate_id_type(relate_id, type);

-- order
alter table pay_order add UNIQUE KEY uk_order_num(order_num);
alter table pay_order add KEY idx_member_id_status(member_id, status);
alter table pay_order add KEY idx_merchant_id_status(merchant_id, status);

alter table shopping_cart add KEY idx_member_id(member_id);

alter table shopping_order add UNIQUE KEY uk_order_num(order_num);
alter table shopping_order add KEY idx_member_id_status_order_status(member_id, status, order_status);
alter table shopping_order add KEY idx_merchant_id_status_order_status(merchant_id, status, order_status);

alter table shopping_order_item add KEY idx_shopping_order_id(shopping_order_id);

alter table shopping_refund_detail add KEY idx_shopping_order_item_id(shopping_order_item_id);

alter table transaction_record add UNIQUE KEY uk_relate_id_type(relate_id, type);

-- product
alter table favorite_product add KEY idx_member_id(member_id);

alter table product add KEY idx_merchant_id_status_sales(merchant_id, status, total_sales_volume);

alter table product_category add KEY idx_parent_id(parent_id);

alter table product_image add KEY idx_product_id_status_img_type_sortid(product_id, status, img_type, sortid);

alter table product_model add KEY idx_product_id_status(product_id, status);

alter table product_model_inventory add KEY idx_product_model_id(product_model_id);

alter table transaction_record add UNIQUE KEY uk_relate_id_type(relate_id, type);

-- property
alter table bank_account add KEY idx_user_num_status(user_num, status);

alter table business_deposit add KEY idx_business_id(business_id);
alter table business_deposit add KEY idx_user_num(user_num);

alter table fans_invite_detail add KEY idx_merchant_id(merchant_id);

alter table freeze add KEY idx_user_num_status(user_num, status);

alter table love_detail add UNIQUE KEY uk_love_num(love_num);
alter table love_detail add KEY idx_user_num_gmt_create(user_num, gmt_create);

alter table point_detail add UNIQUE KEY uk_point_num(point_num);
alter table point_detail add KEY idx_user_num_gmt_create(user_num, gmt_create);

alter table recharge add KEY idx_user_num(user_num);

alter table transaction_detail add UNIQUE KEY uk_transaction_num(transaction_num);
alter table transaction_detail add KEY idx_user_num_gmt_create(user_num, gmt_create);

alter table withdraw_cash add KEY idx_user_num(user_num);

alter table transaction_record add UNIQUE KEY uk_relate_id_type(relate_id, type);

-- user
alter table address add KEY idx_user_num(user_num);

alter table fans_merchant add KEY idx_merchant_id(merchant_id);
alter table fans_merchant add KEY idx_member_id(member_id);

alter table favorite_merchant add KEY idx_member_id(member_id);

alter table invite_relation add KEY idx_user_num(user_num);
alter table invite_relation add KEY idx_invite_user_num_depth(invite_user_num, depth);

alter table merchant add UNIQUE KEY uk_account(account);
alter table merchant add KEY idx_mobile(mobile);

alter table merchant_store add KEY idx_merchant_id(merchant_id);

alter table merchant_store_image add KEY idx_merchant_id_status(merchant_id, status);
alter table merchant_store_image add KEY idx_merchant_store_id_status(merchant_store_id, status);

alter table merchant_store_profile add KEY idx_merchant_id(merchant_id);

alter table transaction_record add UNIQUE KEY uk_relate_id_type(relate_id, type);

-- operator
alter table permission add UNIQUE KEY uk_permission_key(permission_key);

alter table role add UNIQUE KEY uk_role_key(role_key);

alter table role_permission add KEY idx_role_id(role_id);

alter table user add UNIQUE KEY uk_account(account);

alter table user_role add KEY idx_user_id(user_id);

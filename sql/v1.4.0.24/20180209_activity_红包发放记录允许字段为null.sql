use eshop_activity;
alter table redpacket_send_record MODIFY return_code varchar(16) null;
alter table redpacket_send_record MODIFY send_list_id varchar(32) null;
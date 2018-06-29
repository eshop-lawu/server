--20170418
--订单流程和退款流程的时间节点的配置
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (3, "automatic_cancel_order", "2", "自动取消未付款订单的时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (4, "automatic_remind_shipments", "5", "自动提醒卖家发货时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (5, "automatic_receipt", "14", "自动收货时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (6, "delete_order", "7", "删除订单时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (7, "to_be_confirmed_for_refund_remind_time", "2", "买家申请退款，提醒处理商家时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (8, "to_be_confirmed_for_refund_refund_time", "3", "买家申请退款，商家未处理，平台自动退款时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (9, "to_be_confirmed_for_return_refund_remind_time", "5", "买家申请退货退款，提醒处理商家时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (10, "to_be_confirmed_for_return_refund_refund_time", "7", "买家申请退货退款，商家未处理，平台自动退款时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (11, "refund_failed_remind_time", "5", "商家拒绝退款，提醒买家处理时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (12, "refund_failed_revoke_refund_time", "7", "商家拒绝退款，买家未处理，平台自动撤销退款时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (13, "fill_return_address_remind_time", "5", "商家填写退货地址,平台提醒商家操作时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (14, "fill_return_address_refund_time", "7", "商家填写退货地址,商家未操作，平台自动退款时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (15, "to_be_returned_remind_time", "5", "买家填写退货物流信息,平台提醒买家操作时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (16, "to_be_returned_revoke_time", "7", "买家填写退货物流信息,买家未操作，平台自动撤销退款申请时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (17, "to_be_refunded_remind_first_time", "10", "等待商家退款,平台第一次提醒商家操作时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (18, "to_be_refunded_remind_second_time", "13", "等待商家退款,平台第二次提醒商家操作时间", NOW(), NOW());
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (19, "to_be_returned_refund_time", "14", "等待商家退款,商家未操作，平台自动退款时间", NOW(), NOW());
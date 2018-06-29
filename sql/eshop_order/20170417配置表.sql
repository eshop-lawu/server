/**
 * 添加自动取消订单时间配置
 */
INSERT INTO property (id, name, value, remark, gmt_create, gmt_modified) VALUES (3, "automatic_cancel_order", "2", "自动取消未付款订单的时间", NOW(), NOW())
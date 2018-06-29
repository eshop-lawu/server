-- 商家跟用户的称呼统一为E店商家和E店用户
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店用户，您的订单{1}尚未付款，点击立即付款。' WHERE `id`='2';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店用户，您的商品{9}正在派件（{20}）。运单编号：{2}。' WHERE `id`='3';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店用户，您已签收商品{9}' WHERE `id`='4';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E点用户，您购买的商品{9}已发货' WHERE `id`='5';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店用户，商家已同意您的退款申请，退款金额为{21}元。点击查看退款进度。退款编号：{8}' WHERE `id`='12';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店用户，商家拒绝您的退款申请，您可以申请平台介入' WHERE `id`='13';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店用户，您购买的商品{9}已成功付款，等待商家发货' WHERE `id`='15';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店用户，商家已经填写退货地址，请您及时退货' WHERE `id`='42';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店商家，买家申请退货，请及时处理，订单编号：{1}' WHERE `id`='33';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店商家，买家的退货申请（订单号：{1}）已发货。运单编号：{2}' WHERE `id`='28';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店商家，买家申请退款，请及时处理' WHERE `id`='35';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店用户，您已成功退款，退款金额为{21}元' WHERE `id`='14';
UPDATE `eshop_mall`.`message_template` SET `content`='亲爱的E店商家，买家的退货申请（订单号：{1}）已发货。运单编号：{2}' WHERE `id`='34';
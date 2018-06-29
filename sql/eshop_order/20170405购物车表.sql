/*2017/04/05 删除购物车表的非必须字段*/
ALTER TABLE shopping_cart DROP COLUMN product_name;
ALTER TABLE shopping_cart DROP COLUMN product_model_name;
ALTER TABLE shopping_cart DROP COLUMN regular_price;
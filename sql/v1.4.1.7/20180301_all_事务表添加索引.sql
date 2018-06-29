ALTER TABLE `eshop_ad`.`transaction_record`
DROP INDEX `uk_relate_id_type`,
ADD UNIQUE INDEX `uk_type_relate_id`(`type`, `relate_id`) USING BTREE;

ALTER TABLE `eshop_mall`.`transaction_record`
DROP INDEX `uk_relate_id_type`,
ADD UNIQUE INDEX `uk_type_relate_id`(`type`, `relate_id`) USING BTREE;

ALTER TABLE `eshop_order`.`transaction_record`
DROP INDEX `uk_relate_id_type`,
ADD UNIQUE INDEX `uk_type_relate_id`(`type`, `relate_id`) USING BTREE;

ALTER TABLE `eshop_product`.`transaction_record`
DROP INDEX `uk_relate_id_type`,
ADD UNIQUE INDEX `uk_type_relate_id`(`type`, `relate_id`) USING BTREE;

ALTER TABLE `eshop_property`.`transaction_record`
DROP INDEX `uk_relate_id_type`,
ADD UNIQUE INDEX `uk_type_relate_id`(`type`, `relate_id`) USING BTREE;

ALTER TABLE `eshop_user`.`transaction_record`
DROP INDEX `uk_relate_id_type`,
ADD UNIQUE INDEX `uk_type_relate_id`(`type`, `relate_id`) USING BTREE;
-- 删除市辖区
DELETE FROM region WHERE name = "市辖区";

-- 嘉峪关市没有三级选项
INSERT INTO `eshop_mall`.`region` (`id`, `parent_id`, `path`, `level`, `name`) VALUES ('620201', '6202', '62/6202/620201', '3', '市辖区');
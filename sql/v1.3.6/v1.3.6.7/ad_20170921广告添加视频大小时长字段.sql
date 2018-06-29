alter table ad add column  `file_size` varchar(10)  COMMENT '文件大小' after media_url;

alter table ad add column  `file_time` varchar(10)  COMMENT '文件时长(视频)' after file_size;
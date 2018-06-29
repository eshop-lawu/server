alter table `merchant`  modify  column  `pwd` char(57) not null  COMMENT '登录密码';
alter table `member`  modify  column  `pwd` char(57) not null  COMMENT '登录密码';
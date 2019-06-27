

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `nickname` varchar(16) NOT NULL COMMENT '用户昵称',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `submit_token` varchar(512) NOT NULL COMMENT '用户提交凭证',
  `salt` varchar(16) NOT NULL COMMENT '盐',
  `status` tinyint(11) NOT NULL DEFAULT '1' COMMENT '用户状态(1.正常,2锁定)',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `head_img` varchar(128) NOT NULL DEFAULT '' COMMENT '用户头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`nickname`,`password`,`submit_token`,`salt`,`status`,`create_date`,`update_time`,`head_img`) values (4,'a','a','a','B1C6C3215FD27C23BB8B70FAC1A3B734','P95b8QWk',1,'2019-03-27 14:50:42','2019-03-27 14:50:42',''),(5,'a','a','a','7FFF1BD6CE01503C789505D603427706','KQARO6zh',1,'2019-03-27 14:50:50','2019-03-27 14:50:50',''),(6,'a','a','a','2C9046C9DD386595A93EF1DDC3DE68A3','xHm9PqfI',1,'2019-03-27 14:51:04','2019-03-27 14:51:04','');

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL DEFAULT '' COMMENT '文章标题 ',
  `subtitle` varchar(128) NOT NULL COMMENT '文章副标题',
  `submit_token` varchar(128) NOT NULL COMMENT '文章提交凭证',
  `salt` varchar(128) NOT NULL COMMENT '盐',
  `admin_id` bigint(20) NOT NULL COMMENT '用户ID',
  `context` text NOT NULL COMMENT '文章内容',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态(-1 删除 ,0. 待发布 ,1.正常)',
  `view_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `up_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `down_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article` */

/*Table structure for table `article_label` */

DROP TABLE IF EXISTS `article_label`;

CREATE TABLE `article_label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `label_id` bigint(20) NOT NULL COMMENT '标签ID',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article_label` */

/*Table structure for table `label` */

DROP TABLE IF EXISTS `label`;

CREATE TABLE `label` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '类别名称 ',
  `channel_id` bigint(20) NOT NULL COMMENT '频道id',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `available` tinyint(1) DEFAULT '0' COMMENT '是否可用, 0可用,-1不可用',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `channel`;

CREATE TABLE `channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '频道名称 ',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `available` tinyint(1) DEFAULT '0' COMMENT '是否可用, 0可用,-1不可用',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `label` */

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permission_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`permission`,`description`,`available`) values (1,'user:create','用户模块新增',1),(2,'user:update','用户模块修改',1),(3,'menu:create','菜单模块新增',1),(4,'admin:list',NULL,0),(5,'admin:add',NULL,0),(6,'admin:edit',NULL,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_role_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role`,`description`,`available`) values (1,'admin','管理员',1),(2,'user','用户管理员',1);

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`role_id`,`permission_id`,`role_permission_id`) values (1,4,0),(1,1,1),(1,2,2),(2,3,3),(2,1,4),(2,2,5),(1,5,7),(1,6,8);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`,`user_role_id`) values (1,1,1),(2,1,2),(3,1,3),(4,2,4),(2,2,5);

/*Table structure for table `system_setting` */

DROP TABLE IF EXISTS `system_setting`;

CREATE TABLE `system_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_key` varchar(64) DEFAULT '' COMMENT '键',
  `_value` varchar(1024) DEFAULT '' COMMENT '值',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_sp_key` (`_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统设置表';

/*Data for the table `system_setting` */

/*Table structure for table `visitor` */

DROP TABLE IF EXISTS `visitor`;

CREATE TABLE `visitor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL DEFAULT '' COMMENT '用户名 ',
  `nickname` varchar(128) NOT NULL DEFAULT '' COMMENT '用户昵称 ',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '用户密码 ',
  `access_token` varchar(128) NOT NULL DEFAULT '' COMMENT '访问凭证(可以凭此凭证留言) ',
  `salt` varchar(128) NOT NULL DEFAULT '' COMMENT '用户盐 ',
  `email` varchar(128) NOT NULL DEFAULT '' COMMENT '用户盐 ',
  `ip` varchar(128) NOT NULL DEFAULT '' COMMENT '访客最后一次登录IP ',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `visitor` */

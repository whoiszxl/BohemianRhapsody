# 轮播图表
DROP TABLE IF EXISTS `zxl_banner`;
CREATE TABLE `zxl_banner` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT '' COMMENT '轮播图名称',
  `type` tinyint(1) DEFAULT '0' COMMENT '轮播位置：0->PC首页轮播；1->app首页轮播',
  `pic` varchar(500) DEFAULT '' COMMENT '图片地址',
  `status` int(1) DEFAULT '1' COMMENT '上下线状态：0->下线；1->上线',
  `click_count` int(11) DEFAULT '0' COMMENT '点击数',
  `url` varchar(500) DEFAULT '' COMMENT '链接地址',
  `note` varchar(500) DEFAULT '' COMMENT '备注',
  `sort` int(3) DEFAULT '0' COMMENT '排序',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='首页轮播表';




DROP TABLE IF EXISTS `zxl_user`;
CREATE TABLE `zxl_user` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `realname` varchar(16) DEFAULT '' COMMENT '真实姓名',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(16) DEFAULT '' COMMENT '手机',
  `country_code` varchar(5) DEFAULT '' COMMENT '国家码',
  `google_code` varchar(32) DEFAULT '' COMMENT '谷歌验证码',
  `status` tinyint(2) DEFAULT 1 COMMENT '状态(0：无效 1：有效)',
  `last_login` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

DROP TABLE IF EXISTS `zxl_admin`;
CREATE TABLE `zxl_admin` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `realname` varchar(16) DEFAULT '' COMMENT '真实姓名',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(16) DEFAULT '' COMMENT '手机',
  `country_code` varchar(5) DEFAULT '' COMMENT '国家码',
  `google_code` varchar(32) DEFAULT '' COMMENT '谷歌验证码',
  `status` tinyint(2) DEFAULT 1 COMMENT '状态(0：无效 1：有效)',
  `last_login` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';


docker run ‐di ‐‐name=tensquare_redis ‐p 6379:6379 redis


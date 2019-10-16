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
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

DROP TABLE IF EXISTS `zxl_currency`;
CREATE TABLE `zxl_currency` (
  `id` int(10) NOT NULL COMMENT '主键ID',
  `currency_name` varchar(32) NOT NULL COMMENT '货币名称',
  `currency_mark` varchar(32) NOT NULL COMMENT '英文标识',
  `currency_logo` varchar(255) NOT NULL COMMENT '货币logo',
  `currency_type` varchar(10) NOT NULL DEFAULT 'mainnet' COMMENT '货币类型： mainnet：主网币 token：代币',
  `currency_content` text NOT NULL COMMENT '币种描述',
  `currency_total_num` decimal(40,4) DEFAULT '0.0000' COMMENT '币总数量',
  `currency_decimals_num` int(3) DEFAULT 18 COMMENT '币种小数位',
  `currency_buy_fee` decimal(20,18) NOT NULL DEFAULT '0' COMMENT '买入手续费',
  `currency_sell_fee` decimal(20,18) NOT NULL DEFAULT '0' COMMENT '卖出手续费',
  `currency_url` varchar(128) NOT NULL COMMENT '该币种的链接地址',
  `contract_abi` longtext COMMENT '智能合约abi接口',
  `contract_address` varchar(128) NOT NULL DEFAULT '' COMMENT '智能合约地址',
  `rpc_url` varchar(255) NOT NULL DEFAULT '' COMMENT 'rpc路径',
  `rpc_username` varchar(255) NOT NULL DEFAULT '' COMMENT 'rpc用户名',
  `rpc_password` varchar(255) NOT NULL DEFAULT '' COMMENT 'rpc密码',
  `max_withdraw` decimal(36,18) NOT NULL DEFAULT '0' COMMENT '最大提币额',
  `min_withdraw` decimal(36,18) NOT NULL DEFAULT '0' COMMENT '最小提币额',
  `fee_withdraw` decimal(36,18) NOT NULL DEFAULT '0' COMMENT '提币手续费',
  `wallet_url` varchar(64) NOT NULL DEFAULT '' COMMENT '钱包储存路径',
  `wallet_key` varchar(64) NOT NULL DEFAULT '' COMMENT '钱包密钥',
  `confirms` tinyint(2) NOT NULL DEFAULT '1' COMMENT '充值确认数',
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '展示顺序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '币种状态，0：关闭 1：开启',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='币种表';



DROP TABLE IF EXISTS `zxl_user_address`;
CREATE TABLE `zxl_user_address` (
  `id` varchar(20) NOT NULL COMMENT '主键ID',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `currency_id` int(10) NOT NULL COMMENT '币种ID',
  `recharge_address` varchar(255) NOT NULL COMMENT '充值地址',
  `private_key` varchar(255) DEFAULT '' COMMENT '私钥',
  `password` varchar(255) DEFAULT '' COMMENT '密码',
  `keystore` LONGTEXT COMMENT 'keystore',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '钱包状态，0：关闭 1：开启',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ON UPDATE CURRENT_TIMESTAMP '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户充值地址表';



DROP TABLE IF EXISTS `zxl_user_balance`;
CREATE TABLE `zxl_user_balance` (
  `id` varchar(20) NOT NULL COMMENT '主键ID',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `currency_id` int(10) NOT NULL COMMENT '币种ID',
  `all_balance` decimal(40,18) NOT NULL COMMENT '数量',
  `lock_balance` decimal(40,18) NOT NULL COMMENT '冻结数量',
  `usable_balance` decimal(40,18) NOT NULL COMMENT '可用数量',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '充值地址状态，0：关闭 1：开启',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ON UPDATE CURRENT_TIMESTAMP '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id_key` (`user_id`) USING BTREE,
  KEY `currency_id_key` (`currency_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户余额表';


DROP TABLE IF EXISTS `zxl_user_withdraw`;
CREATE TABLE `zxl_user_withdraw` (
  `id` varchar(20) NOT NULL COMMENT '主键ID',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `currency_id` int(10) NOT NULL COMMENT '币种ID',
  `tx_hash` varchar(255) NOT NULL COMMENT '提现交易hash',
  `withdraw_all` decimal(40,18) NOT NULL COMMENT '总提现额',
  `withdraw_fee` decimal(40,18) NOT NULL COMMENT '提现手续费',
  `withdraw_actual` decimal(40,18) NOT NULL COMMENT '用户实际获得的提现金额',
  `from_address` varchar(255) DEFAULT NULL COMMENT '交易所出币地址(BTC系列为从节点，所以为空)',
  `to_address` varchar(255) DEFAULT NULL COMMENT '用户提币后收币地址',
  `audit_at` datetime COMMENT '审核时间',
  `audit_uid` varchar(20) COMMENT '审核操作人(管理员）',
  `audit_status` tinyint(1) NOT NULL DEFAULT '2' COMMENT '审核状态，0：审核不通过 1：审核通过 2：审核中',
  `upchain_at` datetime COMMENT '上链时间',
  `upchain_success_at` datetime COMMENT '上链成功时间',
  `upchain_status` tinyint(1) NOT NULL DEFAULT '2' COMMENT '上链状态，0：失败 1：成功 2：上链后等待确认中',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ON UPDATE CURRENT_TIMESTAMP '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户提现记录表';


DROP TABLE IF EXISTS `zxl_user_recharge`;
CREATE TABLE `zxl_user_recharge` (
  `id` varchar(20) NOT NULL COMMENT '主键ID',
  `user_id` varchar(20) NOT NULL COMMENT '用户ID',
  `currency_id` int(10) NOT NULL COMMENT '币种ID',
  `tx_hash` varchar(255) NOT NULL COMMENT '充值的交易hash',
  `recharge_actual` decimal(40,18) NOT NULL COMMENT '用户实际充值的金额',
  `from_address` varchar(255) DEFAULT NULL COMMENT '用户的出币地址',
  `to_address` varchar(255) DEFAULT NULL COMMENT '交易所分配给用户的唯一地址',
  `upchain_at` datetime COMMENT '上链时间',
  `upchain_success_at` datetime COMMENT '上链成功时间',
  `upchain_status` tinyint(1) NOT NULL DEFAULT '2' COMMENT '上链状态，0：失败 1：成功 2：上链后等待确认中',
  `height` int(20) DEFAULT NULL COMMENT '当前交易所处区块的高度',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT ON UPDATE CURRENT_TIMESTAMP '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户充值记录表';





DROP TABLE IF EXISTS `zxl_config`;
CREATE TABLE `zxl_config` (
  `id` int(10) NOT NULL COMMENT '主键ID',
  `key` varchar(32) NOT NULL COMMENT '配置键',
  `value` text NOT NULL COMMENT '配置值',
  `remark` varchar(32) COMMENT '配置说明',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '配置状态，0：关闭 1：开启',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
	UNIQUE KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='币种表';
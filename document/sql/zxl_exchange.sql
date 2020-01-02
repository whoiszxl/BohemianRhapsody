DROP TABLE IF EXISTS `cms_banner`;
CREATE TABLE `cms_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
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

-- ---------------------------------------------------------
-- 【用户】member用户相关表 start
-- ---------------------------------------------------------

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `realname` varchar(16) DEFAULT '' COMMENT '真实姓名',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(16) DEFAULT '' COMMENT '手机',
  `country_code` varchar(5) DEFAULT '' COMMENT '国家码',
  `google_key` varchar(32) DEFAULT '' COMMENT '谷歌验证码',
  `google_status` int(1) DEFAULT '0' COMMENT '谷歌验证码是否开启，默认不开启',
  `country` varchar(100) DEFAULT NULL COMMENT '国家',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(30) DEFAULT NULL COMMENT '城市',
  `district` varchar(200) DEFAULT NULL COMMENT '区域',
  `grade_level` varchar(255) DEFAULT NULL COMMENT '用户等级',
  `day_withdraw_count` int(11) DEFAULT NULL COMMENT '当前等级每日提现额度',
  `trade_fee_rate` decimal(10,6) NOT NULL COMMENT '当前等级交易手续费率',
  `login_count` int(11) NOT NULL COMMENT '用户登录次数',
  `status` tinyint(2) DEFAULT 1 COMMENT '状态(0：无效 1：有效)',
  `last_login` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

DROP TABLE IF EXISTS `member_address`;
CREATE TABLE `member_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` varchar(20) NOT NULL COMMENT '用户ID',
  `coin_id` int(10) NOT NULL COMMENT '币种ID',
  `recharge_address` varchar(255) NOT NULL COMMENT '充值地址',
  `private_key` varchar(255) DEFAULT '' COMMENT '私钥',
  `password` varchar(255) DEFAULT '' COMMENT '密码',
  `keystore` LONGTEXT COMMENT 'keystore',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '钱包状态，0：关闭 1：开启',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  KEY `key_coin_id` (`coin_id`),
  KEY `key_member_id` (`member_id`),
  KEY `key_recharge_address` (`recharge_address`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户充值地址表';

DROP TABLE IF EXISTS `member_kyc`;
CREATE TABLE `member_kyc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '用户ID',
  `id_card` varchar(255) NOT NULL COMMENT '身份证ID',
  `idcard_front_url` varchar(255)  COMMENT '身份证正面图',
  `idcard_reverse_url` varchar(255)  COMMENT '身份证背面图',
  `idcard_hold_url` varchar(255)  COMMENT '手持身份证图',
  `video_url` varchar(255) DEFAULT NULL COMMENT '视频地址',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `reject_reason` varchar(255) DEFAULT NULL COMMENT '审核拒绝理由',
  `type` int(11) DEFAULT '0' COMMENT '认证类型',
  `audit_status` int(11) NOT NULL COMMENT '审核状态: 0审核中 1审核成功 -1审核失败',
  `video_random` varchar(6) DEFAULT NULL COMMENT '六位视频认证随机数',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `key_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户KYC认证表';


DROP TABLE IF EXISTS `member_wallet`;
CREATE TABLE `member_wallet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` varchar(20) NOT NULL COMMENT '用户ID',
  `coin_id` int(10) NOT NULL COMMENT '币种ID',
  `all_balance` decimal(40,18) NOT NULL COMMENT '总金额数量',
  `lock_balance` decimal(40,18) NOT NULL COMMENT '冻结金额数量',
  `usable_balance` decimal(40,18) NOT NULL COMMENT '可用金额数量',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '钱包，0：关闭 1：开启',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `member_id_key` (`member_id`) USING BTREE,
  KEY `coin_id_key` (`coin_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户钱包余额表';


DROP TABLE IF EXISTS `trade_transaction_internal`;
CREATE TABLE `trade_transaction_internal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coin_id` bigint(20) NOT NULL COMMENT '币种ID',
  `amount` decimal(40,18) DEFAULT '0.00000000' COMMENT '转账金额',
  `from_member_id` bigint(20) NOT NULL COMMENT '转出方用户ID',
  `to_member_id` bigint(20) NOT NULL COMMENT '转入方用户ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内部转账表';

-- ---------------------------------------------------------
-- 【用户】member用户相关表 end
-- ---------------------------------------------------------



-- ---------------------------------------------------------
-- 【系统】管理员相关表 start
-- ---------------------------------------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `realname` varchar(16) DEFAULT '' COMMENT '真实姓名',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(16) DEFAULT '' COMMENT '手机',
  `country_code` varchar(5) DEFAULT '' COMMENT '国家码',
  `google_code` varchar(32) DEFAULT '' COMMENT '谷歌验证码',
  `last_login_ip` varchar(50) DEFAULT '' COMMENT '最后登录的IP',
  `last_login` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录',
  `role_id` bigint(20) NOT NULL COMMENT '关联的角色ID',
  `status` tinyint(2) DEFAULT 1 COMMENT '状态(0：无效 1：有效)',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

DROP TABLE IF EXISTS `admin_access_log`;
CREATE TABLE `admin_access_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_ip` varchar(50) DEFAULT NULL COMMENT '访问IP',
  `access_method` varchar(255) DEFAULT NULL COMMENT '访问的方法',
  `access_module` int(11) DEFAULT NULL COMMENT '访问的模块',
  `access_time` datetime DEFAULT NULL COMMENT '访问的时间',
  `admin_id` bigint(20) DEFAULT NULL COMMENT '访问的管理员ID',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作',
  `uri` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员访问日志表';

DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(255) NOT NULL COMMENT '角色描述',
  `status` tinyint(2) DEFAULT 1 COMMENT '状态(0：无效 1：有效)',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `per_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `per_value` varchar(100) DEFAULT NULL COMMENT '权限值',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父权限ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员权限表';


DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `per_id` bigint(20) NOT NULL COMMENT '权限ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  UNIQUE KEY `unique_role_per_id` (`role_id`,`per_id`),
  KEY `key_role_id` (`role_id`) USING BTREE,
  KEY `key_per_id` (`per_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与权限关联表';

-- ---------------------------------------------------------
-- 【系统】管理员相关表 end
-- ---------------------------------------------------------










-- ---------------------------------------------------------
-- 【基础】基础的表 start
-- ---------------------------------------------------------

DROP TABLE IF EXISTS `cms_app_version`;
CREATE TABLE `cms_app_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `download_url` varchar(255) DEFAULT NULL COMMENT '下载地址',
  `platform` int(11) DEFAULT NULL COMMENT '平台，类型字符串 IOS && ANDROID',
  `app_version` varchar(10) DEFAULT NULL COMMENT '版本号',
  `app_title` varchar(255) DEFAULT NULL COMMENT '更新标题',
  `app_content` varchar(255) DEFAULT NULL COMMENT '更新内容',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cms_notice`;
CREATE TABLE `cms_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(100) DEFAULT '' COMMENT '通知标题',
  `content` text COMMENT '通知内容',
  `sort` int(3) DEFAULT '0' COMMENT '排序',
  `click_count` int(11) DEFAULT '0' COMMENT '点击数',
  `status` int(1) DEFAULT '1' COMMENT '上下线状态：0->下线；1->上线',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知表';



DROP TABLE IF EXISTS `cms_country`;
CREATE TABLE `cms_country` (
  `zh_name` varchar(255) NOT NULL,
  `area_code` varchar(255) DEFAULT NULL,
  `en_name` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `local_currency` varchar(255) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`zh_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ---------------------------------------------------------
-- 【基础】基础的表 end
-- ---------------------------------------------------------


-- ---------------------------------------------------------
-- 【币种】coin币种相关的表 start
-- ---------------------------------------------------------
DROP TABLE IF EXISTS `cms_coin`;
CREATE TABLE `cms_coin` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '币种ID',
  `coin_name` varchar(32) NOT NULL COMMENT '货币名称',
  `coin_mark` varchar(32) NOT NULL COMMENT '英文标识',
  `coin_logo` varchar(255) NOT NULL COMMENT '货币logo',
  `coin_type` varchar(10) NOT NULL DEFAULT 'mainnet' COMMENT '货币类型： mainnet：主网币 token：代币',
  `coin_content` text NOT NULL COMMENT '币种描述',
  `coin_total_num` decimal(40,4) DEFAULT '0.0000' COMMENT '币总数量',
  `coin_decimals_num` int(3) DEFAULT 18 COMMENT '币种小数位',
  `coin_buy_fee` decimal(20,18) NOT NULL DEFAULT '0' COMMENT '买入手续费',
  `coin_sell_fee` decimal(20,18) NOT NULL DEFAULT '0' COMMENT '卖出手续费',
  `coin_url` varchar(128) NOT NULL COMMENT '该币种的链接地址',
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

DROP TABLE IF EXISTS `member_recharge_record`;
CREATE TABLE `member_recharge_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` varchar(20) NOT NULL COMMENT '用户ID',
  `coin_id` int(10) NOT NULL COMMENT '币种ID',
  `coin_name` varchar(32) NOT NULL COMMENT '货币名称',
  `tx_hash` varchar(255) NOT NULL COMMENT '充值的交易hash',
  `recharge_actual` decimal(40,18) NOT NULL COMMENT '用户实际充值的金额',
  `from_address` varchar(255) DEFAULT NULL COMMENT '用户的出币地址',
  `to_address` varchar(255) DEFAULT NULL COMMENT '交易所分配给用户的唯一地址',
  `upchain_at` datetime COMMENT '上链时间',
  `upchain_success_at` datetime COMMENT '上链成功时间',
  `upchain_status` tinyint(1) NOT NULL DEFAULT '2' COMMENT '上链状态，0：失败 1：成功 2：上链后等待确认中',
  `height` int(20) DEFAULT NULL COMMENT '当前交易所处区块的高度',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户充值记录表';


-- ---------------------------------------------------------
-- 【币种】coin币种相关的表 end
-- ---------------------------------------------------------







DROP TABLE IF EXISTS `member_withdraw`;
CREATE TABLE `member_withdraw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_id` varchar(20) NOT NULL COMMENT '用户ID',
  `coin_id` int(10) NOT NULL COMMENT '币种ID',
  `coin_name` varchar(32) NOT NULL COMMENT '货币名称',
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
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户提现记录表';








DROP TABLE IF EXISTS `cms_config`;
CREATE TABLE `cms_config` (
  `id` int(10) NOT NULL COMMENT '主键ID',
  `key` varchar(32) NOT NULL COMMENT '配置键',
  `value` text NOT NULL COMMENT '配置值',
  `remark` varchar(32) COMMENT '配置说明',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '配置状态，0：关闭 1：开启',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
	UNIQUE KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配置表';



DROP TABLE IF EXISTS `trade_contracts`;
CREATE TABLE `trade_contracts` (
  `id` int(4) NOT NULL COMMENT '主键ID,交易对ID',
  `contract_name` varchar(20) NOT NULL COMMENT '交易对名称',
  `contract_type` tinyint(2) DEFAULT '1' COMMENT '交易对类型：1：主版   2：P版  3:创业版',
  `coin_id` int(10) NOT NULL COMMENT '交易对第一个币种ID',
  `replace_coin_id` int(10) NOT NULL COMMENT '交易对第二个币种ID',
  `buyer_fee` decimal(40,18) NOT NULL DEFAULT '0.001' COMMENT '购买者所出费率',
  `seller_fee` decimal(40,18) NOT NULL DEFAULT '0.001' COMMENT '出售者所出费率',
  `max_orders` int(4) NOT NULL COMMENT '最大挂单笔数',
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '展示顺序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '交易对状态，0：关闭 1：开启',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='交易对表';


DROP TABLE IF EXISTS `trade_transactions`;
CREATE TABLE `trade_transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '挂单主键ID',
  `member_id` varchar(20) NOT NULL COMMENT '用户ID',
  `coin_id` int(10) NOT NULL COMMENT '交易对第一个币种ID',
  `replace_coin_id` int(10) NOT NULL COMMENT '交易对第二个币种ID',
  `price` decimal(40,18) NOT NULL DEFAULT '0.0000' COMMENT '委托价格',
  `total_count` decimal(40,18) NOT NULL DEFAULT '0.0000' COMMENT '委托总数量',
  `current_count` decimal(40,18) NOT NULL DEFAULT '0.0000' COMMENT '当前可交易数量（挂单的金额可能超过当前所有挂单的总和）',
  `type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1：买入 -1：卖出',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0代表部分交易，可交易，1是所有已成交，交易结束， -1用户撤单',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,挂单时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='挂单表';



DROP TABLE IF EXISTS `trade_orders`;
CREATE TABLE `trade_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单主键ID',
  `member_id` varchar(20) NOT NULL COMMENT '买家用户ID',
  `transaction_id` varchar(20) NOT NULL COMMENT '挂单ID',
  `coin_id` int(10) NOT NULL COMMENT '交易对第一个币种ID',
  `replace_coin_id` int(10) NOT NULL COMMENT '交易对第二个币种ID',
  `price` decimal(40,18) NOT NULL DEFAULT '0.0000' COMMENT '成交价格',
  `success_count` decimal(40,18) NOT NULL DEFAULT '0.0000' COMMENT '委托总数量',
  `type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0：买入 1：卖出',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,成交时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单成交表';
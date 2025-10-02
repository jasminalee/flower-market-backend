-- 订单模块表结构定义

-- 订单表
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(32) NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `merchant_id` bigint NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `total_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `discount_amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
  `pay_amount` decimal(10,2) NOT NULL COMMENT '实际支付金额',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '订单状态（1-待付款，2-已付款，3-已发货，4-已完成，5-已取消，6-退款中，7-已退款）',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `payment_method` tinyint DEFAULT NULL COMMENT '支付方式（1-支付宝，2-微信，3-银行卡）',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '订单备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_merchant_id` (`merchant_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- 订单明细表
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
  `order_id` bigint NOT NULL COMMENT '订单ID（逻辑关联order表）',
  `product_id` bigint NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_id` bigint NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
  `merchant_id` bigint NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `product_name` varchar(200) NOT NULL COMMENT '产品名称',
  `sku_name` varchar(200) NOT NULL COMMENT 'SKU名称',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `quantity` int NOT NULL COMMENT '数量',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单明细表';

-- 购物车表
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车项ID',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `product_id` bigint NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_id` bigint NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
  `merchant_id` bigint NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '数量',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-无效，1-有效）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_product_sku` (`user_id`,`product_id`,`sku_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车表';
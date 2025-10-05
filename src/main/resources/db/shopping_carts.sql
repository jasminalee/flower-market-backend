-- ----------------------------
-- Table structure for shopping_carts
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车项ID',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `product_id` bigint NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_id` bigint NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
  `merchant_id` bigint NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-无效，1-有效）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (1, 2, 3, 5, 1, 1, 45.00, 1, '2025-10-01 10:00:00', '2025-10-01 10:00:00');
INSERT INTO `shopping_cart` VALUES (2, 3, 4, 6, 1, 2, 38.00, 1, '2025-10-01 15:00:00', '2025-10-01 15:00:00');
INSERT INTO `shopping_cart` VALUES (3, 2, 1, 2, 1, 1, 168.00, 1, '2025-10-02 09:30:00', '2025-10-02 09:30:00');
INSERT INTO `shopping_cart` VALUES (4, 3, 5, 7, 1, 3, 28.00, 1, '2025-10-02 14:20:00', '2025-10-02 14:20:00');
/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 127.0.0.1:3306
 Source Schema         : flower_market

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 05/10/2025 12:02:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `source_id` bigint(0) NOT NULL COMMENT '来源ID（可以是产品ID、文章ID等）',
  `source_type` varchar(50) CHARACTER SET utf8mb4  NOT NULL COMMENT '来源类型（product, article, forum等）',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父评论ID（用于构建评论树，0表示顶级评论）',
  `order_id` bigint(0) NULL DEFAULT NULL COMMENT '订单ID（逻辑关联order表，可为空，仅对产品评论有效）',
  `rating` tinyint(0) NULL DEFAULT NULL COMMENT '评分（1-5分，可为空）',
  `content` text CHARACTER SET utf8mb4  NOT NULL COMMENT '评论内容',
  `is_anonymous` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否匿名（0-否，1-是）',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态（0-隐藏，1-显示）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_source`(`source_id`, `source_type`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_rating`(`rating`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4  COMMENT = '通用评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 'product', 2, 0, NULL, 5, '红玫瑰非常新鲜，包装也很精美，很满意！', 0, 1, '2025-09-29 13:17:23', '2025-09-29 13:17:23');
INSERT INTO `comment` VALUES (2, 1, 'product', 3, 0, NULL, 4, '花很不错，但配送稍微晚了一天', 1, 1, '2025-09-29 13:17:23', '2025-09-29 13:17:23');
INSERT INTO `comment` VALUES (3, 1, 'product', 2, 1, NULL, 5, '感谢您的好评，我们会继续努力提供更好的服务', 0, 1, '2025-09-29 13:17:23', '2025-09-29 13:17:23');
INSERT INTO `comment` VALUES (4, 2, 'product', 3, 0, NULL, 5, '白百合很香，品质很好', 0, 1, '2025-09-29 13:17:23', '2025-09-29 13:17:23');

-- ----------------------------
-- Table structure for merchant_product
-- ----------------------------
DROP TABLE IF EXISTS `merchant_product`;
CREATE TABLE `merchant_product`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `merchant_id` bigint(0) NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `product_id` bigint(0) NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_id` bigint(0) NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
  `price` decimal(10, 2) NOT NULL COMMENT '商户定价',
  `brand` varchar(100) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '品牌',
  `description` text CHARACTER SET utf8mb4  NULL COMMENT '产品描述',
  `sub_images` text CHARACTER SET utf8mb4  NULL COMMENT '子图URL集合，JSON格式存储',
  `detail` text CHARACTER SET utf8mb4  NULL COMMENT '产品详情',
  `stock` int(0) NOT NULL DEFAULT 0 COMMENT '商户库存',
  `avg_rating` decimal(3, 2) NULL COMMENT '平均评分',
  `total_sales` int(0) NULL DEFAULT 0 COMMENT '总销量',
  `min_price` decimal(10, 2) NULL COMMENT '最低价格',
  `is_hot` tinyint(1) NULL DEFAULT 0 COMMENT '是否热销(1:是,0:否)',
  `is_discounted` tinyint(1) NULL DEFAULT 0 COMMENT '是否打折(1:是,0:否)',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态（0-下架，1-上架）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_merchant_product_sku`(`merchant_id`, `product_id`, `sku_id`) USING BTREE,
  INDEX `idx_merchant_id`(`merchant_id`) USING BTREE,
  INDEX `idx_product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4  COMMENT = '商户产品关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchant_product
-- ----------------------------
INSERT INTO `merchant_product` VALUES (1, 1, 1, 1, 109.00, NULL, NULL, NULL, NULL, 20, NULL, NULL, NULL, NULL, NULL, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `merchant_product` VALUES (2, 1, 1, 2, 178.00, NULL, NULL, NULL, NULL, 10, NULL, NULL, NULL, NULL, NULL, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `merchant_product` VALUES (3, 1, 3, 5, 50.00, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `merchant_product` VALUES (4, 1, 2, 3, 85.00, NULL, NULL, NULL, NULL, 15, NULL, NULL, NULL, NULL, NULL, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `merchant_product` VALUES (5, 1, 4, 6, 42.00, NULL, NULL, NULL, NULL, 8, NULL, NULL, NULL, NULL, NULL, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `merchant_product` VALUES (11, 1, 5, 7, 28.00, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 1, '2025-09-29 14:49:12', '2025-09-29 14:49:12');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(32) CHARACTER SET utf8mb4  NOT NULL COMMENT '订单编号',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `merchant_id` bigint(0) NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '订单总金额',
  `discount_amount` decimal(10, 2) NULL COMMENT '优惠金额',
  `pay_amount` decimal(10, 2) NOT NULL COMMENT '实际支付金额',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '订单状态（1-待付款，2-已付款，3-已发货，4-已完成，5-已取消，6-退款中，7-已退款）',
  `receiver_name` varchar(50) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '收货人电话',
  `receiver_address` varchar(200) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '收货地址',
  `payment_method` tinyint(0) NULL DEFAULT NULL COMMENT '支付方式（1-支付宝，2-微信，3-银行卡）',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `remark` varchar(200) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '订单备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`order_no`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_merchant_id`(`merchant_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4  COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 'ORD202510020001', 2, 1, 218.00, 10.00, 208.00, 4, '用户1', '13800000001', '上海市浦东新区', 2, '2025-10-02 10:00:00', '2025-10-02 15:00:00', '2025-10-05 10:00:00', '请尽快发货', '2025-10-02 10:00:00', '2025-10-05 10:00:00');
INSERT INTO `order` VALUES (2, 'ORD202510020002', 3, 1, 78.00, 0.00, 78.00, 2, '用户2', '13800000002', '广州市天河区', 1, '2025-10-02 11:00:00', NULL, NULL, '', '2025-10-02 11:00:00', '2025-10-02 11:00:00');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
  `order_id` bigint(0) NOT NULL COMMENT '订单ID（逻辑关联order表）',
  `product_id` bigint(0) NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_id` bigint(0) NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
  `merchant_id` bigint(0) NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `product_name` varchar(200) CHARACTER SET utf8mb4  NOT NULL COMMENT '产品名称',
  `sku_name` varchar(200) CHARACTER SET utf8mb4  NOT NULL COMMENT 'SKU名称',
  `price` decimal(10, 2) NOT NULL COMMENT '单价',
  `quantity` int(0) NOT NULL COMMENT '数量',
  `total_price` decimal(10, 2) NOT NULL COMMENT '总价',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_product_id`(`product_id`) USING BTREE,
  INDEX `idx_sku_id`(`sku_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4  COMMENT = '订单明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (1, 1, 1, 2, 1, '红玫瑰1', '红玫瑰-21朵装', 168.00, 1, 168.00, '2025-10-02 10:00:00', '2025-10-02 10:00:00');
INSERT INTO `order_item` VALUES (2, 1, 5, 7, 1, '有机肥料', '有机肥料-1kg装', 28.00, 2, 56.00, '2025-10-02 10:00:00', '2025-10-02 10:00:00');
INSERT INTO `order_item` VALUES (3, 2, 2, 3, 1, '白百合', '白百合-6支装', 78.00, 1, 78.00, '2025-10-02 11:00:00', '2025-10-02 11:00:00');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_name` varchar(200) CHARACTER SET utf8mb4  NOT NULL COMMENT '产品名称',
  `product_code` varchar(100) CHARACTER SET utf8mb4  NOT NULL COMMENT '产品编码',
  `category_id` bigint(0) NOT NULL COMMENT '分类ID（逻辑关联product_category表）',
  `main_image` varchar(500) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '主图URL',
  `product_type` tinyint(0) NOT NULL DEFAULT 1 COMMENT '产品类型（1-花卉，2-第三方产品）',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态（0-下架，1-上架）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_product_code`(`product_code`) USING BTREE,
  INDEX `idx_category_id`(`category_id`) USING BTREE,
  INDEX `idx_product_type`(`product_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4  COMMENT = '产品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '红玫瑰12', 'FLOWER-001', 4, '花语1', '鲜艳的红玫瑰，适合表达爱意', 'http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg', NULL, '<ul><li><span style=\"color: rgb(255, 77, 79);\">测试标签</span></li></ul><p><img src=\"http://localhost:18091/images/uploads/760c4697d3d640ce82faa0652d9c97ff.jpg\" alt=\"image.png\" data-href=\"图片加载中...\" style=\"\"/></p>', 2, 1, 4.80, 150, 109.00, 1, 1, '2025-09-29 13:17:22', '2025-10-04 02:05:55');
INSERT INTO `product` VALUES (2, '白百合', 'FLOWER-002', 5, '花之语', '纯洁的白百合，象征百年好合', 'http://localhost:18091/images/products/lily.svg', NULL, NULL, 1, 1, 4.75, 85, 85.00, 0, 0, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product` VALUES (3, '园艺剪刀', 'TOOL-001', 6, '园艺大师', '专业园艺剪刀，锋利耐用', 'http://localhost:18091/images/products/scissors.svg', NULL, NULL, 2, 1, 5.00, 30, 50.00, 0, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product` VALUES (4, '浇水壶', 'TOOL-002', 7, '花园家', '容量大，喷洒均匀的浇水壶', 'http://localhost:18091/images/products/watering-can.svg', NULL, NULL, 2, 1, 0.00, 0, 42.00, 0, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product` VALUES (5, '有机肥料', 'FERT-001', 8, '绿色家园', '天然有机肥料，环保无污染', 'http://localhost:18091/images/products/fertilizer.svg', NULL, NULL, 2, 1, 0.00, 0, 28.00, 0, 0, '2025-09-29 13:17:22', '2025-09-29 13:17:22');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(100) CHARACTER SET utf8mb4  NOT NULL COMMENT '分类名称',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父分类ID（用于构建分类树）',
  `category_level` tinyint(0) NOT NULL DEFAULT 1 COMMENT '分类级别（1-一级分类，2-二级分类，3-三级分类）',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序号',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-正常）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4  COMMENT = '产品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, '花卉植物', 0, 1, 1, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (2, '园艺工具', 0, 1, 2, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (3, '肥料农药', 0, 1, 3, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (4, '玫瑰花', 1, 2, 1, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (5, '百合花', 1, 2, 2, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (6, '修剪工具', 2, 2, 1, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (7, '浇水工具', 2, 2, 2, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (8, '有机肥料', 3, 2, 1, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (9, '杀虫农药', 3, 2, 2, 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_category` VALUES (10, '盆栽', 0, 1, 0, 1, '2025-10-04 02:00:37', '2025-10-04 02:00:37');

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'SKU ID',
  `product_id` bigint(0) NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_name` varchar(200) CHARACTER SET utf8mb4  NOT NULL COMMENT 'SKU名称（如：红色-L）',
  `sku_code` varchar(100) CHARACTER SET utf8mb4  NOT NULL COMMENT 'SKU编码',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `stock` int(0) NOT NULL DEFAULT 0 COMMENT '库存数量',
  `specifications` varchar(500) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '规格描述，JSON格式存储',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态（0-无效，1-有效）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_sku_code`(`sku_code`) USING BTREE,
  INDEX `idx_product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4  COMMENT = '产品SKU表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_sku
-- ----------------------------
INSERT INTO `product_sku` VALUES (1, 1, '红玫瑰-11朵装', 'ROSE-11', 99.00, 100, '{\"颜色\": \"红色\", \"数量\": 11}', 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_sku` VALUES (2, 1, '红玫瑰-21朵装', 'ROSE-21', 168.00, 50, '{\"颜色\": \"红色\", \"数量\": 21}', 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_sku` VALUES (3, 2, '白百合-6支装', 'LILY-6', 78.00, 80, '{\"颜色\": \"白色\", \"数量\": 6}', 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_sku` VALUES (4, 2, '白百合-12支装', 'LILY-12', 138.00, 40, '{\"颜色\": \"白色\", \"数量\": 12}', 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_sku` VALUES (5, 3, '园艺剪刀-标准版', 'SCISSORS-S', 45.00, 30, '{\"材质\": \"不锈钢\", \"长度\": \"20cm\"}', 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_sku` VALUES (6, 4, '浇水壶-大号', 'WATERCAN-L', 38.00, 25, '{\"容量\": \"2L\", \"颜色\": \"绿色\"}', 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `product_sku` VALUES (7, 5, '有机肥料-1kg装', 'FERT-1KG', 28.00, 100, '{\"重量\": \"1kg\", \"类型\": \"颗粒状\"}', 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');

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
-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父权限ID（用于构建权限树）',
  `permission_name` varchar(100) CHARACTER SET utf8mb4  NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) CHARACTER SET utf8mb4  NOT NULL COMMENT '权限编码',
  `permission_type` tinyint(0) NOT NULL COMMENT '权限类型（1-菜单，2-按钮，3-接口）',
  `url` varchar(255) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '资源路径（如URL）',
  `method` varchar(10) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '请求方法（GET/POST等，适用于接口权限）',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_permission_code`(`permission_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4  COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '系统管理', 'sys:manage', 1, '', '', 1, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (2, 1, '用户管理', 'sys:user:manage', 1, '/user/**', '', 2, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (3, 2, '查询用户', 'sys:user:list', 3, '/user/list', 'GET', 3, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (4, 2, '新增用户', 'sys:user:add', 3, '/user/add', 'POST', 4, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (5, 2, '修改用户', 'sys:user:update', 3, '/user/update', 'PUT', 5, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (6, 2, '删除用户', 'sys:user:delete', 3, '/user/delete', 'DELETE', 6, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (7, 1, '角色管理', 'sys:role:manage', 1, '/role/**', '', 7, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (8, 7, '查询角色', 'sys:role:list', 3, '/role/list', 'GET', 8, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (9, 7, '新增角色', 'sys:role:add', 3, '/role/add', 'POST', 9, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (10, 7, '修改角色', 'sys:role:update', 3, '/role/update', 'PUT', 10, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (11, 7, '删除角色', 'sys:role:delete', 3, '/role/delete', 'DELETE', 11, '2025-09-29 13:17:22', '2025-09-29 13:17:22');
INSERT INTO `sys_permission` VALUES (12, 0, '商户管理', 'MARVHSNT', 1, '', '', 0, '2025-10-04 01:58:11', '2025-10-04 01:58:11');
INSERT INTO `sys_permission` VALUES (13, 12, '商户仪表板', 'test', 1, '', '', 0, '2025-10-04 01:58:54', '2025-10-04 01:58:54');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) CHARACTER SET utf8mb4  NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4  NOT NULL COMMENT '角色编码',
  `description` varchar(200) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4  COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROLE_ADMIN', '拥有所有权限', '2025-09-29 13:17:21', '2025-09-29 13:17:21');
INSERT INTO `sys_role` VALUES (2, '普通用户', 'ROLE_USER', '普通用户权限', '2025-09-29 13:17:21', '2025-09-29 13:17:21');
INSERT INTO `sys_role` VALUES (3, '商户', 'ROLE_MERCHANT', '商户权限', '2025-09-29 13:17:21', '2025-09-29 13:17:21');
INSERT INTO `sys_role` VALUES (4, '访客', 'ROLE_GUEST', '访客权限', '2025-09-29 13:17:21', '2025-09-29 13:17:21');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(0) NOT NULL COMMENT '权限ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_role_permission`(`role_id`, `permission_id`) USING BTREE,
  INDEX `idx_permission_id`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4  COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 1, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (2, 1, 2, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (3, 1, 3, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (4, 1, 4, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (5, 1, 5, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (6, 1, 6, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (7, 1, 7, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (8, 1, 8, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (9, 1, 9, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (10, 1, 10, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (11, 1, 11, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (12, 2, 3, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (13, 2, 8, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (14, 3, 3, '2025-09-29 13:17:22');
INSERT INTO `sys_role_permission` VALUES (15, 3, 8, '2025-09-29 13:17:22');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4  NOT NULL COMMENT '用户名(登陆)',
  `password` varchar(100) CHARACTER SET utf8mb4  NOT NULL COMMENT '密码(加密存储)',
  `nickname` varchar(50) CHARACTER SET utf8mb4  NOT NULL COMMENT '昵称',
  `email` varchar(100) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4  NULL DEFAULT NULL COMMENT '手机号',
  `addr` varchar(255) CHARACTER SET utf8mb4  NOT NULL COMMENT '地址',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-正常）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4  COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '管理员', 'admin@example.com', '13800000000', '北京市朝阳区', 1, '2025-09-29 13:17:21', '2025-09-29 13:17:21');
INSERT INTO `sys_user` VALUES (2, 'user1', 'user1', '用户1', 'user1@example.com', '13800000001', '上海市浦东新区', 1, '2025-09-29 13:17:21', '2025-09-29 13:17:21');
INSERT INTO `sys_user` VALUES (3, 'user2', 'user2', '用户2', 'user2@example.com', '13800000002', '广州市天河区', 1, '2025-09-29 13:17:21', '2025-09-29 13:17:21');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_role`(`user_id`, `role_id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4  COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1, '2025-09-29 13:17:22');
INSERT INTO `sys_user_role` VALUES (2, 2, 2, '2025-09-29 13:17:22');
INSERT INTO `sys_user_role` VALUES (3, 3, 2, '2025-09-29 13:17:22');

SET FOREIGN_KEY_CHECKS = 1;

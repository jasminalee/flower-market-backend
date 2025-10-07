-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: flower_market
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `source_id` bigint NOT NULL COMMENT '来源ID（可以是产品ID、文章ID等）',
  `source_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源类型（product, article, forum等）',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `parent_id` bigint DEFAULT '0' COMMENT '父评论ID（用于构建评论树，0表示顶级评论）',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID（逻辑关联order表，可为空，仅对产品评论有效）',
  `rating` tinyint DEFAULT NULL COMMENT '评分（1-5分，可为空）',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `is_anonymous` tinyint NOT NULL DEFAULT '0' COMMENT '是否匿名（0-否，1-是）',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-隐藏，1-显示）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_source` (`source_id`,`source_type`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_rating` (`rating`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='通用评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` VALUES (9,1,'product',1,0,NULL,5,'测试阿斯达',0,1,'2025-10-06 08:00:14','2025-10-06 08:00:14'),(10,1,'product',1,0,NULL,NULL,'1231 ',0,1,'2025-10-06 08:00:18','2025-10-06 08:00:18'),(11,1,'product',1,10,NULL,0,'测试',0,1,'2025-10-06 08:03:10','2025-10-06 08:03:10'),(12,1,'product',1,9,NULL,NULL,'测试',0,1,'2025-10-06 08:06:49','2025-10-06 08:06:49'),(13,2,'product',1,0,NULL,4,'测试算阿达打',0,1,'2025-10-06 08:08:29','2025-10-06 08:08:29');

--
-- Table structure for table `merchant_product`
--

DROP TABLE IF EXISTS `merchant_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchant_product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `merchant_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `merchant_id` bigint NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `product_id` bigint NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_id` bigint NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
  `price` decimal(10,2) NOT NULL COMMENT '商户定价',
  `brand` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '品牌',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '产品描述',
  `main_image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '主图URL',
  `sub_images` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '子图URL集合，JSON格式存储',
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '产品详情',
  `stock` int NOT NULL DEFAULT '0' COMMENT '商户库存',
  `avg_rating` decimal(3,2) DEFAULT NULL COMMENT '平均评分',
  `total_sales` int DEFAULT '0' COMMENT '总销量',
  `min_price` decimal(10,2) DEFAULT NULL COMMENT '最低价格',
  `is_hot` tinyint(1) DEFAULT '0' COMMENT '是否热销(1:是,0:否)',
  `is_discounted` tinyint(1) DEFAULT '0' COMMENT '是否打折(1:是,0:否)',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-下架，1-上架）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_merchant_product_sku` (`merchant_id`,`product_id`,`sku_id`) USING BTREE,
  KEY `idx_merchant_id` (`merchant_id`) USING BTREE,
  KEY `idx_product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='商户产品关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant_product`
--

INSERT INTO `merchant_product` VALUES (1,'红玫瑰11朵装-花语',1,1,1,109.00,'花语','鲜艳的红玫瑰，适合表达爱意','http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg','[\"http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg\", \"http://localhost:18091/images/uploads/760c4697d3d640ce82faa0652d9c97ff.jpg\"]','<ul><li><span style=\"color: rgb(255, 77, 79);\">测试标签</span></li></ul><p><img src=\"http://localhost:18091/images/uploads/760c4697d3d640ce82faa0652d9c97ff.jpg\" alt=\"image.png\" data-href=\"图片加载中...\" style=\"\"/></p>',20,4.80,150,109.00,1,1,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(2,'红玫瑰21朵装-花语',1,1,2,178.00,'花语','21朵红玫瑰礼盒装，浪漫升级','http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg','[\"http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg\"]','<p>21朵红玫瑰礼盒，适合重要节日和纪念日</p>',10,4.80,80,178.00,1,0,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(3,'园艺剪刀-园艺大师',1,3,5,50.00,'园艺大师','专业园艺剪刀，锋利耐用','http://localhost:18091/images/products/scissors.svg','[\"http://localhost:18091/images/products/scissors.svg\"]','<p>专业园艺剪刀，不锈钢材质，锋利耐用</p>',5,5.00,30,50.00,0,1,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(4,'白百合6支装-花之语',1,2,3,85.00,'花之语','纯洁的白百合，象征百年好合','http://localhost:18091/images/products/lily.svg','[\"http://localhost:18091/images/products/lily.svg\"]','<p>优质白百合，新鲜花材，花期长</p>',15,4.75,85,85.00,0,0,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(5,'浇水壶-花园家',1,4,6,42.00,'花园家','容量大，喷洒均匀的浇水壶','http://localhost:18091/images/products/watering-can.svg','[\"http://localhost:18091/images/products/watering-can.svg\"]','<p>优质塑料浇水壶，容量2L，喷洒均匀</p>',8,0.00,0,42.00,0,1,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(11,'有机肥料-绿色家园',1,5,7,28.00,'绿色家园','天然有机肥料，环保无污染','http://localhost:18091/images/products/fertilizer.svg','[\"http://localhost:18091/images/products/fertilizer.svg\"]','<p>天然有机肥料，环保无污染，适合各种植物</p>',100,0.00,0,28.00,0,0,1,'2025-09-29 14:49:12','2025-09-29 14:49:12');

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `merchant_id` bigint NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `total_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠金额',
  `pay_amount` decimal(10,2) NOT NULL COMMENT '实际支付金额',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '订单状态（1-待付款，2-已付款，3-已发货，4-已完成，5-已取消，6-退款中，7-已退款）',
  `receiver_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货人电话',
  `receiver_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货地址',
  `payment_method` tinyint DEFAULT NULL COMMENT '支付方式（1-支付宝，2-微信，3-银行卡）',
  `payment_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '订单备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_order_no` (`order_no`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_merchant_id` (`merchant_id`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--


--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
  `order_id` bigint NOT NULL COMMENT '订单ID（逻辑关联order表）',
  `product_id` bigint NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_id` bigint NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
  `merchant_id` bigint NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `sku_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU名称',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `quantity` int NOT NULL COMMENT '数量',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_order_id` (`order_id`) USING BTREE,
  KEY `idx_product_id` (`product_id`) USING BTREE,
  KEY `idx_sku_id` (`sku_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='订单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` VALUES (1,1,1,2,1,'红玫瑰1','红玫瑰-21朵装',168.00,1,168.00,'2025-10-02 10:00:00','2025-10-02 10:00:00'),(2,1,5,7,1,'有机肥料','有机肥料-1kg装',28.00,2,56.00,'2025-10-02 10:00:00','2025-10-02 10:00:00'),(3,2,2,3,1,'白百合','白百合-6支装',78.00,1,78.00,'2025-10-02 11:00:00','2025-10-02 11:00:00'),(4,3,1,2,1,'红玫瑰-测试','红玫瑰-21朵装',178.00,1,178.00,'2025-10-06 21:10:44','2025-10-06 21:10:44'),(5,4,1,2,1,'红玫瑰-测试','红玫瑰-21朵装',178.00,1,178.00,'2025-10-06 21:12:43','2025-10-06 21:12:43'),(6,5,1,2,1,'红玫瑰-测试','红玫瑰-21朵装',178.00,1,178.00,'2025-10-06 21:14:35','2025-10-06 21:14:35'),(7,6,1,2,1,'红玫瑰-测试','红玫瑰-21朵装',178.00,1,178.00,'2025-10-06 21:15:09','2025-10-06 21:15:09'),(8,7,1,2,1,'红玫瑰-测试','红玫瑰-21朵装',178.00,1,178.00,'2025-10-06 21:16:10','2025-10-06 21:16:10'),(9,8,1,2,1,'红玫瑰-测试','红玫瑰-21朵装',178.00,2,356.00,'2025-10-06 21:20:14','2025-10-06 21:20:14'),(10,9,1,2,1,'红玫瑰-测试','红玫瑰-21朵装',178.00,1,178.00,'2025-10-06 22:24:26','2025-10-06 22:24:26'),(11,10,1,1,1,'红玫瑰-测试','红玫瑰-11朵装',109.00,1,109.00,'2025-10-06 22:53:27','2025-10-06 22:53:27'),(12,11,1,2,1,'红玫瑰-测试','红玫瑰-21朵装',178.00,1,178.00,'2025-10-06 22:55:48','2025-10-06 22:55:48'),(13,12,2,3,1,'白百合','白百合-6支装',85.00,1,85.00,'2025-10-07 12:18:40','2025-10-07 12:18:40'),(14,13,5,7,1,'有机肥料','有机肥料-1kg装',28.00,1,28.00,'2025-10-07 12:24:21','2025-10-07 12:24:21');

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '支付方式ID',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `method_name` varchar(50) NOT NULL COMMENT '支付方式名称',
  `method_code` varchar(20) NOT NULL COMMENT '支付方式编码',
  `description` varchar(200) DEFAULT NULL COMMENT '支付方式描述',
  `account_number` varchar(50) DEFAULT NULL COMMENT '账户号码/卡号',
  `account_name` varchar(50) DEFAULT NULL COMMENT '账户名称',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '银行名称',
  `bank_branch` varchar(100) DEFAULT NULL COMMENT '开户行',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-启用）',
  `sort` int DEFAULT '0' COMMENT '排序号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付方式表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

INSERT INTO `payment_method` VALUES (1,1,'支付宝','ALIPAY','支付宝支付','13800000001','张三',NULL,NULL,1,1,'2025-10-06 13:53:03','2025-10-06 13:53:03'),(2,1,'微信支付','WECHAT_PAY','微信支付','13800000001','张三',NULL,NULL,1,2,'2025-10-06 13:53:03','2025-10-06 13:53:03'),(3,2,'银行卡支付','BANK_CARD','银行卡支付','6222001234567890','李四','工商银行','北京分行',1,3,'2025-10-06 13:53:03','2025-10-06 13:53:03'),(4,3,'支付宝','ALIPAY','支付宝支付','13800000002','王五',NULL,NULL,1,1,'2025-10-06 13:53:03','2025-10-06 13:53:03');

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品名称',
  `product_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '产品编码',
  `category_id` bigint NOT NULL COMMENT '分类ID（逻辑关联product_category表）',
  `main_image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '主图URL',
  `product_type` tinyint NOT NULL DEFAULT '1' COMMENT '产品类型（1-花卉，2-第三方产品）',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-下架，1-上架）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_product_code` (`product_code`) USING BTREE,
  KEY `idx_category_id` (`category_id`) USING BTREE,
  KEY `idx_product_type` (`product_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='产品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

INSERT INTO `product` VALUES (1,'红玫瑰-测试','FLOWER-001',4,'http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg',1,1,'2025-09-29 13:17:22','2025-10-06 06:42:29'),(2,'白百合','FLOWER-002',5,'http://localhost:18091/images/products/lily.svg',1,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(3,'园艺剪刀','TOOL-001',6,'http://localhost:18091/images/products/scissors.svg',2,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(4,'浇水壶','TOOL-002',7,'http://localhost:18091/images/products/watering-can.svg',2,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(5,'有机肥料','FERT-001',8,'http://localhost:18091/images/products/fertilizer.svg',2,1,'2025-09-29 13:17:22','2025-09-29 13:17:22');

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父分类ID（用于构建分类树）',
  `category_level` tinyint NOT NULL DEFAULT '1' COMMENT '分类级别（1-一级分类，2-二级分类，3-三级分类）',
  `sort` int DEFAULT '0' COMMENT '排序号',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-正常）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='产品分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` VALUES (1,'花卉植物',0,1,1,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(2,'园艺工具',0,1,2,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(3,'肥料农药',0,1,3,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(4,'玫瑰花',1,2,1,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(5,'百合花',1,2,2,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(6,'修剪工具',2,2,1,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(7,'浇水工具',2,2,2,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(8,'有机肥料',3,2,1,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(9,'杀虫农药',3,2,2,1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(10,'盆栽',0,1,0,1,'2025-10-04 02:00:37','2025-10-04 02:00:37');

--
-- Table structure for table `product_sku`
--

DROP TABLE IF EXISTS `product_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_sku` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'SKU ID',
  `product_id` bigint NOT NULL COMMENT '产品ID（逻辑关联product表）',
  `sku_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU名称（如：红色-L）',
  `sku_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU编码',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `stock` int NOT NULL DEFAULT '0' COMMENT '库存数量',
  `specifications` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '规格描述，JSON格式存储',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-无效，1-有效）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_sku_code` (`sku_code`) USING BTREE,
  KEY `idx_product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='产品SKU表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_sku`
--

INSERT INTO `product_sku` VALUES (1,1,'红玫瑰-11朵装','ROSE-11',99.00,100,'{\"颜色\": \"红色\", \"数量\": 11}',1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(2,1,'红玫瑰-21朵装','ROSE-21',168.00,50,'{\"颜色\": \"红色\", \"数量\": 21}',1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(3,2,'白百合-6支装','LILY-6',78.00,80,'{\"颜色\": \"白色\", \"数量\": 6}',1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(4,2,'白百合-12支装','LILY-12',138.00,40,'{\"颜色\": \"白色\", \"数量\": 12}',1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(5,3,'园艺剪刀-标准版','SCISSORS-S',45.00,30,'{\"材质\": \"不锈钢\", \"长度\": \"20cm\"}',1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(6,4,'浇水壶-大号','WATERCAN-L',38.00,25,'{\"容量\": \"2L\", \"颜色\": \"绿色\"}',1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(7,5,'有机肥料-1kg装','FERT-1KG',28.00,100,'{\"重量\": \"1kg\", \"类型\": \"颗粒状\"}',1,'2025-09-29 13:17:22','2025-09-29 13:17:22');

--
-- Table structure for table `receiver_address`
--

DROP TABLE IF EXISTS `receiver_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receiver_address` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `receiver_name` varchar(50) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) NOT NULL COMMENT '收货人电话',
  `province` varchar(50) NOT NULL COMMENT '省份',
  `city` varchar(50) NOT NULL COMMENT '城市',
  `district` varchar(50) DEFAULT NULL COMMENT '区/县',
  `detail_address` varchar(200) NOT NULL COMMENT '详细地址',
  `postal_code` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `is_default` tinyint NOT NULL DEFAULT '0' COMMENT '是否默认地址（0-否，1-是）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收货信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiver_address`
--

INSERT INTO `receiver_address` VALUES (1,1,'张三','13800000001','北京市','北京市','朝阳区','陆家嘴金融中心1号楼101室','200001',1,'2025-10-02 10:00:00','2025-10-02 10:00:00'),(2,1,'张三','13800000001','北京市','北京市','朝阳区','三里屯大街18号','100001',0,'2025-10-02 10:05:00','2025-10-02 10:05:00'),(3,3,'李四','13800000002','广东省','广州市','天河区','珠江新城华夏路101号','510000',1,'2025-10-02 11:00:00','2025-10-02 11:00:00'),(4,4,'王五','13800000003','浙江省','杭州市','西湖区','文三路259号昌地火炬大厦201室','310000',1,'2025-10-02 12:00:00','2025-10-02 12:00:00');

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车项ID',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `merchant_product_id` bigint NOT NULL COMMENT '商品品ID（逻辑关联merchant_product表）',
  `sku_id` bigint NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
  `merchant_id` bigint NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-无效，1-有效）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_merchant_product_id` (`merchant_product_id`),
  KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

INSERT INTO `shopping_cart` VALUES (1,2,3,5,1,1,45.00,1,'2025-10-01 10:00:00','2025-10-01 10:00:00'),(2,3,4,6,1,2,38.00,1,'2025-10-01 15:00:00','2025-10-01 15:00:00'),(3,2,1,2,1,1,168.00,1,'2025-10-02 09:30:00','2025-10-02 09:30:00'),(4,3,5,7,1,3,28.00,1,'2025-10-02 14:20:00','2025-10-02 14:20:00'),(5,1,1,1,1,1,395.00,1,'2025-10-06 15:32:46','2025-10-06 13:01:20'),(6,1,3,5,1,1,304.00,1,'2025-10-06 15:33:42','2025-10-06 15:33:42');

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint DEFAULT '0' COMMENT '父权限ID（用于构建权限树）',
  `permission_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限编码',
  `permission_type` tinyint NOT NULL COMMENT '权限类型（1-菜单，2-按钮，3-接口）',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '资源路径（如URL）',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方法（GET/POST等，适用于接口权限）',
  `sort` int DEFAULT '0' COMMENT '排序号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_permission_code` (`permission_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

INSERT INTO `sys_permission` VALUES (1,0,'系统管理','sys:manage',1,'','',1,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(2,1,'用户管理','sys:user:manage',1,'/user/**','',2,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(3,2,'查询用户','sys:user:list',3,'/user/list','GET',3,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(4,2,'新增用户','sys:user:add',3,'/user/add','POST',4,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(5,2,'修改用户','sys:user:update',3,'/user/update','PUT',5,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(6,2,'删除用户','sys:user:delete',3,'/user/delete','DELETE',6,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(7,1,'角色管理','sys:role:manage',1,'/role/**','',7,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(8,7,'查询角色','sys:role:list',3,'/role/list','GET',8,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(9,7,'新增角色','sys:role:add',3,'/role/add','POST',9,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(10,7,'修改角色','sys:role:update',3,'/role/update','PUT',10,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(11,7,'删除角色','sys:role:delete',3,'/role/delete','DELETE',11,'2025-09-29 13:17:22','2025-09-29 13:17:22'),(12,0,'商户管理','MARVHSNT',1,'','',0,'2025-10-04 01:58:11','2025-10-04 01:58:11'),(13,12,'商户仪表板','test',1,'','',0,'2025-10-04 01:58:54','2025-10-04 01:58:54');

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色编码',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_code` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

INSERT INTO `sys_role` VALUES (1,'超级管理员','ROLE_ADMIN','拥有所有权限','2025-09-29 13:17:21','2025-09-29 13:17:21'),(2,'普通用户','ROLE_USER','普通用户权限','2025-09-29 13:17:21','2025-09-29 13:17:21'),(3,'商户','ROLE_MERCHANT','商户权限','2025-09-29 13:17:21','2025-09-29 13:17:21'),(4,'访客','ROLE_GUEST','访客权限','2025-09-29 13:17:21','2025-09-29 13:17:21');

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`) USING BTREE,
  KEY `idx_permission_id` (`permission_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

INSERT INTO `sys_role_permission` VALUES (1,1,1,'2025-09-29 13:17:22'),(2,1,2,'2025-09-29 13:17:22'),(3,1,3,'2025-09-29 13:17:22'),(4,1,4,'2025-09-29 13:17:22'),(5,1,5,'2025-09-29 13:17:22'),(6,1,6,'2025-09-29 13:17:22'),(7,1,7,'2025-09-29 13:17:22'),(8,1,8,'2025-09-29 13:17:22'),(9,1,9,'2025-09-29 13:17:22'),(10,1,10,'2025-09-29 13:17:22'),(11,1,11,'2025-09-29 13:17:22'),(12,2,3,'2025-09-29 13:17:22'),(13,2,8,'2025-09-29 13:17:22'),(14,3,3,'2025-09-29 13:17:22'),(15,3,8,'2025-09-29 13:17:22');

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名(登陆)',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码(加密存储)',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像URL',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-正常）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

INSERT INTO `sys_user` VALUES (1,'admin','admin','管理员','admin@example.com','13800000000','北京市朝阳区','http://localhost:18091/images/uploads/7af6d51e3b2841d3bbba15d3ab923bb1.jpg',1,'2025-09-29 13:17:21','2025-09-29 13:17:21'),(2,'user1','user1','用户1','user1@example.com','13800000001','上海市浦东新区',NULL,1,'2025-09-29 13:17:21','2025-09-29 13:17:21'),(3,'user2','user2','用户2','user2@example.com','13800000002','广州市天河区',NULL,1,'2025-09-29 13:17:21','2025-09-29 13:17:21');

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_role` (`user_id`,`role_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

INSERT INTO `sys_user_role` VALUES (1,1,1,'2025-09-29 13:17:22'),(2,2,2,'2025-09-29 13:17:22'),(3,3,2,'2025-09-29 13:17:22');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-07 12:33:14

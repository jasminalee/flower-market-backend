CREATE TABLE `product`
(
    `id`           bigint(0) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `product_name` varchar(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '产品名称',
    `product_code` varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '产品编码',
    `category_id`  bigint(0) NOT NULL COMMENT '分类ID（逻辑关联product_category表）',
    `main_image`   varchar(500) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '主图URL',
    `product_type` tinyint(0) NOT NULL DEFAULT 1 COMMENT '产品类型（1-花卉，2-第三方产品）',
    `status`       tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态（0-下架，1-上架）',
    `create_time`  datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`  datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_product_code`(`product_code`) USING BTREE,
    INDEX          `idx_category_id`(`category_id`) USING BTREE,
    INDEX          `idx_product_type`(`product_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4  COMMENT = '产品信息表' ROW_FORMAT = Dynamic

DROP TABLE IF EXISTS `merchant_product`;
CREATE TABLE `merchant_product`
(
    `id`            bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `merchant_name` varchar(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '商品名称',
    `merchant_id`   bigint(0) NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
    `product_id`    bigint(0) NOT NULL COMMENT '产品ID（逻辑关联product表）',
    `sku_id`        bigint(0) NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
    `price`         decimal(10, 2) NOT NULL COMMENT '商户定价',
    `brand`         varchar(100) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '品牌',
    `description`   text CHARACTER SET utf8mb4  NULL COMMENT '产品描述',
    `main_image`   varchar(500) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '主图URL',
    `sub_images`    text CHARACTER SET utf8mb4  NULL COMMENT '子图URL集合，JSON格式存储',
    `detail`        text CHARACTER SET utf8mb4  NULL COMMENT '产品详情',
    `stock`         int(0) NOT NULL DEFAULT 0 COMMENT '商户库存',
    `avg_rating`    decimal(3, 2) NULL COMMENT '平均评分',
    `total_sales`   int(0) NULL DEFAULT 0 COMMENT '总销量',
    `min_price`     decimal(10, 2) NULL COMMENT '最低价格',
    `is_hot`        tinyint(1) NULL DEFAULT 0 COMMENT '是否热销(1:是,0:否)',
    `is_discounted` tinyint(1) NULL DEFAULT 0 COMMENT '是否打折(1:是,0:否)',
    `status`        tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态（0-下架，1-上架）',
    `create_time`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_merchant_product_sku`(`merchant_id`, `product_id`, `sku_id`) USING BTREE,
    INDEX           `idx_merchant_id`(`merchant_id`) USING BTREE,
    INDEX           `idx_product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4  COMMENT = '商户产品关联表' ROW_FORMAT = Dynamic

-- ----------------------------
-- Records of merchant_product
-- ----------------------------
INSERT INTO `merchant_product`
VALUES (1, '红玫瑰11朵装-花语', 1, 1, 1, 109.00, '花语', '鲜艳的红玫瑰，适合表达爱意', 'http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg', '[\"http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg\", \"http://localhost:18091/images/uploads/760c4697d3d640ce82faa0652d9c97ff.jpg\"]', '<ul><li><span style=\"color: rgb(255, 77, 79);\">测试标签</span></li></ul><p><img src=\"http://localhost:18091/images/uploads/760c4697d3d640ce82faa0652d9c97ff.jpg\" alt=\"image.png\" data-href=\"图片加载中...\" style=\"\"/></p>', 20, 4.80, 150, 109.00, 1, 1, 1, '2025-09-29 13:17:22',
        '2025-09-29 13:17:22');
INSERT INTO `merchant_product`
VALUES (2, '红玫瑰21朵装-花语', 1, 1, 2, 178.00, '花语', '21朵红玫瑰礼盒装，浪漫升级', 'http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg', '[\"http://localhost:18091/images/uploads/9497d8b9074b4652bc2bf04804a451a3.jpg\"]', '<p>21朵红玫瑰礼盒，适合重要节日和纪念日</p>', 10, 4.80, 80, 178.00, 1, 0, 1, '2025-09-29 13:17:22',
        '2025-09-29 13:17:22');
INSERT INTO `merchant_product`
VALUES (3, '园艺剪刀-园艺大师', 1, 3, 5, 50.00, '园艺大师', '专业园艺剪刀，锋利耐用', 'http://localhost:18091/images/products/scissors.svg', '[\"http://localhost:18091/images/products/scissors.svg\"]', '<p>专业园艺剪刀，不锈钢材质，锋利耐用</p>', 5, 5.00, 30, 50.00, 0, 1, 1, '2025-09-29 13:17:22',
        '2025-09-29 13:17:22');
INSERT INTO `merchant_product`
VALUES (4, '白百合6支装-花之语', 1, 2, 3, 85.00, '花之语', '纯洁的白百合，象征百年好合', 'http://localhost:18091/images/products/lily.svg', '[\"http://localhost:18091/images/products/lily.svg\"]', '<p>优质白百合，新鲜花材，花期长</p>', 15, 4.75, 85, 85.00, 0, 0, 1, '2025-09-29 13:17:22',
        '2025-09-29 13:17:22');
INSERT INTO `merchant_product`
VALUES (5, '浇水壶-花园家', 1, 4, 6, 42.00, '花园家', '容量大，喷洒均匀的浇水壶', 'http://localhost:18091/images/products/watering-can.svg', '[\"http://localhost:18091/images/products/watering-can.svg\"]', '<p>优质塑料浇水壶，容量2L，喷洒均匀</p>', 8, 0.00, 0, 42.00, 0, 1, 1, '2025-09-29 13:17:22',
        '2025-09-29 13:17:22');
INSERT INTO `merchant_product`
VALUES (11, '有机肥料-绿色家园', 1, 5, 7, 28.00, '绿色家园', '天然有机肥料，环保无污染', 'http://localhost:18091/images/products/fertilizer.svg', '[\"http://localhost:18091/images/products/fertilizer.svg\"]', '<p>天然有机肥料，环保无污染，适合各种植物</p>', 100, 0.00, 0, 28.00, 0, 0, 1, '2025-09-29 14:49:12',
        '2025-09-29 14:49:12');

-- 产品分类表：存储花卉和第三方产品的分类信息
CREATE TABLE `product_category` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    `category_name` VARCHAR(100) NOT NULL COMMENT '分类名称',
    `parent_id` BIGINT(20) DEFAULT 0 COMMENT '父分类ID（用于构建分类树）',
    `category_level` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '分类级别（1-一级分类，2-二级分类，3-三级分类）',
    `sort` INT(11) DEFAULT 0 COMMENT '排序号',
    `status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-正常）',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品分类表';

-- 产品信息表：存储具体的花卉和第三方产品信息
CREATE TABLE `product` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `product_name` VARCHAR(200) NOT NULL COMMENT '产品名称',
    `product_code` VARCHAR(100) NOT NULL COMMENT '产品编码',
    `category_id` BIGINT(20) NOT NULL COMMENT '分类ID（逻辑关联product_category表）',
    `brand` VARCHAR(100) DEFAULT NULL COMMENT '品牌',
    `description` TEXT DEFAULT NULL COMMENT '产品描述',
    `main_image` VARCHAR(500) DEFAULT NULL COMMENT '主图URL',
    `sub_images` TEXT DEFAULT NULL COMMENT '子图URL集合，JSON格式存储',
    `detail` TEXT DEFAULT NULL COMMENT '产品详情',
    `product_type` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '产品类型（1-花卉，2-第三方产品）',
    `status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '状态（0-下架，1-上架）',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_product_code` (`product_code`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_product_type` (`product_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品信息表';

-- 产品SKU表：存储产品的不同规格、价格等信息
CREATE TABLE `product_sku` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'SKU ID',
    `product_id` BIGINT(20) NOT NULL COMMENT '产品ID（逻辑关联product表）',
    `sku_name` VARCHAR(200) NOT NULL COMMENT 'SKU名称（如：红色-L）',
    `sku_code` VARCHAR(100) NOT NULL COMMENT 'SKU编码',
    `price` DECIMAL(10,2) NOT NULL COMMENT '价格',
    `stock` INT(11) NOT NULL DEFAULT 0 COMMENT '库存数量',
    `specifications` VARCHAR(500) DEFAULT NULL COMMENT '规格描述，JSON格式存储',
    `status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '状态（0-无效，1-有效）',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_sku_code` (`sku_code`),
    KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品SKU表';

-- 商户产品关联表：记录商户上架的产品
CREATE TABLE `merchant_product` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `merchant_id` BIGINT(20) NOT NULL COMMENT '商户ID（逻辑关联sys_user表）',
    `product_id` BIGINT(20) NOT NULL COMMENT '产品ID（逻辑关联product表）',
    `sku_id` BIGINT(20) NOT NULL COMMENT 'SKU ID（逻辑关联product_sku表）',
    `price` DECIMAL(10,2) NOT NULL COMMENT '商户定价',
    `stock` INT(11) NOT NULL DEFAULT 0 COMMENT '商户库存',
    `status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '状态（0-下架，1-上架）',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_merchant_product_sku` (`merchant_id`, `product_id`, `sku_id`),
    KEY `idx_merchant_id` (`merchant_id`),
    KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户产品关联表';

-- 通用评论表：存储各种类型的评论
CREATE TABLE `comment` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `source_id` BIGINT(20) NOT NULL COMMENT '来源ID（可以是产品ID、文章ID等）',
    `source_type` VARCHAR(50) NOT NULL COMMENT '来源类型（product, article, forum等）',
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
    `parent_id` BIGINT(20) DEFAULT 0 COMMENT '父评论ID（用于构建评论树，0表示顶级评论）',
    `order_id` BIGINT(20) DEFAULT NULL COMMENT '订单ID（逻辑关联order表，可为空，仅对产品评论有效）',
    `rating` TINYINT(4) DEFAULT NULL COMMENT '评分（1-5分，可为空）',
    `content` TEXT NOT NULL COMMENT '评论内容',
    `is_anonymous` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '是否匿名（0-否，1-是）',
    `status` TINYINT(4) NOT NULL DEFAULT 1 COMMENT '状态（0-隐藏，1-显示）',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_source` (`source_id`, `source_type`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_rating` (`rating`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通用评论表';
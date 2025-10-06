-- 支付方式表
DROP TABLE IF EXISTS `payment_method`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付方式表';

--
-- 支付方式测试数据
--

INSERT INTO `payment_method` VALUES
(1, 1, '支付宝', 'ALIPAY', '支付宝支付', '13800000001', '张三', NULL, NULL, 1, 1, NOW(), NOW()),
(2, 1, '微信支付', 'WECHAT_PAY', '微信支付', '13800000001', '张三', NULL, NULL, 1, 2, NOW(), NOW()),
(3, 2, '银行卡支付', 'BANK_CARD', '银行卡支付', '6222001234567890', '李四', '工商银行', '北京分行', 1, 3, NOW(), NOW()),
(4, 3, '支付宝', 'ALIPAY', '支付宝支付', '13800000002', '王五', NULL, NULL, 1, 1, NOW(), NOW());
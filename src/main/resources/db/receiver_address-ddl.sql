-- 收货信息表
DROP TABLE IF EXISTS `receiver_address`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收货信息表';

--
-- 收货地址测试数据
--

INSERT INTO `receiver_address` VALUES
(1, 2, '张三', '13800000001', '上海市', '上海市', '浦东新区', '陆家嘴金融中心1号楼101室', '200001', 1, '2025-10-02 10:00:00', '2025-10-02 10:00:00'),
(2, 2, '张三', '13800000001', '北京市', '北京市', '朝阳区', '三里屯大街18号', '100001', 0, '2025-10-02 10:05:00', '2025-10-02 10:05:00'),
(3, 3, '李四', '13800000002', '广东省', '广州市', '天河区', '珠江新城华夏路101号', '510000', 1, '2025-10-02 11:00:00', '2025-10-02 11:00:00'),
(4, 4, '王五', '13800000003', '浙江省', '杭州市', '西湖区', '文三路259号昌地火炬大厦201室', '310000', 1, '2025-10-02 12:00:00', '2025-10-02 12:00:00');
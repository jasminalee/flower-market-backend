truncate table sys_user;
-- 重新插入测试用户数据
INSERT INTO sys_user (username, password, nickname, email, phone, addr, status) VALUES
('admin', 'admin', '管理员', 'admin@example.com', '13800000000', '北京市朝阳区', 1),
('user1', 'user1', '用户1', 'user1@example.com', '13800000001', '上海市浦东新区', 1),
('user2', 'user2', '用户2', 'user2@example.com', '13800000002', '广州市天河区', 1);

truncate table sys_role;
-- 插入测试角色数据
INSERT INTO sys_role (role_name, role_code, description) VALUES
('超级管理员', 'ROLE_ADMIN', '拥有所有权限'),
('普通用户', 'ROLE_USER', '普通用户权限'),
('商户', 'ROLE_MERCHANT', '商户权限'),
('访客', 'ROLE_GUEST', '访客权限');

truncate table sys_permission;
-- 插入测试权限数据
INSERT INTO sys_permission (parent_id, permission_name, permission_code, permission_type, url, method, sort) VALUES
(0, '系统管理', 'sys:manage', 1, '', '', 1),
(1, '用户管理', 'sys:user:manage', 1, '/user/**', '', 2),
(2, '查询用户', 'sys:user:list', 3, '/user/list', 'GET', 3),
(2, '新增用户', 'sys:user:add', 3, '/user/add', 'POST', 4),
(2, '修改用户', 'sys:user:update', 3, '/user/update', 'PUT', 5),
(2, '删除用户', 'sys:user:delete', 3, '/user/delete', 'DELETE', 6),
(1, '角色管理', 'sys:role:manage', 1, '/role/**', '', 7),
(7, '查询角色', 'sys:role:list', 3, '/role/list', 'GET', 8),
(7, '新增角色', 'sys:role:add', 3, '/role/add', 'POST', 9),
(7, '修改角色', 'sys:role:update', 3, '/role/update', 'PUT', 10),
(7, '删除角色', 'sys:role:delete', 3, '/role/delete', 'DELETE', 11);

truncate table sys_user_role;
-- 插入用户角色关联数据
INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1), -- admin 用户分配 ROLE_ADMIN 角色
(2, 2), -- user1 用户分配 ROLE_USER 角色
(3, 2); -- user2 用户分配 ROLE_USER 角色

truncate table sys_role_permission;
-- 插入角色权限关联数据
INSERT INTO sys_role_permission (role_id, permission_id) VALUES
-- 管理员拥有所有权限
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11),
-- 普通用户只拥有基本查询权限
(2, 3), (2, 8),
-- 商户权限（预留）
(3, 3), (3, 8);

-- 清空并插入产品分类测试数据
truncate table product_category;
INSERT INTO product_category (category_name, parent_id, category_level, sort, status) VALUES
('花卉植物', 0, 1, 1, 1),
('园艺工具', 0, 1, 2, 1),
('肥料农药', 0, 1, 3, 1),
('玫瑰花', 1, 2, 1, 1),
('百合花', 1, 2, 2, 1),
('修剪工具', 2, 2, 1, 1),
('浇水工具', 2, 2, 2, 1),
('有机肥料', 3, 2, 1, 1),
('杀虫农药', 3, 2, 2, 1);

-- 清空并插入产品测试数据
truncate table product;
INSERT INTO product (product_name, product_code, category_id, brand, description, main_image, product_type, status) VALUES
('红玫瑰', 'FLOWER-001', 4, '花语', '鲜艳的红玫瑰，适合表达爱意', 'http://localhost:18091/images/products/rose.svg', 1, 1),
('白百合', 'FLOWER-002', 5, '花之语', '纯洁的白百合，象征百年好合', 'http://localhost:18091/images/products/lily.svg', 1, 1),
('园艺剪刀', 'TOOL-001', 6, '园艺大师', '专业园艺剪刀，锋利耐用', 'http://localhost:18091/images/products/scissors.svg', 2, 1),
('浇水壶', 'TOOL-002', 7, '花园家', '容量大，喷洒均匀的浇水壶', 'http://localhost:18091/images/products/watering-can.svg', 2, 1),
('有机肥料', 'FERT-001', 8, '绿色家园', '天然有机肥料，环保无污染', 'http://localhost:18091/images/products/fertilizer.svg', 2, 1);

-- 清空并插入产品SKU测试数据
truncate table product_sku;
INSERT INTO product_sku (product_id, sku_name, sku_code, price, stock, specifications, status) VALUES
(1, '红玫瑰-11朵装', 'ROSE-11', 99.00, 100, '{"颜色": "红色", "数量": 11}', 1),
(1, '红玫瑰-21朵装', 'ROSE-21', 168.00, 50, '{"颜色": "红色", "数量": 21}', 1),
(2, '白百合-6支装', 'LILY-6', 78.00, 80, '{"颜色": "白色", "数量": 6}', 1),
(2, '白百合-12支装', 'LILY-12', 138.00, 40, '{"颜色": "白色", "数量": 12}', 1),
(3, '园艺剪刀-标准版', 'SCISSORS-S', 45.00, 30, '{"材质": "不锈钢", "长度": "20cm"}', 1),
(4, '浇水壶-大号', 'WATERCAN-L', 38.00, 25, '{"容量": "2L", "颜色": "绿色"}', 1),
(5, '有机肥料-1kg装', 'FERT-1KG', 28.00, 100, '{"重量": "1kg", "类型": "颗粒状"}', 1);

-- 清空并插入商户产品测试数据
truncate table merchant_product;
INSERT INTO merchant_product (merchant_id, product_id, sku_id, price, stock, status) VALUES
(1, 1, 1, 109.00, 20, 1),
(1, 1, 2, 178.00, 10, 1),
(1, 3, 5, 50.00, 5, 1),
(1, 2, 3, 85.00, 15, 1),
(1, 4, 6, 42.00, 8, 1);

-- 清空并插入评论测试数据
truncate table comment;
INSERT INTO comment (source_id, source_type, user_id, parent_id, rating, content, is_anonymous, status) VALUES
(1, 'product', 2, 0, 5, '红玫瑰非常新鲜，包装也很精美，很满意！', 0, 1),
(1, 'product', 3, 0, 4, '花很不错，但配送稍微晚了一天', 1, 1),
(1, 'product', 2, 1, 5, '感谢您的好评，我们会继续努力提供更好的服务', 0, 1),
(2, 'product', 3, 0, 5, '白百合很香，品质很好', 0, 1);

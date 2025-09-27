
-- 重新插入测试用户数据
INSERT INTO sys_user (username, password, nickname, email, phone, addr, status) VALUES
('admin', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '管理员', 'admin@example.com', '13800000000', '北京市朝阳区', 1),
('user1', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '用户1', 'user1@example.com', '13800000001', '上海市浦东新区', 1),
('user2', '$2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', '用户2', 'user2@example.com', '13800000002', '广州市天河区', 1);

-- 插入测试角色数据
INSERT INTO sys_role (role_name, role_code, description) VALUES
('超级管理员', 'ROLE_ADMIN', '拥有所有权限'),
('普通用户', 'ROLE_USER', '普通用户权限'),
('访客', 'ROLE_GUEST', '访客权限');

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

-- 插入用户角色关联数据
INSERT INTO sys_user_role (user_id, role_id) VALUES
(1, 1), -- admin 用户分配 ROLE_ADMIN 角色
(2, 2), -- user1 用户分配 ROLE_USER 角色
(3, 2); -- user2 用户分配 ROLE_USER 角色

-- 插入角色权限关联数据
INSERT INTO sys_role_permission (role_id, permission_id) VALUES
-- 管理员拥有所有权限
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11),
-- 普通用户只拥有基本查询权限
(2, 3), (2, 8);
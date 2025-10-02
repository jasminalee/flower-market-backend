
-- 用户表：存储用户基本信息
CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `username` varchar(50) NOT NULL COMMENT '用户名(登陆)',
                            `password` varchar(100) NOT NULL COMMENT '密码(加密存储)',
                            `nickname` varchar(50) NOT NULL comment '昵称',
                            `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                            `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
                            `addr` varchar(255) NOT NULL COMMENT '地址',
                            `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-正常）',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 角色表：存储角色信息
CREATE TABLE `sys_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                            `role_name` varchar(50) NOT NULL COMMENT '角色名称',
                            `role_code` varchar(50) NOT NULL COMMENT '角色编码',
                            `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';

-- 权限表：存储权限信息
CREATE TABLE `sys_permission` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                                  `parent_id` bigint(20) DEFAULT 0 COMMENT '父权限ID（用于构建权限树）',
                                  `permission_name` varchar(100) NOT NULL COMMENT '权限名称',
                                  `permission_code` varchar(100) NOT NULL COMMENT '权限编码',
                                  `permission_type` tinyint(4) NOT NULL COMMENT '权限类型（1-菜单，2-按钮，3-接口）',
                                  `url` varchar(255) DEFAULT NULL COMMENT '资源路径（如URL）',
                                  `method` varchar(10) DEFAULT NULL COMMENT '请求方法（GET/POST等，适用于接口权限）',
                                  `sort` int(11) DEFAULT 0 COMMENT '排序号',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_permission_code` (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限表';

-- 用户角色关联表：用户与角色的多对多关系
CREATE TABLE `sys_user_role` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `uk_user_role` (`user_id`,`role_id`),
                                 KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 角色权限关联表：角色与权限的多对多关系
CREATE TABLE `sys_role_permission` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                       `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                                       `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
                                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`),
                                       KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

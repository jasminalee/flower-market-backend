-- 论坛板块表
DROP TABLE IF EXISTS `forum_category`;
CREATE TABLE `forum_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '板块ID',
  `name` varchar(100) NOT NULL COMMENT '板块名称',
  `description` varchar(500) DEFAULT NULL COMMENT '板块描述',
  `parent_id` bigint DEFAULT '0' COMMENT '父板块ID（用于构建板块树，0表示顶级板块）',
  `level` tinyint NOT NULL DEFAULT '1' COMMENT '板块级别（1-一级板块，2-二级板块）',
  `sort` int DEFAULT '0' COMMENT '排序号',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-启用）',
  `icon` varchar(255) DEFAULT NULL COMMENT '板块图标URL',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='论坛板块表';

-- 论坛帖子表
DROP TABLE IF EXISTS `forum_post`;
CREATE TABLE `forum_post` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `category_id` bigint NOT NULL COMMENT '板块ID（逻辑关联forum_category表）',
  `user_id` bigint NOT NULL COMMENT '用户ID（逻辑关联sys_user表）',
  `title` varchar(200) NOT NULL COMMENT '帖子标题',
  `content` longtext COMMENT '帖子内容',
  `post_type` tinyint NOT NULL DEFAULT '1' COMMENT '帖子类型（1-普通文本，2-视频）',
  `video_url` varchar(500) DEFAULT NULL COMMENT '视频URL（仅当post_type为2时有效）',
  `cover_image` varchar(500) DEFAULT NULL COMMENT '视频封面图片URL',
  `view_count` int NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞次数',
  `favorite_count` int NOT NULL DEFAULT '0' COMMENT '收藏次数',
  `comment_count` int NOT NULL DEFAULT '0' COMMENT '评论次数',
  `is_top` tinyint NOT NULL DEFAULT '0' COMMENT '是否置顶（0-否，1-是）',
  `is_essence` tinyint NOT NULL DEFAULT '0' COMMENT '是否精华（0-否，1-是）',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-删除，1-正常，2-审核中）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='论坛帖子表';
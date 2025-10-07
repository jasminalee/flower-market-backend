# 论坛评论测试数据使用说明

## 测试数据说明

我们已经为您准备了用于测试的论坛评论数据，这些数据可以用于测试[sourceType](file:///E:/IdeaProjects/flower-market-backend/src/main/java/vtc/xueqing/flower/entity/Comment.java#L27-L27)为"forum"的评论功能。

测试数据文件位置: `src/main/resources/db/comment-forum-test-data.sql`

## 数据内容

测试数据包括:

1. 为4个论坛帖子添加评论和回复
2. 更新帖子的评论计数

### 评论结构

- 帖子1 (ID: 1, 标题: "如何正确浇水") - 5条评论(3条顶级评论，2条回复)
- 帖子2 (ID: 2, 标题: "我的玫瑰花园") - 3条评论(2条顶级评论，1条回复)
- 帖子3 (ID: 3, 标题: "叶子发黄怎么办") - 3条评论(2条顶级评论，1条回复)
- 帖子4 (ID: 4, 标题: "玫瑰修剪技巧") - 3条评论(2条顶级评论，1条回复)

## 导入测试数据

由于环境中可能存在不同的数据库配置，您可以选择以下任一方式导入测试数据:

### 方式1: 使用MySQL命令行(推荐)

```sql
mysql -u [用户名] -p[密码] [数据库名] < src/main/resources/db/comment-forum-test-data.sql
```

示例:
```bash
mysql -u root -p flower_market < src/main/resources/db/comment-forum-test-data.sql
```

### 方式2: 手动复制粘贴

打开 `src/main/resources/db/comment-forum-test-data.sql` 文件，将其中的SQL语句复制到您的MySQL客户端中执行。

### 方式3: 在应用程序中执行

您也可以通过应用程序的数据库连接来执行这些SQL语句。

## 验证数据

导入成功后，您可以执行以下查询来验证数据:

```sql
-- 查看所有论坛评论
SELECT * FROM comment WHERE source_type = 'forum';

-- 查看特定帖子的评论
SELECT * FROM comment WHERE source_id = 1 AND source_type = 'forum';

-- 查看评论回复
SELECT * FROM comment WHERE parent_id > 0 AND source_type = 'forum';

-- 查看帖子评论计数
SELECT id, title, comment_count FROM forum_post;
```

## 测试用例

您可以使用以下测试场景:

1. 获取特定帖子的所有评论(包括回复)
2. 获取特定帖子的顶级评论
3. 获取特定评论的回复
4. 验证帖子的评论计数是否正确更新
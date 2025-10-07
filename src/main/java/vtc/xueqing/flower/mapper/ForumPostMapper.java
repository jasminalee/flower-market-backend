package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.ForumPost;

/**
 * 论坛帖子表;(forum_post)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface ForumPostMapper extends BaseMapper<ForumPost> {
}
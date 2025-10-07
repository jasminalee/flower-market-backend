package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.ForumCategory;

/**
 * 论坛板块表;(forum_category)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface ForumCategoryMapper extends BaseMapper<ForumCategory> {
}
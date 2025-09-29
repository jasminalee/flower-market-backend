package vtc.xueqing.flower.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vtc.xueqing.flower.entity.Comment;

/**
 * 通用评论表;(comment)表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
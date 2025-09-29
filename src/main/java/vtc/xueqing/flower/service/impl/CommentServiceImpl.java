package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.Comment;
import vtc.xueqing.flower.mapper.CommentMapper;
import vtc.xueqing.flower.service.CommentService;

/**
 * 通用评论表;(comment)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
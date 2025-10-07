package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.ForumPost;
import vtc.xueqing.flower.mapper.ForumPostMapper;
import vtc.xueqing.flower.service.ForumPostService;

/**
 * 论坛帖子表;(forum_post)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class ForumPostServiceImpl extends ServiceImpl<ForumPostMapper, ForumPost> implements ForumPostService {
}
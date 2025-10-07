package vtc.xueqing.flower.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vtc.xueqing.flower.entity.ForumCategory;
import vtc.xueqing.flower.mapper.ForumCategoryMapper;
import vtc.xueqing.flower.service.ForumCategoryService;

/**
 * 论坛板块表;(forum_category)表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class ForumCategoryServiceImpl extends ServiceImpl<ForumCategoryMapper, ForumCategory> implements ForumCategoryService {
}
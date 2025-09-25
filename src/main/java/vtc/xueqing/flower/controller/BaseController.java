package vtc.xueqing.flower.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 通用基础控制器，封装常用的CRUD操作
 */
public class BaseController {

    /**
     * 获取分页对象，可直接用于service.page()
     *
     * @param current 当前页码
     * @param size    每页大小
     * @return Page对象
     */
    protected <T> Page<T> getPage(Long current, Long size) {
        return new Page<>(current, size);
    }
}
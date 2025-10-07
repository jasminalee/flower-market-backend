package vtc.xueqing.flower.vo;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vtc.xueqing.flower.entity.ForumCategory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 论坛分类树VO类
 * 用于封装分类树结构，方便前端使用
 */
@ApiModel("论坛分类树VO")
@Data
public class ForumCategoryTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("板块ID")
    private Long id;

    @ApiModelProperty("板块名称")
    private String name;

    @ApiModelProperty("板块描述")
    private String description;

    @ApiModelProperty("父板块ID")
    private Long parentId;

    @ApiModelProperty("板块级别")
    private Integer level;

    @ApiModelProperty("排序号")
    private Integer sort;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("板块图标URL")
    private String icon;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("子板块列表")
    private List<ForumCategoryTreeVO> children;
    
    public ForumCategoryTreeVO(ForumCategory category) {
        BeanUtil.copyProperties(category, this);
    }
}
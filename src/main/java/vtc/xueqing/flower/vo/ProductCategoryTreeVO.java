package vtc.xueqing.flower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vtc.xueqing.flower.entity.ProductCategory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 产品分类树VO类
 * 用于封装分类树结构，方便前端使用
 */
@ApiModel("产品分类树VO")
@Data
public class ProductCategoryTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类ID")
    private Long id;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("父分类ID")
    private Long parentId;

    @ApiModelProperty("分类级别")
    private Integer categoryLevel;

    @ApiModelProperty("排序号")
    private Integer sort;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("子分类列表")
    private List<ProductCategoryTreeVO> children;
    
    public ProductCategoryTreeVO(ProductCategory category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.parentId = category.getParentId();
        this.categoryLevel = category.getCategoryLevel();
        this.sort = category.getSort();
        this.status = category.getStatus();
        this.createTime = category.getCreateTime();
        this.updateTime = category.getUpdateTime();
    }
}
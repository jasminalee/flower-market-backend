package vtc.xueqing.flower.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-10-06
 * @desc : 收货信息表
 */
@ApiModel("收货信息")
@TableName("receiver_address")
@Data
@Accessors(chain = true)
public class ReceiverAddress implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("地址ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户ID（逻辑关联sys_user表）")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty("收货人姓名")
    @NotBlank(message = "收货人姓名不能为空")
    private String receiverName;

    @ApiModelProperty("收货人电话")
    @NotBlank(message = "收货人电话不能为空")
    private String receiverPhone;

    @ApiModelProperty("省份")
    @NotBlank(message = "省份不能为空")
    private String province;

    @ApiModelProperty("城市")
    @NotBlank(message = "城市不能为空")
    private String city;

    @ApiModelProperty("区/县")
    private String district;

    @ApiModelProperty("详细地址")
    @NotBlank(message = "详细地址不能为空")
    private String detailAddress;

    @ApiModelProperty("邮政编码")
    private String postalCode;

    @ApiModelProperty("是否默认地址（0-否，1-是）")
    @NotNull(message = "是否默认地址不能为空")
    private Integer isDefault;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
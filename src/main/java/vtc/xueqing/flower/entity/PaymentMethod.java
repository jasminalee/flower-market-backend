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
 * @desc : 支付方式表
 */
@ApiModel("支付方式信息")
@TableName("payment_method")
@Data
@Accessors(chain = true)
public class PaymentMethod implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("支付方式ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户ID（逻辑关联sys_user表）")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty("支付方式名称")
    @NotBlank(message = "支付方式名称不能为空")
    private String methodName;

    @ApiModelProperty("支付方式编码")
    @NotBlank(message = "支付方式编码不能为空")
    private String methodCode;

    @ApiModelProperty("支付方式描述")
    private String description;

    @ApiModelProperty("账户号码/卡号")
    private String accountNumber;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("银行名称")
    private String bankName;

    @ApiModelProperty("开户行")
    private String bankBranch;

    @ApiModelProperty("状态（0-禁用，1-启用）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("排序号")
    private Integer sort;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
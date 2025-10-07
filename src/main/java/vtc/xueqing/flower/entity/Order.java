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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : Xueqing
 * @date : 2025-10-02
 * @desc : 订单表
 */
@ApiModel("订单信息")
@TableName("`order`")
@Data
@Accessors(chain = true)
public class Order implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单编号")
    @NotBlank(message = "订单编号不能为空")
    private String orderNo;

    @ApiModelProperty("用户ID（逻辑关联sys_user表）")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty("订单总金额")
    @NotNull(message = "订单总金额不能为空")
    private BigDecimal totalAmount;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("实际支付金额")
    @NotNull(message = "实际支付金额不能为空")
    private BigDecimal payAmount;

    @ApiModelProperty("订单状态（1-待付款，2-已付款，3-已发货，4-已完成，5-已取消，6-退款中，7-已退款）")
    @NotNull(message = "订单状态不能为空")
    private Integer status;

    @ApiModelProperty("收货人姓名")
    private String receiverName;

    @ApiModelProperty("收货人电话")
    private String receiverPhone;

    @ApiModelProperty("收货地址")
    private String receiverAddress;

    @ApiModelProperty("支付方式（1-支付宝，2-微信，3-银行卡）")
    private Integer paymentMethod;

    @ApiModelProperty("支付时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty("发货时间")
    private LocalDateTime deliveryTime;

    @ApiModelProperty("收货时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty("订单备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
package vtc.xueqing.flower.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import vtc.xueqing.flower.entity.Order;
import vtc.xueqing.flower.entity.OrderItem;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Xueqing
 * @date : 2025-10-06
 * @desc : 订单详情VO
 */
@ApiModel("订单详情信息")
@Data
@Accessors(chain = true)
public class OrderDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单信息")
    private Order order;

    @ApiModelProperty("订单项列表")
    private List<OrderItem> orderItems;
}
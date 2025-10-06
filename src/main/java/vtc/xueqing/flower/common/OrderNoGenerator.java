package vtc.xueqing.flower.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 订单号生成器
 */
public class OrderNoGenerator {
    
    /**
     * 生成订单号
     * 格式：ORD + 年月日时分秒 + 4位随机数
     * @return 订单号
     */
    public static String generateOrderNo() {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int random = (int) (Math.random() * 9000 + 1000); // 4位随机数
        return "ORD" + dateTime + random;
    }
}
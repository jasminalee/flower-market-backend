package vtc.xueqing.flower;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("vtc.xueqing.flower.mapper")
public class FlowerMarket {

    public static void main(String[] args) {
        SpringApplication.run(FlowerMarket.class, args);
    }

}
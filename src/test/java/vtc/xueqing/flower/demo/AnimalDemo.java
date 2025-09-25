package vtc.xueqing.flower.demo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import java.util.ArrayList;
import java.util.List;

public class AnimalDemo {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Animal.class)
                .build();

        // 核心改动：使用完整的 activateDefaultTyping 方法，并传递 ptv
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        List<Animal> animals = new ArrayList<>();
        animals.add(new Cat("Kitty", 9));
        animals.add(new Dog("Buddy", "Golden Retriever"));

        // 序列化集合
        String json = mapper.writeValueAsString(animals);
        System.out.println("序列化结果：");
        System.out.println(json);

        // 序列化单个对象
        Animal a = new Cat("Kitty", 9);
        String json1 = mapper.writeValueAsString(a);
        System.out.println("序列化结果1：");
        System.out.println(json1);



        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Cat("Kitty", 9));
        animalList.add(new Dog("Buddy", "Golden Retriever"));

        Zoo zoo = new Zoo(animalList);

        // 序列化
        String json2 = mapper.writeValueAsString(zoo);
        System.out.println("序列化结果2：");
        System.out.println(json2);
    }
}
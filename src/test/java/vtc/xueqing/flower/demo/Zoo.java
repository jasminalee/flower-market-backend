package vtc.xueqing.flower.demo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import java.util.List;

public class Zoo {
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "$type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Cat.class, name = "Cat"),
            @JsonSubTypes.Type(value = Dog.class, name = "Dog")
    })
    public List<Animal> animals;

    public Zoo() {}
    public Zoo(List<Animal> animals) {
        this.animals = animals;
    }
}

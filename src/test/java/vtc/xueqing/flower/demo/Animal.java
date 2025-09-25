package vtc.xueqing.flower.demo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "$type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cat.class, name = "Cat"),
        @JsonSubTypes.Type(value = Dog.class, name = "Dog")
})
public abstract class Animal {
    private String name;

    public Animal() {}
    public Animal(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public abstract String speak();
}

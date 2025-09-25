package vtc.xueqing.flower.demo;


import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Dog")
public class Dog extends Animal {
    private String breed;

    public Dog() {}

    public Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String speak() {
        return getName() + " says: Woof!";
    }
}

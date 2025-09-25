package vtc.xueqing.flower.demo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Cat")
public class Cat extends Animal {
    private int livesLeft;

    public Cat() {}

    public Cat(String name, int livesLeft) {
        super(name);
        this.livesLeft = livesLeft;
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    @Override
    public String speak() {
        return getName() + " says: Meow!";
    }
}
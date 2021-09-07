package design_pattern.decorator_pattern.decorator;

import design_pattern.decorator_pattern.drink.Drink;

public class Milk extends Decorator {
    public Milk(Drink obj) {
        super(obj);
        setDes(" 牛奶 ");
        setPrice(2.0f);
    }

}

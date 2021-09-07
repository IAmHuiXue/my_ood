package design_pattern.decorator_pattern.decorator;

import design_pattern.decorator_pattern.drink.Drink;

public class Soy extends Decorator {
    public Soy(Drink obj) {
        super(obj);
        setDes(" 豆浆  ");
        setPrice(1.5f);
    }
}

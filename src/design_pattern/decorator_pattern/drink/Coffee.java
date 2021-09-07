package design_pattern.decorator_pattern.drink;

public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }

}

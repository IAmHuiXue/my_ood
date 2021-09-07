package vending_machine;

public class Item {
    private final ItemType type;
    private final String barCode;

    public Item(ItemType type, String barCode) {
        this.type = type;
        this.barCode = barCode;
    }

    public ItemType getType() {
        return type;
    }

    String getBarCode() {
        return barCode;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}

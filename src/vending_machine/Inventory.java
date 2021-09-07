package vending_machine;

import java.util.Random;

public class Inventory {
    private final ItemType type;
    private final int capacity;
    private int amount;
    private int price; // in cents
    private Item[] items;

    public Inventory(ItemType type, int capacity, int price) {
        this.type = type;
        this.capacity = capacity;
        this.items = new Item[capacity];
        this.price = price;

        fillItem();
    }

    public Item dispense() {
        if (amount > 0) {
            Item item = items[amount - 1];
            items[amount - 1] = null;
            amount--;
            return item;
        }
        return null; // can also throw exception here
    }

    public boolean canVend() {
        return this.amount > 0;
    }

    public boolean isEnoughFund(int money) {
        return money >= this.price;
    }

    public int getPrice() {
        return this.price;
    }

    private void fillItem() {
        for (int i = amount; i < capacity; i++) {
            items[i] = new Item(type, generateBarCode());
        }
        this.amount = capacity;
    }

    private String generateBarCode() {
        byte[] bytes = new byte[8];
        new Random().nextBytes(bytes);
        return new String(bytes);
    }
}

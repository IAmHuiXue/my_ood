package vending_machine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private final static int INVENTORY_DEFAULT_CAPACITY = 20;
    Map<ItemType, Inventory> listing;
    Map<PaymentType, PaymentService> paymentMethod;
    Inventory currentPick;
    PaymentService currentMethod;
    boolean paid;

    public VendingMachine(Map<ItemType, Integer> items) {
        paymentMethod = new HashMap<>(){{
            put(PaymentType.CASH, new CashPayment());
        }};
        listing = new HashMap<>();
        for (Map.Entry<ItemType, Integer> entry : items.entrySet()) {
            listing.put(entry.getKey(), new Inventory(entry.getKey(), INVENTORY_DEFAULT_CAPACITY, entry.getValue()));
        }
    }

    public Map<ItemType, Integer> getList() {
        Map<ItemType, Integer> map = new HashMap<>();
        for (Map.Entry<ItemType, Inventory> entry : listing.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getPrice());
        }
        return map;
    }

    public void pickItem(ItemType type) {
        Inventory inventory = listing.get(type);
        if (inventory == null) {
            throw new RuntimeException("No such item");
        }
        if (!inventory.canVend()) {
            throw new RuntimeException("No item left");
        }
        this.currentPick = inventory;
    }

    public void cashPay(int amount) { // in cents
        PaymentService cash = paymentMethod.get(PaymentType.CASH);
        if (currentMethod != null && currentMethod != cash) {
            throw new RuntimeException("Payment method conflict");
        }
        this.currentMethod = cash;
        if (this.currentPick.isEnoughFund(amount)) {
            this.currentMethod.pay(amount, this.currentPick.getPrice()); // no exception
            this.paid = true;
        }
    }

    public void cardPay() {
        PaymentService card = paymentMethod.get(PaymentType.CARD);
        if (currentMethod != null && currentMethod != card) {
            throw new RuntimeException("Payment method conflict");
        }
        this.currentMethod = card;
        this.currentMethod.pay(this.currentPick.getPrice(), this.currentPick.getPrice()); // no exception
        this.paid = true;
    }

    public int refund() {
        return currentMethod.refund();
    }

    public boolean hasChange() {
        return currentMethod.hasChange();
    }

    public int giveChange() {
        if (hasChange()) {
            return currentMethod.returnChange();
        }
        return 0;
    }

    public Item dispense() {
        if (!this.paid) {
            return  null;
        }
        Item item = currentPick.dispense();
        reset();
        return item;
    }

    private void reset() {
        currentMethod = null;
        currentPick = null;
        paid = false;
    }
}

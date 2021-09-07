package vending_machine;

public interface PaymentService {
    boolean pay(int amount, int price);
    int refund();
    int returnChange();
    boolean hasChange();
}

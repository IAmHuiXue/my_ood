package vending_machine;

public class CashPayment implements PaymentService {
    int currentFund;
    int toPay;
    int total;

    @Override
    public boolean pay(int amount, int price) {
        this.currentFund = amount;
        this.toPay = price;
        return true;
    }

    @Override
    public int refund() {
        int refund = this.currentFund;
        reset();
        return refund;
    }

    @Override
    public int returnChange() {
        int change = this.currentFund - this.toPay;
        this.total += this.toPay;
        reset();
        return change;
    }

    public boolean hasChange() {
        return this.currentFund > this.toPay;
    }

    private void reset() {
        this.currentFund = 0;
        this.toPay = 0;
    }
}

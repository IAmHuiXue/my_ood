package elevator;

public class OnAboard extends Request {
    public OnAboard(int destination, int number) {
        super(destination, number);
    }

    @Override
    public int fulfill() {
        return this.number;
    }
}

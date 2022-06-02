package elevator;

public class GetOff extends Request {
    public GetOff(int destination, int number) {
        super(destination, number);
    }

    @Override
    public int fulfill() {
        return -this.number;
    }
}

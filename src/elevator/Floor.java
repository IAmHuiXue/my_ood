package elevator;

public class Floor implements Comparable<Floor> {
    int number;

    public Floor(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Floor o) {
        return Integer.compare(number, o.number);
    }
}

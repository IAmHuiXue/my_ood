package elevator;

public abstract class Request {
    int destination;
    int number;

    public Request(int destination, int number) {
        this.destination = destination;
        this.number = number;
    }

    abstract public int fulfill();
}

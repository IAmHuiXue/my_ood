package elevator;

public interface Elevator {
    void takeCommand(Request request);
    int stop();
    void move();
    int getCurrentFloor();
    boolean isFull();
    boolean isStopped();
}

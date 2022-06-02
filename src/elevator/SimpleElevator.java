package elevator;

import java.util.*;

public class SimpleElevator implements Elevator {
    Map<Integer, Floor> map;
    Floor current;
    Direction currentDirection;
    private final int capacity;
    private int numberOfPpl;
    Map<Floor, List<Request>> counter; // records all requests for a given floor number
    PriorityQueue<Floor> goingUp;
    PriorityQueue<Floor> goingDown;
    private boolean forceStopped;

    public SimpleElevator(Map<Integer, Floor> map, int capacity) {
        this.map = map;
        this.current = map.get(0);
        this.currentDirection = Direction.UP;
        this.counter = new HashMap<>();
        for (Floor floor : this.map.values()) {
            counter.put(floor, new ArrayList<>());
        }
        this.capacity = capacity;
        this.goingUp = new PriorityQueue<>();
        this.goingDown = new PriorityQueue<>(Collections.reverseOrder());
        this.forceStopped = false;
    }

    public boolean hasRequests() {
        if (forceStopped) {
            return false;
        }
        return goingUp.size() > 0 || goingDown.size() > 0;
    }

    @Override
    public void takeCommand(Request request) {
        if (!map.containsKey(request.destination)) {
            throw new IllegalArgumentException("Invalid floor number");
        }
        Floor goTo = map.get(request.destination);

        if (request.destination == current.number) {
            return;
        }

        if (counter.get(goTo).size() > 0) { // ?
            counter.get(goTo).add(request);
            return;
        }

        if (request.destination > current.number) {
            goingUp.offer(goTo);
        } else {
            goingDown.offer(goTo);
        }
        counter.get(goTo).add(request);
    }

    @Override
    public int stop() {
        return 0;
    }

    @Override
    public void move() {
        if (this.forceStopped) {
            return;
        }
        if (currentDirection == Direction.UP && goingUp.size() == 0) {
            currentDirection = Direction.DOWN;
        } else if (currentDirection == Direction.DOWN && goingDown.size() == 0) {
            currentDirection = Direction.UP;
        } else if (goingDown.size() == 0 && goingUp.size() == 0) {
            currentDirection = current.number <= (map.size() + 1) / 2 ? Direction.UP : Direction.DOWN;
            return;
        }
        Floor toStop = null;
        if (currentDirection == Direction.UP) {
            toStop = goingUp.poll();
        } else {
            toStop = goingDown.poll();
        }
        this.current = toStop;
    }

    @Override
    public int getCurrentFloor() {
        return current.number;
    }

    @Override
    public boolean isFull() {
        return this.numberOfPpl == this.capacity;
    }

    @Override
    public boolean isStopped() {
        return this.forceStopped;
    }
}

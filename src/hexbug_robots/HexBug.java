package hexbug_robots;

public class HexBug {
    private int moveDistance;

    public int getMoveDistance() {
        return moveDistance;
    }

    public HexBug(int moveDistance) {
        if (moveDistance <= 0) {
            throw new IllegalArgumentException("Invalid distance set");
        }
        this.moveDistance = moveDistance;
    }


}

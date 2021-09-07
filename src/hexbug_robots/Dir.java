package hexbug_robots;

public enum Dir {
    N(-1, 0),
    W(0, -1),
    E(0, 1),
    S(1, 0);

    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Dir(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

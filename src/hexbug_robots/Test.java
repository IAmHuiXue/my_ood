package hexbug_robots;

public class Test {
    public static void main(String[] args) {
        HexBug h = new HexBug(3);
        Room room = new Room(4, 4);
        room.roomState();
        room.placeHexBug(h, 2, 1, Dir.E);
        room.roomState();
        room.pushHexbugButton();
        room.roomState();
    }
}

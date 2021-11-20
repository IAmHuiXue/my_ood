package library;

public class FineRequiredException extends Exception {
    public FineRequiredException() {
        super("There is an outstanding fine.");
    }
}

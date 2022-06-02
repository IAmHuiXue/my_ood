package unix_find;

public class MinSizeFilter extends Filter {
    int minSize;

    public MinSizeFilter(int minSize) {
        this.minSize = minSize;
    }

    public int getMinSize() {
        return minSize;
    }

    @Override
    boolean apply(File file) {
        return file.getSize() > minSize;
    }
}

package unix_find;

import java.util.List;

public class AndFilterCombo extends Filter {
    List<Filter> filters;

    public AndFilterCombo(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    boolean apply(File file) {
        for (Filter f : filters) {
            if (!f.apply(file)) {
                return false;
            }
        }
        return true;
    }
}

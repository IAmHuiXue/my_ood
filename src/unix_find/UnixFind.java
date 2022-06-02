package unix_find;

import java.util.ArrayList;
import java.util.List;

public class UnixFind {
    public List<File> findWithFilters(Dir directory, AndFilterCombo filters) {
        List<File> res = new ArrayList<>();
        dfs(directory, filters, res);
        return res;
    }

    private void dfs(Dir dir, AndFilterCombo filters, List<File> res) {
        for (Entry e : dir.getContents()) {
            if (e instanceof Dir) {
                dfs((Dir) e, filters, res);
            }
            if (e instanceof File) {
                File file = (File) e;
                if (filters.apply(file)) {
                    res.add(file);
                }
            }
        }
    }
}

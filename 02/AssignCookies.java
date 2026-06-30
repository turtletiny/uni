
import java.util.HashMap;

class Solution {

    public int findContentChildren(int[] g, int[] s) {
        int res = 0;
        HashMap<Integer, Integer> sCount = new HashMap<>();
        for (int i : s) {
            sCount.put(i, sCount.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < g.length; i++) {
            if (sCount.containsKey(g[i]) && sCount.get(g[i]) > 0) {
                res++;
                sCount.put(g[i], sCount.get(g[i] - 1));
            }
        }

        return res;
    }
}

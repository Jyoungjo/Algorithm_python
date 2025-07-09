import java.util.*;

class Solution {
    int len;
    int[] w;
    int answer = 987654321;
    
    public int solution(int n, int[] weak, int[] dist) {
        len = weak.length;
        w = new int[len * 2];
        for (int i = 0; i < len; i++) {
            w[i] = weak[i];
            w[i + len] = weak[i] + n;
        }
        permutations(dist, 0, new ArrayList<>());
        return answer == 987654321 ? -1 : answer;
    }
    
    private void permutations(int[] dist, int mask, List<Integer> list) {
        if (list.size() >= answer) return;
        
        for (int i = 0; i < dist.length; i++) {
            if ((mask & (1 << i)) != 0) continue;
            List<Integer> tmp = new ArrayList<>(list);
            tmp.add(dist[i]);
            exe(tmp);
            permutations(dist, mask | (1 << i), tmp);
        }
    }
    
    private void exe(List<Integer> list) {
        for (int i = 0; i < len; i++) {
            int idx = i;
            for (int n : list) {
                int pos = w[idx] + n;
                while (idx < w.length && pos >= w[idx]) idx++;
            }
            
            if (idx - i >= len) {
                answer = Math.min(answer, list.size());
                return;
            }
        }
    }
}
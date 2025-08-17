import java.util.*;

class Solution {
    int[] WEAK;
    int answer = 987654321;
    
    public int solution(int n, int[] weak, int[] dist) {
        int len = weak.length;
        WEAK = new int[len * 2];
        for (int i = 0; i < len; i++) {
            WEAK[i] = weak[i];
            WEAK[i + len] = weak[i] + n;
        }
        
        permutations(new ArrayList<>(), dist, 0);
        
        return answer == 987654321 ? -1 : answer;
    }
    
    private void permutations(List<Integer> list, int[] dist, int masked) {
        if (answer <= list.size()) return;
        
        for (int i = 0; i < dist.length; i++) {
            if ((masked & (1 << i)) != 0) continue;
            List<Integer> tmp = new ArrayList<>(list);
            tmp.add(dist[i]);
            check(tmp);
            permutations(tmp, dist, masked | (1 << i));
        }
    }
    
    private void check(List<Integer> list) {
        for (int i = 0; i < WEAK.length / 2; i++) {
            int idx = i;
            
            for (int dist : list) {
                int pos = WEAK[idx] + dist;
                while (idx < WEAK.length && pos >= WEAK[idx]) idx++;
            }
            
            if (idx - i >= WEAK.length / 2) {
                answer = Math.min(answer, list.size());
                return;
            }
        }
    }
}
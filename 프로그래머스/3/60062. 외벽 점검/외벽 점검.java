import java.util.*;

class Solution {
    int[] WEAK;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[] weak, int[] dist) {
        WEAK = new int[weak.length * 2];
        for (int i = 0; i < weak.length; i++) {
            WEAK[i] = weak[i];
            WEAK[i + weak.length] = weak[i] + n;
        }
        
        permutations(dist, new ArrayList<>(), 0);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    private void permutations(int[] dist, List<Integer> picked, int mask) {
        if (answer <= picked.size()) return;
        
        for (int i = 0; i < dist.length; i++) {
            if ((mask & (1 << i)) != 0) continue;
            
            List<Integer> tmp = new ArrayList<>(picked);
            tmp.add(dist[i]);
            check(tmp);
            permutations(dist, tmp, mask | (1 << i));
        }
    }
    
    private void check(List<Integer> picked) {
        for (int i = 0; i < WEAK.length / 2; i++) {
            int idx = i;
            
            for (int dist : picked) {
                int d = WEAK[idx] + dist;
                while (idx < WEAK.length && d >= WEAK[idx]) idx++;
            }
            
            if (idx - i >= WEAK.length / 2) {
                answer = Math.min(answer, picked.size());
                return;
            }
        }
    }
}
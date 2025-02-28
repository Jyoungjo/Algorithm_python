import java.util.*;

class Solution {
    int[] golds, silvers, weights, times;
    int size;
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long l = 0, r = 1000000000000000L;
        golds = g;
        silvers = s;
        weights = w;
        times = t;
        size = g.length;
        
        while (l <= r) {
            long mid = (l + r) / 2;
            if (isPossible(mid, a, b)) r = mid - 1;
            else l = mid + 1;
        }
        
        return r + 1;
    }
    
    private boolean isPossible(long time, int a, int b) {
        long tot = 0, totG = 0, totS = 0;
        for (int i = 0; i < size; i++) {
            long cnt = time / (2L * times[i]);
            if (time % (2L * times[i]) >= times[i]) cnt++;
            
            long weight = Math.min(golds[i] + silvers[i], weights[i] * cnt);
            tot += weight;
            totG += Math.min(weight, golds[i]);
            totS += Math.min(weight, silvers[i]);
        }
        return tot >= a + b && totG >= a && totS >= b;
    }
}
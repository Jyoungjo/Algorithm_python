import java.util.*;

class Solution {
    int[] weak;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[] weak, int[] dist) {
        this.weak = new int[weak.length * 2];
        for (int i = 0; i < weak.length; i++) {
            this.weak[i] = weak[i];
            this.weak[i + weak.length] = weak[i] + n;
        }
        
        permutation(new ArrayList<>(), dist, 0);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    private void permutation(List<Integer> list, int[] dist, int maskNum) {
        if (list.size() >= answer) return;
        
        for (int i = 0; i < dist.length; i++) {
            if ((maskNum & (1 << i)) != 0) continue;
            List<Integer> tmp = new ArrayList<>(list);
            tmp.add(dist[i]);
            checkIfCanOver(tmp);
            permutation(tmp, dist, maskNum | (1 << i));
        }
    }
    
    private void checkIfCanOver(List<Integer> list) {
        for (int i = 0; i < this.weak.length / 2; i++) {
            int idx = i;
            for (int distance : list) {
                int pos = this.weak[idx] + distance;
                while (idx < this.weak.length && pos >= this.weak[idx]) idx++;
            }
            
            if (idx - i >= this.weak.length / 2) {
                answer = Math.min(answer, list.size());
                return;
            }
        }
    }
}
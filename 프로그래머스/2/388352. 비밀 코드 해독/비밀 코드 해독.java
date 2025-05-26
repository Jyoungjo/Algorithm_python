import java.util.*;

class Solution {
    List<Set<Integer>> result = new ArrayList<>();
    
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        combinations(n, 5, new HashSet<>(), 1);
        for (Set<Integer> set : result) {
            boolean isCorrect = true;
            
            for (int i = 0; i < q.length; i++) {
                int[] question = q[i];
                int correct = ans[i];
                
                int cnt = 0;
                for (int j = 0; j < 5; j++) {
                    if (set.contains(question[j])) cnt++;
                }
                
                if (cnt != correct) {
                    isCorrect = false;
                    break;
                }
            }
            
            if (isCorrect) answer++;
        }
        
        return answer;
    }
    
    private void combinations(int n, int r, Set<Integer> tmp, int now) {
        if (tmp.size() == r) {
            result.add(tmp);
            return;
        }
        
        for (int i = now; i <= n; i++) {
            if (tmp.contains(i)) continue;
            
            Set<Integer> nSet = new HashSet<>(tmp);
            nSet.add(i);
            combinations(n, r, nSet, i);
        }
    }
}
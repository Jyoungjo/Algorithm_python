import java.util.*;

class Solution {
    Queue<Integer> q = new LinkedList<>();
    
    public int[] solution(int[] prices) {
        List<Integer> answer = new ArrayList<>();
        for (int p : prices) q.add(p);
        
        for (int i = 0; i < prices.length; i++) {
            int now = q.poll();
            
            if (q.isEmpty()) {
                answer.add(0);
                continue;
            }
            
            int tmp = 0;
            for (int nxt : q) {
                if (now > nxt) {
                    tmp++;
                    break;
                }
                tmp++;
            }
            
            answer.add(tmp);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
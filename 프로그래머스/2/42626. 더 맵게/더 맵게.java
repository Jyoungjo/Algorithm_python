import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        Queue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        for (int s : scoville) pq.add(s);
        
        int answer = 0;
        while (pq.peek() < K) {
            if (pq.size() == 1) break;
            int f = pq.poll(), s = pq.poll();
            pq.add(f + (s * 2));
            answer++;
        }
        
        return pq.peek() < K ? -1 : answer;
    }
}
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        /*
            야근 피로도 = 남은 일 작업량 제곱을 더한 값
            Demi의 1시간 동안 작업량 = 1
            return = 야근 피로도 최솟값
        */
        long answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            pq.add((long) work);
        }
        
        while (n != 0 && !pq.isEmpty()) {
            long work = pq.poll();
            if (work != 1) pq.add(--work);
            n--;
        }
        
        while (!pq.isEmpty()) {
            answer += (long) Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}
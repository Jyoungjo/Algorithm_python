import java.util.PriorityQueue;


class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int s : scoville) {
            pq.add(s);
        }

        while (pq.peek() < K) {
            if (pq.size() == 1) {
                return -1;
            }
            int result = pq.poll() + (pq.poll() * 2);
            pq.add(result);
            answer++;
        }

        return answer;
    }
}
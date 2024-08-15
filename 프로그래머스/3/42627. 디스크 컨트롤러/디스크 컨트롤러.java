import java.util.*;


class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int start = -1, now = 0, i = 0;
        while (i < jobs.length) {
            for (int[] job : jobs) {
                if (start < job[0] && job[0] <= now) {
                    pq.add(job);
                }
            }

            if (!pq.isEmpty()) {
                int[] current = pq.poll();
                start = now;
                now += current[1];
                answer += now - current[0];
                i++;
            } else {
                now++;
            }
        }

        return answer / i;
    }
}
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            int[] tmp = new int[2];
            tmp[0] = i;
            tmp[1] = priorities[i];
            q.add(tmp);
        }

        while (!q.isEmpty()) {
            boolean flag = true;
            int[] target = q.poll();
            for (int[] e : q) {
                if (target[1] < e[1]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                q.add(target);
            } else {
                if (target[0] == location) {
                    return answer;
                } else {
                    answer++;
                }
            }
        }

        return answer;
    }
}
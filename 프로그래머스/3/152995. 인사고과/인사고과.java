import java.util.*;


class Solution {
    public int solution(int[][] scores) {
        int answer = 1, w1 = scores[0][0], w2 = scores[0][1];
        
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        });
        
        int s2 = scores[0][1];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i][1] < s2) {
                if (scores[i][0] == w1 && scores[i][1] == w2) return -1;
                scores[i][0] = -1;
                scores[i][1] = -1;
            }
            else s2 = scores[i][1];
        }
        
        Arrays.sort(scores, (o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));
        
        for (int i = 0; i < scores.length; i++) {
            if (scores[i][0] + scores[i][1] == w1 + w2) return answer;
            answer++;
        }
        
        return answer;
    }
}
import java.util.*;


class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) return o2[0] - o1[0];
            return o1[col - 1] - o2[col - 1];
        });
        
        int eLen = data[0].length;
        
        for (int i = row_begin; i <= row_end; i++) {
            int tmp = 0;
            for (int j = 0; j < eLen; j++) {
                tmp += data[i - 1][j] % i;
            }
            answer = (answer ^ tmp);
        }
        
        return answer;
    }
}
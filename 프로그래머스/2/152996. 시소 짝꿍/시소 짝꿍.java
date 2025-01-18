import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        long[] cnt = new long[1001];
        long[] multiCnt = new long[4001];
        
        for (int weight : weights) {
            long cntW1 = cnt[weight];
            int w2 = weight * 2, w3 = weight * 3, w4 = weight * 4;
            
            if (cntW1 > 0) {
                answer += cntW1;
                answer += multiCnt[w2] - cntW1;
                answer += multiCnt[w3] - cntW1;
                answer += multiCnt[w4] - cntW1;
            } else {
                answer += multiCnt[w2];
                answer += multiCnt[w3];
                answer += multiCnt[w4];
            }
            
            cnt[weight]++;
            multiCnt[w2]++;
            multiCnt[w3]++;
            multiCnt[w4]++;
        }
        
        return answer;
    }
}
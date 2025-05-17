/*
    최적화 -> 결정 문제 변환
    이분탐색으로 특정 시간 구했을 때, 이 시간안에 금과 은을 운반할 수 있는가?
*/

import java.util.*;

class Solution {
    int[] GOLD, SILVER, WEIGHT, TIME;
    int size;
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long l = 0, r = 1000000000000000L, answer = 0;
        GOLD = g; SILVER = s; WEIGHT = w; TIME = t;
        size = t.length;
        
        while (l <= r) {
            long mid = (l + r) / 2;
            
            if (canMove(mid, a, b)) {
                answer = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        
        return answer;
    }
    
    private boolean canMove(long time, int a, int b) {
        long tot = 0, totG = 0, totS = 0;
        for (int i = 0; i < size; i++) {
            long moveCnt = time / (TIME[i] * 2L);
            if (time % (TIME[i] * 2L) >= TIME[i]) moveCnt++;
            
            long max = WEIGHT[i] * moveCnt;
            tot += Math.min(max, GOLD[i] + SILVER[i]);
            totG += Math.min(max, GOLD[i]);
            totS += Math.min(max, SILVER[i]);
        }
        
        return tot >= a + b && totG >= a && totS >= b;
    }
}
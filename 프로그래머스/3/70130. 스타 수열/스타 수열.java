import java.util.*;

class Solution {
    public int solution(int[] a) {
        int len = a.length;
        int[] cnts = new int[len];
        for (int i = 0; i < len; i++) cnts[a[i]]++;
        
        int answer = -1;
        for (int i = 0; i < len; i++) {
            if (cnts[i] <= answer) continue;
            
            int cnt = 0;
            for (int j = 0; j < len - 1; j++) {
                if (a[j] != a[j + 1] && (a[j] == i || a[j + 1] == i)) {
                    j++;
                    cnt++;
                }
            }
            
            answer = Math.max(answer, cnt);
        }
        
        return answer * 2;
    }
}
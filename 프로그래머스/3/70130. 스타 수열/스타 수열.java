import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = -1;
        int[] cntArr = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            cntArr[a[i]]++;
        }
        
        for (int i = 0; i < cntArr.length; i++) {
            if (cntArr[i] <= answer) continue;
            
            int cnt = 0;
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] != a[j + 1] && (a[j] == i || a[j + 1] == i)) {
                    cnt++;
                    j++;
                }
            }
            answer = Math.max(answer, cnt);
        }
        
        return answer * 2;
    }
}
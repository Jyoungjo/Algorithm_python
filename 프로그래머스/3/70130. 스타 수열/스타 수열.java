import java.util.*;

class Solution {
    public int solution(int[] a) {
        int len = a.length;
        if (len == 1) return 0;
        
        int[] cnts = new int[len];
        for (int aa : a) cnts[aa]++;
        
        int answer = 0;
        for (int l = 0; l < cnts.length; l++) {
            if (cnts[l] <= answer) continue;
            
            int tmp = 0;
            for (int i = 0; i < len - 1; i++) {
                if (a[i] == l || a[i + 1] == l) {
                    if (a[i] != a[i + 1]) {
                        tmp++;
                        i++;
                    }
                }
            }
            
            answer = Math.max(answer, tmp);
        }
        
        return answer * 2;
    }
}
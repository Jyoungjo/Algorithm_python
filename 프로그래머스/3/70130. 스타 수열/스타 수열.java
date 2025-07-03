import java.util.*;

class Solution {
    public int solution(int[] a) {
        /*
            1. 길이 2 이상의 짝수
            2. 두 개씩 짝 지었을 때, 교집합 원소 개수 1 이상
            3. 두 개 비교했을 때, 두 원소가 달라야 함.
        */
        
        int[] cnt = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) cnt[a[i]]++;
        
        int answer = -1;
        for (int i = 0; i < cnt.length; i++) {
            if (answer >= cnt[i]) continue;
            
            int res = 0;
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] != a[j + 1] && (a[j] == i || a[j + 1] == i)) {
                    res++;
                    j++;
                }
            }
            
            answer = Math.max(answer, res);
        }
        
        return answer * 2;
    }
}
import java.util.*;

class Solution {
    int[] cnts;
    
    public int[] solution(int e, int[] starts) {
        // 약수 개수만큼 나오는 것 같음
        List<Integer> answer = new ArrayList<>();
        cnts = new int[e + 1];
        calCnts(e);
        
        int[] maxArr = new int[e + 1];
        int max = -1, idx = -1;
        for (int i = e; i >= 1; i--) {
            if (cnts[i] >= max) {
                max = cnts[i];
                idx = i;
            }
            maxArr[i] = idx;
        }
        
        for (int s : starts) {
            answer.add(maxArr[s]);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private void calCnts(int e) {
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                cnts[j]++;
            }
        }
    }
}
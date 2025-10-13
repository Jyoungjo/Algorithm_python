import java.util.*;

class Solution {
    final String PLUS = "+", MINUS = "-";
    char[] oper;
    int[] nums;
    int[][] max, min;
    int len;
    
    public int solution(String arr[]) {
        init(arr);
        return cal();
    }
    
    private int cal() {
        for (int d = 0; d < len; d++) {
            for (int s = 0; s < len - d; s++) {
                int e = s + d;
                
                if (s == e) {
                    max[s][e] = nums[s];
                    min[s][e] = nums[s];
                    continue;
                }
                
                for (int m = s; m < e; m++) cal(s, e, m);
            }
        }
        
        return max[0][len - 1];
    }
    
    private void cal(int s, int e, int m) {
        if (oper[m] == '+') {
            max[s][e] = Math.max(max[s][e], max[s][m] + max[m + 1][e]);
            min[s][e] = Math.min(min[s][e], min[s][m] + min[m + 1][e]);
            return;
        }
        
        max[s][e] = Math.max(max[s][e], max[s][m] - min[m + 1][e]);
        min[s][e] = Math.min(min[s][e], min[s][m] - max[m + 1][e]);
    }
    
    private void init(String[] arr) {
        len = arr.length / 2 + 1;
        oper = new char[len - 1];
        nums = new int[len];
        
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 1) oper[i / 2] = arr[i].charAt(0);
            else nums[i / 2] = Integer.parseInt(arr[i]);
        }
        
        max = new int[len][len];
        min = new int[len][len];
        
        for (int i = 0; i < len; i++) {
            Arrays.fill(max[i], Integer.MIN_VALUE);
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }
    }
}
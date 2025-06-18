import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        
        for (int[] d : data) {
            xList.add(d[0]);
            yList.add(d[1]);
        }
        
        List<Integer> uniqueXList = new ArrayList<>(new HashSet<>(xList));
        List<Integer> uniqueYList = new ArrayList<>(new HashSet<>(yList));
        
        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);
        
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            data[i][0] = uniqueXList.indexOf(xList.get(i));
            data[i][1] = uniqueYList.indexOf(yList.get(i));
            
            dp[data[i][1]][data[i][0]] = 1;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= 1) dp[i][j] += dp[i - 1][j];
                if (j >= 1) dp[i][j] += dp[i][j - 1];
                if (i >= 1 && j >= 1) dp[i][j] -= dp[i - 1][j - 1];
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int r1 = Math.min(data[i][1], data[j][1]);
                int c1 = Math.min(data[i][0], data[j][0]);
                int r2 = Math.max(data[i][1], data[j][1]);
                int c2 = Math.max(data[i][0], data[j][0]);
                
                if (r1 == r2 || c1 == c2) continue;
                
                int blank = dp[r2 - 1][c2 - 1] - dp[r2 - 1][c1] - dp[r1][c2 - 1] + dp[r1][c1];
                if (blank == 0) answer++;
            }
        }
        
        return answer;
    }
}
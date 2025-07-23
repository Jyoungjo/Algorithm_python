import java.util.*;

class Solution {
    List<Integer> xList, yList, uXList, uYList;
    
    public int solution(int n, int[][] data) {
        makeList(n, data);
        
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            data[i][0] = uXList.indexOf(xList.get(i));
            data[i][1] = uYList.indexOf(yList.get(i));
            
            dp[data[i][1]][data[i][0]] = 1;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) dp[i][j] += dp[i - 1][j];
                if (j - 1 >= 0) dp[i][j] += dp[i][j - 1];
                if (i - 1 >= 0 && j - 1 >= 0) dp[i][j] -= dp[i - 1][j - 1];
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
                
                int cnt = dp[r2 - 1][c2 - 1] - dp[r2 - 1][c1] - dp[r1][c2 - 1] + dp[r1][c1];
                if (cnt == 0) answer++;
            }
        }
        
        return answer;
    }
    
    private void makeList(int n, int[][] data) {
        xList = new ArrayList<>();
        yList = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            xList.add(data[i][0]);
            yList.add(data[i][1]);
        }
        
        uXList = new ArrayList<>(new HashSet<>(xList));
        uYList = new ArrayList<>(new HashSet<>(yList));
        
        Collections.sort(uXList);
        Collections.sort(uYList);
    }
}
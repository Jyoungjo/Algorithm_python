import java.util.*;

class CoordinateCompressor {
    
    CoordinateCompressor() {}
    
    public void compress(List<Integer> list, int n, int[][] data, int idx) {
        List<Integer> uniqueList = new ArrayList<>(new HashSet<>(list));
        Collections.sort(uniqueList);
        for (int i = 0; i < n; i++) data[i][idx] = uniqueList.indexOf(list.get(i));
    }
}

class Solution {
    public int solution(int n, int[][] data) {
        List<Integer> xList = getList(data, 0);
        List<Integer> yList = getList(data, 1);
        
        CoordinateCompressor compressor = new CoordinateCompressor();
        compressor.compress(xList, n, data, 0);
        compressor.compress(yList, n, data, 1);
        
        int[][] dp = getDp(n, data);
        
        Arrays.sort(data, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        
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
    
    private List<Integer> getList(int[][] data, int idx) {
        List<Integer> result = new ArrayList<>();
        for (int[] d : data) result.add(d[idx]);
        return result;
    }
    
    private int[][] getDp(int n, int[][] data) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[data[i][1]][data[i][0]] = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) dp[i][j] += dp[i - 1][j];
                if (j > 0) dp[i][j] += dp[i][j - 1];
                if (i > 0 && j > 0) dp[i][j] -= dp[i - 1][j - 1];
            }
        }
        
        return dp;
    }
}
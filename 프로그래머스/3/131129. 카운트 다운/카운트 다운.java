import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2]; // score, shoot/single + bull
        for (int i = 0; i <= target; i++) {
            dp[i] = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        
        dp[0][0] = 0;
        dp[0][1] = 0;
        
        /*
            dp[now][0] + 1 (지금 점수에서 i만큼 더하게 되면 발사 횟수 1 증가)
            dp[now + i][0] (지금 점수 + i 가 이미 기록 되어있을수도 있음)
            그럼 이 둘을 비교 해서 dp[now][0] + 1 가 발사 횟수가 더 적으면 기록
                
            만약 발사 횟수가 같다면 single / bull 합산 스코어를 비교
            합산 스코어가 더 큰 경우 기록
        */
        
        for (int i = 0; i < target; i++) {
            hitSingle(dp, i, target);
            hitDouble(dp, i, target);
            hitTriple(dp, i, target);
            hitBull(dp, i, target);
        }
        
        return dp[target];
    }
    
    private void hitSingle(int[][] dp, int now, int target) {
        for (int i = 1; i <= 20; i++) {
            if (now + i > target) break;
            
            if (dp[now][0] + 1 < dp[now + i][0]) {
                dp[now + i][0] = dp[now][0] + 1;
                dp[now + i][1] = dp[now][1] + 1;
            } else if (dp[now][0] + 1 == dp[now + i][0] && dp[now][1] + 1 > dp[now + i][1]) {
                dp[now + i][1] = dp[now][1] + 1;
            }
        }
    }
    
    private void hitDouble(int[][] dp, int now, int target) {
        for (int i = 2; i <= 40; i += 2) {
            if (now + i > target) break;
            
            if (dp[now][0] + 1 < dp[now + i][0]) {
                dp[now + i][0] = dp[now][0] + 1;
                dp[now + i][1] = dp[now][1];
            } else if (dp[now][0] + 1 == dp[now + i][0] && dp[now][1] > dp[now + i][1]) {
                dp[now + i][1] = dp[now][1];
            }
        }
    }
    
    private void hitTriple(int[][] dp, int now, int target) {
        for (int i = 3; i <= 60; i += 3) {
            if (now + i > target) break;
            
            if (dp[now][0] + 1 < dp[now + i][0]) {
                dp[now + i][0] = dp[now][0] + 1;
                dp[now + i][1] = dp[now][1];
            } else if (dp[now][0] + 1 == dp[now + i][0] && dp[now][1] > dp[now + i][1]) {
                dp[now + i][1] = dp[now][1];
            }
        }
    }
    
    private void hitBull(int[][] dp, int now, int target) {
        if (now + 50 > target) return;
        
        if (dp[now][0] + 1 < dp[now + 50][0]) {
            dp[now + 50][0] = dp[now][0] + 1;
            dp[now + 50][1] = dp[now][1] + 1;
        } else if (dp[now][0] + 1 == dp[now + 50][0] && dp[now][1] + 1 > dp[now + 50][1]) {
            dp[now + 50][1] = dp[now][1] + 1;
        }
    }
}
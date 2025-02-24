import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0, r = board.length, c = board[0].length;
        int[][] arr = new int[r + 1][c + 1];
        
        // skill 반복문 돌면서 각 좌표 범위 지정 -> O(skill.length) = 250000
        for (int[] s : skill) {
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], val = s[0] == 1 ? -s[5] : s[5];
            arr[r1][c1] += val;
            arr[r1][c2 + 1] -= val;
            arr[r2 + 1][c1] -= val;
            arr[r2 + 1][c2 + 1] += val;
        }
        
        // 누적합 계산을 통한 전체 범위 변화량 계산 -> O(r * c) = 1000000
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                if (i > 0) arr[i][j] += arr[i - 1][j];
                if (j > 0) arr[i][j] += arr[i][j - 1];
                if (i > 0 && j > 0) arr[i][j] -= arr[i - 1][j - 1];
            }
        }
        
        // board의 각 위치에 해당하는 값 적용 -> O(r * c) = 1000000
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] += arr[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}
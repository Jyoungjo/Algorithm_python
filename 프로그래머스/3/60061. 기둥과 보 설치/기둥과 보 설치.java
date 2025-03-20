import java.util.*;
import java.util.stream.*;

class Solution {
    boolean[][][] frame;
    int N;
    int cnt = 0;
    
    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        frame = new boolean[n + 1][n + 1][2];
        build(build_frame);
        int[][] answer = new int[cnt][3];
        int idx = 0;
        for (int i = 0; i <= N; i++) {
            for (int j = N; j >= 0; j--) {
                for (int k = 0; k < 2; k++) {
                    if (frame[i][j][k]) {
                        answer[idx][0] = i;
                        answer[idx][1] = N - j;
                        answer[idx++][2] = k;
                    }
                }
            }
        }
        return answer;
    }
    
    private void build(int[][] build_frame) {
        for (int[] info : build_frame) {
            /*
                설치 조건
                기둥 - 바닥 위, 보 끝 부분, 다른 기둥 위
                보 - 기둥 위, 양쪽 끝이 다른 보와 연결

                a - 구조물 종류 (0 = 기둥, 1 = 보)
                b - 구조물 설치 및 삭제 (0 = 삭제, 1 = 설치)
                1 = 기둥, 2 = 보
            */
            int x = info[0], y = N - info[1], a = info[2], b = info[3];
            // 설치
            if (b == 1) {
                if (isInstallable(x, y, a)) {
                    frame[x][y][a] = true;
                    cnt++;
                }
            }
            // 삭제
            else {
                frame[x][y][a] = false;
                if (isRemovable(x, y, a)) cnt--;
                else frame[x][y][a] = true;
            }
        }
    }
    
    private boolean isRemovable(int x, int y, int a) {
        // 기둥
        if (a == 0) {
            if (
                (x - 1 >= 0 && frame[x - 1][y - 1][1] && !isInstallable(x - 1, y - 1, 1)) ||
                (x + 1 >= 0 && frame[x][y - 1][1] && !isInstallable(x, y - 1, 1)) ||
                (y - 1 >= 0 && frame[x][y - 1][0] && !isInstallable(x, y - 1, 0))
            ) {
                return false;
            }
        }
        // 보
        else {
            if (
                (y - 1 >= 0 && frame[x][y][0] && !isInstallable(x, y, 0)) ||
                (y - 1 >= 0 && frame[x + 1][y][0] && !isInstallable(x + 1, y, 0)) ||
                (x - 1 >= 0 && frame[x - 1][y][1] && !isInstallable(x - 1, y, 1)) ||
                (x + 2 <= N && frame[x + 1][y][1] && !isInstallable(x + 1, y, 1))
            ) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isInstallable(int x, int y, int a) {
        // 기둥
        if (a == 0) {
            // 바닥 위, 보 끝 부분, 다른 기둥 위
            if (y == N || (x - 1 >= 0 && frame[x - 1][y][1]) ||
                (x + 1 <= N && frame[x][y][1]) || (y + 1 <= N && frame[x][y + 1][0])) 
            {
                return true;
            }
        }
        // 보
        else {
            // 기둥 위, 양쪽 끝이 다른 보와 연결
            if (
                (y + 1 <= N && frame[x][y + 1][0]) || (y + 1 <= N && frame[x + 1][y + 1][0]) ||
                (x - 1 >= 0 && x + 2 <= N && frame[x - 1][y][1] && frame[x + 1][y][1])) 
            {
                return true;
            }
        }
        return false;
    }
}
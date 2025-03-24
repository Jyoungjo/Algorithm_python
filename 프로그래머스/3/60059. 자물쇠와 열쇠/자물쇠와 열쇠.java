import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        int size = N + 2 * (M - 1);

        // 4가지 회전
        for (int rot = 0; rot < 4; rot++) {
            int[][] rotatedKey = rotate(key, rot);
            
            // 확장된 판 위에서 key를 움직이며 확인
            for (int y = 0; y <= size - M; y++) {
                for (int x = 0; x <= size - M; x++) {
                    if (check(rotatedKey, lock, y, x, size)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // key 90도 회전 rot번
    private int[][] rotate(int[][] key, int rot) {
        int M = key.length;
        int[][] result = new int[M][M];
        for (int r = 0; r < rot; r++) {
            int[][] tmp = new int[M][M];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    tmp[j][M - 1 - i] = key[i][j];
                }
            }
            key = tmp;
        }
        return key;
    }

    // 확장된 판에서 key와 lock을 합쳐 확인
    private boolean check(int[][] key, int[][] lock, int y, int x, int size) {
        int M = key.length;
        int N = lock.length;
        int[][] board = new int[size][size];

        // lock 배치
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i + M - 1][j + M - 1] = lock[i][j];
            }
        }

        // key 배치
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                board[y + i][x + j] += key[i][j];
            }
        }

        // lock 영역 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i + M - 1][j + M - 1] != 1) return false;
            }
        }
        return true;
    }
}

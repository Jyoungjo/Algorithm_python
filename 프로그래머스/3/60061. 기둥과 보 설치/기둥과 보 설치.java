import java.util.*;

class Solution {
    boolean[][][] frame;
    int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        frame = new boolean[n + 1][n + 1][2];
        N = n;
        
        List<int[]> answer = new ArrayList<>();
        for (int[] bf : build_frame) {
            int x = bf[0], y = bf[1], type = bf[2], install = bf[3];
            if (install == 1) {
                if (canInstall(x, y, type)) frame[y][x][type] = true;
            } else {
                frame[y][x][type] = false;
                if (!canRemove(x, y, type)) frame[y][x][type] = true;
            }
        }
        
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k < 2; k++) {
                    if (frame[i][j][k]) answer.add(new int[]{j, i, k});
                }
            }
        }
        
        answer.sort((a, b) -> {
            if (a[0] == b[0] && a[1] == b[1]) return a[2] - b[2];
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        return answer.toArray(int[][]::new);
    }
    
    private boolean canInstall(int x, int y, int type) {
        if (type == 0) { // 기둥
            // 바닥 위에 있으면 설치 가능
            if (y == 0) return true;
            // 보의 한쪽 끝 부분 위에 있으면 설치 가능
            if (frame[y][x][1] || (x > 0 && frame[y][x - 1][1])) return true;
            // 다른 기둥 위에 있으면 설치 가능
            if (y > 0 && frame[y - 1][x][0]) return true;
        } else { // 보
            // 한쪽 끝 부분이 기둥 위에 있으면 설치 가능
            if ((y > 0 && frame[y - 1][x][0]) || (y > 0 && x < N && frame[y - 1][x + 1][0])) return true;
            // 양쪽 끝 부분이 다른 보와 동시에 연결되어 있으면 설치 가능
            if ((x > 0 && frame[y][x - 1][1]) && (x < N && frame[y][x + 1][1])) return true;
        }
        
        return false;
    }
    
    private boolean canRemove(int x, int y, int type) {
        if (type == 0) { // 기둥
            // 해당 기둥 바로 위에 보가 존재할 경우 그 보가 설치 안 되면 없앨 수 없음
            if (y < N && frame[y + 1][x][1] && !canInstall(x, y + 1, 1)) return false;
            // 해당 기둥 왼쪽 위에 보가 존재할 경우 그 보가 설치 안 되면 없앨 수 없음
            if (y < N && x > 0 && frame[y + 1][x - 1][1] && !canInstall(x - 1, y + 1, 1)) return false;
            // 해당 기둥 위에 다른 기둥이 존재할 경우 그 기둥이 설치 안 되면 없앨 수 없음
            if (y < N && frame[y + 1][x][0] && !canInstall(x, y + 1, 0)) return false;
        } else { // 보
            // 해당 보 위치에 기둥이 존재할 경우 그 기둥이 설치 안 되면 없앨 수 없음
            if (frame[y][x][0] && !canInstall(x, y, 0)) return false;
            // 해당 보 오른쪽에 기둥이 존재할 경우 그 기둥이 설치 안 되면 없앨 수 없음
            if (x < N && frame[y][x + 1][0] && !canInstall(x + 1, y, 0)) return false;
            // 해당 보 왼쪽에 보가 존재할 경우 그 보가 설치 안 되면 없앨 수 없음
            if (x > 0 && frame[y][x - 1][1] && !canInstall(x - 1, y, 1)) return false;
            // 해당 보 오른쪽에 보가 존재할 경우 그 보가 설치 안 되면 없앨 수 없음
            if (x < N && frame[y][x + 1][1] && !canInstall(x + 1, y, 1)) return false;
        }
        
        return true;
    }
}
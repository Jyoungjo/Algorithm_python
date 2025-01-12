import java.util.*;

class Solution {
    static Queue<int[]>[] path;
    
    public int solution(int[][] points, int[][] routes) {
        // n개의 포인트, m개의 로봇 운송 경로, x대의 로봇 - 0초 출발
        // 다음 포인트 이동 시, 최단 경로 이동 -> 최단 경로 여러개면 r 먼저 (세로 먼저)
        // 이동 중 같은 좌표에 로봇 2대 이상 모이면 충돌 -> 몇 번 충돌하는지 카운트
        // n = points.length, x = routes.length, m = routes[i].length
        // routes -> [a, b] 라면 a에서 b로 가는 것
        int size = routes.length;
        path = new Queue[size];
        for (int i = 0; i < size; i++) {
            path[i] = new LinkedList<>();
        }
        
        findPath(size, routes, points);
        return calCollisionCnt(size);
    }
    
    private int calCollisionCnt(int size) {
        int result = 0, limit = 0;
        
        while (limit != size) {
            int[][] map = new int[101][101];
            limit = 0;
            for (int i = 0; i < size; i++) {
                if (path[i].isEmpty()) {
                    limit++;
                    continue;
                }
                
                int[] coord = path[i].poll();
                map[coord[0]][coord[1]]++;
            }
            
            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (map[i][j] > 1) result++;
                }
            }
        }
        
        return result;
    }
    
    private void findPath(int size, int[][] routes, int[][] points) {
        for (int i = 0; i < size; i++) {
            int[] s = points[routes[i][0] - 1];
            path[i].add(new int[]{s[0], s[1]});
            
            int y = s[0], x = s[1];
            for (int j = 1; j < routes[0].length; j++) {
                int[] nxt = points[routes[i][j] - 1];
                
                int ny = nxt[0], nx = nxt[1];
                int cy = ny - y, cx = nx - x;
                while (cy != 0) {
                    if (cy > 0) {
                        cy--;
                        y++;
                    } else {
                        cy++;
                        y--;
                    }
                    path[i].add(new int[]{y, x});
                }
                while (cx != 0) {
                    if (cx > 0) {
                        cx--;
                        x++;
                    } else {
                        cx++;
                        x--;
                    }
                    path[i].add(new int[]{y, x});
                }
            }
        }
    }
}
import java.util.*;

class Solution {
    int r, c;
    char[][] cArr;
    int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    
    public int solution(String[] storage, String[] requests) {
        r = storage.length;
        c = storage[0].length();
        
        cArr = convertToArray(storage);
        for (String request : requests) {
            char req = request.charAt(0);
            
            if (request.length() > 1) takeoutByCrane(req);
            else takeoutByFolk(req);
        }

        return calContainerCnt(cArr);
    }
    
    private int calContainerCnt(char[][] cArr) {
        int cnt = 0;
        
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (cArr[i][j] != '-' && cArr[i][j] != '@') cnt++;
            }
        }
        
        return cnt;
    }
    
    private void takeoutByCrane(char target) {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (cArr[i][j] == target) {
                    cArr[i][j] = '@';
                    checkOutside(cArr, i, j);
                }
            }
        }
    }
    
    private void takeoutByFolk(char target) {
        List<int[]> res = new ArrayList<>();
        
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (cArr[i][j] == target) takeout(cArr, i, j, res);
            }
        }
        
        for (int[] coord : res) {
            int y = coord[0], x = coord[1];
            cArr[y][x] = '@';
            checkOutside(cArr, y, x);
        }
    }
    
    private void checkOutside(char[][] cArr, int y, int x) {
        boolean flag = false;
        
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];
            if (cArr[ny][nx] == '-') {
                cArr[y][x] = '-';
                flag = true;
                break;
            }
        }
        
        if (flag) {
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                if (cArr[ny][nx] == '@') {
                    cArr[ny][nx] = '-';
                    checkOutside(cArr, ny, nx);
                }
            }
        }
    }
    
    private void takeout(char[][] cArr, int y, int x, List<int[]> result) {
        for (int i = 0; i < 4; i++) {
            if (cArr[y + dy[i]][x + dx[i]] == '-') {
                result.add(new int[]{y, x});
                break;
            }
        }
    }
    
    private char[][] convertToArray(String[] storage) {
        char[][] newStorage = new char[r + 2][c + 2];
        
        for (int i = 0; i < r + 2; i++) {
            Arrays.fill(newStorage[i], '-');
        }
        
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                newStorage[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
        
        return newStorage;
    }
}
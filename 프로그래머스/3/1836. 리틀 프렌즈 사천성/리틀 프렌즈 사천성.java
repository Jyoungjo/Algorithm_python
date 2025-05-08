import java.util.*;

class Solution {
    char BLANK = '.';
    Map<Character, List<int[]>> pairMap = new HashMap<>();
    int M, N;
    StringBuilder sb = new StringBuilder();
    
    public String solution(int m, int n, String[] board) {
        M = m; N = n;
        char[][] newBoard = convertToArr(board);
        findPair(newBoard);
        return findVal(newBoard, new HashSet<>(pairMap.keySet()));
    }
    
    private char[][] convertToArr(String[] board) {
        char[][] arr = new char[M][N];
        
        for (int i = 0; i < M; i++) {
            arr[i] = board[i].toCharArray();
        }
        
        return arr;
    }
    
    private void findPair(char[][] board) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                char now = board[i][j];
                if ('A' <= now && now <= 'Z') {
                    pairMap.putIfAbsent(now, new ArrayList<>());
                    pairMap.get(now).add(new int[]{i, j});
                }
            }
        }
    }
    
    private String findVal(char[][] board, Set<Character> remaining) {
        List<Character> list = new LinkedList<>(remaining);
        Collections.sort(list);

        int idx = 0;
        while (!list.isEmpty()) {
            char now = list.get(idx);
            if (canRemove(board, now)) {
                list.remove(idx);
                remove(board, now, pairMap.get(now).get(0), pairMap.get(now).get(1));
                idx = 0;
            } else {
                idx++;
                if (idx == list.size()) return "IMPOSSIBLE";
            }
        }

        return sb.toString();
    }

    private boolean canRemove(char[][] board, char target) {
        int[] first = pairMap.get(target).get(0), second = pairMap.get(target).get(1);
        int y1 = first[0], x1 = first[1], y2 = second[0], x2 = second[1];

        if (x1 > x2) {
            if (hasOnlyOneInTheRow(board, target, y1, y2, x2) && hasOnlyOneInTheColumn(board, target, x2, x1, y1)) return true;
            if (hasOnlyOneInTheColumn(board, target, x2, x1, y2) && hasOnlyOneInTheRow(board, target, y1, y2, x1)) return true;
        } else {
            if (hasOnlyOneInTheRow(board, target, y1, y2, x1) && hasOnlyOneInTheColumn(board, target, x1, x2, y2)) return true;
            if (hasOnlyOneInTheColumn(board, target, x1, x2, y1) && hasOnlyOneInTheRow(board, target, y1, y2, x2)) return true;
        }

        return false;
    }

    private boolean hasOnlyOneInTheRow(char[][] board, char target, int r1, int r2, int c) {
        for (int i = r1; i <= r2; i++) {
            if (board[i][c] != BLANK && board[i][c] != target) return false;
        }

        return true;
    }

    private boolean hasOnlyOneInTheColumn(char[][] board, char target, int c1, int c2, int r) {
        for (int i = c1; i <= c2; i++) {
            if (board[r][i] != BLANK && board[r][i] != target) return false;
        }

        return true;
    }
    
    private void remove(char[][] board, char target, int[] first, int[] second) {
        board[first[0]][first[1]] = BLANK;
        board[second[0]][second[1]] = BLANK;
        sb.append(target);
    }
}
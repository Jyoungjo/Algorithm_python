import java.util.*;

class Solution {
    int[][] clock;
    List<List<Integer>> times = new ArrayList<>();
    int answer = Integer.MAX_VALUE;
    int R, C;
    
    public int solution(int[][] clockHands) {
        R = clockHands.length;
        C = clockHands[0].length;
        repeatPermutation(new ArrayList<>(), 0);
        findMinVal(clockHands);
        return answer;
    }
    
    private void findMinVal(int[][] clockHands) {
        for (List<Integer> time : times) {
            int[][] newClock = copy(time, clockHands);
            int rotCnt = solve(newClock);
            if (isCorrect(newClock)) answer = Math.min(answer, rotCnt);
        }
    }
    
    private boolean isCorrect(int[][] clock) {
        for (int i = 1; i <= R; i++) {
            for (int j = 0; j < C; j++) {
                if (clock[i][j] != 0) return false;
            }
        }
        return true;
    }
    
    private int solve(int[][] newClock) {
        int tot = 0;
        for (int i = 1; i <= R; i++) {
            for (int j = 0; j < C; j++) {
                if (newClock[i - 1][j] != 0) {
                    int cnt = (4 - newClock[i - 1][j]) % 4;
                    rotate(newClock, i, j, cnt);
                    tot += cnt;
                }
            }
        }
        return tot;
    }
    
    private void rotate(int[][] newClock, int i, int j, int cnt) {
        newClock[i][j] = (newClock[i][j] + cnt) % 4;
        newClock[i - 1][j] = (newClock[i - 1][j] + cnt) % 4;
        if (i + 1 <= R) newClock[i + 1][j] = (newClock[i + 1][j] + cnt) % 4;
        if (j - 1 >= 0) newClock[i][j - 1] = (newClock[i][j - 1] + cnt) % 4;
        if (j + 1 < C) newClock[i][j + 1] = (newClock[i][j + 1] + cnt) % 4;
    }
    
    private int[][] copy(List<Integer> time, int[][] clockHands) {
        int[][] newClock = new int[R + 1][C];
        newClock[0] = time.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < R; i++) {
            newClock[i + 1] = Arrays.copyOf(clockHands[i], C);
        }
        return newClock;
    }
    
    private void repeatPermutation(List<Integer> tmp, int depth) {
        if (depth == C) {
            times.add(tmp);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            List<Integer> newList = new ArrayList<>(tmp);
            newList.add(i);
            repeatPermutation(newList, depth + 1);
        }
    }
}
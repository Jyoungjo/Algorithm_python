import java.util.*;
import java.util.stream.*;

class Game {
    int[] total, now;
    
    Game(int len, int[] stages) {
        this.total = new int[len + 2];
        this.now = new int[len + 2];
        
        for (int s : stages) {
            total[s]++;
            now[s]++;
        }
        
        for (int i = len; i >= 1; i--) total[i] += total[i + 1];
    }
}

class Result {
    int stage;
    double failureRate;
    
    Result(int stage, double failureRate) {
        this.stage = stage;
        this.failureRate = failureRate;
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        Game game = new Game(N, stages);
        Result[] results = new Result[N];
        for (int i = 1; i <= N; i++) {
            if (game.now[i] == 0) results[i - 1] = new Result(i, 0);
            else results[i - 1] = new Result(i, game.now[i] / (double) game.total[i]);
        }
        
        Arrays.sort(results, (o1, o2) -> {
            if (Double.compare(o1.failureRate, o2.failureRate) == 0) return o1.stage - o2.stage;
            return Double.compare(o1.failureRate, o2.failureRate) > 0 ? -1 : 1;
        });
        
        return Arrays.stream(results).map(r -> r.stage).mapToInt(Integer::intValue).toArray();
    }
}
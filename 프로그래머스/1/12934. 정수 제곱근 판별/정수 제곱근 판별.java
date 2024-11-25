import java.util.*;


class Solution {
    public long solution(long n) {
        long answer = 0;
        double sqrt = Math.sqrt((double) n);
        if ((sqrt % 1 == 0.0)) {
            return (long) Math.pow(sqrt + 1, 2);
        } else {
            return -1;
        }
    }
}
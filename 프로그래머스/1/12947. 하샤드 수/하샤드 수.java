import java.util.*;


class Solution {
    public boolean solution(int x) {
        int hashard = 0;
        int val = x;
        int len = (int) Math.log10(x) + 1;
        for (int i = 0; i < len; i++) {
            hashard += val % 10;
            val /= 10;
        }
        return x % hashard == 0;
    }
}
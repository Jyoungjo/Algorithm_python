import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (sb.length() <= m * t) {
            sb.append(Integer.toString(num++, n));
        }
        
        String target = sb.toString();
        sb.setLength(0);
        for (int i = p - 1; i < target.length(); i += m) {
            if (sb.length() == t) break;
            sb.append(target.charAt(i));
        }
        
        return sb.toString().toUpperCase();
    }
}
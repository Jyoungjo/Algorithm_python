import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 100000; i++) {
            sb.append(convert(i, n));
        }
        
        String target = sb.toString();
        sb.setLength(0);
        for (int i = p - 1; i < target.length(); i += m) {
            if (sb.length() == t) break;
            sb.append(target.charAt(i));
        }
        
        return sb.toString();
    }
    
    private String convert(int num, int n) {
        if (num == 0) return String.valueOf(0);
        
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(divide(num % n));
            num /= n;
        }
        return sb.reverse().toString();
    }
    
    private String divide(int num) {
        switch (num) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                return String.valueOf(num);
        }
    }
}
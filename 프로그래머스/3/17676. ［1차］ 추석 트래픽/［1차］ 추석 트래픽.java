import java.util.*;

class Solution {
    public int solution(String[] lines) {
        int len = lines.length;
        int[][] time_table = new int[len][2];
        for (int i = 0; i < len; i++) {
            String[] line = lines[i].split(" ");
            int end = convertToMillisec(line[1]);
            int T = convertFromExecuteTime(line[2]);
            int start = end - T + 1;
            time_table[i] = new int[]{start, end};
        }
        
        int answer = -1;
        for (int i = 0; i < time_table.length; i++) {
            int s = time_table[i][1], e = s + 999;
            
            int cnt = 0;
            for (int[] time : time_table) {
                if (!(s > time[1] || time[0] > e)) cnt++;
            }
            
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
    
    private int convertToMillisec(String time) {
        String[] t = time.split("[:.]");
        int h = Integer.parseInt(t[0]) * 60 * 60 * 1000;
        int m = Integer.parseInt(t[1]) * 60 * 1000;
        int s = Integer.parseInt(t[2]) * 1000;
        int ss = Integer.parseInt(t[3]);
        return h + m + s + ss;
    }
    
    private int convertFromExecuteTime(String T) {
        String t = T.substring(0, T.length() - 1).replace(".", "");
        while (t.length() < 4) t += "0";
        return Integer.parseInt(t);
    }
}
import java.util.*;

class Solution {
    public int solution(String[] lines) {
        List<int[]> times = new ArrayList<>();
        for (String line : lines) {
            String[] splited = line.split(" ");
            int S = convertToMilliSecFromTime(splited[1].split(":"));
            int T = convertToMilliSecFromExecuteTime(splited[2].substring(0, splited[2].length() - 1).replace(".", ""));
            
            int start = S - T + 1;
            times.add(new int[]{start, S});
        }
        
        int answer = 0;
        for (int[] t : times) {
            int cnt = 0;
            int s = t[1], e = s + 999;
            for (int[] tt : times) {
                if (tt[0] <= e && tt[1] >= s) cnt++;
            }
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
    
    private int convertToMilliSecFromTime(String[] time) {
        int h = Integer.parseInt(time[0]) * 3600000;
        int m = Integer.parseInt(time[1]) * 60000;
        int s = Integer.parseInt(time[2].split("\\.")[0]) * 1000;
        int ss = Integer.parseInt(time[2].split("\\.")[1]);
        
        return h + m + s + ss;
    }
    
    private int convertToMilliSecFromExecuteTime(String s) {
        while (s.length() < 4) s += "0";
        return Integer.parseInt(s);
    }
}
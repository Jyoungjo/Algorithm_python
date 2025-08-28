import java.util.*;

class TimeConverter {
    String time, processTime;
    
    public TimeConverter(String time, String processTime) {
        this.time = time;
        this.processTime = processTime;
    }
    
    int convertTime() {
        String[] sArr = this.time.split("[:.]");
        int h = Integer.parseInt(sArr[0]) * 3600 * 1000;
        int m = Integer.parseInt(sArr[1]) * 60 * 1000;
        int s = Integer.parseInt(sArr[2]) * 1000;
        int ss = Integer.parseInt(sArr[3]);
            
        return h + m + s + ss;
    }
    
    int convertProcessTime() {
        String str = this.processTime.substring(0, this.processTime.length() - 1).replace(".", "");
        while (str.length() < 4) str += "0";
        return Integer.parseInt(str);
    }
}

class Solution {
    public int solution(String[] lines) {
        int len = lines.length;
        int[][] times = new int[len][2];
        
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" ");
            TimeConverter tc = new TimeConverter(line[1], line[2]);
            int end = tc.convertTime(), processTime = tc.convertProcessTime();
            int start = end - processTime + 1;
            times[i][0] = start; times[i][1] = end;
        }
        
        Arrays.sort(times, Comparator.comparing(o -> o[1]));
        
        int max = -1;
        for (int i = 0; i < len; i++) {
            int s = times[i][1], e = s + 999;
            
            int res = 0;
            for (int[] t : times) {
                if (t[1] < s || e < t[0]) continue;
                res++;
            }
            
            max = Math.max(res, max);
        }
        
        return max;
    }
}
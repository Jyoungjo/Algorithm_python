import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int play = convertToSec(play_time), adv = convertToSec(adv_time);
        int[] times = new int[360000];
        for (String log : logs) {
            String[] splited = log.split("-");
            int s = convertToSec(splited[0]), e = convertToSec(splited[1]);
            for (int i = s; i < e; i++) {
                times[i]++;
            }
        }
        
        long tot = 0;
        int idx = 0;
        for (int i = 0; i < adv; i++) {
            tot += times[i];
        }
        
        long maxTot = tot;
        for (int i = adv; i <= play; i++) {
            tot += times[i] - times[i - adv];
            if (maxTot < tot) {
                maxTot = tot;
                idx = i - adv + 1;
            }
        }
        
        return convertToStringTime(idx);
    }
    
    private String addZero(int time) {
        return time < 10 ? "0" : "";
    }
    
    private String convertToStringTime(int time) {
        int h = time / 3600, m = (time - h * 3600) / 60, s = time - 3600 * h - 60 * m;
        return addZero(h) + h + ":" + addZero(m) + m + ":" + addZero(s) + s;
    }
    
    private int convertToSec(String time) {
        int[] tmp = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return 3600 * tmp[0] + 60 * tmp[1] + tmp[2];
    }
}
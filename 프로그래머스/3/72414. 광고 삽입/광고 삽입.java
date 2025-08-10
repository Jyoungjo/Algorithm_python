class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int[] time_log = new int[360000];
        for (String log : logs) {
            String[] times = log.split("-");
            int s = convertToSec(times[0]), e = convertToSec(times[1]);
            
            for (int i = s; i < e; i++) time_log[i]++;
        }
        
        int pt = convertToSec(play_time), at = convertToSec(adv_time);
        long acc_sum = 0;
        int start_time = 0;
        for (int i = 0; i < at; i++) acc_sum += time_log[i];
        
        long max = acc_sum;
        for (int end_time = at; end_time <= pt; end_time++) {
            acc_sum += time_log[end_time] - time_log[end_time - at];
            if (max < acc_sum) {
                max = acc_sum;
                start_time = end_time - at + 1;
            }
        }
        
        return convertToString(start_time);
    }
    
    private int convertToSec(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 3600 + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
    }
    
    private String convertToString(int sec) {
        int h = sec / 3600, m = (sec % 3600) / 60, s = (sec % 3600) % 60;
        return addZero(h) + h + ":" + addZero(m) + m + ":" + addZero(s) + s;
    }
    
    private String addZero(int t) {
        return t >= 10 ? "" : "0";
    }
}
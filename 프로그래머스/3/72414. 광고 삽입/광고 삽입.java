class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        // 누적 재생시간이 가장 많이 나오는 곳에 광고 삽입
        // return 광고 시작 시각 -> 누적 재생시간 많은 곳 여러개면 가장 빠른 시작 시각
        int pt = convertToSec(play_time);
        int at = convertToSec(adv_time);
        int[] video = new int[pt + 1];
        
        for (String log : logs) {
            String[] l = log.split("-");
            int s = convertToSec(l[0]), e = convertToSec(l[1]);
            for (int i = s; i < e; i++) video[i]++;
        }
        
        long tot = 0;
        for (int i = 0; i < at; i++) tot += video[i];
        
        long max = tot;
        int start_time = 0;
        for (int i = at; i <= pt; i++) {
            tot += video[i];
            tot -= video[i - at];
            
            if (max < tot) {
                max = tot;
                start_time = i - at + 1;
            }
        }
        
        return convertToString(start_time);
    }
    
    private String convertToString(int start_time) {
        int h = start_time / 3600;
        int m = (start_time % 3600) / 60;
        int s = (start_time % 3600) % 60;
        return addZero(h) + h + ":" + addZero(m) + m + ":" + addZero(s) + s;
    }
    
    private String addZero(int time) {
        return time < 10 ? "0" : "";
    }
    
    private int convertToSec(String time) {
        String[] t = time.split(":");
        int h = Integer.parseInt(t[0]) * 3600;
        int m = Integer.parseInt(t[1]) * 60;
        int s = Integer.parseInt(t[2]);
        return h + m + s;
    }
}
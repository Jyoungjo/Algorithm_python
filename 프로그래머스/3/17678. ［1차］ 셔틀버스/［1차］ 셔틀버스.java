import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        /*
            n = 운행 횟수, t = 운행 간격, m = 최대 탑승 인원 수
            
            진행 과정
            1. timetable의 인원들이 셔틀(queue)에 탄다.
                if (현재 셔틀 도착 시간 < timetable[i]) {
                    탑승 불가
                    break;
                } else if (!riding[i]) {
                    셔틀(queue).add(timetable[i])
                    riding[i] = true;
                }
                
                1-1. 만약 timetable[i] == 23:59 라면?
                    집에 가야함. continue
            2. n까지 반복 했을 때, 탑승 못 했다면? 마지막 탑승객 시간 - 1이 정답
         */
        String answer = "";
        boolean[] riding = new boolean[timetable.length];
        Arrays.sort(timetable, Comparator.naturalOrder());
        
        int start = 540, last = 0;
        boolean isRide = false;
        for (int i = 0; i < n; i++) {
            int size = 0;
            for (int j = 0; j < timetable.length; j++) {
                if (timetable[j].equals("23:59")) break;
                if (riding[j]) continue;
            
                int now = convertToMinutes(timetable[j]);
                if (now > start) break;
                size++;
                riding[j] = true;
                last = now;
                
                if (size == m) break;
            }
            if (i == n - 1) {
                if (size < m) answer = convertToString(start);
                else answer = convertToString(last - 1);
                break;
            }
            start += t;
        }
        
        
        return answer;
    }
    
    private String convertToString(int time) {
        StringBuilder sb = new StringBuilder();
        int h = time / 60, m = time % 60;
        if (h < 10) sb.append("0");
        sb.append(h).append(":");
        if (m < 10) sb.append("0");
        sb.append(m);
        return sb.toString();
    }
    
    private int convertToMinutes(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}
import java.util.*;

class Solution {
    final String MAX_TIME = "24:00:00.000";
    
    public int solution(String[] lines) {
        /*
            1초(1000밀리초)간 처리하는 요청 최대 개수 구하기
            로그 문자열마다 응답 완료시간 S와 처리시간 T 존재
            S -> 고정 길이 2016-09-15 hh:mm:ss.sss 형태
            T -> 최대 소수점 셋째 자리까지 기록. 뒤에 s로 끝남(처음과 끝 포함)
            
            1. lines의 String의 시간을 밀리초로 변환하여 구간을 구한다.
            2. 구한 구간을 배열로 만들어 시간대 정리
            3. 1000 밀리초 간격을 두고 순회하면서 최대 개수 확인
        */
        
        int answer = 0;
        List<int[]> times = new ArrayList<>();
        for (String log : lines) {
            String[] info = log.split(" ");
            int end = convertToMillisec(info[1]);
            int T = convertFromDecimalToMillisec(info[2]);
            int start = end - T + 1;
            times.add(new int[]{start, end});
        }
        
        for (int i = 0; i < times.size(); i++) {
            int start = times.get(i)[1], end = start + 999;

            int cnt = 0;
            for (int[] time : times) {
                if (!(time[1] < start || time[0] > end)) cnt++;
            }
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
    
    private int convertToMillisec(String time) {
        String[] times = time.split("[:.]");
        int h = Integer.parseInt(times[0]) * 60 * 60 * 1000;
        int m = Integer.parseInt(times[1]) * 60 * 1000;
        int s = Integer.parseInt(times[2]) * 1000;
        return h + m + s + Integer.parseInt(times[3]);
    }
    
    private int convertFromDecimalToMillisec(String decimal) {
        String strT = decimal.substring(0, decimal.length() - 1).replace(".", "");
        while (strT.length() < 4) strT += "0";
        return Integer.parseInt(strT);
    }
}
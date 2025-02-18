import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        /*
            같은 시간대에 m명 늘어날 때마다 서버 1대 증설해야함
            m명 미만이면 증설 필요 X
            
            n * m 명 이상 (n + 1) * m 미만이면 최소 n 대의 증설 서버 운영해야함
            
            한 번 증설한 서버는 k 시간 동안 운영하고 반남
        */
        
        int answer = 0;
        Queue<Integer> servers = new LinkedList<>();
        
        for (int player : players) {            
            // 서버 시간 증가
            for (int i = 0; i < servers.size(); i++) {
                servers.add(servers.poll() + 1);
            }
            
            // 서버 증설
            int n = player / m;
            int additional = n - servers.size();
            
            if (additional > 0) {
                for (int i = 0; i < additional; i++) {
                    servers.add(1);
                    answer++;
                }
            }
            
            // 서버 유지 시간 지나면 반납
            int len = servers.size();
            for (int i = 0; i < len; i++) {
                int s = servers.poll();
                
                if (s < k) servers.add(s);
            }
        }
        
        return answer;
    }
}
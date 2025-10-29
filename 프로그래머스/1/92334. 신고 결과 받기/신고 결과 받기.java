import java.util.*;

class Solution {
    Map<String, Set<String>> reportMap = new HashMap<>();
    Map<String, Integer> cntMap = new HashMap<>();
    
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        for (String r : report) {
            String[] log = r.split(" ");
            reportMap.putIfAbsent(log[0], new HashSet<>());
            Set<String> members = reportMap.get(log[0]);
            if (!members.contains(log[1])) cntMap.put(log[1], cntMap.getOrDefault(log[1], 0) + 1);
            members.add(log[1]);
        }
        
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            
            Set<String> reportUsers = reportMap.get(id);
            if (reportUsers == null) {
                answer[i] = 0;
                continue;
            }
            
            int cnt = 0;
            for (String u : reportUsers) {
                if (cntMap.get(u) >= k) cnt++;
            }
            
            answer[i] = cnt;
        }
        
        return answer;
    }
}
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();
        Map<String, String> idMap = new HashMap<>();
        
        for (int i = 0; i < record.length; i++) {
            String[] splited = record[i].split(" ");
            if (splited[0].equals("Enter") || splited[0].equals("Change")) {
                idMap.put(splited[1], splited[2]);
            }
        }
        for (int i = 0; i < record.length; i++) {
            String[] splited = record[i].split(" ");
            if (splited[0].equals("Enter")) {
                answer.add(idMap.get(splited[1]) + "님이 들어왔습니다.");
            } else if (splited[0].equals("Leave")) {
                answer.add(idMap.get(splited[1]) + "님이 나갔습니다.");
            }
        }
        
        return answer.toArray(String[]::new);
    }
}
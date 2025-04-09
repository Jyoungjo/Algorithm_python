import java.util.*;

class Solution {
    public int[] solution(String msg) {
        /*
            1. 사전 내에 메시지의 n번째 글자가 등록되어 있으면 해당 글자 색인 번호 출력
            2. n+1번째 글자를 포함시켜서 사전 마지막 색인 번호로 등록
            3. n+1번째 글자로 이동
            4. 위 과정 반복
        */
        Map<String, Integer> dict = new HashMap<>();
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 1; i <= alpha.length(); i++) {
            dict.put(String.valueOf(alpha.charAt(i - 1)), i);
        }
        
        List<Integer> answer = new ArrayList<>();
        
        int idx = 0, num = 27;
        boolean isLast = false;
        for (int i = idx; i < msg.length(); i++) {
            if (isLast) break;
            char now = msg.charAt(i);
            
            int j = i;
            String tmp = String.valueOf(now);
            while (dict.get(tmp) != null) {
                if (j == msg.length() - 1) {
                    answer.add(dict.get(tmp));
                    isLast = true;
                    break;
                }
                
                if (dict.get(tmp + msg.charAt(++j)) == null) {
                    answer.add(dict.get(tmp)); // 사전 색인 번호 출력
                    dict.put(tmp + msg.charAt(j), num++);
                    break;
                }
                tmp += msg.charAt(j);
                i = j;
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
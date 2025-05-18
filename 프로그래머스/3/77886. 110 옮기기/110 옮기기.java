/*
    문자열 순회하면서 110 나오는 개수 찾기
    0 뒤에 110 붙는게 사전 순으로 오름차순이고 0이 아예 없다면 맨 앞에 두는게 좋다
*/

import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        List<String> answer = new ArrayList<>();
        
        for (String str : s) {
            Stack<Character> stack = new Stack<>();
            int cnt = 0;
            for (int i = 0; i < str.length(); i++) {
                stack.push(str.charAt(i));
                int size = stack.size();
                if (size < 3) continue;
                
                if (stack.get(size - 1) == '0' && 
                    stack.get(size - 2) == '1' && 
                    stack.get(size - 3) == '1') 
                {
                    for (int j = 0; j < 3; j++) stack.pop();
                    cnt++;
                }
            }
            
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) sb.append(stack.pop());
            sb.reverse();
            
            int idx = 0;
            for (int i = sb.length() - 1; i >= 0; i--) {
                if (sb.charAt(i) == '0') {
                    idx = i + 1;
                    break;
                }
            }
            
            while (cnt > 0) {
                sb.insert(idx, "110");
                cnt--;
            }
            
            answer.add(sb.toString());
        }
        
        return answer.stream().toArray(String[]::new);
    }
}
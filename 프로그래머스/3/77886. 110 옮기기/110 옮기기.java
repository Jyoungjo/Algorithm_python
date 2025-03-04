import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    String NUM = "110";
    
    public String[] solution(String[] s) {
        // 사전순으로 앞에 오려면 0이 최대한 앞쪽에 위치해야한다.
        // 110이 와야하는 지점은 111 앞 -> 11 앞 -> 1 앞앞
        
        for (int i = 0; i < s.length; i++) { // n -> 100만
            findVal(s[i]);
        }
        return answer.toArray(new String[s.length]);
    }
    
    private void findVal(String current) {
        // 111 앞에 오는게 무조건 사전 오름차순
        // last 0 뒤에 110 넣어주는게 좋다.
        int cnt = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < current.length(); i++) {
            stack.push(current.charAt(i));
            int len = stack.size();
            if (len < 3) continue;
            
            if (stack.get(len - 1) == '0' &&
                stack.get(len - 2) == '1' &&
                stack.get(len - 3) == '1')
            {
                for (int j = 0; j < 3; j++) stack.pop();
                cnt++;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        int idx = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '0') {
                idx = i + 1;
                break;
            }
        }

        while (cnt != 0) {
            sb.insert(idx, NUM);
            cnt--;
        }
        answer.add(sb.toString());
    }
}
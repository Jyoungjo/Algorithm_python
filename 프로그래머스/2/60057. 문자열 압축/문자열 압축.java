import java.util.*;

class Text {
    String word;
    int cnt;
    
    Text(String word, int cnt) {
        this.word = word;
        this.cnt = cnt;
    }
}

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE, half = s.length() / 2;
        if (s.length() == 1) return 1;
        
        for (int i = 1; i <= half; i++) {
            Deque<Text> deque = new ArrayDeque<>();
            int cnt = 1;
            for (int j = 0; j < s.length(); j+=i) {
                if (s.length() - j < i) {
                    deque.push(new Text(s.substring(j), 1));
                    break;
                }
                String tmp = s.substring(j, j + i);
                if (deque.isEmpty()) {
                    deque.push(new Text(tmp, 1));
                    continue;
                }
                
                if (deque.peek().word.equals(tmp)) deque.peek().cnt++;
                else deque.push(new Text(tmp, 1));
            }
            
            StringBuilder sb = new StringBuilder();
            while (!deque.isEmpty()) {
                Text text = deque.pollLast();
                if (text.cnt > 1) sb.append(text.cnt);
                sb.append(text.word);
            }
            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }
}
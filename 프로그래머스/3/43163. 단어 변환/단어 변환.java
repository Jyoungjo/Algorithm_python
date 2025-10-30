import java.util.*;

class Word {
    String original;
    int cnt;
    
    Word(String original, int cnt) {
        this.original = original;
        this.cnt = cnt;
    }
}

class Solution {
    final Set<String> wordSet = new HashSet<>();
    
    public int solution(String begin, String target, String[] words) {
        for (String word : words) wordSet.add(word);
        if (!wordSet.contains(target)) return 0;
        
        return bfs(begin, target);
    }
    
    private int bfs(String begin, String target) {
        Queue<Word> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(new Word(begin, 0));
        visited.add(begin);
        
        int cnt = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Word now = q.poll();
            
            if (now.original.equals(target)) cnt = Math.min(cnt, now.cnt);
            
            for (int idx = 0; idx < now.original.length(); idx++) {
                char[] cArr = now.original.toCharArray();
                for (int j = 0; j <= 26; j++) {
                    cArr[idx] = (char) ((int) 'a' + j);
                    String tmp = String.valueOf(cArr);
                    if (!visited.contains(tmp) && wordSet.contains(tmp)) {
                        q.add(new Word(tmp, now.cnt + 1));
                        visited.add(tmp);
                    }
                }
            }
        }
        
        return cnt;
    }
}
import java.util.*;

class Solution {
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    boolean[] visited;
    Map<Character, List<char[]>> conditions = new HashMap<>();
    List<String> result = new ArrayList<>();
    
    public int solution(int n, String[] data) {
        /*
            위 조건 만족하면서 다르게 서는 방법 생각
        */
        int answer = 0;
        visited = new boolean[8];
        for (String d : data) {
            char key1 = d.charAt(0), key2 = d.charAt(2);
            char symbol = d.charAt(3), num = d.charAt(4);
            conditions.computeIfAbsent(key1, k -> new ArrayList<>());
            conditions.computeIfAbsent(key2, k -> new ArrayList<>());
            conditions.get(key1).add(new char[]{key2, symbol, num});
            conditions.get(key2).add(new char[]{key1, symbol, num});
        }
        permutations(0, new StringBuilder());
        return result.size();
    }
    
    private void permutations(int depth, StringBuilder sb) {
        if (depth == 8) {
            result.add(sb.toString());
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            sb.append(i);
            if (isFind(sb.toString())) permutations(depth + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }
    
    private boolean isFind(String target) {
        for (int i = 0; i < target.length(); i++) {
            int idx = Integer.parseInt(String.valueOf(target.charAt(i)));

            List<char[]> infos = conditions.get(friends[idx]);
            if (infos != null) {
                for (char[] info : infos) {
                    for (int k = 0; k < target.length(); k++) {
                        if (i == k) continue;
                        int idx2 = Integer.parseInt(String.valueOf(target.charAt(k)));
                        if (friends[idx2] == info[0]) {
                            int num = Integer.parseInt(String.valueOf(info[2]));

                            if (info[1] == '=' && Math.abs(i - k) != num + 1) return false;
                            else if (info[1] == '<' && Math.abs(i - k) >= num + 1) return false;
                            else if (info[1] == '>' && Math.abs(i - k) <= num + 1) return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
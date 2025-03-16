import java.util.*;

class Solution {
    Set<Set<String>> set = new HashSet<>();
    Map<String, List<String>> idMap = new HashMap<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        for (int i = 0; i < user_id.length; i++) {
            findUser(user_id[i], "", 0);
        }
        dfs(banned_id, 0, new HashSet<>());
        // for (String key : idMap.keySet()) {
        //     System.out.printf("k: %s / v: ", key);
        //     System.out.println(idMap.get(key));
        // }
        for (Set<String> set2 : set) {
            System.out.println(set2);
        }
        return set.size();
    }
    
    private void dfs(String[] banned_id, int depth, Set<String> ss) {
        if (depth == banned_id.length) {
            set.add(ss);
            return;
        }
        
        for (String key : idMap.get(banned_id[depth])) {
            Set<String> tmp = new HashSet<>(ss);
            if (!tmp.contains(key)) {
                tmp.add(key);
                dfs(banned_id, depth + 1, tmp);
            }
        }
    }
    
    private void findUser(String user_id, String tmp, int depth) {
        if (depth == user_id.length()) {
            addKey(tmp, user_id);
            return;
        }
        
        char target = user_id.charAt(depth);
        findUser(user_id, tmp + "*", depth + 1);
        findUser(user_id, tmp + target, depth + 1);
    }
    
    private void addKey(String key, String word) {
        List<String> list = idMap.get(key);
        if (list == null) {
            list = new ArrayList<>();
            idMap.put(key, list);
        }
        list.add(word);
    }
}
import java.util.*;

class Solution {
    Map<Integer, Set<String>> map = new HashMap<>();
    Map<String, Integer> cntMap = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        /*
            orders를 통해 단품메뉴 조합의 횟수에 따라 코스요리 메뉴 선정
            
            1. orders를 활용하여 메뉴 조합이 몇번 나오는지 데이터 가공 -> 조합이기 때문에 순서를 고려하지 않는다.
                ex) WX = XW
            2. course 순회하면서 course[i]에 따라 적합한 조합이 무엇인지 리턴
        */
        List<String> answer = new ArrayList<>();
        for (String order : orders) {
            dfs(order, new HashSet<>(), new HashSet<>(), 0);
        }
        
        for (int c : course) {
            int max = -1;
            Set<String> tmp = map.get(c);
            if (tmp == null) continue;
            List<String> list = new ArrayList<>(tmp);
            list.sort((o1, o2) -> cntMap.get(o2) - cntMap.get(o1));
            for (String s : list) {
                if (max == -1) max = cntMap.get(s);
                if (cntMap.get(s) != max || cntMap.get(s) <= 1) break;
                answer.add(s);
            }
        }
        
        answer.sort(Comparator.naturalOrder());
        return answer.toArray(new String[0]);
    }
    
    private void dfs(String target, Set<Character> startSet, Set<Set<Character>> visited, int depth) {
        if (depth == target.length()) return;
        
        for (int i = depth; i < target.length(); i++) {
            Set<Character> set = addAll(startSet, target.charAt(i));
            if (visited.contains(set)) continue;
            visited.add(set);
            String tmp = set.stream().sorted(Comparator.naturalOrder()).map(String::valueOf).reduce("", String::concat);
            if (tmp.length() > 1) {
                Set<String> tmpSet = map.get(set.size());
                if (tmpSet == null) {
                    tmpSet = new HashSet<>();
                    map.put(set.size(), tmpSet);
                }
                tmpSet.add(tmp);
                cntMap.put(tmp, cntMap.getOrDefault(tmp, 0) + 1);
            }
            dfs(target, set, visited, depth + 1);
        }
    }
    
    private Set<Character> addAll(Set<Character> original, char c) {
        Set<Character> set = new HashSet<>();
        set.add(c);
        set.addAll(original);
        return set;
    }
}
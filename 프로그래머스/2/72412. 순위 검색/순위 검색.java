import java.util.*;

class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        /*
            info 50000 / query 100000
        */
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            String[] s = info[i].split(" ");
            dfs(new StringBuilder(), 0, s, Integer.parseInt(s[s.length - 1]));
        }
        
        for (List<Integer> list : map.values()) {
            list.sort(Comparator.naturalOrder());
        }
        
        for (int i = 0; i < query.length; i++) {
            String[] q = query[i].split(" ");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < q.length; j+=2) {
                sb.append(q[j]);
            }
            List<Integer> list = map.get(sb.toString());
            int score = Integer.parseInt(q[q.length - 1]);
            answer.add(binarySearch(list, score));
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int binarySearch(List<Integer> list, int limit) {
        if (list == null) list = List.of(0);
        
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) >= limit) r = mid;
            else l = mid + 1;
        }
        return list.size() - l;
    }
    
    private void dfs(StringBuilder key, int depth, String[] infos, int score) {
        if (depth == infos.length - 1) {
            List<Integer> list = map.get(key.toString());
            if (list == null) {
                list = new ArrayList<>();
                map.put(key.toString(), list);
            }
            list.add(score);
            return;
        }
        
        StringBuilder sb = new StringBuilder(key.toString());
        sb.append(infos[depth]);
        dfs(sb, depth + 1, infos, score);
        
        sb = new StringBuilder(key.toString());
        sb.append("-");
        dfs(sb, depth + 1, infos, score);
    }
}
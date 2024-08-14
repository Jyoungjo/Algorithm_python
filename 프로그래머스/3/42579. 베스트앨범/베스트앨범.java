import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();

        Map<String, Integer> mostPlay = new HashMap<>();
        Map<String, Map<Integer, Integer>> musicMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            mostPlay.put(genres[i], mostPlay.getOrDefault(genres[i], 0) + plays[i]);
            Map<Integer, Integer> nMap = musicMap.getOrDefault(genres[i], new HashMap<>());
            nMap.put(i, plays[i]);
            musicMap.put(genres[i], nMap);
        }

        List<String> mostKeySet = new ArrayList<>(mostPlay.keySet());
        mostKeySet.sort((o1, o2) -> mostPlay.get(o2) - mostPlay.get(o1));

        for (String key : mostKeySet) {
            Map<Integer, Integer> nMap = musicMap.get(key);
            List<Integer> nMapKeySet = new ArrayList<>(nMap.keySet());
            nMapKeySet.sort((o1, o2) -> nMap.get(o2) - nMap.get(o1));

            answer.add(nMapKeySet.get(0));
            if (nMapKeySet.size() > 1) {
                answer.add(nMapKeySet.get(1));
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
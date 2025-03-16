import java.util.*;

class Solution {
    public int[] solution(String s) {
        List<Integer> answer = new ArrayList<>();
        String[] sArr = convertToArr(s);
        Arrays.sort(sArr, Comparator.comparing(a -> a.length()));
        Set<String> set = new HashSet<>(List.of(sArr[0]));
        answer.add(Integer.parseInt(sArr[0]));
        for (int i = 1; i < sArr.length; i++) {
            Set<String> tmp = new HashSet<>(List.of(sArr[i].split(",")));
            tmp.removeAll(set);
            for (String a : tmp) answer.add(Integer.parseInt(a));
            set.addAll(tmp);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private String[] convertToArr(String str) {
        String s = str.substring(2, str.length() - 2);
        return s.split("},\\{");
    }
}

// System.out.println();
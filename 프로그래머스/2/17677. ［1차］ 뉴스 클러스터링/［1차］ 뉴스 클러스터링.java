import java.util.*;

class Solution {
    final int MULTI = 65536;
    
    public int solution(String str1, String str2) {
        /*
            자카드 유사도 = 두 집합의 교집합 크기 / 합집합 크기
            두 집합이 공집합? -> 유사도 = 1
            
            문자열 끊을 때, 두 글자씩(i, i+1) / 영문(대소문자 구분X)만
            집합 중복 허용
        */
        int answer = 0;
        // 1. 부분 집합 만들기
        List<String> set1 = makeSet(str1.toLowerCase());
        List<String> set2 = makeSet(str2.toLowerCase());
        set1.forEach(a -> System.out.printf("%s, ", a));
        System.out.println();
        set2.forEach(a -> System.out.printf("%s, ", a));
        if (set1.isEmpty() && set2.isEmpty()) return MULTI;
        return calculateSimilarity(set1, set2);
    }
    
    private int calculateSimilarity(List<String> set1, List<String> set2) {
        int inter = 0, union = set1.size() + set2.size();
        boolean[] used1 = new boolean[set1.size()], used2 = new boolean[set2.size()];
        
        for (int i = 0; i < set1.size(); i++) {
            for (int j = 0; j < set2.size(); j++) {
                if (set1.get(i).equals(set2.get(j)) && !used1[i] && !used2[j]) {
                    used1[i] = true;
                    used2[j] = true;
                    inter++;
                    break;
                }
            }
        }
        
        union -= inter;
        return (int) (MULTI * ((double) inter / union));
    }
    
    private List<String> makeSet(String str) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            if (!isAlpha(str.charAt(i)) || !isAlpha(str.charAt(i + 1))) continue;
            result.add(String.valueOf(str.charAt(i)) + String.valueOf(str.charAt(i + 1)));
        }
        return result;
    }
    
    private boolean isAlpha(char c) {
        return 0 <= c - 'a' && c - 'a' < 26;
    }
}
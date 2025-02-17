import java.util.*;

class Solution {    
    public String solution(long n, String[] bans) {
        /*
            정렬 기준
            1. 글자 수가 적은 주문부터 먼저 기록
            2. 숫자 같다면 사전 순
            
            몇개의 주문을 삭제한 뒤, 남은 주문서에서 n번째 주문 찾는 문제
            삭제된 주문의 위치를 파악하여 계산
        */
        
        Arrays.sort(bans, (o1, o2) -> {
            if (o1.length() == o2.length()) return o1.compareTo(o2);
            return o1.length() - o2.length();
        });
        
        long tmp = n;
        
        for (String bannedWord : bans) {
            if (tmp >= convertToIdx(bannedWord)) tmp++;
            else break;
        }
        

        return convertToAlpha(tmp);
    }
    
    private long convertToIdx(String word) {
        long res = 0, len = word.length() - 1;
        for (int i = 0; i < word.length(); i++) {
            res += Math.pow(26, len--) * (word.charAt(i) - 'a' + 1);
        }
        return res;
    }
    
    private String convertToAlpha(long idx) {
        StringBuilder sb = new StringBuilder();
        
        while (idx > 26) {
            long remainder = --idx % 26;
            sb.append(Character.toString('a' + (int) remainder));
            idx /= 26;
            
            System.out.println(Character.toString('a' + (int) remainder));
            System.out.println(idx);
        }
        
        sb.append(Character.toString('a' + (int) idx - 1));
        System.out.println(Character.toString('a' + (int) idx - 1));
        
        return sb.reverse().toString();
    }
}
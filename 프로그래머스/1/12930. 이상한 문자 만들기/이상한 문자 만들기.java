import java.util.*;


class Solution {
    public String solution(String s) {
        String[] words = s.split(" ", -1);
        for (int i = 0; i < words.length; i++) {
            if (words[i].isEmpty()) {
                continue;
            }

            char[] cArr = words[i].toCharArray();
            for (int j = 0; j < cArr.length; j++) {
                if (j % 2 == 0) {
                    cArr[j] = Character.toUpperCase(cArr[j]);
                } else {
                    cArr[j] = Character.toLowerCase(cArr[j]);
                }
            }
            
            words[i] = String.valueOf(cArr);
        }
        return String.join(" ", words);
    }
}
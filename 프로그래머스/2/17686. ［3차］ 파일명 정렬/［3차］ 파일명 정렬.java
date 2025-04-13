import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<String[]> answer = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String file = files[i].toLowerCase();
            String head = "", number = "";
            boolean isHead = true;
            for (int j = 0; j < file.length(); j++) {
                if (isHead) {
                    if (!(0 <= file.charAt(j) - '0' && file.charAt(j) - '0' < 10)) {
                        head += file.charAt(j);
                    } else {
                        number += file.charAt(j);
                        isHead = false;
                    }
                } else {
                    if (0 <= file.charAt(j) - '0' && file.charAt(j) - '0' < 10) {
                        number += file.charAt(j);
                    } else break;
                }
            }
            
            answer.add(new String[]{files[i], file, head, number});
        }
        
        answer.sort((a, b) -> {
            if (a[2].equals(b[2])) return Integer.parseInt(a[3]) - Integer.parseInt(b[3]);
            return a[2].compareTo(b[2]);
        });
        
        return answer.stream().map(s -> s[0]).toArray(String[]::new);
    }
}
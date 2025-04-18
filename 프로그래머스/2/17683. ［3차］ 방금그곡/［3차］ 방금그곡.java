import java.util.*;

class Solution {
    final String[] sharp = {"C#", "D#", "E#", "F#", "G#", "A#", "B#", "c", "d", "e", "f", "g", "a", "b"};
    Map<String, String> sharpMap = new HashMap<>();
    final String NONE = "(None)";
    StringBuilder sb = new StringBuilder();
    
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        for (int i = 0; i < 7; i++) {
            sharpMap.put(sharp[i], sharp[i + 7]);
        }
        
        int max = 0;
        for (String mInfo : musicinfos) {
            String[] info = mInfo.split(",");
            int playTime = convertToSec(info[1]) - convertToSec(info[0]);
            if (isCorrect(info[3], playTime, m)) {
                if (max < playTime) {
                    max = playTime;
                    answer = info[2];
                }
            }
            sb.setLength(0);
        }
        
        return answer.isEmpty() ? NONE : answer;
    }
    
    private boolean isCorrect(String sheet, int playTime, String m) {
        String fullSheet = makeFullSheet(replaceToNote(sheet), playTime);
        String replacedM = replaceToNote(m);
        if (fullSheet.length() < replacedM.length()) return false;

        int r = replacedM.length() - 1;

        for (int i = 0; i <= r; i++) {
            sb.append(fullSheet.charAt(i));
        }

        while (r < fullSheet.length() - 1) {
            if (replacedM.equals(sb.toString())) return true;
            sb.append(fullSheet.charAt(++r));
            sb.deleteCharAt(0);
        }

        return replacedM.equals(sb.toString());
    }
    
    private String makeFullSheet(String original, int playTime) {
        for (int i = 0; i < playTime; i++) {
            sb.append(original.charAt(i % original.length()));
        }

        String res = sb.toString();
        sb.setLength(0);
        return res;
    }
    
    private String replaceToNote(String sheet) {
        for (int i = 0; i < sheet.length() - 1; i++) {
            char c = sheet.charAt(i);
            if (sheet.charAt(i + 1) == '#') {
                String tmp = sheet.substring(i, i + 2);
                sb.append(sharpMap.get(tmp));
                i++;
            } else sb.append(c);
        }
        if (sheet.charAt((sheet.length() - 1)) != '#') sb.append(sheet.charAt(sheet.length() - 1));

        String res = sb.toString();
        sb.setLength(0);
        return res;
    }
    
    private int convertToSec(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}
import java.util.*;

class Song {
    int play_time;
    String name;
    String melody;
    
    public Song(String name) {
        this.name = name;
    }
    
    public void setPlayTime(String start, String end) {
        this.play_time = convertToMin(end) - convertToMin(start);
    }
    
    public void setMelody(String melody) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0, idx = 0;
        while (cnt < this.play_time) {
            sb.append(melody.charAt((idx % melody.length())));
            idx++;
            if (melody.charAt((idx % melody.length())) == '#') {
                char tmp = sb.charAt(sb.length() - 1);
                sb.deleteCharAt(sb.length() - 1);
                sb.append(String.valueOf(tmp).toLowerCase());
                idx++;
            }
            cnt++;
        }
        
        this.melody = sb.toString();
    }
    
    private int convertToMin(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}

class Solution {
    public String solution(String m, String[] musicinfos) {
        Song[] songs = new Song[musicinfos.length];
        
        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");
            Song song = new Song(info[2]);
            song.setPlayTime(info[0], info[1]);
            song.setMelody(info[3]);
            songs[i] = song;
        }
        
        int pt = -1;
        String answer = "";
        String M = convert(m);
        for (int i = 0; i < songs.length; i++) {
            Song current = songs[i];
            for (int j = 0; j < current.melody.length() - M.length() + 1; j++) {
                String tmp = current.melody.substring(j, j + M.length());
                
                if (tmp.equals(M)) {
                    if (pt < current.play_time) {
                        pt = current.play_time;
                        answer = current.name;
                    }
                }
            }
        }
        
        return answer.isEmpty() ? "(None)" : answer;
    }
    
    private String convert(String m) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < m.length(); i++) {
            if (i < m.length() - 1 && m.charAt(i + 1) == '#') {
                sb.append(String.valueOf(m.charAt(i)).toLowerCase());
                i++;
            } else sb.append(m.charAt(i));
        }
        
        return sb.toString();
    }
}
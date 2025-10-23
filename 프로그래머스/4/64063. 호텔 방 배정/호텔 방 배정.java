import java.util.*;

class Solution {
    Map<Long, Long> nextRoom = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] answer = new long[len];
        
        for (int i = 0; i < len; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }
        
        return answer;
    }
    
    private long findEmptyRoom(long id) {
        if (!nextRoom.containsKey(id)) {
            nextRoom.put(id, id + 1);
            return id;
        }
        
        long empty = findEmptyRoom(nextRoom.get(id));
        
        nextRoom.put(id, empty);
        return empty;
    }
}
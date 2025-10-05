import java.util.*;

class Solution {
    int size;
    Map<Long, Long> roomInfo = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        size = room_number.length;
        long[] answer = new long[size];
        
        for (int i = 0; i < size; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }
        
        return answer;
    }
    
    private long findEmptyRoom(long rn) {
        if (!roomInfo.containsKey(rn)) {
            roomInfo.put(rn, rn + 1);
            return rn;
        }
        
        long nextRoom = roomInfo.get(rn);
        long emptyRoom = findEmptyRoom(nextRoom);
        
        roomInfo.put(rn, emptyRoom);
        return emptyRoom;
    }
}
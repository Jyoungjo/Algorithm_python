import java.util.*;

class Solution {
    Queue<Integer> bridge = new LinkedList<>();
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        makeBridge(bridge_length);
        return move(weight, truck_weights);
    }
    
    private int move(int weight, int[] trucks) {
        int limit = 0, time = 0;
        for (int truck : trucks) {
            while (true) {
                limit -= bridge.poll();
            
                if (limit + truck <= weight) {
                    bridge.add(truck);
                    limit += truck;
                    time++;
                    break;
                }
                
                bridge.add(0);
                time++;
            }
        }
        
        while (!bridge.isEmpty()) {
            bridge.poll();
            time++;
        }
        
        return time;
    }
    
    private void makeBridge(int len) {
        for (int i = 0; i < len; i++) bridge.add(0);
    }
}
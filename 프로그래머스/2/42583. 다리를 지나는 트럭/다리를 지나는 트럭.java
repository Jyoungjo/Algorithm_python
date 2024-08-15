import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Deque<Integer> bridge = new LinkedList<>();
        Queue<Integer> waiting = new LinkedList<>();

        for (int truckWeight : truck_weights) {
            waiting.add(truckWeight);
        }

        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        int currentWeight = 0;
        while (!waiting.isEmpty()) {
            int current = waiting.peek();
            currentWeight -= bridge.pollFirst();
            if (currentWeight + current <= weight) {
                currentWeight += current;
                bridge.add(waiting.poll());
            } else {
                bridge.add(0);
            }
            answer++;
        }

        return answer + bridge_length;
    }
}
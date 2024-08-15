import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> result = new LinkedList<>();

        for (String operation : operations) {
            String[] op = operation.split(" ");
            String command = op[0];
            int num = Integer.parseInt(op[1]);

            if (command.equals("I")) {
                minHeap.add(num);
                maxHeap.add(num);
                result.add(num);
            } else {
                if (num == -1) {
                    minHeap.poll();
                } else {
                    maxHeap.poll();
                }

                if (minHeap.isEmpty() || maxHeap.isEmpty()) {
                    minHeap.clear();
                    maxHeap.clear();
                }
            }
        }

        for (int x : result.stream().collect(Collectors.toList())) {
            if (!minHeap.contains(x) || !maxHeap.contains(x)) {
                result.remove(result.indexOf(x));
            }
        }

        if (result.isEmpty()) {
            return new int[]{0, 0};
        }

        int[] answer = new int[2];
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int n : result) {
            if (max < n) max = n;
            if (min > n) min = n;

            answer[0] = max;
            answer[1] = min;
        }

        return answer;
    }
}
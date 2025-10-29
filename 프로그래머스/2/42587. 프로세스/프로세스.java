import java.util.*;

class Process {
    int priority, idx;
    
    Process(int priority, int idx) {
        this.priority = priority;
        this.idx = idx;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Process> q = new LinkedList<>();
        
        for (int i = 0; i < priorities.length; i++) {
            q.add(new Process(priorities[i], i));
        }
        
        int turn = 1;
        while (!q.isEmpty()) {
            Process now = q.poll();
            if (canExecute(q, now)) {
                if (now.idx == location) return turn;
                else turn++;
            } else q.add(now);
        }
        
        return -1;
    }
    
    private boolean canExecute(Queue<Process> q, Process process) {
        for (Process p : q) {
            if (process.priority < p.priority) return false;
        }
        
        return true;
    }
}
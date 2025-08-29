import java.util.*;

class TimeConverter {
    public TimeConverter() {}
    
    int convert(String time) {
        String[] splited = time.split(":");
        return Integer.parseInt(splited[0]) * 60 + Integer.parseInt(splited[1]);
    }
}

class Reservation implements Comparable<Reservation> {
    int start, end;
    
    public Reservation(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo(Reservation o) {
        if (this.end > o.end) return 1;
        else if (this.end == o.end) return 0;
        else return -1;
    }
}

class Solution {
    public int solution(String[][] book_time) {
        List<Reservation> times = new ArrayList<>();
        TimeConverter converter = new TimeConverter();
        for (String[] bt : book_time) {
            int start = converter.convert(bt[0]), end = converter.convert(bt[1]);
            times.add(new Reservation(start, end));
        }
        
        Collections.sort(times, (o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });
        
        int answer = 0;
        Queue<Reservation> pq = new PriorityQueue<>();
        for (Reservation r : times) {
            if (pq.isEmpty()) {
                pq.add(r);
                answer++;
                continue;
            }
            
            if (pq.peek().end + 10 > r.start) {
                answer++;
                pq.add(r);
            } else {
                pq.poll();
                pq.add(r);
            }
        }
        
        return answer;
    }
}
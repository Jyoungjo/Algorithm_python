import java.util.*;
import java.util.stream.*;

class TimeConverter {
    
    TimeConverter() {}
    
    int convert(String time) {
        String[] splited = time.split(":");
        return Integer.parseInt(splited[0]) * 60 + Integer.parseInt(splited[1]);
    }
}

class Parking {
    private int in, out;
    
    public Parking(int in) {
        this.in = in;
    }
    
    public void setOut(int out) {
        this.out = out;
    }
    
    public int getIn() {
        return this.in;
    }
    
    public int getOut() {
        return this.out;
    }
    
    public int getParkingTime() {
        int tmp = this.out;
        if (tmp == 0) tmp = 1439;
        return tmp - this.in;
    }
}

class Solution {
    final String IN = "IN", OUT = "OUT";
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, List<Parking>> parkingMap = new HashMap<>();
        TimeConverter tc = new TimeConverter();
        
        for (String record : records) {
            String[] info = record.split(" ");
            if (info[2].equals(IN)) {
                parkingMap.putIfAbsent(info[1], new ArrayList<>());
                parkingMap.get(info[1]).add(new Parking(tc.convert(info[0])));
            } else {
                int size = parkingMap.get(info[1]).size();
                parkingMap.get(info[1]).get(size - 1).setOut(tc.convert(info[0]));
            }
        }
        
        List<String> carIds = new ArrayList<>(parkingMap.keySet());
        carIds.sort(Comparator.naturalOrder());
        
        List<Integer> answer = new ArrayList<>();
        for (String carId : carIds) {
            List<Parking> logs = parkingMap.get(carId);
            
            int accumulated = 0;
            for (Parking log : logs) accumulated += log.getParkingTime();
            
            if (accumulated <= fees[0]) answer.add(fees[1]);
            else answer.add(fees[1] + (int) Math.ceil((accumulated - fees[0]) * 1.0 / fees[2]) * fees[3]);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
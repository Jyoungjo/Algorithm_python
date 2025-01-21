import java.util.*;


class Solution {
    static int[] coords = new int[2501];
    static String[] values = new String[2501];
    static List<String> answer = new ArrayList<>();
    
    public String[] solution(String[] commands) {
        // update -> 해당 셀의 값을 변경한다.
        // 이 때, 병합된 셀 전부 같은 값으로 변경해야한다.
        // merge -> 셀을 병합한다.
        // 각 좌표를 병합하여 한 곳에 저장 -> 앞 좌표의 값을 덮어씌운다.
        // unmerge -> 병합된 셀을 분리한다.
        // 원래 좌표로 분해한다. -> 값을 커맨드 좌표에만 남기고 나머지 빈칸 만든다.
        for (int i = 1; i < 2501; i++) {
            coords[i] = i;
            values[i] = "";
        }
        
        for (String command : commands) {
            String[] c = command.split(" ");
            String keyword = c[0];
            
            if (keyword.equals("UPDATE")) {
                if (c.length == 4) {
                    // 좌표 업데이트
                    int r1 = Integer.parseInt(c[1]), c1 = Integer.parseInt(c[2]);
                    int converted = convert(r1, c1);
                    update(converted, c[3]);
                } else {
                    // 밸류 업데이트
                    update(c[1], c[2]);
                }
            } else if (keyword.equals("MERGE")) {
                int r1 = Integer.parseInt(c[1]), c1 = Integer.parseInt(c[2]);
                int r2 = Integer.parseInt(c[3]), c2 = Integer.parseInt(c[4]);
                merge(convert(r1, c1), convert(r2, c2));
            } else if (keyword.equals("UNMERGE")) {
                int r1 = Integer.parseInt(c[1]), c1 = Integer.parseInt(c[2]);
                unmerge(convert(r1, c1));
            } else {
                // PRINT
                int r1 = Integer.parseInt(c[1]), c1 = Integer.parseInt(c[2]);
                print(convert(r1, c1));
            }
        }
        
        return answer.toArray(String[]::new);
    }
    
    private void print(int n) {
        String val = values[find(n)];
        if (val.isEmpty()) answer.add("EMPTY");
        else answer.add(val);
    }
    
    private void unmerge(int n) {
        int root = find(n);
        String val = values[root];
        
        List<Integer> list = new ArrayList<>();
        
        for (int i = 2500; i >= 1; i--) {
            if (find(i) == root) {
                list.add(i);
                if (i == n) values[i] = val;
                else values[i] = "";
            }
        }
        
        for (int a : list) {
            coords[a] = a;
        }
    }
    
    private void merge(int n1, int n2) {
        if (n1 == n2) return;

        int root1 = find(n1);
        int root2 = find(n2);

        if (!values[root1].isEmpty()) coords[root2] = root1;
        else coords[root1] = root2;
    }
    
    private void update(String original, String newVal) {
        for (int i = 1; i < 2501; i++) {
            if (values[i].equals(original)) values[i] = newVal;
        }
    }
    
    private void update(int n, String newValue) {
        int root = find(n);
        values[root] = newValue;
    }
    
    private int find(int n) {
        if (coords[n] == n) return n;
        return find(coords[n]);
    }
    
    private int convert(int r, int c) {
        return 50 * (r - 1) + c;
    }
}
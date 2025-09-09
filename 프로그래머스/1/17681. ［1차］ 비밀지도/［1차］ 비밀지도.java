import java.util.*;

class Converter {
    
    static final String ZERO = "0";
    
    Converter() {}
    
    String[] convert(int n, int[] arr1, int[] arr2) {
        String[] newArr = new String[n];
        StringBuilder sb;
        for (int i = 0; i < n; i++) {
            int tmp = arr1[i] | arr2[i];
            sb = new StringBuilder(Integer.toBinaryString(tmp));
            while (sb.length() < n) sb.insert(0, ZERO);
            newArr[i] = sb.toString();
        }
        
        return newArr;
    }
}

class Solution {
    final String ZERO = "0", ONE = "1", WALL = "#", BLANK = " ";
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        Converter converter = new Converter();
        String[] password = converter.convert(n, arr1, arr2);
        
        for (int i = 0; i < n; i++) {
            password[i] = password[i].replace(ONE, WALL);
            password[i] = password[i].replace(ZERO, BLANK);
        }
        
        return password;
    }
}
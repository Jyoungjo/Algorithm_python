class Solution {
    public String solution(int n) {
        /*
            자릿수 별로 나오는 숫자 개수 패턴이 다름
            1의 자리: 1~3 len = 3 (1 2 4)
            10의 자리: 4~12 len = 9 (1*3 2*3 4*3)
            100의 자리: 13~40 len = 27 (1*9 2*9 4*9)
            1000의 자리: 41~122 len = 81 (1*27 2*27 4*27)
        */
        StringBuilder sb = new StringBuilder();
        while (n > 3) {
            int remainder = n % 3;
            if (remainder == 0) {
                sb.append(4);
                n = (n / 3) - 1;
            } else {
                sb.append(n % 3);
                n /= 3;
            }
        }
        
        sb.append(n == 3 ? 4 : n);
        return sb.reverse().toString();
    }
}
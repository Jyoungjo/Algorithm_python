import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        // 1. 수식 중 X가 없는 수식 찾기
        // 2. X가 있다면 해당 수식 킵
        // 3. 없다면 해당 수식으로 진법 확인
        // 4. 킵해둔 수식에서 X 값 유추하기 -> 수식 계산값이 작은 순으로 분석
        int size = expressions.length;
        Deque<String> list = new ArrayDeque<>();
        Set<Integer> dSet = new HashSet<>(List.of(2,3,4,5,6,7,8,9));
        for (int i = 0; i < size; i++) {
            String e = expressions[i];

            Set<Integer> set = new HashSet<>();
            int max = 0;

            String[] ex = e.split(" ");

            // 자릿수로 진법 확인
            for (int j = 0; j < 3; j++) {
                if (ex[j * 2].equals("X")) {
                    list.add(e);
                    break;
                }
                max = Math.max(max, checkDigit(ex[j * 2]));
            }

            for (int j = max; j < 10; j++) {
                set.add(j);
            }

            // 수식으로 진법 확인
            Set<Integer> tmpSet = new HashSet<>();
            if (!ex[4].equals("X")) {
                for (int s : set) {
                    if (!calRadix(ex, s)) tmpSet.add(s);
                }
            }

            set.removeAll(tmpSet);
            dSet.retainAll(set);
        }

        int l = list.size();
        for (int i = 0; i < l; i++) {
            String[] sArr = list.pollFirst().split(" ");
            String tmp = "";
            int cnt = 0;

            for (int a : dSet) {
                int convertedNum = sArr[1].equals("+") ?
                        Integer.parseInt(sArr[0], a) + Integer.parseInt(sArr[2], a)
                        : Integer.parseInt(sArr[0], a) - Integer.parseInt(sArr[2], a);

                if (!tmp.equals(Integer.toString(convertedNum, a))) {
                    tmp = Integer.toString(convertedNum, a);
                    cnt++;
                    if (cnt > 1) break;
                }
            }

            if (cnt > 1) {
                list.addLast(convertToString(sArr, "?"));
                continue;
            }

            list.addLast(convertToString(sArr, tmp));
        }

        return list.toArray(new String[0]);
    }

    private String convertToString(String[] sArr, String result) {
        return sArr[0] + " " + sArr[1] + " " + sArr[2] + " " + sArr[3] + " " + result;
    }

    private boolean calRadix(String[] ex, int sn) {
        try {
            int n1 = Integer.parseInt(ex[0], sn);
            int n2 = Integer.parseInt(ex[2], sn);
            int n3 = Integer.parseInt(ex[4], sn);

            if (ex[1].equals("+")) return n1 + n2 == n3;
            else return n1 - n2 == n3;
        } catch(RuntimeException e) {
            System.out.println(e);
        }
        return false;
    }

    private int checkDigit(String num) {
        int res = 0;
        for (char c : num.toCharArray()) {
            int digit = c - '0';
            res = Math.max(res, digit + 1);
        }
        return res;
    }
}
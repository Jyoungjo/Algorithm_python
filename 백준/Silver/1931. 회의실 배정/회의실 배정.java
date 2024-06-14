import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfMeeting = sc.nextInt();
        int[][] timeOfMeeting = new int[numOfMeeting][2];
        int cnt = 0;

        for (int i = 0; i < numOfMeeting; i++) {
            timeOfMeeting[i][0] = sc.nextInt();
            timeOfMeeting[i][1] = sc.nextInt();
        }

        Arrays.sort(timeOfMeeting, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        int time = 0;
        for (int i = 0; i < numOfMeeting; i++) {
            if (timeOfMeeting[i][0] >= time) {
                time = timeOfMeeting[i][1];
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}
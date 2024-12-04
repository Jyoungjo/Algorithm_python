import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    n = 팀 개수, k = 문제 개수, t = 팀 id, m = 엔트리 개수
     */
    static int T, n, k, t, m;
    static List<int[]> scores;

    static class Team {
        int teamId;
        int[] scoreList;
        int submitCnt;
        int lastSubmit;
        int totScore;
    }

    public static int solution() {
        // 여러 풀이 중 최고 점수가 해당 문제 최종 점수 -> 한 번도 제출하지 않으면 0점
        // 팀의 최종 점수는 점수 총합, 순위는 팀보다 높은 점수 받은 팀 + 1
        // 점수가 동일 -> 제출 횟수 적은 팀
        // 점수, 제출 횟수 동일 -> 마지막 제출 시간 빠른 팀
        Team[] teams = new Team[n];

        int submitTime = 0;
        for (int[] score : scores) {
            int teamId = score[0], qNo = score[1], qScore = score[2];

            if (teams[teamId - 1] == null) {
                teams[teamId - 1] = new Team();
                teams[teamId - 1].teamId = teamId;
                teams[teamId - 1].scoreList = new int[k + 1];
            }

            teams[teamId - 1].scoreList[qNo] = Math.max(qScore, teams[teamId - 1].scoreList[qNo]);
            teams[teamId - 1].submitCnt++;
            teams[teamId - 1].lastSubmit = submitTime++;
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 1; j <= k; j++) {
                sum += teams[i].scoreList[j];
            }
            teams[i].totScore = sum;
        }

        Arrays.sort(teams, (o1, o2) -> {
            if (o1.totScore != o2.totScore) {
                return o2.totScore - o1.totScore;
            }

            if (o1.submitCnt != o2.submitCnt) {
                return o1.submitCnt - o2.submitCnt;
            }

            return o1.lastSubmit - o2.lastSubmit;
        });

        for (int i = 0; i < n; i++) {
            if (teams[i].teamId == t) {
                return i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            scores = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int[] score = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                scores.add(score);
            }

            System.out.println(solution());
        }
    }
}
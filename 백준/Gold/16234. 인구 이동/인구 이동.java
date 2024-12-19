import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] countries;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static class Country {
        int[] coord;
        int population;

        public Country(int[] coord, int population) {
            this.coord = coord;
            this.population = population;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static int solution() {
        /*
            인구 이동이 발생하는 일수 구하기 (최대 2000일)

            1. 인접한 국가의 인구 차이가 L <= x <= R 이라면 국경선 오픈
            2. 조건에 맞게 국경선 다 열렸으면 인구 이동 시작
            3. 국경선 열린 국가끼리 연합
            4. 연합의 각 칸 인구수 = 연합 총 인구수 / 연합 칸 개수 (소수점 버림 -> ceil)
            5. 1번 부터 반복
         */
        int answer = 0;

        while (answer <= 2000) {
            List<List<Country>> adjCountryList = new ArrayList<>();
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    adjCountryList.add(searchAdjCountries(new int[]{i, j}, visited));
                }
            }

            if (adjCountryList.stream().allMatch(a -> a.size() == 1)) return answer;

            for (List<Country> adjCountry : adjCountryList) {
                if (adjCountry.size() == 1) continue;

                int pSum = adjCountry.stream()
                        .mapToInt(Country::getPopulation)
                        .sum();
                int newPop = pSum / adjCountry.size();

                for (Country country : adjCountry) {
                    int[] cPos = country.coord;
                    countries[cPos[0]][cPos[1]] = newPop;
                }
            }

            answer++;
        }

        return answer;
    }

    private static List<Country> searchAdjCountries(int[] pos, boolean[][] visited) {
        List<Country> result = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(pos);
        visited[pos[0]][pos[1]] = true;
        result.add(new Country(pos, countries[pos[0]][pos[1]]));

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                int[] nPos = {ny, nx};

                if (0 <= ny && ny < N && 0 <= nx && nx < N && !visited[ny][nx]) {
                    int populationNum = Math.abs(countries[y][x] - countries[ny][nx]);

                    if (L <= populationNum && populationNum <= R) {
                        visited[ny][nx] = true;
                        q.offer(nPos);
                        result.add(new Country(nPos, countries[ny][nx]));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        countries = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }
}
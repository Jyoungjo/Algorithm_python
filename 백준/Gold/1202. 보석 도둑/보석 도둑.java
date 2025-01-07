import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Jewel {
        int mass;
        int value;

        public Jewel(int mass, int value) {
            this.mass = mass;
            this.value = value;
        }
    }
    static int N, K;
    static Jewel[] jewels;
    static int[] bags;

    public static long solution() {
        Arrays.sort(bags);
        Arrays.sort(jewels, (o1, o2) -> {
            if (o1.mass == o2.mass) return o2.value - o1.value;
            return o1.mass - o2.mass;
        });

        long cnt = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && bags[i] >= jewels[j].mass) pq.add(jewels[j++].value);
            if (!pq.isEmpty()) cnt += pq.poll();
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }

        bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution());
    }
}
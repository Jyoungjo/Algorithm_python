import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int x, y, next;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void connect(int next) {
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return this.x - o.x;
    }
}

class Main {
    int N, answer = 0;
    boolean[] visited;
    List<Node> wormholes = new ArrayList<>();

    private void solution() throws Exception {
        readInput();
        findWormhole();
        System.out.println(answer);
    }

    private void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            wormholes.add(new Node(x, y));
        }
    }

    private void findWormhole() {
        Collections.sort(wormholes);
        setWormhole(0, 0);
    }

    private void setWormhole(int cnt, int idx) {
        if (cnt == N) {
            for (int i = 0; i < N; i++) {
                if (hasCycle(i)) {
                    answer++;
                    return;
                }
            }
        }

        for (int i = idx; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            for (int j = i + 1; j < N; j++) {
                if (visited[j]) continue;

                visited[j] = true;
                wormholes.get(i).connect(j);
                wormholes.get(j).connect(i);
                setWormhole(cnt + 2, i + 1);
                visited[j] = false;
            }
            visited[i] = false;
        }
    }

    private boolean hasCycle(int idx) {
        boolean[] use = new boolean[N];

        while (true) {
            if (use[idx]) return true;

            use[idx] = true;
            int x = wormholes.get(wormholes.get(idx).next).x;
            int y = wormholes.get(wormholes.get(idx).next).y;
            idx = searchNext(x, y);
            if (idx == -1) return false;
        }
    }

    private int searchNext(int x, int y) {
        for (int i = 0; i < N; i++) {
            if (wormholes.get(i).y == y && wormholes.get(i).x > x) return i;
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
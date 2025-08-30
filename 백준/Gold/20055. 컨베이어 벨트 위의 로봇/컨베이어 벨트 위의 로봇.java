import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Conveyor {
    private int durability;
    private boolean onRobot;

    Conveyor(int durability) {
        this.durability = durability;
        this.onRobot = false;
    }

    int getDurability() { return this.durability; }
    boolean getOnRobot() { return this.onRobot; }

    void putRobot() { this.onRobot = true; }
    void unloadRobot() { this.onRobot = false; }
    void decreaseDurability() { this.durability--; }
}

class Main {
    int N, K;
    Conveyor[] conveyors;
    int cnt = 0;

    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        conveyors = Arrays.stream(br.readLine().split(" "))
                .map(d -> new Conveyor(Integer.parseInt(d)))
                .toArray(Conveyor[]::new);

        int answer = 0;
        while (cnt < K) {
            answer++;

            // 회전
            rotate();
            // 로봇 이동
            move();
            // 로봇 올림
            put(0);
        }

        System.out.println(answer);
    }

    private void rotate() {
        Conveyor tmp = conveyors[conveyors.length - 1];
        for (int i = conveyors.length - 1; i >= 1; i--) conveyors[i] = conveyors[i - 1];
        conveyors[0] = tmp;

        conveyors[0].unloadRobot();
        conveyors[N - 1].unloadRobot();
    }

    private void move() {
        for (int i = N - 1; i >= 1; i--) {
            if (!conveyors[i].getOnRobot() && conveyors[i - 1].getOnRobot() && conveyors[i].getDurability() >= 1) {
                conveyors[i - 1].unloadRobot();
                put(i);
            }
        }
        conveyors[N - 1].unloadRobot();
    }

    private void put(int idx) {
        if (conveyors[idx].getDurability() >= 1) {
            conveyors[idx].putRobot();
            conveyors[idx].decreaseDurability();
            if (conveyors[idx].getDurability() == 0) cnt++;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
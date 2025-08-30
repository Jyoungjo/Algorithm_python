import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Lecture implements Comparable<Lecture> {
    int start, end;

    Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture o) {
        return Integer.compare(this.end, o.end);
    }
}

class Main {
    int N;
    Lecture[] lectures;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        lectures = new Lecture[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()), T = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(S, T);
        }

        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });

        int answer = 0;
        Queue<Lecture> pq = new PriorityQueue<>();
        for (Lecture lecture : lectures) {
            if (pq.isEmpty()) {
                answer++;
                pq.add(lecture);
                continue;
            }

            if (pq.peek().end <= lecture.start) pq.poll();
            else answer++;

            pq.add(lecture);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
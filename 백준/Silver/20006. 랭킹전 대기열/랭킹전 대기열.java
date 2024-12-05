import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Player {
        int level;
        String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }
    }
    static int p, m;
    static Player[] players;

    public static String solution() {
        List<List<Player>> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            Player player = players[i];

            if (rooms.isEmpty()) {
                List<Player> newRoom = new ArrayList<>();
                newRoom.add(player);
                rooms.add(newRoom);
                continue;
            }

            boolean isJoined = false;
            for (List<Player> room : rooms) {
                if (room.size() < m && Math.abs(player.level - room.get(0).level) <= 10) {
                    room.add(player);
                    isJoined = true;
                    break;
                }
            }

            if (!isJoined) {
                List<Player> otherRoom = new ArrayList<>();
                otherRoom.add(player);
                rooms.add(otherRoom);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (List<Player> room : rooms) {
            room.sort(Comparator.comparing(o -> o.nickname));

            sb.append(room.size() == m ? "Started!" : "Waiting!").append("\n");

            for (Player player : room) {
                sb.append(player.level).append(" ").append(player.nickname).append("\n");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        players = new Player[p];
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            players[i] = new Player(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        System.out.print(solution());
    }
}
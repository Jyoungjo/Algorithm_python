import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String text;
    static int M;
    static String[] commands;

    public static String solution() {
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            left.push(text.charAt(i));
        }

        for (String command : commands) {
            switch (command.charAt(0)) {
                case 'L' : {
                    if (!left.isEmpty()) right.push(left.pop());
                    break;
                }
                case 'D' : {
                    if (!right.isEmpty()) left.push(right.pop());
                    break;
                }
                case 'B' : {
                    if (!left.isEmpty()) left.pop();
                    break;
                }
                case 'P' : {
                    left.push(command.charAt(2));
                    break;
                }
            }
        }

        while (!right.isEmpty()) left.push(right.pop());

        StringBuilder sb = new StringBuilder();
        left.forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        text = br.readLine();
        M = Integer.parseInt(br.readLine());
        commands = new String[M];
        for (int i = 0; i < M; i++) {
            commands[i] = br.readLine();
        }

        System.out.println(solution());
    }
}
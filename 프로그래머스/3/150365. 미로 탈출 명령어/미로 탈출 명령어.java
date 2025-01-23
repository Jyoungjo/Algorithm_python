import java.util.*;


class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        
        int diff = Math.abs(x - r) + Math.abs(y - c); // 맨해튼 거리
        k -= diff; // 맨해튼 거리만큼 이동
        if (k < 0 || k % 2 == 1) return "impossible"; // 만약 이동했는데 k가 음수이거나 홀수일 경우 다음 좌표로 이동할 수 없기 때문에 불가능
        // u: 0, d: 1, l: 2, r: 3
        int[] dir = new int[]{0, 0, 0, 0};
        if (x > r) dir[0] += x - r;
        else dir[1] += r - x;
        if (y > c) dir[2] += y - c;
        else dir[3] += c - y;
        
        /*
            사전순으로 빠른 경로를 출력하기 위해서 d -> l -> r -> u 순으로 이동하는 것이 제일 빠르다.
            남은 k에 따라 더 진행할 지, 멈출지 결정.
        */
        
        answer += "d".repeat(dir[1]);
        int d = Math.min(k / 2, n - (x + dir[1]));
        answer += "d".repeat(d);
        dir[0] += d; // dir[1] 만큼 진행하고도 k가 남아서 + n이 길어서 아래로 더 진행해야할 경우, 목표 지점까지 다시 가야 하기 때문에 u의 횟수를 d만큼 늘린다.
        k -= 2 * d; // 내려갔다가 올라가야하므로 같은 횟수만큼 빼준다.
        
        answer += "l".repeat(dir[2]);
        int l = Math.min(k / 2, y - dir[2] - 1);
        answer += "l".repeat(l);
        dir[3] += l; // dir[2] 만큼 진행하고도 k가 남아서 + m이 길어서 왼쪽으로 더 진행해야할 경우, 목표 지점까지 다시 가야 하기 때문에 r의 횟수를 l만큼 늘린다.
        k -= 2 * l; // 왼쪽 갔다가 오른쪽가야하므로 같은 횟수만큼 빼준다.
        
        // 그럼에도 k가 남았다면 k의 절반만큼 rl 진행 -> 아마 위의 절차를 진행했다면 왼쪽 아래에 위치해있을텐데 여기서 k의 절반만큼 rl을 해주면 남은 k는 오로지 목표지점으로 갈 수 있는 횟수만큼 남게 된다.
        answer += "rl".repeat(k / 2);
        answer += "r".repeat(dir[3]);
        answer += "u".repeat(dir[0]);
        
        return answer;
    }
}
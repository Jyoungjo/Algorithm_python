# 백준 1697 숨바꼭질 - 실버 1

# N, K = 10만 -> 완탐 시도
# 수빈이가 갈 수 있는 경우의 수 3가지 (N-1, N+1, 2N)
# 각 진행별로 3번씩 반복 -> 가장 빠른 시간(최단거리) 찾는 문제이므로 bfs 진행
import sys
from collections import deque
input = sys.stdin.readline


def bfs(N, K, time):
    q = deque([N])
    while q:
        x = q.popleft()
        op = [x - 1, x + 1, 2 * x]
        if x == K:
            return time[x]
        for i in range(3):
            nx = op[i]
            if 0 <= nx <= 10**5 and not time[nx]:
                time[nx] = time[x] + 1
                q.append(nx)
    return 0


def main():
    N, K = map(int, input().split())
    time = [0] * (10**5 + 1)
    return print(bfs(N, K, time))


main()
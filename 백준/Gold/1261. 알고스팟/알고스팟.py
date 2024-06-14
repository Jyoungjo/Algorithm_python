# 백준 1261 알고스팟 - 골드 4

# 1인 지점만 뚫고 나아가는데 최소 비용으로 나아가야함 -> 다익스트라
import sys
from heapq import heappush, heappop
input = sys.stdin.readline


def solution(N, M, maps):
    INF = float('inf')
    dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]
    costs = [[INF] * N for _ in range(M)]
    costs[0][0] = maps[0][0]
    q = [(costs[0][0], (0, 0))]
    while q:
        cost, now = heappop(q)
        y, x = now
        if costs[y][x] < cost:
            continue

        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if 0 <= ny < M and 0 <= nx < N and costs[ny][nx] > maps[ny][nx] + cost:
                costs[ny][nx] = maps[ny][nx] + cost
                heappush(q, (costs[ny][nx], (ny, nx)))
    return costs[-1][-1]


def main():
    N, M = map(int, input().split())
    maps = [[int(x) for x in input().strip()] for _ in range(M)]
    return print(solution(N, M, maps))


main()
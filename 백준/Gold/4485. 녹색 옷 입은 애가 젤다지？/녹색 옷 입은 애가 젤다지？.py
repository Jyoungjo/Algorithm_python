# 백준 4485 녹색 온 입은 애가 젤다지? - 골드 4

# 사방탐색 + 다익스트라
import sys
from heapq import heappush, heappop
input = sys.stdin.readline


def find_min_cost(N, cave):
    INF = float('inf')
    dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]
    costs = [[INF] * N for _ in range(N)]
    costs[0][0] = cave[0][0]
    q = [(costs[0][0], (0, 0))]
    while q:
        cost, now = heappop(q)
        y, x = now
        if costs[y][x] < cost:
            continue

        for d in range(4):
            ny, nx = y + dy[d], x + dx[d]
            if 0 <= ny < N and 0 <= nx < N and costs[ny][nx] > cave[ny][nx] + cost:
                costs[ny][nx] = cave[ny][nx] + cost
                heappush(q, (costs[ny][nx], (ny, nx)))
    return costs[-1][-1]


def solution():
    num = 1
    result = []
    while True:
        N = int(input())
        if N == 0:
            break
        cave = [list(map(int, input().split())) for _ in range(N)]
        result.append((num, find_min_cost(N, cave)))
        num += 1
    return result


def main():
    return print('\n'.join(f'Problem {num}: {ans}' for num, ans in solution()))


main()
# 백준 1916 최소비용 구하기 - 골드 5

# 다익스트라 알고리즘으로 start부터 end까지 비용 구하기
import sys
from heapq import heappush, heappop
input = sys.stdin.readline


def find_min_cost(N, bus_routes, start, end):
    INF = float('inf')
    costs = [INF] * (N+1)
    costs[start] = 0
    q = [(0, start)]
    while q:
        cost, now = heappop(q)
        if costs[now] < cost:
            continue

        for nxt, c in bus_routes[now]:
            if costs[nxt] > cost + c:
                costs[nxt] = cost + c
                heappush(q, (costs[nxt], nxt))
    return costs[end]


def make_bus_routes(N, M):
    arr = [[] for _ in range(N+1)]
    for _ in range(M):
        s, e, c = map(int, input().split())
        arr[s].append((e, c))
    return arr


def main():
    N = int(input())
    M = int(input())
    bus_routes = make_bus_routes(N, M)
    start, end = map(int, input().split())
    return print(find_min_cost(N, bus_routes, start, end))


main()
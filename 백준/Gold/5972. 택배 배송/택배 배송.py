# 백준 5972 택배 배송 - 골드 5


import sys
from heapq import heappush, heappop
input = sys.stdin.readline


def make_roads(N, M):
    res = [[] for _ in range(N + 1)]
    for _ in range(M):
        s, e, d = map(int, input().split())
        res[s].append((e, d))
        res[e].append((s, d))
    return res


def solution(N, roads):
    INF = float('inf')
    distance = [INF] * (N+1)
    q = [(0, 1)]
    while q:
        dist, now = heappop(q)
        if distance[now] < dist:
            continue

        for nxt, d in roads[now]:
            if distance[nxt] > dist + d:
                distance[nxt] = dist + d
                heappush(q, (distance[nxt], nxt))
    return distance[N]


def main():
    N, M = map(int, input().split())
    roads = make_roads(N, M)
    return print(solution(N, roads))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()
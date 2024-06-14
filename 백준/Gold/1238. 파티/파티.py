# 백준 1238 파티 - 골드 3

# 유향 그래프이긴 한데 사이클이 존재함
# X 위치에서 나가는거 한번, 들어오는거 한번 다익스트라 써서 두개 값 합치기
import sys
from heapq import heappush, heappop
input = sys.stdin.readline

INF = float('inf')


def find_max_val(N, home, party):
    max_val = 0
    for i in range(1, N+1):
        max_val = max(max_val, home[i] + party[i])
    return max_val


def dijkstra(N, X, village):
    times = [INF] * (N + 1)
    times[X] = 0
    q = [(0, X)]
    while q:
        t, now = heappop(q)
        if times[now] < t:
            continue

        for nxt, time in village[now]:
            if times[nxt] > time + t:
                times[nxt] = time + t
                heappush(q, (times[nxt], nxt))
    return times


def solution(N, X, from_X, to_X):
    home, party = dijkstra(N, X, from_X), dijkstra(N, X, to_X)
    return find_max_val(N, home, party)


def make_village(N, M):
    from_X, to_X = [[] for _ in range(N+1)], [[] for _ in range(N+1)]
    for _ in range(M):
        A, B, T = map(int, input().split())
        from_X[A].append((B, T))
        to_X[B].append((A, T))
    return from_X, to_X


def main():
    N, M, X = map(int, input().split())
    from_X, to_X = make_village(N, M)
    return print(solution(N, X, from_X, to_X))


main()
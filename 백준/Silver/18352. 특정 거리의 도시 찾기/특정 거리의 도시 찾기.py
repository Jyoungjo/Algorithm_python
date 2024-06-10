# 백준 18352 특정 거리의 도시 찾기 - 실버 2

# starting point에서 부터 bfs 진행
# 진행하면서 거리가 K에 도달한 모든 point 기록
import sys
from collections import deque
input = sys.stdin.readline


def bfs(K, X, graph, visited):
    res = []
    q = deque()
    q.append((X, 0))
    visited[X] = True
    while q:
        cur, dist = q.popleft()
        if dist == K:
            res.append(cur)
            continue
        for next_target in graph[cur]:
            if not visited[next_target]:
                q.append((next_target, dist + 1))
                visited[next_target] = True
    return res


def main():
    N, M, K, X = map(int, input().split())
    graph = [[] for _ in range(N+1)]
    visited = [False]*(N+1)
    for _ in range(M):
        start, end = map(int, input().split())
        graph[start].append(end)
    ans = sorted(bfs(K, X, graph, visited))
    return print('\n'.join(map(str, ans))) if ans else print(-1)


main()
# 프로그래머스 49189 가장 먼 노드 LV.3
from collections import deque


def solution(n, vertex):
    graph = [[] for _ in range(n + 1)]
    for a, b in vertex:
        graph[a].append(b)
        graph[b].append(a)

    visited = [0] * (n + 1)
    q = deque()
    q.append(1)
    while q:
        start = q.popleft()
        for num in graph[start]:
            if visited[num] == 0 and num != 1:
                visited[num] = visited[start] + 1
                q.append(num)
                
    return visited.count(max(visited))
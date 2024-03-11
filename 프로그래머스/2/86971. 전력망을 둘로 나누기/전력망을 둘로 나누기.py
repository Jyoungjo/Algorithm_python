# 프로그래머스 86971 전력망을 둘로 나누기 LV.2
from collections import deque

def solution(n, wires):
    nodes = [[] for _ in range(n + 1)]
    for a, b in wires:
        nodes[a].append(b)
        nodes[b].append(a)

    def bfs(start):
        visited = [0] * (n + 1)
        q = deque([start])
        visited[start] = 1
        cnt = 1
        while q:
            s = q.popleft()
            for i in nodes[s]:
                if not visited[i]:
                    q.append(i)
                    visited[i] = 1
                    cnt += 1
        return cnt

    res = n
    for a, b in wires:
        nodes[a].remove(b)
        nodes[b].remove(a)

        res = min(abs(bfs(a) - bfs(b)), res)

        nodes[a].append(b)
        nodes[b].append(a)

    return res
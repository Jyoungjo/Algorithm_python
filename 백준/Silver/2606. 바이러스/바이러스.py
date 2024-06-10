# 백준 2606 바이러스 - 실버 3

# bfs 진행하면서 q에 append 될 때마다 카운트 1씩 증가
import sys
from collections import deque
input = sys.stdin.readline


def bfs(computers, visited):
    cnt = 0
    q = deque()
    q.append(1)
    visited[1] = True
    while q:
        cur = q.popleft()
        for nxt in computers[cur]:
            if not visited[nxt]:
                q.append(nxt)
                visited[nxt] = True
                cnt += 1
    return cnt

def main():
    n = int(input())
    m = int(input())
    computers = [[] for _ in range(n+1)]
    visited = [False] * (n+1)
    for _ in range(m):
        start, end = map(int, input().split())
        computers[start].append(end)
        computers[end].append(start)
    return print(bfs(computers, visited))


main()
from collections import deque


def solution(n, computers):
    answer = 0
    visited = [False] * n
    
    def bfs(computer):
        visited[computer] = True
        q = deque()
        q.append(computer)
        while q:
            target = q.popleft()
            visited[target] = True
            for i in range(n):
                if computers[target][i] == 1 and target != i and not visited[i]:
                    q.append(i)
                    
    for c in range(n):
        if not visited[c]:
            bfs(c)
            answer += 1
    
    return answer
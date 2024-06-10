# 백준 21937 작업 - 실버 1

# X의 작업을 끝내기 위해서는 X 이전의 작업이 선종료가 되어야 한다.
# 그럼 역으로 X부터 시작하는 DFS 진행해서 끝 노드에 다다르면 종료
import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline
ans = 0


def dfs(works, visited, start):
    global ans

    visited[start] = True
    for nxt in works[start]:
        if not visited[nxt]:
            ans += 1
            dfs(works, visited, nxt)


def main():
    N, M = map(int, input().split())
    works = [[] for _ in range(N + 1)]
    visited = [False] * (N + 1)
    for _ in range(M):
        start, end = map(int, input().split())
        works[end].append(start)
    X = int(input())
    dfs(works, visited, X)
    return print(ans)


main()
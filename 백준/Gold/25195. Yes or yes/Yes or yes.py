# 백준 25195 Yes or yes - 골드 4

# dfs/bfs 둘 다 풀 수 있는 문제
# 그래프를 순회하면서 팬클럽 곰곰이가 있는 지점에 도착했는지 여부에 따라 True/False 기록
# True가 하나라도 있으면 진행이 가능하단 뜻이므로 yes 아니라면 Yes
import sys
sys.setrecursionlimit(1000000)
input = sys.stdin.readline

result = []


def dfs(destinations, visited, existed_points, start):
    global result

    if start in existed_points:
        result.append(False)
        return

    if not destinations[start]:
        result.append(True)
        return

    visited[start] = True
    for dest in destinations[start]:
        if not visited[dest]:
            dfs(destinations, visited, existed_points, dest)


def main():
    N, M = map(int, input().split())
    destinations = [[] for _ in range(N+1)]
    visited = [False] * (N+1)
    for _ in range(M):
        start, end = map(int, input().split())
        destinations[start].append(end)
    S = int(input())
    existed_point = list(map(int, input().split()))
    dfs(destinations, visited, existed_point, 1)
    return print('Yes') if all(not r for r in result) else print('yes')


main()
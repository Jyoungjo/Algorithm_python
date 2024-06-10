# 백준 1240 노드사이의 거리 - 골드 5

# 주어진 트리를 양방향으로 연결하고 이를 dfs로 탐색하면서 내가 찾는 end까지 도달하면 거기에 해당하는 result 리턴
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

result = 0
flag = False


def make_the_tree(arr, N):
    for _ in range(N - 1):
        start, end, dist = map(int, input().split())
        arr[start].append((end, dist))
        arr[end].append((start, dist))
    return arr


def dfs(tree, nodes, visited, cnt):
    global result, flag

    start, end = nodes
    visited[start] = True

    if start == end:
        flag = True
        result = cnt
        return

    for val in tree[start]:
        s, d = val
        if not visited[s]:
            dfs(tree, (s, end), visited, cnt + d)
            if flag:
                return


def main():
    global result, flag

    N, M = map(int, input().split())
    tree = make_the_tree([[] for _ in range(N+1)], N)
    answer = []
    for _ in range(M):
        visited = [False] * (N+1)
        nodes = map(int, input().split())
        dfs(tree, nodes, visited, 0)
        answer.append(result)
        result, flag = 0, False
    return print('\n'.join(map(str, answer)))


main()
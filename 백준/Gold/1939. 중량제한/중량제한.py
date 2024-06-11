# 백준 1939 중량제한 - 골드 3

# 주어진 섬 위치 사이에 다리를 건널 수 있는 최대 중량 구하기
# bfs로 탐색하면서 다음 섬으로 이동할 때, 다리를 건널 수 있나 확인 -> 중량 확인
# 중량 범위가 크므로 이분탐색 사용해서 물품 중량 조절
import sys
from collections import deque
input = sys.stdin.readline


def binary_search(N, bridge, start, end):
    left, right = 1, 10**9
    while left <= right:
        mid = (left + right) // 2
        visited = [False] * (N + 1)
        if bfs(start, end, mid, bridge, visited):
            left = mid + 1
        else:
            right = mid - 1
    return right


def bfs(start, end, weight, bridge, visited):
    q = deque([start])
    visited[start] = True
    while q:
        current = q.popleft()
        if current == end:
            return True
        for nxt, lim in bridge[current]:
            if not visited[nxt] and weight <= lim:
                q.append(nxt)
                visited[nxt] = True
    return False


def main():
    N, M = map(int, input().split())
    bridge = [[] for _ in range(N + 1)]
    for _ in range(M):
        start, end, limit = map(int, input().split())
        bridge[start].append([end, limit])
        bridge[end].append([start, limit])
    begin, finish = map(int, input().split())
    return print(binary_search(N, bridge, begin, finish))


main()
# 백준 10282 해킹 - 골드 4

# 연결된 컴퓨터들의 정보를 파악해서 기록 후 감염된 컴퓨터, 시간 리턴
# 다익스트라 사용
import sys
from heapq import heappush, heappop
input = sys.stdin.readline

INF = float('inf')


def find_max_val(n, times):
    t = 0
    for i in range(n, -1, -1):
        if times[i] != INF:
            t = max(t, times[i])
    return t


def count_hacked_computers(dependency, n, c):
    times = [INF] * (n + 1)
    times[c] = 0
    q = [(0, c)]
    cnt = 0
    while q:
        time, cur = heappop(q)
        if times[cur] < time:
            continue
        cnt += 1

        for nxt, t in dependency[cur]:
            if times[nxt] > time + t:
                times[nxt] = time + t
                heappush(q, (time + t, nxt))
    return cnt, find_max_val(n, times)


def check_dependency(n, d):
    links = [[] for _ in range(n + 1)]
    for i in range(d):
        a, b, s = map(int, input().split())
        links[b].append((a, s))
    return links


def solution(T):
    result = []
    for i in range(T):
        n, d, c = map(int, input().split())
        dependency = check_dependency(n, d)
        result.append(count_hacked_computers(dependency, n, c))
    return result


def main():
    T = int(input())
    return print('\n'.join(' '.join(map(str, ans)) for ans in solution(T)))


main()
# 백준 10473 인간 대포 - 골드 2

# 걷는거랑 대포타고 가는 거랑 거리 비교해서 최소 시간 갱신 -> 전 구간의 시간이 일정하지 않다 -> 다익스트라
# 주어진 입력값이 좌표들로만 입력 -> 시작점부터 각 대포까지 거리를 구해서 시간으로 변환한다.
import sys
from heapq import heappush, heappop
input = sys.stdin.readline


def dijkstra(n, time_graph):
    INF = float('inf')
    distance = [INF] * (n+2)
    distance[0] = 0.0
    q = [(0.0, 0)]
    while q:
        dist, now = heappop(q)
        if distance[now] < dist:
            continue
        for nxt, d in enumerate(time_graph[now]):
            if distance[nxt] > dist + d:
                distance[nxt] = dist + d
                heappush(q, (dist + d, nxt))
    return distance[n+1]


def cal_distance(cur_x, cur_y, next_x, next_y):
    return ((next_x - cur_x)**2 + (next_y - cur_y)**2)**0.5


def convert_to_time_list(cannons):
    times = [[] for _ in range(len(cannons))]
    for i in range(len(cannons)):
        for j in range(len(cannons)):
            # 시작과 끝은 걷는 것 밖에 못 한다.
            if i == 0 or i == len(cannons) - 1:
                times[i].append((cal_distance(cannons[i][0], cannons[i][1], cannons[j][0], cannons[j][1])) / 5)
            # 나머지는 대포타고 걷기 (대포에서 다음 대포까지 거리 - 50 / 초당 5m/s + 대포 발사하고 도착까지 2초)
            else:
                times[i].append(2.0 + abs(cal_distance(cannons[i][0], cannons[i][1], cannons[j][0], cannons[j][1]) - 50) / 5)
    return times


def solution(n, coord):
    time_graph = convert_to_time_list(coord)
    return dijkstra(n, time_graph)


def main():
    cur_x, cur_y = map(float, input().split())
    dest_x, dest_y = map(float, input().split())
    n = int(input())
    coord = [(cur_x, cur_y)] + [tuple(map(float, input().split())) for _ in range(n)] + [(dest_x, dest_y)]
    return print(solution(n, coord))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()
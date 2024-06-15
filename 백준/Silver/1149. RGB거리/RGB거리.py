# 백준 1149 RGB거리 - 실버 1

# 이전에 칠했던 색으로 칠해서도 안 되고, 그 다음 색도 나와 같은 색을 칠해서는 안 된다.
# ex) R -> G -> B 로 칠했다면, 그 다음 집은 무조건 R로 칠해야함.
import sys
input = sys.stdin.readline


def solution(N, costs):
    for n in range(1, N):
        costs[n][0] = min(costs[n-1][1], costs[n-1][2]) + costs[n][0]
        costs[n][1] = min(costs[n-1][0], costs[n-1][2]) + costs[n][1]
        costs[n][2] = min(costs[n-1][0], costs[n-1][1]) + costs[n][2]
    return min(costs[N-1])


def main():
    N = int(input())
    costs = [list(map(int, input().split())) for _ in range(N)]
    return print(solution(N, costs))


main()
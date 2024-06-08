# 백준 11561 징검다리 - 실버 3

# 돌의 개수 -> 점프할 칸의 개수
# 이분탐색 진행하면서 점프할 칸의 개수를 세고 N보다 작거나 같은지 체크
# -> N보다 작거나 같아야 다음 점프가 이전에 뛰었던 점프보다 더 클 수가 있다. (N을 무조건 밟아야 하므로)
import sys
input = sys.stdin.readline


def cross_the_bridge(N):
    res, left, right = 0, 1, N
    while left <= right:
        mid = (left + right) // 2
        sum_mid = mid * (mid + 1) // 2

        if sum_mid <= N:
            left = mid + 1
            res = mid
        else:
            right = mid - 1
    return res


def main():
    T = int(input())
    answer = []
    for _ in range(T):
        N = int(input())
        answer.append(cross_the_bridge(N))
    return '\n'.join(map(str, answer))


print(main())
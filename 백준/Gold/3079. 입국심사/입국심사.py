# 백준 3079 입국심사 - 골드 5

# 걸리는 시간의 총 합의 최솟값을 구한다. -> 시간에 대한 리스트 탐색
# M = 10억 -> 브루트 포스 절대 불가 -> 이분 탐색 진행
# 경계값은 left, right = min(times), max(times) * M -> 최소 걸리는 시간이 있고, 최대로 걸리는 시간이 있다.
# mid(총 걸린 시간)에서 각 시간 k로 나누면서 몇 명의 인원을 심사할 수 있는지 체크한다.
# 이 때, 사람 수가 M보다 작다면 M의 인원 전부를 심사하지 못하므로 시작점 = mid + 1로 해서 탐색해주고
# 심사 가능한 사람 수가 M보다 커도 어차피 총 인원인 M명을 심사하기에 충분한 시간이라고 판단.
# 물론 같아도 총 인원인 M명 심사 가능

import sys
input = sys.stdin.readline


def check_time(M, times):
    res, left, right = 0, min(times), max(times) * M
    while left <= right:
        mid = (left + right) // 2
        people = 0
        for time in times:
            people += mid // time
            if people >= M:
                break
        if people >= M:
            right = mid - 1
            res = mid
        else:
            left = mid + 1
    return res


def main():
    N, M = map(int, input().split())
    times = [int(input()) for _ in range(N)]
    return check_time(M, times)


print(main())
# 백준 2110 공유기 설치 - 골드 4

# 한 집에는 공유기 1개만 설치 가능, 가장 인접한 두 공유기 사이의 거리를 가능한 크게
# 두 공유기 사이의 거리를 최대로 하는 문제 -> M의 크기 10억
# 그럼 거리에 대해 이분 탐색 진행 -> 첫 집부터 순회하면서 중간값과 비교해서 카운트 증가 시켜주기
import sys
input = sys.stdin.readline


def install_routers(M, houses):
    res, left, right = 0, 1, houses[-1] - houses[0]
    while left <= right:
        cur = houses[0]
        mid = (left + right) // 2
        cnt = 1

        for i in range(1, len(houses)):
            if mid <= houses[i] - cur:
                cnt += 1
                cur = houses[i]
                if cnt > M:
                    break
        if cnt >= M:
            left = mid + 1
            res = mid
        elif cnt < M:
            right = mid - 1
    return res


def main():
    N, M = map(int, input().split())
    houses = sorted([int(input()) for _ in range(N)])
    return install_routers(M, houses)


print(main())
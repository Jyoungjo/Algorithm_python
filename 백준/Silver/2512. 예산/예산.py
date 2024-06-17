# 백준 2512 예산 - 실버 2

# 전체 국가 예산 중에서 배정된 요청에 따라 금액 나누는 문제
# 모든 요청이 배정될 수 있다면 그대로 배정하지만, 없다면 정수 상한액을 계산하고 요청이 상한액을 넘으면 상한액 만큼만 배정
# 전체 예산 범위가 10^9 -> 이분탐색
import sys
from heapq import heappush
input = sys.stdin.readline


def solution(M, budgets):
    result, left, right = 0, 1, M
    while left <= right:
        mid = (left + right) // 2
        l, req_sum = [], 0
        for budget in budgets:
            if mid < budget:
                req_sum += mid
                heappush(l, -mid)
            else:
                req_sum += budget
                heappush(l, -budget)
        if req_sum <= M:
            left = mid + 1
            result = -l[0]
        else:
            right = mid - 1
    return result


def main():
    N = int(input())
    budget_requests = list(map(int, input().split()))
    M = int(input())
    return print(solution(M, budget_requests))


main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()
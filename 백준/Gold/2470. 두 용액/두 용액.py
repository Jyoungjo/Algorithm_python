# 백준 2470 두 용액 - 골드 5

# 혼합 용액 특성값 = 용액의 특성값의 합
# 0에 가깝다? -> 절댓값 사용
# 제일 작은 특성값 + 제일 큰 특성값 부터 시작해서 조건에 맞게 비교

import sys
input = sys.stdin.readline


def find_mixed_solution(solutions):
    left, right = 0, len(solutions) - 1
    res = [solutions[left], solutions[right]]
    mixed = abs(solutions[left] + solutions[right])

    while left < right:
        cur_mixed = solutions[left] + solutions[right]
        if abs(cur_mixed) < mixed:
            mixed = abs(cur_mixed)
            res = [solutions[left], solutions[right]]
            if mixed == 0:
                return res

        # abs(cur_mixed)가 0보다 작으면 왼쪽 증가 / 크면 오른쪽 증가 -> 0과 가깝게 하기 위해서
        if cur_mixed < 0:
            left += 1
        else:
            right -= 1
    return res


def main():
    N = int(input())
    solutions = list(map(int, input().strip().split()))
    solutions.sort()
    return find_mixed_solution(solutions)


print(*main())
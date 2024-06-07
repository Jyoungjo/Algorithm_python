# 백준 18870 좌표 압축 - 실버 2

# X'_i = X_i > X_j 인 서로 다른 X_j의 개수
import sys
input = sys.stdin.readline


def compress_coordinate(nums):
    sorted_nums = sorted(set(nums))
    num_idx = {num: i for i, num in enumerate(sorted_nums)}
    return [num_idx[num] for num in nums]


def main():
    N = int(input())
    nums = list(map(int, input().split()))
    return compress_coordinate(nums)


print(*main())
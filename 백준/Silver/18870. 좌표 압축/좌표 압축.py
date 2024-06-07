# 백준 18870 좌표 압축 - 실버 2

# X'_i = X_i > X_j 인 서로 다른 X_j의 개수
import sys
input = sys.stdin.readline


def compress_coordinate(nums):
    sorted_nums = sorted(list(set(nums)))
    return [binary_search(sorted_nums, num) for num in nums]


def binary_search(nums, target):
    start, end = 0, len(nums) - 1
    while start <= end:
        mid = (start + end) // 2
        if nums[mid] < target:
            start = mid + 1
        elif nums[mid] > target:
            end = mid - 1
        else:
            return mid
    return 0


def main():
    N = int(input())
    nums = list(map(int, input().split()))
    return compress_coordinate(nums)


print(*main())
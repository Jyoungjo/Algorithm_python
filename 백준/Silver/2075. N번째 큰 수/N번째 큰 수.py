# 백준 2075 N번째 큰 수 - 실버 2

import sys
import heapq
input = sys.stdin.readline


N = int(input())
nums = []
for _ in range(N):
    row = list(map(int, input().split()))
    for e in row:
        heapq.heappush(nums, e)
        if len(nums) > N:
            heapq.heappop(nums)
print(sorted(nums)[0])
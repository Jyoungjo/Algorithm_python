# 백준 11286 절댓값 힙 - 실버 1

import sys
import heapq
input = sys.stdin.readline


N = int(input())
abs_heap = []
for _ in range(N):
    num = int(input())
    if num:
        heapq.heappush(abs_heap, (abs(num), num))
    else:
        print(heapq.heappop(abs_heap)[1]) if abs_heap else print(0)
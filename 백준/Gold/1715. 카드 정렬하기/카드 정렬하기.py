# 백준 1715 카드 정렬하기 - 골드 4

import sys
import heapq
input = sys.stdin.readline


N = int(input())
cards = list(int(input()) for _ in range(N))
cards.sort()
ans = 0
while len(cards) > 1:
    x_y = heapq.heappop(cards) + heapq.heappop(cards)
    ans += x_y
    heapq.heappush(cards, x_y)
print(ans)
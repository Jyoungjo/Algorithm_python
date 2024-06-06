# 백준 15903 카드 합체 놀이 - 실버 1

import sys
import heapq
input = sys.stdin.readline


n, m = map(int, input().strip().split())
cards = list(map(int, input().strip().split()))
heapq.heapify(cards)
while m > 0:
    x_y = heapq.heappop(cards) + heapq.heappop(cards)
    for _ in range(2):
        heapq.heappush(cards, x_y)
    m -= 1
print(sum(cards))
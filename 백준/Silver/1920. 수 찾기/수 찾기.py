# 백준 1920 수 찾기 - 실버 4

# 31120/44
import sys
from collections import Counter

input = sys.stdin.readline


N = int(input())
A = list(map(int, input().strip().split()))
M = int(input())
B = list(map(int, input().strip().split()))

c = Counter(A)
for b in B:
    print(1) if c[b] else print(0)
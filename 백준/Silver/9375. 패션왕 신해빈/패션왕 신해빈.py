# 백준 9375 패션왕 신해빈 - 실버 3

import sys
from collections import Counter
from functools import reduce

input = sys.stdin.readline


TC = int(input())
for _ in range(TC):
    n = int(input())
    clothes = Counter([input().strip().split()[1] for _ in range(n)])
    answer = reduce(lambda x, y: x * (y + 1), clothes.values(), 1) - 1
    print(answer)
# 백준 15650 N과 M (2) - 실버 3

# 조건을 만족하는 길이 M인 수열 모두 구하기
# 1. 1~N 까지 자연수 중, 중복 없이 M개 고른 수열 -> 순서는 상관없이 고르기만 하면 되니까 조합
# 2. 오름차순
import sys
from itertools import combinations


N, M = map(int, sys.stdin.readline().split())
print('\n'.join(sorted([' '.join(map(str, x)) for x in combinations(range(1, N+1), M)])))
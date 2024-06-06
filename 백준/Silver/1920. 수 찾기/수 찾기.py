# 백준 1920 수 찾기 - 실버 4

import sys
input = sys.stdin.readline


N = int(input())
A = set(input().strip().split())
M = int(input())
print('\n'.join('1' if num in A else '0' for num in input().strip().split()))
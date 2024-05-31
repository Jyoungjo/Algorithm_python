# 백준 24313 알고리즘 수업-점근적 표기 1 - 실버 5

# n >= n0 일때, f(n) <= c * g(n)을 만족하는가?
import sys


a1, a0 = map(int, sys.stdin.readline().split())
c = int(sys.stdin.readline())
n0 = int(sys.stdin.readline())

flag = False
for n in range(n0, 100):
    if a1 * n + a0 >= c * n:
        print(0)
        flag = True
        break

if not flag:
    print(1)
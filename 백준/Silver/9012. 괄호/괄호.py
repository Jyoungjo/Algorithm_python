# 백준 9012 괄호 - 실버 4

# 들어오는 괄호의 모양과 현재 존재하는 괄호의 모양에 따라 기준을 나누어 구현
import sys


input = sys.stdin.readline
T = int(input())
for i in range(T):
    stack = []
    PS = [x for x in input().strip()]
    for p in PS:
        if p == '(':
            stack.append(p)
        elif stack:
            stack.pop()
        else:
            stack.append(p)
            break

    print('YES') if not stack else print('NO')
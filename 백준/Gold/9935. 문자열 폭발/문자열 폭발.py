# 백준 9935 문자열 폭발 - 골드 4

# 특정 문자열이 발견되면 모든 폭발 문자열이 폭발한다.
# 슬라이싱 사용해서 하면 O(N^2) = 10^12 예상 -> 무조건 터짐
# 괄호 문제랑 비슷하게 접근 -> stack에 한 글자씩 보내면서 stack의 뒤의 두 자리가 폭발 문자열이 됐을 때, pop해주는 방법
import sys
input = sys.stdin.readline


def search_explosion_s(string, explosion_string):
    stack = []
    ex_len = len(explosion_string)

    for i in range(len(string)):
        stack.append(string[i])
        if ''.join(stack[-ex_len:]) == explosion_string:
            for _ in range(ex_len):
                stack.pop()

    return stack


def main():
    s = input().strip()
    explosion_s = input().strip()
    arr = search_explosion_s(s, explosion_s)
    return ''.join(arr) if arr else 'FRULA'

print(main())
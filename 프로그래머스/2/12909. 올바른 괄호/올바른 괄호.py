# 프로그래머스 12909 올바른 괄호 LV.2
def solution(s):
    answer = True
    stack = []
    for c in s:
        if c == '(':
            stack.append(c)
        else:
            if len(stack) == 0:
                return False
            else:
                stack.pop()

    return len(stack) == 0
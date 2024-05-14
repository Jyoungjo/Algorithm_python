# 프로그래머스 131704 택배상자


def solution(order):
    stack = []
    idx = 0
    for i in range(1, len(order) + 1):
        stack.append(i)
        
        while stack and stack[-1] == order[idx]:
            stack.pop()
            idx += 1
        
    return idx
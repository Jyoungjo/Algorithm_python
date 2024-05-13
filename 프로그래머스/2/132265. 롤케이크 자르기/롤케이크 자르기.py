# 프로그래머스 132265 롤케이크 자르기


def solution(topping):
    answer = 0
    a, b = set(), dict()
    for x in topping:
        b[x] = b.get(x, 0)
        b[x] += 1

    for x in topping:
        a.add(x)
        b[x] -= 1
        if b[x] == 0:
            del b[x]
        if len(a) == len(b.keys()):
            answer += 1

    return answer
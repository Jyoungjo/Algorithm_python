# 프로그래머스 42842 카펫 LV.2
def solution(brown, yellow):
    answer = []
    total = brown + yellow
    for b in range(1, total + 1):
        if total % b == 0:
            a = int(total / b)
            if a >= b and 2 * a + 2 * b - 4 == brown:
                return [a, b]
    return answer
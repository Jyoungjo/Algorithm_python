# 프로그래머스 42746 가장 큰 수 LV.2
def solution(numbers):
    numbers = sorted(map(str, numbers), key=lambda x: x * 3, reverse=True)
    return str(int(''.join(numbers)))
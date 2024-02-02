# 프로그래머스 42746 가장 큰 수 LV.2
def solution(numbers):
    answer = ''
    numbers = sorted(map(str, numbers), key=lambda x: x * 3, reverse=True)
    for n in numbers:
        if answer == '' and n == '0':
            return '0'
        answer += n
    return answer
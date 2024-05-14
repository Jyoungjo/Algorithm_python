# 프로그래머스 42883 큰 수 만들기 LV.2
def solution(number, k):
    answer = [number[0]]
    for num in number[1:]:
        while answer and answer[-1] < num and k > 0:
            k -= 1
            answer.pop()
        answer.append(num)
    if k != 0:
        answer = answer[:-k]
    return ''.join(answer)
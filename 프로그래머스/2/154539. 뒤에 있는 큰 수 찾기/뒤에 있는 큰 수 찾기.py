# 프로그래머스 154539 뒤에 있는 큰 수 찾기


def solution(numbers):
    answer = [-1 for _ in range(len(numbers))]
    stack = []
    for i in range(len(numbers)):
        while stack and numbers[stack[-1]] < numbers[i]:
            answer[stack.pop()] = numbers[i]
        stack.append(i)

    return answer
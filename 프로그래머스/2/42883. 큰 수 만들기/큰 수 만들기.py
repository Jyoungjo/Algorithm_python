# 프로그래머스 42883 큰 수 만들기 LV.2
def solution(number, k):
    answer = []

    for n in number:
        if not answer:
            answer.append(n)
            continue
        while answer[-1] < n and k > 0:
            answer.pop()
            k -= 1
            if not answer or k <= 0:
                break
        answer.append(n)

    if len(answer) != len(number) - k:
        for _ in range(len(answer) - len(number) + k):
            answer.pop()

    return ''.join(answer)
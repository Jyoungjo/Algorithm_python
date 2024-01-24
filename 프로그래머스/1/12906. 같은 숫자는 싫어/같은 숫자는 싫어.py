# 프로그래머스 12906 같은 숫자는 싫어 LV.1
def solution(arr):
    answer = []
    for c in arr:
        if len(answer) == 0 or answer[-1] != c:
            answer.append(c)
    return answer
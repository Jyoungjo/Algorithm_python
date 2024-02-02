# 프로그래머스 42748 K번째 수 LV.1
def solution(array, commands):
    answer = []
    for i, j, k in commands:
        new_array = sorted(array[i-1:j])
        answer.append(new_array[k-1])
    return answer
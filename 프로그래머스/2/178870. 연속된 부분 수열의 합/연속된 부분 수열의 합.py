# 프로그래머스 178870 연속된 부분 수열의 합 LV.2

def solution(sequence, k):
    answer = [0, len(sequence)]
    s, e = 0, 0
    p_sum = sequence[0]
    
    while True:
        if p_sum < k:
            e += 1
            if e == len(sequence):
                break
            p_sum += sequence[e]
        else:
            if p_sum == k:
                if e - s < answer[1] - answer[0]:
                    answer = [s, e]
            p_sum -= sequence[s]
            s += 1
    
    return answer
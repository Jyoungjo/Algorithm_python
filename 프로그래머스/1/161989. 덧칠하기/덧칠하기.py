# 프로그래머스 161989 덧칠하기 LV.1
def solution(n, m, section):
    answer = 1 # 최소 칠하는 횟수
    paint = section[0] # 시작점
    for sec in section:
        if sec - paint >= m:
            paint = sec
            answer += 1

    return answer
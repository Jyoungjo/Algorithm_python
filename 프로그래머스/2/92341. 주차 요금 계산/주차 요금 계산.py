import math


# 프로그래머스 92341 주차 요금 계산
def solution(fees, records):
    answer = []
    r_time = {}
    
    # 차량별 누적 주차 시간 계산
    for r in records:
        _r = r.split()
        _r[0] = int(_r[0].split(':')[0]) * 60 + int(_r[0].split(':')[1])

        # 여기서 IN 의 경우 (-), OUT 의 경우 (+) 를 해준다.
        # OUT - IN = 주차한 시간
        if not r_time.get(_r[1]):
            if _r[2] == 'IN':
                r_time[_r[1]] = -_r[0]
            else:
                r_time[_r[1]] = _r[0]
        else:
            if _r[2] == 'IN':
                r_time[_r[1]] -= _r[0]
            else:
                r_time[_r[1]] += _r[0]

    # 누적 주차 시간을 이용하여 차량별 요금 계산
    for k, v in sorted(r_time.items(), key=lambda x: x[0]):
        # value(누적 주차 시간) 가 0 이하라면 23:59 까지 주차한 것이므로 그만큼 더해준다.
        if v <= 0:
            v += 1439

        if v > fees[0]:
            answer.append(fees[1] + math.ceil((v - fees[0]) / fees[2]) * fees[3])
        else:
            answer.append(fees[1])

    return answer
import math


def solution(fees, records):
    answer = []
    r_time = {}
    for r in records:
        _r = r.split()
        _r[0] = int(_r[0].split(':')[0]) * 60 + int(_r[0].split(':')[1])

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

    for k, v in sorted(r_time.items(), key=lambda x: x[0]):
        if v <= 0:
            v += 1439

        if v > fees[0]:
            answer.append(fees[1] + math.ceil((v - fees[0]) / fees[2]) * fees[3])
        else:
            answer.append(fees[1])

    return answer
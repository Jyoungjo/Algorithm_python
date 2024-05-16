from functools import reduce


def solution(k, ranges):
    answer = []
    x = 0
    coord = []
    while True:
        coord.append([x, k])
        if k == 1:
            break
        if k % 2 == 0:
            k //= 2
        else:
            k = k * 3 + 1
        x += 1
    
    area = [0]
    for i in range(len(coord) - 1):
        x1, y1 = coord[i]
        x2, y2 = coord[i + 1]
        a = (abs(y2 - y1) * abs(x2 - x1)) / 2 + min(y1, y2) * abs(x2 - x1)
        area.append(a) if not area else area.append(area[-1] + a)

    n = len(area) - 1
    for r in ranges:
        x1, x2 = r[0], n + r[1]
        if x1 > x2:
            answer.append(-1.0)
        else:
            answer.append(area[x2] - area[x1])
    return answer
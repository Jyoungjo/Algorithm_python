# 프로그래머스 86491 최소직사각형 LV.1
def solution(sizes):
    rows = []
    cols = []
    for size in sizes:
        if size[0] < size[1]:
            size[0], size[1] = size[1], size[0]
        rows.append(size[0])
        cols.append(size[1])
    return max(rows) * max(cols)
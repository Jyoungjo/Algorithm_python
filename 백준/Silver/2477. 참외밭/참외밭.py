# 백준 2477 참외밭 - 실버 2

# 참외밭의 참외 개수 비례식으로 구하는 문제
# 참외밭의 넓이 구하기 -> 비례식으로 개수 구하기
# 작은 영역의 가로, 세로 = 제일 큰 가로, 세로의 양 옆에 있는 값들의 차의 절댓값
import sys


def find_max_val():
    w, h, w_i, h_i = 0, 0, 0, 0
    for i in range(6):
        if field[i][0] == 1 or field[i][0] == 2:
            if w < field[i][1]:
                w = field[i][1]
                w_i = i
        else:
            if h < field[i][1]:
                h = field[i][1]
                h_i = i
    return w, h, w_i, h_i


def find_min_val():
    w = abs(field[(h_idx - 1) % 6][1] - field[(h_idx + 1) % 6][1])
    h = abs(field[(w_idx - 1) % 6][1] - field[(w_idx + 1) % 6][1])
    return w, h


km_cnt = int(sys.stdin.readline().strip())
field = [[a for a in map(int, sys.stdin.readline().strip().split())] for _ in range(6)]
max_r, max_c, w_idx, h_idx = find_max_val()
min_r, min_c = find_min_val()
print(km_cnt * (max_r * max_c - min_r * min_c))
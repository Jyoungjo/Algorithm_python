# 프로그래머스 172928 공원 산책 LV.1
def solution(park, routes):
    x, y = 0, 0

    # 시작 위치 찾기
    for row in range(len(park)):
        for col in range(len(park[row])):
            if park[row][col] == 'S':
                x, y = row, col

    # 방향별 이동 거리 지정
    op = {'N': (-1, 0), 'S': (1, 0), 'E': (0, 1), 'W': (0, -1)}

    # 산책 시작
    for route in routes:
        xx, yy = x, y # 현재 위치 기록용 변수
        flag = True # 이동 가능 여부 체크 변수

        dx, dy = op[route.split()[0]] # 이동 방향
        n = int(route.split()[1]) # 이동 횟수

        for _ in range(n):
            nx = xx + dx # 이동거리 x
            ny = yy + dy # 이동거리 y

            # 이동 가능한 경우 (공원 크기를 벗어나지 않거나 장애물이 없는 경우)
            if 0 <= nx <= len(park) - 1 and 0 <= ny <= len(park[0]) - 1 and park[nx][ny] != 'X':
                flag = True
                xx, yy = nx, ny
            else:
                flag = False
                break

        if flag:
            x, y = xx, yy

    return [x, y]
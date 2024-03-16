# 프로그래머스 84021 퍼즐 조각 채우기 LV.3
from collections import deque

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]


def bfs(begin, target, return_list, r_c_len, original, changed):
    i, j = begin
    q = deque()
    q.append(begin)
    target[i][j] = changed

    while q:
        x, y = q.popleft()
        return_list.append([x, y])
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < r_c_len and 0 <= ny < r_c_len and target[nx][ny] == original:
                q.append([nx, ny])
                target[nx][ny] = changed


def arrange_relative_coordinates(target):
    target.sort()
    x0, y0 = target[0]

    for k in range(len(target)):
        target[k][0] = target[k][0] - x0
        target[k][1] = target[k][1] - y0


def solution(game_board, table):
    answer = 0
    blocks = []
    m = len(game_board)

    # 블록 찾기 -> 찾아서 blocks 에 좌표 저장 (시작이 (0, 0) 으로 저장)
    for i in range(m):
        for j in range(m):
            if table[i][j] == 1:
                # bfs 로 block 찾기
                block = []
                bfs([i, j], table, block, m, 1, 0)

                # block 의 좌표를 상대 좌표로 정리하기
                arrange_relative_coordinates(block)

                # blocks 에 저장
                blocks.append(block)

    # 빈칸 찾기 -> 찾아서 blanks 에 좌표 저장(상대 좌표) -> block 과 blank 일치하는지 체크
    for i in range(m):
        for j in range(m):
            if game_board[i][j] == 0:
                blank = []
                bfs([i, j], game_board, blank, m, 0, 1)

                # blank 의 좌표를 상대 좌표로 정리하기
                arrange_relative_coordinates(blank)

                # 현재 blank 와 일치하는 block 찾기 -> 회전하면서 일치하면 정답 기록
                for b in range(len(blocks)):
                    cur_block = blocks[b]
                    flag = False

                    # block 시계 방향 회전 (90 -> 180 -> 270 -> 360)
                    for c in range(4):
                        for idx in range(len(cur_block)):
                            cur_block[idx][0], cur_block[idx][1] = cur_block[idx][1], -cur_block[idx][0]

                        # 상대 좌표 정리
                        arrange_relative_coordinates(cur_block)

                        # 일치하면 해당 블록 제거
                        if blank == cur_block:
                            del blocks[b]
                            answer += len(cur_block)
                            flag = True
                            break

                    if flag:
                        break

    return answer
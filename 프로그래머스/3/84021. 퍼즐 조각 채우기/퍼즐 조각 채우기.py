# 프로그래머스 84021 퍼즐 조각 채우기 LV.3
empty, blocks = [], []
def chk():   # 빈칸에 맞는 퍼즐조각이 있는지 확인
    global empty, blocks
    for b in range(len(blocks)):
        block = blocks[b].copy()
        for i in range(4):
            for j in range(len(block)):  # 회전---------
                block[j][0], block[j][1] = block[j][1], -block[j][0]   # 회전---------
            block.sort()   # 좌표 초기화------
            x0, y0 = block[0]
            for k in range(len(block)):
                block[k][0] = block[k][0] - x0
                block[k][1] = block[k][1] - y0   # 좌표 초기화------
            if empty == block:
                del blocks[b]
                return True
    return False


def solution(game_board, table):
    global empty, blocks
    answer = 0
    n = len(game_board)
    blocks = []
    dx, dy = [-1, 0, 1, 0],[0, -1, 0, 1]
    for i in range(n):
        for j in range(n):
            if table[i][j] == 1:
                block = []  # 퍼즐조각
                qu = [[i, j]]
                table[i][j] = 0
                while qu:
                    x, y = qu.pop(0)
                    block.append([x, y])
                    for d in range(4):
                        nx, ny = x + dx[d], y + dy[d]
                        if 0 <= nx < n and 0 <= ny < n:
                            if table[nx][ny] == 1:
                                qu.append([nx, ny])
                                table[nx][ny] = 0
                block.sort()   # 좌표 초기화------
                x0, y0 = block[0]
                for k in range(len(block)):
                    block[k][0] = block[k][0] - x0
                    block[k][1] = block[k][1] - y0   # 좌표 초기화------
                blocks.append(block.copy())

    for i in range(n):
        for j in range(n):
            if game_board[i][j] == 0:
                empty = []   # 빈칸
                qu = [[i, j]]
                game_board[i][j] = 1
                while qu:
                    x, y = qu.pop(0)
                    empty.append([x, y])
                    for d in range(4):
                        nx, ny = x + dx[d], y + dy[d]
                        if 0 <= nx < n and 0 <= ny < n:
                            if game_board[nx][ny] == 0:
                                qu.append([nx, ny])
                                game_board[nx][ny] = 1
                empty.sort()    # 좌표 초기화-----
                x0, y0 = empty[0]
                for k in range(len(empty)):
                    empty[k][0] = empty[k][0] - x0
                    empty[k][1] = empty[k][1] - y0   # 좌표 초기화-----
                if chk():  # 빈칸에 맞는 퍼즐조각이 있는지 확인
                    answer += len(empty)

    return answer
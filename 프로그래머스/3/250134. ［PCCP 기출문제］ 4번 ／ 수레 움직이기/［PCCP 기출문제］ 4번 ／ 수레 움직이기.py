# 프로그래머스 250134 수레 움직이기 LV.3
answer = 1e9


def solution(maze):
    n, m = len(maze), len(maze[0])
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1), (0, 0)]
    red, blue, red_goal, blue_goal = 0, 0, 0, 0
    for i in range(n):
        for j in range(m):
            if maze[i][j] == 1: red = (i, j)
            if maze[i][j] == 2: blue = (i, j)
            if maze[i][j] == 3: red_goal = (i, j)
            if maze[i][j] == 4: blue_goal = (i, j)
    red_visited, blue_visited = [[False] * m for _ in range(n)], [[False] * m for _ in range(n)]
    red_visited[red[0]][red[1]], blue_visited[blue[0]][blue[1]] = True, True

    def out_of_range(x, y):
        return x < 0 or y < 0 or x >= n or y >= m

    def already_visited(x, y, visited, goal):
        return maze[x][y] != goal and visited[x][y]

    def duplicated(x1, y1, x2, y2):
        return x1 == x2 and y1 == y2

    def crossed(r_cur, b_cur, r_next, b_next):
        return r_cur == b_next and b_cur == r_next

    def dfs(r_cur, b_cur, r_vis, b_vis, turn):
        global answer
        if r_cur == red_goal and b_cur == blue_goal:
            answer = min(answer, turn)
            return

        for r_dx, r_dy in directions:
            rx_next, ry_next = r_cur[0] + r_dx, r_cur[1] + r_dy

            if out_of_range(rx_next, ry_next) \
                or already_visited(rx_next, ry_next, r_vis, 3) \
                or maze[rx_next][ry_next] == 5:
                continue

            r_vis[rx_next][ry_next] = True
            for b_dx, b_dy in directions:
                if (r_dx, r_dy) == (0, 0) and (b_dx, b_dy) == (0, 0):
                    break
                bx_next, by_next = b_cur[0] + b_dx, b_cur[1] + b_dy

                if out_of_range(bx_next, by_next) \
                    or already_visited(bx_next, by_next, b_vis, 4) \
                    or maze[bx_next][by_next] == 5 \
                    or duplicated(rx_next, ry_next, bx_next, by_next) \
                    or crossed(r_cur, b_cur, (rx_next, ry_next), (bx_next, by_next)):
                    continue

                b_vis[bx_next][by_next] = True
                dfs((rx_next, ry_next), (bx_next, by_next), r_vis, b_vis, turn + 1)
                b_vis[bx_next][by_next] = False
            r_vis[rx_next][ry_next] = False
    dfs(red, blue, red_visited, blue_visited, 0)

    return 0 if answer == 1e9 else answer
# 백준 8911 거북이 - 실버 3

# 명령어에 따라 움직인 거북이의 좌표를 기반으로 해당 영역의 넓이 구하기
# 회전한다는 것이 전진한다는 의미가 아니다. -> dx, dy 만 바꿔준다.
import sys
input = sys.stdin.readline

x, y = 0, 0
dx, dy = 0, 1


def cal_area(min_val, max_val):
    return (max_val[0] - min_val[0]) * (max_val[1] - min_val[1])


def execute_command(command):
    global x, y, dx, dy
    
    if command == 'F':
        x, y = x + dx, y + dy
    elif command == 'B':
        x, y = x - dx, y - dy
    elif command == 'L':
        dx, dy = -dy, dx
    else:
        dx, dy = dy, -dx


def move_the_turtle(commands):
    min_x, min_y, max_x, max_y = 0, 0, 0, 0
    for c in commands:
        execute_command(c)
        min_x, min_y, max_x, max_y = min(min_x, x), min(min_y, y), max(max_x, x), max(max_y, y)
    return (min_x, min_y), (max_x, max_y)


def main():
    global x, y, dx, dy

    T = int(input())
    answer = []
    for _ in range(T):
        commands = [c for c in input().strip()]
        x, y, dx, dy = 0, 0, 0, 1
        min_val, max_val = move_the_turtle(commands)
        answer.append(cal_area(min_val, max_val))
    return print('\n'.join(str(ans) for ans in answer))


main()
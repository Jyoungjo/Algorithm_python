# 백준 5588 별자리 찾기 - 골드 5

# 찾고 싶은 별자리를 set에 x좌표 오름차순으로 저장 -> x가 제일 작은 좌표 기준으로 상대 좌표 재저장
# 사진에 있는 별자리들 중에서 len(set) 만큼 조합해서 x가 제일 작은 좌표 기준으로 상대 좌표 저장
# 두 좌표 비교후 일치하면 원래 좌표와 사진 속 좌표가 얼마나 차이나는지 확인
# 시간 초과 -> 조합 하는 과정에서 느려진거 같은데 다른 방법 시도

# 찾고 싶은 별자리에서 아무 좌표 하나 지정 -> 그 좌표로 부터 사진 속 좌표들마다 이동량 계산
# 계산한 다음에 찾고 싶은 별자리의 좌표마다 이동량 적용해서 이동한 좌표들이 전부 사진 속 좌표에 있는지 확인
# 있다면 이동량만 리턴
import sys


m = int(sys.stdin.readline())
target_star = list(tuple(map(int, sys.stdin.readline().split())) for _ in range(m))
n = int(sys.stdin.readline())
picture_star = set(tuple(map(int, sys.stdin.readline().split())) for _ in range(n))
x0, y0 = target_star[0]
for star in picture_star:
    flag = True
    move_x, move_y = star[0] - x0, star[1] - y0
    for target in target_star[1:]:
        t_move_x, t_move_y = target[0] + move_x, target[1] + move_y
        if (t_move_x, t_move_y) not in picture_star:
            flag = False
            break
    if flag:
        print(move_x, move_y)
        break
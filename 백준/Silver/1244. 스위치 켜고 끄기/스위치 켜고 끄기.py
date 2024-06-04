# 1번
# 남학생은 스위치가 받은 수의 배수 -> 상태 변경
# 여학생은 받은 수와 같은 번호 양 옆을 기준으로 대칭이면 바꾼다
import sys


def change_switch(index):
    switch[index] = 1 if not switch[index] else 0


input = sys.stdin.readline
s_num = int(input())
switch = list(map(int, input().split()))
students = int(input())
for i in range(students):
    gender, num = map(int, input().split())
    if gender == 1:
        for j in range(len(switch)):
            if (j + 1) % num == 0:
                change_switch(j)
    else:
        idx = num - 1
        s, e = idx, idx
        while True:
            if s >= 0 and e < len(switch) and switch[s] == switch[e]:
                s -= 1
                e += 1
            elif s < 0 or e >= len(switch) or switch[s] != switch[e]:
                s += 1
                e -= 1
                break
        for j in range(s, e + 1):
            change_switch(j)
for i in range(0, len(switch), 20):
    print(' '.join(str(s) for s in switch[i:i+20]))
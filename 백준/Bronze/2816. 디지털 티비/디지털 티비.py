# 백준 2816 디지털 티비 -


import sys
input = sys.stdin.readline
N = int(input())
channels = [input().strip() for _ in range(N)]


def solution():
    answer = []
    idx = 0
    flag = True
    while True:
        if channels[0] == 'KBS1' and channels[1] == 'KBS2':
            break

        if flag:
            if channels[idx] != 'KBS1':
                idx += 1
                answer.append('1')
            else:
                while idx != 0:
                    channels[idx], channels[idx - 1] = channels[idx - 1], channels[idx]
                    answer.append('4')
                    idx -= 1
                flag = False
        else:
            if channels[idx] != 'KBS2':
                idx += 1
                answer.append('1')
            else:
                while idx != 1:
                    channels[idx], channels[idx - 1] = channels[idx - 1], channels[idx]
                    answer.append('4')
                    idx -= 1
    return print(''.join(answer))


solution()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()
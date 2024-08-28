# 백준 25757 임스와 함께하는 미니게임 -


import sys
from collections import defaultdict

input = sys.stdin.readline
inp = input().split()
N, game_kind = int(inp[0]), inp[1]
game = {'Y': 1, 'F': 2, 'O': 3}


def solution():
    answer = 0
    tmp = []
    player = defaultdict(int)
    for _ in range(N):
        name = input().strip()
        if not player.get(name):
            player[name] = 1
            tmp.append(name)
            if len(tmp) == game[game_kind]:
                answer += 1
                tmp.clear()

    return print(answer)


solution()
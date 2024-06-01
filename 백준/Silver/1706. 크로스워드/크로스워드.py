# 백준 1706 크로스워드 - 실버 2

# BF 하면서 조건에 맞으면 단어 추가 -> 사전순 정렬 후 맨 앞 단어 출력
import sys


def search_word(a, b, flag):
    result = []
    for i in range(a):
        arr = []
        for j in range(b):
            target = grid[i][j] if flag else grid[j][i]
            if target != '#':
                arr.append(target)
            else:
                if len(arr) > 1:
                    result.append(''.join(arr))
                arr = []
        if len(arr) > 1:
            result.append(''.join(arr))
    return result


R, C = map(int, sys.stdin.readline().strip().split())
grid = [[x for x in sys.stdin.readline().strip()] for _ in range(R)]
answer = []

# 1. 가로
answer += search_word(R, C, True)
# 2. 세로
answer += search_word(C, R, False)

print(sorted(answer)[0])
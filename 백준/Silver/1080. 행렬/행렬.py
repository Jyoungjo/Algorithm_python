# 백준 1080 행렬 - 실버 1

import sys
input = sys.stdin.readline


def change_matrix(i, j, matrix):
    for x in range(i, i + 3):
        for y in range(j, j + 3):
            matrix[x][y] = 1 - matrix[x][y]


def main():
    N, M = map(int, input().split())
    matrix_A = [list(map(int, input().strip())) for _ in range(N)]
    matrix_B = [list(map(int, input().strip())) for _ in range(N)]
    cnt = 0
    for i in range(N - 2):
        for j in range(M - 2):
            if matrix_A[i][j] != matrix_B[i][j]:
                change_matrix(i, j, matrix_A)
                cnt += 1

    if matrix_A == matrix_B:
        return cnt
    
    return -1


print(main())
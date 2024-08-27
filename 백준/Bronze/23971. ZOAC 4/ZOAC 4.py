import sys
input = sys.stdin.readline
H, W, N, M = map(int, input().split())


def solution():
    s1 = H // (N + 1) if H % (N + 1) == 0 else (H // (N + 1)) + 1
    s2 = W // (M + 1) if W % (M + 1) == 0 else (W // (M + 1)) + 1
    return print(s1 * s2)


solution()
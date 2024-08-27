# 백준 5073 삼각형과 세 변 -


import sys
input = sys.stdin.readline


def solution():
    while True:
        a, b, c = sorted(map(int, input().split()), reverse=True)
        if a == b == c == 0:
            break

        if a >= b + c:
            print("Invalid")
        else:
            if a == b == c:
                print("Equilateral")
            elif (a == b and b != c) or (a != b and b == c):
                print("Isosceles")
            elif a != b != c:
                print("Scalene")


solution()
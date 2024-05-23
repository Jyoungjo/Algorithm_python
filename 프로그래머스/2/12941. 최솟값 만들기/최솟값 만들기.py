from functools import reduce


def solution(A,B):
    A.sort()
    B.sort(reverse=True)
    return reduce(lambda x, y: x + y, [a * b for a, b in zip(A, B)], 0)
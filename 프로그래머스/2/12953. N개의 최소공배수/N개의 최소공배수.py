from math import gcd


def solution(arr):
    while len(arr) >= 2:
        a, b = arr[0], arr[1]
        arr.append((a*b) // gcd(a, b))
        arr = arr[2:]
    return arr[0]
# 프로그래머스 135807 숫자 카드 나누기


from math import gcd


def get_integer(arr1, arr2):
    a = arr1[0]
    for x in arr1:
        a = gcd(a, x)
    if a == 1 or any(y % a == 0 for y in arr2):
        return 0
    return a


def solution(arrayA, arrayB):
    return max(get_integer(arrayA, arrayB), get_integer(arrayB, arrayA))
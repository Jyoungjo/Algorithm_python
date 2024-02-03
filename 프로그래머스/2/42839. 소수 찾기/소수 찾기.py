from itertools import permutations

def is_prime(x):
    for i in range(2, int(x**0.5) + 1):
        if x % i == 0:
            return False
    return True

def solution(numbers):
    answer = 0
    n = list(numbers)
    l1 = []
    for i in range(1, len(n) + 1):
        l1 += list(permutations(n, i))
    l2 = []
    for i in l1:
        l2.append(int(''.join(i)))
    l2 = list(set(l2))
    for i in l2:
        if i <= 1:
            continue
        elif is_prime(i):
            answer += 1
    return answer
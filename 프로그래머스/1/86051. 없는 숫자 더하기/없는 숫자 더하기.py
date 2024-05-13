def solution(numbers):
    s = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
    numbers_set = set(numbers)
    return sum(s - numbers_set)
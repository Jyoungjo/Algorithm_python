def solution(x):
    if x % sum(map(int, [c for c in str(x)])) == 0:
        return True
    return False
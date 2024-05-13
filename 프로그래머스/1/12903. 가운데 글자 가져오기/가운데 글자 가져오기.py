def solution(s):
    l = int(len(s) / 2)
    if len(s) % 2 != 0:
        return s[l]
    return s[l - 1:l + 1]
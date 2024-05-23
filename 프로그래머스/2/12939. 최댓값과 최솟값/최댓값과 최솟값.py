def solution(s):
    answer = ''
    l = list(map(int, s.split()))
    mx, mn = max(l), min(l)
    return str(mn) + ' ' + str(mx)
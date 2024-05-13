def solution(absolutes, signs):
    for i, x in enumerate(zip(absolutes, signs)):
        a, s = x
        if not s:
            absolutes[i] = -a
    return sum(absolutes)
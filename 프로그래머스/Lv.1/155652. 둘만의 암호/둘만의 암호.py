# 프로그래머스 155652 둘만의 암호 LV.1
def solution(s, skip, index):
    answer = ''
    alp = sorted(set('abcdefghijklmnopqrstuvwxyz') - set(skip))

    for c in s:
        if alp.index(c) + index >= len(alp):
            answer += alp[(alp.index(c) + index) % len(alp)]
        else:
            answer += alp[alp.index(c) + index]

    return answer
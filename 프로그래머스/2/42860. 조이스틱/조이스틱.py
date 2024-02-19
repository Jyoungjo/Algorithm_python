# 프로그래머스 42860 조이스틱 LV.2
def solution(name):
    up_down = 0
    left_right = len(name) - 1

    for i, spell in enumerate(name):
        up_down += min(ord(spell) - ord('A'), ord('Z') - ord(spell) + 1)

        next = i + 1
        while next < len(name) and name[next] == 'A':
            next += 1

        left_right = min([left_right, 2 * i + len(name) - next, i + 2 * (len(name) - next)])

    return up_down + left_right
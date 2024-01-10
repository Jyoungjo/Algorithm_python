# 프로그래머스 160586 대충 만든 자판 LV.1
def solution(keymap, targets):
    answer = []
    for word in targets:
        times = 0

        for char in word:
            flag = False
            time = 101 # keymap 길이의 최대가 100이라 101로 잡아줌

            for key in keymap:
                if char in key:
                    time = min(key.index(char)+1, time)
                    flag = True

            if not flag:
                times = -1
                break

            times += time

        answer.append(times)

    return answer
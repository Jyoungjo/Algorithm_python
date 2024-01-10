# 프로그래머스 159994 카드 뭉치 LV.1
def solution(cards1, cards2, goal):
    answer = ''
    flag = False

    for word in goal:
        if word == cards1[0]:
            flag = True
            cards1.pop(0) and cards1.append(' ')

        elif word == cards2[0]:
            flag = True
            cards2.pop(0) and cards2.append(' ')

        else:
            flag = False
            answer = 'No'
            break

    if flag:
        answer = 'Yes'

    return answer
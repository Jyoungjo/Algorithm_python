# 프로그래머스 42840 모의고사 LV.1
def solution(answers):
    answer = []
    one = [1, 2, 3, 4, 5]
    two = [2, 1, 2, 3, 2, 4, 2, 5]
    three = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    scores = [0, 0, 0]

    for i in range(len(answers)):
        if answers[i] == one[(i + 1) % (len(one)) - 1]:
            scores[0] += 1
        if answers[i] == two[(i + 1) % (len(two)) - 1]:
            scores[1] += 1
        if answers[i] == three[(i + 1) % (len(three)) - 1]:
            scores[2] += 1
    max_score = max(scores)
    for i in range(len(scores)):
        if scores[i] == max_score:
            answer.append(i + 1)
    return answer
# 프로그래머스 42840 모의고사 LV.1
def solution(answers):
    first = [1, 2, 3, 4, 5]
    second = [2, 1, 2, 3, 2, 4, 2, 5]
    third = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    scores = [0, 0, 0]

    for i in range(len(answers)):
        if answers[i] == first[(i + 1) % (len(first)) - 1]:
            scores[0] += 1
        if answers[i] == second[(i + 1) % (len(second)) - 1]:
            scores[1] += 1
        if answers[i] == third[(i + 1) % (len(third)) - 1]:
            scores[2] += 1
    max_score = max(scores)

    return [i + 1 for i in range(len(scores)) if max_score == scores[i]]
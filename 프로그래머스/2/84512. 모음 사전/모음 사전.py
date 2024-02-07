from itertools import product


def solution(word):
    answer = 1
    alpha = ['A', 'E', 'I', 'O', 'U']
    per = []
    for i in range(1, 6):
        per += product(alpha, repeat=i)
    per = sorted(''.join(p) for p in per)
    for w in per:
        if w == word:
            return answer
        else:
            answer += 1
    return answer
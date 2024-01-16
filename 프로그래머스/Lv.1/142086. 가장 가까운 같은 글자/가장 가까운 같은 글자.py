# 프로그래머스 142086 가장 가까운 같은 글자 LV.1
def solution(s):
    answer = []
    di = dict()
    for i in range(len(s)):
        if s[i] not in di:
            answer.append(-1)
        else:
            answer.append(i - di[s[i]])
        di[s[i]] = i
    return answer
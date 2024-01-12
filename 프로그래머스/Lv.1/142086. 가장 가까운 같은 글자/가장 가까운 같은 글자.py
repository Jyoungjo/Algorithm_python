def solution(s):
    answer = []
    li = []
    idx = 0

    for c in s:
        t = 0
        if c in li:
            for i in range(len(li) - 1, -1, -1):
                if c == li[i]:
                    t = i
                    break
            answer.append(idx - t)
            li.append(c)
        else:
            li.append(c)
            answer.append(-1)
        idx += 1

    return answer
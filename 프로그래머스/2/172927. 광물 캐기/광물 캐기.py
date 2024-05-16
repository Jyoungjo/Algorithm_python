def solution(picks, minerals):
    answer = 0
    l = min(sum(picks) * 5, len(minerals))
    m = {'diamond': 0, 'iron': 0, 'stone': 0}
    fatigue = {0: (1,1,1), 1: (5,1,1), 2: (25,5,1)}
    arr = []
    
    for i in range(l):
        m[minerals[i]] += 1
        if (i + 1) % 5 == 0 or i == len(minerals) - 1:
            arr.append([m['diamond'], m['iron'], m['stone']])
            m['diamond'], m['iron'], m['stone'] = 0, 0, 0

    arr.sort(key=lambda x: (x[0], x[1], x[2]), reverse=True)

    for d, i, s in arr:
        for j in range(3):
            if picks[j]:
                picks[j] -= 1
                answer += d * fatigue[j][0] + i * fatigue[j][1] + s * fatigue[j][2]
                break
                
    return answer
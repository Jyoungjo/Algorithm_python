# 프로그래머스 172927 광물 캐기 LV.2

def solution(picks, minerals):
    answer = 0
    length = min(sum(picks)*5, len(minerals))
    arr = []
    m_cnt = {'diamond': 0, 'iron': 0, 'stone': 0}
    f = {0: (1, 1, 1), 1: (5, 1, 1), 2: (25, 5, 1)}
    
    for i in range(length):
        m_cnt[minerals[i]] += 1
        if (i + 1) % 5 == 0 or i == len(minerals) - 1:
            arr.append([m_cnt['diamond'], m_cnt['iron'], m_cnt['stone']])
            m_cnt['diamond'], m_cnt['iron'], m_cnt['stone'] = 0, 0, 0
    
    arr.sort(key=lambda x: (x[0], x[1], x[2]), reverse = True)
    
    for d, i, s in arr:
        for j in range(3):
            if picks[j]:
                picks[j] -= 1
                answer += d * f[j][0] + i * f[j][1] + s * f[j][2]
                break
    return answer
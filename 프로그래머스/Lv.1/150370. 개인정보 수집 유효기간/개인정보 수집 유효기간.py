# 프로그래머스 150370 개인정보 수집 유효기간 LV.1
def solution(today, terms, privacies):
    answer = []
    i = 1

    today = list(map(int, today.split('.')))

    expiration = {term[0]: int(term[2:]) for term in terms}

    for p in privacies:
        p = p.split()
        p_date = list(map(int, p[0].split(".")))

        p_date[1] += expiration[p[1]]

        if p_date[1] > 12:
            if p_date[1] % 12 == 0:
                p_date[0] += (p_date[1] // 12) - 1
                p_date[1] = 12
            else:
                p_date[0] += p_date[1] // 12
                p_date[1] %= 12

        if not is_expired(p_date, today):
            answer.append(i)

        i += 1

    return answer

def is_expired(expiration_date, today):
    if expiration_date[0] > today[0]:
        return True

    if expiration_date[0] == today[0] and expiration_date[1] > today[1]:
        return True

    if expiration_date[0] == today[0] and expiration_date[1] == today[1] and expiration_date[2] > today[2]:
        return True

    return False
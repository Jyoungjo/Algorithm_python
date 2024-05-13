def solution(phone_number):
    answer = ''
    l = len(phone_number) - 4
    for _ in range(l):
        answer += '*'
    return answer + phone_number[-4:]
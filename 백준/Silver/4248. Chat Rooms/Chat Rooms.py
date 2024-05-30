# 백준 4248 Chat Rooms - 실버 2

# 악플 판별 문제
consonants = 'bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ'
N = int(input())
answer = ['y'] * N
log = [input() for _ in range(N)]
rule2_arr = [False] * N


# 1. 5개 초과의 연속 자음 단어
def rule1(sentence):
    c_count = 0
    for word in sentence:
        if word in consonants:
            c_count += 1
            if c_count > 5:
                return True
        else:
            c_count = 0
    return False


# 2. 연속 자음 4개 초과인 단어가 하나 이상 포함 + 최근 10줄에서 해당 줄을 3개 이상 보냄
def rule2(sentence, idx):
    words = sentence.split()
    c_count = 0

    for word in words:
        for c in word:
            if c in consonants:
                c_count += 1
                if c_count > 4:
                    rule2_arr[idx] = True
                    break
            else:
                c_count = 0

    if rule2_arr[idx] and rule2_arr[max(0, idx - 10):idx].count(True) > 2:
        return True
    else:
        return False


# 3. 최근 10줄에서 같은 줄 두 번 이상
def rule3(sentence, idx):
    if log[max(0, idx - 10):idx].count(sentence) > 1:
        return True
    else:
        return False


for i, target in enumerate(log):
    if rule1(target) or rule2(target, i) or rule3(target, i):
        answer[i] = 'n'

print('\n'.join(answer))
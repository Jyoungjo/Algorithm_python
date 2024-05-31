# 백준 4374 지프의 법칙 - 실버 2

# n번 등장하는 단어 사전순 나열 -> 소문자 출력
# 그러한 단어가 없다면 "There is no such word." 출력
import sys
import re
from collections import Counter, defaultdict

flag1 = True
while flag1:
    inp = sys.stdin.readline().strip()
    # 종료조건: 입력이 없으면 종료 - 맞는지 잘 모르겠음
    if not inp:
        flag1 = False
        continue

    new_words = []
    n = int(inp)

    flag2 = True
    while flag2:
        sentence = sys.stdin.readline().strip()

        if sentence == 'EndOfText':
            flag2 = False
            continue

        words = re.findall(r'\b[a-z]+\b', sentence.lower())
        new_words += words

    # Counter로 개수 세서 개수 == v인 k 값만 리스트로 만든다.
    answer = [k for k, v in sorted(Counter(new_words).items(), key=lambda x: x[0]) if v == n]
    # 만약 리스트에 값이 있다면 출력하면 되고 없다면 지정 문구 출력
    if answer:
        for a in answer:
            print(a)
    else:
        print('There is no such word.')
    print()
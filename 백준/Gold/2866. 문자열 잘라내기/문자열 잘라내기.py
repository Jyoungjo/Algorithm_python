# 백준 2866 문자열 잘라내기 - 골드 5

# 맨 위의 행 지우고 열들을 문자열로 만든다.
# 그 중 하나라도 중복된다면 count 리턴 중복이 없다면 count + 1
import sys
from collections import defaultdict


R, C = map(int, sys.stdin.readline().strip().split())
rows = [[x for x in sys.stdin.readline().strip()] for _ in range(R)]

cnt = 0
s, e = 0, R - 1
while s <= e:
    mid = (s + e) // 2

    flag = True
    word_dict = defaultdict(int)
    for i in range(C):
        temp = ''
        for j in range(mid, R):
            temp += rows[j][i]
        if not word_dict[temp]:
            word_dict[temp] += 1
        else:
            flag = False
            break

    if flag:
        cnt = mid
        s = mid + 1
    else:
        e = mid - 1

print(cnt)
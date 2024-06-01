# 백준 1213 팰린드롬 만들기 - 실버 3

# 영어 이름 -> 팰린드롬
# 정답 여러개 -> 사전순
# 안되면 I'm Sorry Hansoo 출력
# 팰린드롬이 되려면 알파벳 쌍이 문자열 길이의 절반이 되어야 한다.
import sys
from collections import defaultdict


def count_alpha_pair(n, d):
    c = 0
    for al in n:
        d[al] += 1
        if d[al] % 2 == 0:
            c += 1
    return c


def make_palindrome(n, p, alp_cnt):
    if p < len(n) // 2:
        return 'I\'m Sorry Hansoo'
    else:
        alp_list = sorted(list(alp_cnt.keys()))
        mid = ''
        arr = []
        for a in alp_list:
            if alp_cnt[a] % 2 == 1:
                mid = a
            arr.append(a * (alp_cnt[a] // 2))
        ans = ''.join(arr)
        return ans + mid + ans[::-1]


name = sys.stdin.readline().strip()
alpha_cnt = defaultdict(int)
pair = count_alpha_pair(name, alpha_cnt)

print(make_palindrome(name, pair, alpha_cnt))
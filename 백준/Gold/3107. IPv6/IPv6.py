# 백준 3107 IPv6 - 골드 5

# 스플릿으로 나눠서 하나하나 비교해보기
# 25::091:4846:374:bb
# 25:0:00:00:091:4846:374:bb
# 0025:0000:0000:0000:0091:4846:0374:00bb
import sys
input = sys.stdin.readline
abridged = input().strip().split(':')
original = [''] * 8
idx, is_used_rule_2 = 0, False
for i in range(len(abridged)):
    a_len = len(abridged[i])
    # 전부 채워져 있을 경우
    if a_len == 4:
        original[idx] = abridged[i]
        idx += 1
    # 어딘가 비어 있는 경우
    elif a_len > 0:
        original[idx] = '0' * (4 - a_len) + abridged[i]
        idx += 1
    # 없는 경우
    else:
        # :: 인 구간일 경우
        if not is_used_rule_2:
            gap = 8 - len(abridged) + 1
            for _ in range(gap):
                original[idx] = '0' * 4
                idx += 1
            is_used_rule_2 = True # 한번 밖에 사용할 수 없는 규칙
        # 그룹 하나에 전체를 축약한 경우
        else:
            original[idx] = '0' * 4
            idx += 1

print(':'.join(original))
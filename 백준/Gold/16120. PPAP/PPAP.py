# 백준 16120 PPAP - 골드 4

# 문자열 S를 순회하면서 arr 에 넣고 맨 끝 4자리가 PPAP면 P만 남기고 다음 반복 진행
# 문자열 S에서 마지막에 PPAP 가 나올 경우 P만 남지 않고 PPAP가 남기 때문에 P or PPAP 일 때 성공처리
import sys
input = sys.stdin.readline


def find_string(S):
    arr = []
    PPAP = ['P', 'P', 'A', 'P']
    for i in range(len(S)):
        arr.append(S[i])
        if arr[-4:] == PPAP:
            for _ in range(3):
                arr.pop()
    if arr == ['P']:
        return 'PPAP'
    return 'NP'



def main():
    S = input().strip()
    return find_string(S)


print(main())
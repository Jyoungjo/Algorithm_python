# 백준 2941 크로아티아 알파벳 - 실버 5

# 각 크로아티아 알파벳 개수 세기
# c, d로 시작하면 뒤의 글자 뭔지 구분해서 개수 증가 후 idx 증가시키기
import sys


sentence = sys.stdin.readline().strip()
cnt, idx = 0, 0
two = ['c=', 'c-', 'd-', 'lj', 'nj', 's=', 'z=']
three = 'dz='
while idx < len(sentence):
    if sentence[idx:idx+3] == three:
        idx += 3
    elif sentence[idx:idx+2] in two:
        idx += 2
    else:
        idx += 1
    cnt += 1
print(cnt)
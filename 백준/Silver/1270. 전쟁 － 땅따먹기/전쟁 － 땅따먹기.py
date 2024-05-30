# 백준 1270 전쟁-땅따먹기 - 실버 3

# 땅에서 한 번호의 군 병사가 절반 초과? -> 그 땅은 해당 번호의 군대가 지배
# 지배한 군대의 번호 출력 / 전쟁 중이라면 'SYJKGW' 출력
import sys
from collections import Counter


n = int(sys.stdin.readline())
for i in range(n):
    war = list(map(int, sys.stdin.readline().split()))
    Ti, soldiers = war[0], war[1:]
    flag = False
    for n, cnt in Counter(soldiers).items():
        if cnt > Ti//2:
            print(n)
            flag = True
            break
    if not flag:
        print('SYJKGW')
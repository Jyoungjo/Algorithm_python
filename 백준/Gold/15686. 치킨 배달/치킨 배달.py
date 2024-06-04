# 백준 15686 치킨 배달 - 골드 5
import sys
from itertools import combinations


input = sys.stdin.readline
n, m = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(n)]
result = float('inf')
house, chicken = [], []

for i in range(n):
    for j in range(n):
        if city[i][j] == 1:
            house.append((i, j))
        elif city[i][j] == 2:
            chicken.append((i, j))

# combinations 사용해서 m개의 치킨집 선택
for comb in combinations(chicken, m):
    c_dist_of_city = 0 # 도시의 치킨 거리
    for h in house:
        c_dist_of_house = float('inf') # 각 집마다 선택한 치킨집과의 치킨 거리
        # 선택한 치킨집을 순회하면서 해당 집에서 제일 최소가 되는 치킨 거리를 갖는 치킨집 찾기
        for j in range(m):
            c_dist_of_house = min(c_dist_of_house, abs(h[0] - comb[j][0]) + abs(h[1] - comb[j][1]))
        c_dist_of_city += c_dist_of_house
    result = min(result, c_dist_of_city)

print(result)
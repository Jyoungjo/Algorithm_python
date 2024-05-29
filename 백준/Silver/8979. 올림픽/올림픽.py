# 백준 8979 올림픽 - 실버 5

# 금 > 은 > 동
# 한 국가의 등수 = 자신보다 더 잘한 나라 수 + 1
# 공동 순위인 경우 ex) 2, 2인 경우 3은 없고 바로 4

N, K = map(int, input().split())
nations = dict() # 국가가 유일하고 해당 국가별 메달 개수를 정리하기 위해 dict 사용
# 국가별 메달 획득 분류
for _ in range(N):
    nation, g, s, b = map(int, input().split())
    nations[nation] = [g, s, b]

ranks = {} # 국가별 순위 기록용 dict
rank = 1

# 메달 개수 별로 내림차순 정리
nations = sorted(nations.items(), key=lambda x: (x[1][0], x[1][1], x[1][2]), reverse=True)
for i, (k, v) in enumerate(nations):
    if i == 0 or i == N - 1:
        ranks[k] = rank
        continue

    if nations[i - 1][1] == nations[i][1]:
        ranks[k] = rank
        rank += 2
    else:
        rank += 1
        ranks[k] = rank

print(ranks[K])
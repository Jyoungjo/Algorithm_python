# 프로그래머스 42579 베스트앨범 LV.3
def solution(genres, plays):
    answer = []
    genre_dict = {}
    genre_list = {}

    for i, (g, p) in enumerate(zip(genres, plays)):
        if g in genre_dict:
            genre_dict[g] += p
        else:
            genre_dict[g] = p

        if g in genre_list:
            genre_list[g].append((i, p))
        else:
            genre_list[g] = [(i, p)]

    for k, v in sorted(genre_dict.items(), key=lambda x: x[1], reverse=True):
        for i, p in sorted(genre_list[k], key=lambda x: x[1], reverse=True)[:2]:
            answer.append(i)

    return answer
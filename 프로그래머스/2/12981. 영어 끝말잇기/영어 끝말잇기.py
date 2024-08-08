def solution(n, words):
    answer = [0, 0]
    members, calling, turn = [i for i in range(1, n+1)], set(), 0
    prev = ''
    for i, word in enumerate(words):
        if i % n == 0:
            turn += 1
        if word in calling or (prev != '' and prev[-1] != word[0]):
            return [members[i % n], turn]
        calling.add(word)
        prev = word
    return answer
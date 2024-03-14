# 프로그래머스 43163 단어 변환 LV.3
from collections import deque


def solution(begin, target, words):
    def bfs(start):
        q = deque()
        q.append([start, 0])
        while q:
            current, step = q.popleft()
            if current == target:
                return step

            for word in words:
                word_cnt = 0
                for i in range(len(word)):
                    if current[i] != word[i]:
                        word_cnt += 1

                if word_cnt == 1:
                    q.append([word, step + 1])

    if target not in words:
        return 0

    return bfs(begin)
# 프로그래머스 155651 호텔 대실


import heapq


def solution(book_time):
    answer = 1
    for i in range(len(book_time)):
        for j in range(len(book_time[i])):
            a = book_time[i][j].split(':')
            book_time[i][j] = int(a[0]) * 60 + int(a[1])

    book_time.sort(key=lambda x: x[0])
    room = [book_time[0][1]]
    heapq.heapify(room)

    for b in book_time[1:]:
        s, e = b
        flag = False

        for r in room:
            if r + 10 <= s:
                heapq.heapreplace(room, e)
                flag = True
                break

        if not flag:
            answer += 1
            heapq.heappush(room, e)

    return answer
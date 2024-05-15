# 프로그래머스 155651 호텔 대실


import heapq


def convert_time(time):
    return int(time[:2])*60+int(time[3:])


def solution(book_time):
    answer = 1
    for i in range(len(book_time)):
        book_time[i][0], book_time[i][1] = convert_time(book_time[i][0]), convert_time(book_time[i][1])

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
# 프로그래머스 258711 도넛과 막대 그래프 LV.2
def solution(edges):
    answer = [0, 0, 0, 0]
    vertices = {}
    for edge in edges:
        start, end = edge
        if not vertices.get(start):
            vertices[start] = [0, 0] # [in, out]
        if not vertices.get(end):
            vertices[end] = [0, 0] # [in, out]

        vertices[start][1] += 1
        vertices[end][0] += 1

    for vertex, edges_count in vertices.items():
        i, o = edges_count
        if i == 0 and o >= 2:
            answer[0] = vertex
        elif i > 0 and o == 0:
            answer[2] += 1
        elif i >= 2 and o >= 2:
            answer[3] += 1

    answer[1] = (vertices[answer[0]][1] - answer[2] - answer[3])

    return answer
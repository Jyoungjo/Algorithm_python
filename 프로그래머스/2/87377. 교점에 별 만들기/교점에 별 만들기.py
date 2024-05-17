def find_cross_point(l1, l2):
    a, b, e = l1
    c, d, f = l2

    denominator = a * d - b * c
    if denominator == 0:
        return None

    x, y = (b * f - e * d) / denominator, (e * c - a * f) / denominator

    if x != int(x) or y != int(y):
        return None

    return [int(x), int(y)]


def solution(line):
    points = []
    for l1 in line:
        for l2 in line:
            if l1 == l2:
                continue

            point = find_cross_point(l1, l2)

            if point:
                points.append(point)

    points = list(set(map(lambda x: (x[0], x[1]), points)))

    min_x, max_x = min(x for x, _ in points), max(x for x, _ in points)
    min_y, max_y = min(y for _, y in points), max(y for _, y in points)
    size_x, size_y = max_x - min_x + 1, max_y - min_y + 1

    grid = [['.' for _ in range(size_x)] for _ in range(size_y)]

    for x, y in points:
        grid[max_y - y][x - min_x] = '*'

    return [''.join(row) for row in grid]
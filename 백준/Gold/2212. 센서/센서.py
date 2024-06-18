# 백준 2212 센서 - 골드 5

# 센서 경로에 집중국 설치
# 집중국을 K개 설치한다 = 센서들의 묶음을 K개 만든다
# 각 센서 사이의 거리를 구하고 K-1번 가장 큰 거리 차이를 제거하여 구간을 만든다. -> 만들어 진 묶음의 합 구하기
import sys
input = sys.stdin.readline


def solution(N, K, coord):
    if K >= N:
        return 0
    dist = []
    for i in range(1, N):
        dist.append(coord[i] - coord[i - 1])
    dist.sort(reverse=True)
    for i in range(K-1):
        dist.pop(0)
    return sum(dist)


def main():
    N = int(input())
    K = int(input())
    coord = sorted(list(map(int, input().split())))
    return print(solution(N, K, coord))
    
    
main()


# int(input()) / map(int, input().split()) / list(map(int, input().split())) / input().strip()
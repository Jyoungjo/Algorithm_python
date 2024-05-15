# 프로그래머스 148653 마법의 엘리베이터


def solution(storey):
    answer = 0
    storey = list(map(int, str(storey)))
    while storey:
        target = storey.pop()
        
        # 1. target이 5보다 작은 경우
        # 2. target이 5인데 target 앞자리의 숫자가 5보다 작은 경우
        # 3. target이 5인데 앞자리 숫자가 없는 경우
        if target < 5 or (target == 5 and ((storey and storey[-1] < 5) or not storey)):
            answer += target
        # 4. target이 5보다 큰 경우 -> 이때, 앞자리 있으면 올림 처리하기
        else:
            answer += (10 - target)
            if storey:
                storey.append(storey.pop() + 1)
            else:
                answer += 1
            
    return answer
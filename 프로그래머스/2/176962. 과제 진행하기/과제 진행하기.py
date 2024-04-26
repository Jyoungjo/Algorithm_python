# 프로그래머스 176962 과제 진행하기 LV.2

def convert(time):
    h, m = map(int, time.split(':'))
    return h * 60 + m
    

def solution(plans):
    answer, stack = [], []
    for plan in plans:
        plan[1], plan[2] = convert(plan[1]), int(plan[2])
    plans.sort(key=lambda x: x[1])
    
    for i in range(len(plans)):
        if i == len(plans) - 1:
            answer.append(plans[i][0])
            for j in range(len(stack)):
                answer.append(stack[~j][0])
            break
        # gap >= 0 이면 과제를 끝까지 수행할 수 있고 gap < 0 이라면 과제를 하다가 다음 과제 시간이 다가왔다는 것.
        gap = plans[i + 1][1] - (plans[i][1] + plans[i][2])
        
        if gap >= 0:
            # 진행중인 과제를 충분히 끝낼 수 있으므로 답 저장
            answer.append(plans[i][0])
            # 그리고 나서 stack에 있는 다른 과제 수행 가능 여부 판단
            while stack:
                # stack에 저장된 playtime <= gap 이라면 stack에 저장된 과제도 수행이 가능하다.
                if stack[-1][1] <= gap:
                    name, playtime = stack.pop()
                    answer.append(name)
                    gap -= playtime
                # 그게 아니라면 stack 맨 위의 playtime 조정
                else:
                    stack[-1][1] -= gap
                    break
        else:
            stack.append([plans[i][0], -gap])
    
    return answer
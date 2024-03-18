# 프로그래머스 250137 붕대 감기 LV.1
def solution(bandage, health, attacks):
    answer = 0 # 남은 체력 -> 몬스터 공격 맞고 현재 체력 0 이하가 되면 return -1
    # bandage = [시전 시간, 초당 회복량, 추가 회복량]
    # attack = [[공격 시간, 피해량]] (오름차순 되어있음)

    attacks_times = [attack[0] for attack in attacks]
    attacks_dict = {}
    max_health = health
    for attack in attacks:
        attacks_dict[attack[0]] = attack[1]

    continuous_success = 0
    for i in range(1, attacks_times[-1] + 1):
        if health <= 0:
            return -1
        # 공격을 받는 경우
        if i in attacks_times:
            # 체력 감소, 연속 성공 횟수 초기화
            health -= attacks_dict[i]
            continuous_success = 0
        # 그렇지 않은 경우
        else:
            # 최대 체력인 경우 회복 필요 없음
            if health == max_health:
                continue
            # 그렇지 않은 경우 회복 필요함
            else:
                # 체력 초당 회복, 연속 성공 횟수 증가
                health += bandage[1]
                continuous_success += 1
                if health > max_health:
                    health = max_health
                # 연속 성공 횟수 == 시전 시간이면 추가 회복 -> 연속 성공 초기화
                if continuous_success == bandage[0]:
                    health += bandage[2]
                    continuous_success = 0
                    if health > max_health:
                        health = max_health
    if health <= 0:
        return -1

    return health
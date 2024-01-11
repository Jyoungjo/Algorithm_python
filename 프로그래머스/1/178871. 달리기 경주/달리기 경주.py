# 프로그래머스 178871 달리기 경주 LV.1
def solution(players, callings):
    # 선수:순위로 딕셔너리 생성
    running_player = {player: i for i, player in enumerate(players)}

    for c in callings:
        idx = running_player[c] # 부른 선수의 순위
        running_player[c] -= 1 # 해당 선수의 순위를 한 단계 상승
        running_player[players[idx - 1]] += 1 # 해당 선수 앞 선수의 등수를 한 단계 하락
        # 실제 선수 위치 변경
        players[idx], players[idx - 1] = players[idx - 1], players[idx]

    return players
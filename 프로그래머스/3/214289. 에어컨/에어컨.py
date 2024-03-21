# 프로그래머스 214289 에어컨 LV.3
def solution(temperature, t1, t2, a, b, onboard):
    INF = float('inf')
    times = len(onboard)
    outdoor_temp = temperature + 10
    temperature, t1, t2 = temperature + 10, t1 + 10, t2 + 10
    dp = [[INF] * 52 for _ in range(times + 1)]
    dp[0][outdoor_temp] = 0
    for cur_time in range(times):
        next_time = cur_time + 1
        indoor_temp_range = range(51) if onboard[cur_time] == 0 else range(t1, t2 + 1)
        for indoor_temp in indoor_temp_range:
            # 에어컨 켜져 있는 경우
            # 다음 시각 온도보다 현재 온도가 낮다면 온도 변경 코스트 a 발생
            on_temp_down = dp[cur_time][indoor_temp - 1] + a
            # 다음 시각 온도와 현재 온도가 같다면 온도 유지 코스트 b 발생
            on_temp = dp[cur_time][indoor_temp] + b
            # 다음 시각 온도보다 현재 온도가 높다면 온도 변경 코스트 a 발생
            on_temp_up = dp[cur_time][indoor_temp + 1] + a

            # 에어컨 꺼져 있는 경우
            # 다음 시각 온도보다 현재 온도가 낮다면 현재 온도 -> 실외 온도 변화해야하므로 현재 온도가 실외 온도보다 작아야 함
            off_temp_down = dp[cur_time][indoor_temp - 1] if indoor_temp - 1 < outdoor_temp else INF
            # 다음 시각 온도와 현재 온도가 같다면 현재 온도와 실외 온도가 일치해야 해당 조건 성립
            off_temp = dp[cur_time][indoor_temp] if indoor_temp == outdoor_temp else INF
            # 다음 시각 온도보다 현재 온도가 높다면 현재 온도 -> 실외 온도 변화해야하므로 현재 온도가 실외 온도보다 커야 함
            off_temp_up = dp[cur_time][indoor_temp + 1] if indoor_temp + 1 > outdoor_temp else INF

            dp[next_time][indoor_temp] = min(on_temp_down, on_temp, on_temp_up, off_temp_down, off_temp, off_temp_up)

    return min(dp[-1])
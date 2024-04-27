def solution(sequence):
    answer = 0
    n = len(sequence)
    p_sum = [0] * (n+1)
    
    for i in range(n):
        p_sum[i + 1] = p_sum[i] + sequence[i] * (-1) ** i
        
    return max(p_sum) - min(p_sum)
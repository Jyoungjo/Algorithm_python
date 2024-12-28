-- 사원별 성과금 정보 조회
-- 사번, 성명, 평가 등급, 성과금 조회
-- 평가등급 = GRADE, 성과금 = BONUS / 사번 오름차순

SELECT E.EMP_NO, 
       E.EMP_NAME, 
       G.GRADE, 
       CASE
       WHEN G.GRADE = 'S' THEN (E.SAL * 0.2)
       WHEN G.GRADE = 'A' THEN (E.SAL * 0.15)
       WHEN G.GRADE = 'B' THEN (E.SAL * 0.1)
       ELSE 0 END AS BONUS
FROM HR_EMPLOYEES AS E
    JOIN (
        SELECT EMP_NO,
               CASE
               WHEN AVG(SCORE) >= 96 THEN 'S'
               WHEN AVG(SCORE) >= 90 THEN 'A'
               WHEN AVG(SCORE) >= 80 THEN 'B'
               ELSE 'C' END AS GRADE
        FROM HR_GRADE
        GROUP BY EMP_NO
    ) AS G
    ON E.EMP_NO = G.EMP_NO
ORDER BY 1
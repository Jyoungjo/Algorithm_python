-- 부서별 평균 연봉 조회
-- 부서 ID, 영문 부서명, 평균 연봉
-- 평균 연봉 소수점 첫째 자리 반올림, 컬럼명 AVG_SAL / 내림차순

SELECT D.DEPT_ID, D.DEPT_NAME_EN, S.AVG_SAL
FROM HR_DEPARTMENT AS D
    JOIN (
        SELECT DEPT_ID, ROUND(AVG(SAL), 0) AS AVG_SAL
        FROM HR_EMPLOYEES
        GROUP BY DEPT_ID
    ) AS S
    ON D.DEPT_ID = S.DEPT_ID
ORDER BY 3 DESC
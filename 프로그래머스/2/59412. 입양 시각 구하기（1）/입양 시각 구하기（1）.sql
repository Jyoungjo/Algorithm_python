-- 시간대별 입양건수 / 시간대 별 오름차순

SELECT HOUR(DATETIME) AS HOUR, COUNT(*) AS COUNT
FROM ANIMAL_OUTS
GROUP BY HOUR
HAVING HOUR BETWEEN '09' AND '19'
ORDER BY 1
-- 0시 ~ 23시 입양건수
WITH RECURSIVE EMPTY_HOUR(HOUR, COUNT) AS (
    SELECT 0 AS HOUR, 0 AS COUNT
    UNION ALL
    SELECT HOUR + 1, COUNT
    FROM EMPTY_HOUR
    WHERE HOUR < 23
)

SELECT HOUR, SUM(COUNT) AS COUNT
FROM (
    SELECT HOUR(DATETIME) AS HOUR, 1 AS COUNT
    FROM ANIMAL_OUTS
    UNION ALL
    SELECT HOUR, COUNT
    FROM EMPTY_HOUR
) AS T
GROUP BY HOUR
ORDER BY 1
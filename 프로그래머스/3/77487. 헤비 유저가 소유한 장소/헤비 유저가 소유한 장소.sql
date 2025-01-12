-- 공간 둘 이상 등록한 사람 -> 헤비 유저
-- 헤비유저가 등록한 공간 정보 아이디 순 조회
SELECT *
FROM PLACES
WHERE HOST_ID IN (
    SELECT HOST_ID
    FROM PLACES
    GROUP BY HOST_ID
    HAVING COUNT(*) >= 2
)
ORDER BY 1
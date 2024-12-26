-- 완료된 중고 거래의 총 금액이 70만원 이상인 회원의 회원 ID, 닉네임, 총거래금액 조회 / 총거래금액 오름차순

SELECT U.USER_ID, U.NICKNAME, SUM(PRICE) AS TOTAL_SALES
FROM USED_GOODS_BOARD AS B
    JOIN USED_GOODS_USER AS U
    ON U.USER_ID = B.WRITER_ID
WHERE U.USER_ID IN (
    SELECT WRITER_ID
    FROM USED_GOODS_BOARD
    GROUP BY WRITER_ID
    HAVING STATUS = 'DONE'
)
GROUP BY U.USER_ID, U.NICKNAME
HAVING TOTAL_SALES >= 700000
ORDER BY TOTAL_SALES
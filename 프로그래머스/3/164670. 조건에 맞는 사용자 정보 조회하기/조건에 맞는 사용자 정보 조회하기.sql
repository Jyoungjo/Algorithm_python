-- 게시물 3건 이상 등록한 사용자의 ID, 닉네임, 주소, 전화번호
-- 주소는 시, 도로명, 상세 / 전화번호는 XXX-XXXX-XXXX
-- ID 내림차순

SELECT USER_ID, 
       NICKNAME, 
       CONCAT(CITY, " ", STREET_ADDRESS1, " ", STREET_ADDRESS2) AS 전체주소,
       CONCAT(SUBSTR(TLNO, 1, 3), "-", SUBSTR(TLNO, 4, 4), "-", SUBSTR(TLNO, 8, 4)) AS 전화번호
FROM USED_GOODS_USER
WHERE USER_ID IN (
    SELECT WRITER_ID
    FROM USED_GOODS_BOARD
    GROUP BY WRITER_ID
    HAVING COUNT(*) >= 3
)
ORDER BY 1 DESC
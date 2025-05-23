-- 성분 타입별 아이스크림 총 주문량 구하기
-- 총 주문량 오름차순

SELECT I.INGREDIENT_TYPE, SUM(F.TOTAL_ORDER) AS TOTAL_ORDER
FROM FIRST_HALF AS F
    JOIN ICECREAM_INFO AS I
    ON F.FLAVOR = I.FLAVOR
GROUP BY I.INGREDIENT_TYPE
ORDER BY TOTAL_ORDER
-- 판매 ID, 회원 ID, 상품 ID, 판매량, 판매일
-- 동일 회원이 동일 상품 재구매한 데이터 -> 회원 ID, 재구매 상품 ID
-- 회원 ID 오름차순, 상품 ID 내림차순
SELECT USER_ID, PRODUCT_ID
FROM ONLINE_SALE
GROUP BY USER_ID, PRODUCT_ID
HAVING COUNT(*) > 1
ORDER BY USER_ID, PRODUCT_ID DESC
;